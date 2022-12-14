package com.desafio.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VotoResultadoDTO {
	public boolean resposta;
	
	public VotoResultadoDTO(boolean resposta) {
		this.resposta = resposta;
	}	
}
