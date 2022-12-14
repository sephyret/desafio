package com.desafio.dto;

import com.desafio.domain.Pauta;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssembleiaDTO {
	public Pauta pauta;
	public String descricao;
	public Integer tempo = 60;
}
