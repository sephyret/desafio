package com.desafio.domain;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.desafio.dto.AssembleiaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Assembleia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O campo descricao não pode ser nulo")
	@NotEmpty(message = "O campo descricao não pode ser vazio")
	private String descricao;
	
	@Builder.Default
	private Integer tempo = 60;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	
	@OneToOne
	@NotNull(message = "O campo pauta não pode ser nulo")
	private Pauta pauta;
	
	@Builder.Default
	private Status status = Status.ABERTA;
	private String resultado;
	
	private int totalVotoSim;
	private int totalVotoNao;
	
	public Assembleia(@NotNull AssembleiaDTO dto) {
		setPauta(dto.pauta);
		setTempo(dto.tempo);
		setStatus(Status.ABERTA);
		setDescricao(dto.descricao);
		setDataInicio(LocalDateTime.now());
	}

	public void setTempo(Integer tempo) {
		if (tempo == null || tempo < 1)
			tempo = 60;
		else
			this.tempo = tempo;
	}
	
	@JsonIgnore
	public boolean isAberta() {
		return this.status.equals(Status.ABERTA);
	}
}
