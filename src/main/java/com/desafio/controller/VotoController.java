package com.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.domain.Voto;
import com.desafio.dto.VotoDTO;
import com.desafio.dto.VotoResultadoDTO;
import com.desafio.service.VotoService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/voto")
public class VotoController {
	
	@Autowired
	private VotoService service;
	
	@GetMapping
	public ResponseEntity<List<Voto>> listarTodos() {
		return ResponseEntity.ok(service.listarTodos());
	}
	
	@PostMapping
	public ResponseEntity<Voto> salvar(@RequestBody VotoDTO dto) {
		return new ResponseEntity<>(service.salvar(dto), HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/consultar-voto-por-cpf/{cpf}")
	public ResponseEntity<List<VotoResultadoDTO>> consultarVotacao(@PathVariable String cpf) {
		return new ResponseEntity<>(service.consultarVotoPorCpf(cpf), HttpStatus.CREATED);
	}
}
