package com.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.domain.Associado;
import com.desafio.dto.AssociadoDTO;
import com.desafio.repository.AssociadoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssociadoService {

	@Autowired
	private AssociadoRepository repository;
	
	public List<Associado> listarTodos() {
		return repository.findAll();
	}

	public Associado obterPeloId(long id) {
		return repository.findById(id).get();
	}

	public Associado salvar(AssociadoDTO associadoDTO) {
		return repository.save(new Associado(associadoDTO));
	}

	public void deletar(long id) {
		repository.deleteById(id);
	}

}
