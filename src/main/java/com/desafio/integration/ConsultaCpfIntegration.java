package com.desafio.integration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

// TODO nao foi possivel testar a api
public class ConsultaCpfIntegration {
	public static boolean consultarCpf(String cpf) {
		boolean valido = Boolean.FALSE;
		try {
			ResponseEntity<String> ts = new RestTemplate().getForEntity("https://user-info.herokuapp.com/users/" + cpf, String.class);
			if (ts.getStatusCode().equals(HttpStatus.OK)) {
				valido = ts.getBody().contains("ABLE_TO_VOTE");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return valido;
	}
}
