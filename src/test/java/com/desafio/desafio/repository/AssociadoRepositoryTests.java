package com.desafio.desafio.repository;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.desafio.domain.Associado;
import com.desafio.repository.AssociadoRepository;

@DataJpaTest
class AssociadoRepositoryTests {

	@Autowired
	private AssociadoRepository repository;

	private Associado criarAssociado() {
		return Associado.builder().nome("Teste Associado")
				.cpf("07363278601").build();
	}

	@Test
	void validar_cpf_associado_com_mascara() {
		Associado associado = repository.save(criarAssociado());
		Assertions.assertThat(associado.getCpf()).isNotNull();
		Assertions.assertThat(associado.getCpf()).isEqualTo("073.632.786-01");
	}
	
	@Test
	void salvar_associado_com_sucesso() {
		Associado associado = repository.save(criarAssociado());
		Assertions.assertThat(associado).isNotNull();
		Assertions.assertThat(associado.getId()).isNotNull();
	}
	
	@Test
	void validar_campos_obrigatorio_associado() {
		Assertions.assertThatThrownBy(() -> repository.save(new Associado())).isInstanceOf(ConstraintViolationException.class);
	}
}
