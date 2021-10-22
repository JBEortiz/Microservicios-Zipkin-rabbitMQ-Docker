package com.diccionario.juan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.diccionario.juan.dto.Java8Dto;
import com.diccionario.juan.entity.Java8;
import com.diccionario.juan.service.Java8Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class Java8Controller {

	@Autowired
	private Java8Service service;
	
	
	@GetMapping("/listar-conceptos")
	public ResponseEntity<List<Java8Dto>> listar() {
		List<Java8Dto> conceptos = service.getAll();
		if (conceptos.isEmpty()) {
			throw new EntityNotFoundException("la lista de conceptos esta vacia");
		}
		return ResponseEntity.ok(conceptos);
	}

	@GetMapping("/listar-conceptos/{id}")
	public ResponseEntity<Java8Dto> findById(@PathVariable Long id) {
		Java8Dto product;
		product = service.getById(id).get();
		return ResponseEntity.ok(product);
	}

	@GetMapping("/listar-por-conceptos/{concepto}")
	public ResponseEntity<Java8Dto> findById(@PathVariable String concepto) {
		Java8Dto product;
		product = service.getByConcepto(concepto).get();
		return ResponseEntity.ok(product);
	}
	@PostMapping("/crear-conceptos")
	public void create(@RequestBody Java8Dto dto) {
		service.create(dto);
	}

	@PutMapping("/editar-conceptos")
	public ResponseEntity<Java8Dto> update(@RequestBody Java8Dto dto) {
		return ResponseEntity.ok(service.update(dto).get());
	}

	@DeleteMapping("/eliminar-conceptos/{id}")
	public void deleted(@PathVariable Long id) {
		service.delete(id);
	}
	
	@GetMapping("/configuracion-java")
	public ResponseEntity<?> getConfigProperties(
			@Value("${configuracion.text}") String text,
			@Value("${configuracion.name}") String name,
			@Value("${server.port}") String port) {
		
		Map<String,String> infoConfig= new HashMap<>();
		infoConfig.put("port", port);
		infoConfig.put("text", name);
		infoConfig.put("name", text);
		
		return new ResponseEntity<Map<String,String>>(infoConfig, HttpStatus.OK);
	}
	
	
}
