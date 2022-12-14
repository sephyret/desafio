package com.desafio.dto;

import com.desafio.domain.Assembleia;
import com.desafio.domain.Associado;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VotoDTO {
	public Boolean resposta;
	public Associado associado;
	public Assembleia assembleia;
}
