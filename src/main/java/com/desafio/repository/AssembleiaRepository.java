package com.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.domain.Assembleia;
import com.desafio.domain.Status;

public interface AssembleiaRepository extends JpaRepository<Assembleia, Long> {
	List<Assembleia> findAllByStatus(Status status);
}
