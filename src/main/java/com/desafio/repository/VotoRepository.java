package com.desafio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.desafio.domain.Voto;
import com.desafio.dto.VotoResultadoDTO;

public interface VotoRepository extends JpaRepository<Voto, Long> {
	Optional<Voto> findByAssociadoId(Long id);	
	int countByIdAndResposta(Long id, Boolean resposta);
	@Query("SELECT new com.desafio.dto.VotoResultadoDTO(v.resposta) FROM Voto AS v JOIN Associado AS a ON v.associado.id = a.id WHERE a.cpf = ?1")
	List<VotoResultadoDTO> findByAssociadoCpf(String cpf);
}
