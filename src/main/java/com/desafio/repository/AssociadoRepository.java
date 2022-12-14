package com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.domain.Associado;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
}
