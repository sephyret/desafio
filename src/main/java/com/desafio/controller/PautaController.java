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

import com.desafio.domain.Pauta;
import com.desafio.dto.PautaDTO;
import com.desafio.service.PautaService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/pauta")
public class PautaController {
	
	@Autowired
	private PautaService service;
	
	@GetMapping
	public ResponseEntity<List<Pauta>> listarTodos() {
		return ResponseEntity.ok(service.listarTodos());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Pauta> obterPeloId(@PathVariable long id) {
		return ResponseEntity.ok(service.obterPeloId(id));
	}
	
	@PostMapping
	public ResponseEntity<Pauta> salvar(@RequestBody PautaDTO dto) {
		return new ResponseEntity<>(service.salvar(dto), HttpStatus.CREATED);
	}
	
}
