package com.desafio.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.desafio.domain.Assembleia;
import com.desafio.domain.Status;
import com.desafio.dto.AssembleiaDTO;
import com.desafio.publisher.RabbitMQProducer;
import com.desafio.repository.AssembleiaRepository;
import com.desafio.repository.VotoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssembleiaService {

	@Autowired
	private AssembleiaRepository repository;
	
	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
	private RabbitMQProducer producer;
	
	public List<Assembleia> listarTodos() {
		return repository.findAll();
	}
	
	public List<Assembleia> listarSessoesAbertas() {
		return repository.findAllByStatus(Status.ABERTA);
	}

	public Assembleia salvar(AssembleiaDTO dto) {
		return repository.save(new Assembleia(dto));
	}
	
	public void atualizarAsembleia(Assembleia sessao) {
		repository.save(sessao);
	}
	
	public Assembleia consultarResultado(long id) {
		return repository.findById(id).get();
	}
	
	@Scheduled(fixedRate = 5000)
	public void fecharSessoesAbertas() {
		List<Assembleia> assembleia = listarTodos();
		assembleia.stream().filter(i -> i.getStatus().equals(Status.ABERTA)
				&& LocalDateTime.now().isAfter(i.getDataInicio().plusSeconds(i.getTempo()))).forEach(i -> {
			int sim = votoRepository.countByIdAndResposta(i.getId(), Boolean.TRUE);
			int nao = votoRepository.countByIdAndResposta(i.getId(), Boolean.FALSE);
			i.setResultado(sim - nao > 0 ? "APROVADO" : "REPROVADO");
			i.setStatus(Status.FECHADA);
			i.setDataFim(LocalDateTime.now());
			i.setTotalVotoNao(nao);
			i.setTotalVotoSim(sim);
			atualizarAsembleia(i);
			producer.sendMessage(i.toString());
		});
	}
}
