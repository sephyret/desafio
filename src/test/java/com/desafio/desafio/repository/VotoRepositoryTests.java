package com.desafio.desafio.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.desafio.domain.Assembleia;
import com.desafio.domain.Associado;
import com.desafio.domain.Pauta;
import com.desafio.domain.Status;
import com.desafio.domain.Voto;
import com.desafio.dto.VotoResultadoDTO;
import com.desafio.repository.AssembleiaRepository;
import com.desafio.repository.AssociadoRepository;
import com.desafio.repository.PautaRepository;
import com.desafio.repository.VotoRepository;

@DataJpaTest
class VotoRepositoryTests {

	@Autowired
	private VotoRepository repository;
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private AssociadoRepository associadoRepository;
	
	@Autowired
	private AssembleiaRepository assembleiaRepository;

	private Pauta criarPauta() {
		return Pauta.builder().item("Teste Pauta").build();
	}
	
	private Associado criarAssociado() {
		return Associado.builder().nome("Teste Associado")
				.cpf("07363278601").build();
	}
	
	private Assembleia criarAssembleia() {
		return Assembleia.builder()
				.status(Status.ABERTA)
				.dataInicio(LocalDateTime.now())
				.descricao("Teste Assembleia")
				.pauta(pautaRepository.save(criarPauta())).build();
	}
	
	private Voto criarVoto() {
		return Voto.builder()
				.resposta(Boolean.TRUE)
				.dataVoto(LocalDateTime.now())
				.associado(associadoRepository.save(criarAssociado()))
				.assembleia(assembleiaRepository.save(criarAssembleia())).build();
	}

	@Test
	void salvar_voto_com_sucesso() {
		Voto voto = repository.save(criarVoto());
		Assertions.assertThat(voto).isNotNull();
		Assertions.assertThat(voto.getId()).isNotNull();
	}
	
	@Test
	void verificar_se_associado_ja_votou() {
		Voto voto = repository.save(criarVoto());
		Optional<Voto> associadoVotou = repository.findByAssociadoId(voto.getAssociado().getId());
		Assertions.assertThat(associadoVotou).isNotNull();
		Assertions.assertThat(associadoVotou.isPresent()).isTrue();
	}
	
	@Test
	void calcular_quantidade_voto() {
		Voto voto = repository.save(criarVoto());
		int total = repository.countByIdAndResposta(voto.getAssembleia().getId(), Boolean.TRUE);
		Assertions.assertThat(total).isNotNull();
		Assertions.assertThat(total).isEqualTo(1);
	}
	
	@Test
	void buscar_voto_por_cpf() {
		Voto voto = repository.save(criarVoto());
		List<VotoResultadoDTO> lista = repository.findByAssociadoCpf(voto.getAssociado().getCpf());
		Assertions.assertThat(lista).isNotNull();
		Assertions.assertThat(lista.size()).isEqualTo(1);
	}
	
	@Test
	void validar_campos_obrigatorio_pauta() {
		Assertions.assertThatThrownBy(() -> repository.save(new Voto())).isInstanceOf(ConstraintViolationException.class);
	}
}
