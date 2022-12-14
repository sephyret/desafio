package com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.domain.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
