package com.desafio.desafio.repository;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.desafio.domain.Pauta;
import com.desafio.repository.PautaRepository;

@DataJpaTest
class PautaRepositoryTests {

	@Autowired
	private PautaRepository repository;

	private Pauta criarPauta() {
		return Pauta.builder().item("Teste Pauta").build();
	}

	@Test
	void salvar_pauta_com_sucesso() {
		Pauta pauta = repository.save(criarPauta());
		Assertions.assertThat(pauta).isNotNull();
		Assertions.assertThat(pauta.getId()).isNotNull();
	}
	
	@Test
	void validar_campos_obrigatorio_pauta() {
		Assertions.assertThatThrownBy(() -> repository.save(new Pauta())).isInstanceOf(ConstraintViolationException.class);
	}
}