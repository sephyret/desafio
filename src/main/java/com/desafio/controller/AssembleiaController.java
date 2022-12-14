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

import com.desafio.domain.Assembleia;
import com.desafio.dto.AssembleiaDTO;
import com.desafio.service.AssembleiaService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/assembleia")
public class AssembleiaController {
	
	@Autowired
	private AssembleiaService service;
	
	@GetMapping
	public ResponseEntity<List<Assembleia>> listarTodos() {
		return ResponseEntity.ok(service.listarTodos());
	}
	
	@PostMapping
	public ResponseEntity<Assembleia> salvar(@RequestBody AssembleiaDTO dto) {
		return new ResponseEntity<>(service.salvar(dto), HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/consultar-resultado/{id}")
	public ResponseEntity<Assembleia> consultarResultado(@PathVariable long id) {
		return ResponseEntity.ok(service.consultarResultado(id));
	}
}
