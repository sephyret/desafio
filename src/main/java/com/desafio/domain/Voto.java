package com.desafio.domain;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.desafio.dto.VotoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Voto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O campo voto não pode ser nulo")
	private Boolean resposta;

	@OneToOne
	@NotNull(message = "O campo assembleia não pode ser nulo")
	private Assembleia assembleia;
	
	@OneToOne
	@NotNull(message = "O campo associado não pode ser nulo")
	private Associado associado;
	
	private LocalDateTime dataVoto;
	
	public Voto(@NotNull VotoDTO dto) {
		setResposta(dto.resposta);
		setAssociado(dto.associado);
		setAssembleia(dto.assembleia);
		setDataVoto(LocalDateTime.now());
	}
}
