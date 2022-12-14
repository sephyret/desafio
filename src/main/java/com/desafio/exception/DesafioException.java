package com.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DesafioException extends RuntimeException {
	public DesafioException(String mensagem) {
		super(mensagem);
	}
}
