package com.desafio.desafio.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.desafio.domain.Assembleia;
import com.desafio.domain.Pauta;
import com.desafio.domain.Status;
import com.desafio.repository.AssembleiaRepository;
import com.desafio.repository.PautaRepository;

@DataJpaTest
class AssembleiaRepositoryTests {

	@Autowired
	private AssembleiaRepository repository;
	
	@Autowired
	private PautaRepository pautaRepository;

	private Assembleia criarAssembleia() {
		return Assembleia.builder()
				.status(Status.ABERTA)
				.dataInicio(LocalDateTime.now())
				.descricao("Teste Assembleia")
				.pauta(pautaRepository.save(Pauta.builder().item("Teste Pauta").build())).build();
	}

	@Test
	void salvar_assembleia_com_sucesso() {
		Assembleia assembleia = repository.save(criarAssembleia());
		Assertions.assertThat(assembleia).isNotNull();
		Assertions.assertThat(assembleia.getId()).isNotNull();
	}
	
	@Test
	void validar_tempo_assembleia_valor_defor_60() {
		Assembleia assembleia = repository.save(criarAssembleia());
		Assertions.assertThat(assembleia.getTempo()).isEqualTo(60);
	}
	
	@Test
	void validar_status_assembleia_valor_defor_aberta() {
		Assembleia assembleia = repository.save(criarAssembleia());
		Assertions.assertThat(assembleia.getStatus()).isEqualTo(Status.ABERTA);
	}
	
	@Test
	void validar_campos_obrigatorio_assembleia() {
		Assertions.assertThatThrownBy(() -> repository.save(new Assembleia())).isInstanceOf(ConstraintViolationException.class);
	}
	
	@Test
	void buscar_todas_assembleia_com_status_aberta() {
		salvar_assembleia_com_sucesso();
		List<Assembleia> lista = repository.findAllByStatus(Status.ABERTA);
		Assertions.assertThat(lista).isNotNull();
		Assertions.assertThat(lista.size()).isEqualTo(1);
		Assertions.assertThat(lista.get(0).getStatus()).isEqualTo(Status.ABERTA);
	}
}
