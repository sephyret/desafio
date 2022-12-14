package com.desafio.domain;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.desafio.dto.AssociadoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Associado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull(message = "O campo nome não pode ser nulo")
	@NotEmpty(message = "O campo nome não pode ser vazio")
	private String nome;
	
	@Column(unique = true)
	@NotNull(message = "O campo cpf não pode ser nulo")
	@NotEmpty(message = "O campo cpf não pode ser vazio")
	private String cpf;
	
	public Associado(@NotNull AssociadoDTO associadoDTO) {
		setNome(associadoDTO.nome);
		setCpf(associadoDTO.cpf);
	}

	public void setCpf(@NotNull String cpf) {
		Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");
		Matcher matcher = pattern.matcher(cpf); 
		if (matcher.matches())
			this.cpf = matcher.replaceAll("$1.$2.$3-$4");			
	}
}
