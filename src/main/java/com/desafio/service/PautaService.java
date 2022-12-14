package com.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.domain.Pauta;
import com.desafio.dto.PautaDTO;
import com.desafio.repository.PautaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PautaService {

	@Autowired
	private PautaRepository repository;
	
	public List<Pauta> listarTodos() {
		return repository.findAll();
	}

	public Pauta obterPeloId(long id) {
		return repository.findById(id).get();
	}

	public Pauta salvar(PautaDTO dto) {
		return repository.save(new Pauta(dto));
	}
}
