package com.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.domain.Assembleia;
import com.desafio.domain.Voto;
import com.desafio.dto.VotoDTO;
import com.desafio.dto.VotoResultadoDTO;
import com.desafio.exception.DesafioException;
import com.desafio.integration.ConsultaCpfIntegration;
import com.desafio.repository.AssembleiaRepository;
import com.desafio.repository.VotoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VotoService {

	@Autowired
	private VotoRepository repository;
	
	@Autowired
	private AssembleiaRepository assembleiaRepository;
	
	public List<Voto> listarTodos() {
		return repository.findAll();
	}
	
	public Voto obterPeloId(long id) {
		return repository.findById(id).get();
	}

	public Voto salvar(VotoDTO dto) {
		repository.findByAssociadoId(dto.associado.getId()).orElseThrow(() -> new DesafioException("Associado já votou"));
		
		if (!ConsultaCpfIntegration.consultarCpf(dto.associado.getCpf())) {
			throw new DesafioException("Associado com pendência no cpf");
		}
		
		Assembleia assembleia = assembleiaRepository.findById(dto.assembleia.getId())
				.orElseThrow(() -> new DesafioException("Não foi possível encontrar assembleia"));
		
		if (!assembleia.isAberta()) throw new DesafioException("Assembleia fechada para votação");	

		return repository.save(new Voto(dto));			
	}
	
	public void atualizarSessao(Voto sessao) {
		repository.save(sessao);
	}

	public List<VotoResultadoDTO> consultarVotoPorCpf(String cpf) {
		return repository.findByAssociadoCpf(cpf);
	}
	
}
