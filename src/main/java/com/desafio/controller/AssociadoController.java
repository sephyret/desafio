package com.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.domain.Associado;
import com.desafio.dto.AssociadoDTO;
import com.desafio.service.AssociadoService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/associado")
public class AssociadoController {
	
	@Autowired
	private AssociadoService service;
	
	@GetMapping
	public ResponseEntity<List<Associado>> listarTodos() {
		return ResponseEntity.ok(service.listarTodos());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Associado> obterPeloId(@PathVariable long id) {
		return ResponseEntity.ok(service.obterPeloId(id));
	}
	
	@PostMapping
	public ResponseEntity<Associado> salvar(@RequestBody AssociadoDTO dto) {
		return new ResponseEntity<>(service.salvar(dto), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable long id) {
		service.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
