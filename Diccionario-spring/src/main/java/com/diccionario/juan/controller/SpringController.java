package com.diccionario.juan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.diccionario.juan.dto.SpringDto;
import com.diccionario.juan.entity.Spring;
import com.diccionario.juan.service.SpringService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SpringController {
	
	@Autowired
	private SpringService service;
	
	
	@GetMapping("/listar")
	public ResponseEntity<List<SpringDto>> listar(){
		List<SpringDto> conceptos = service.getAll();
		if (conceptos.isEmpty()) {
			throw new EntityNotFoundException("la lista de conceptos esta vacia ");
		}
		return ResponseEntity.ok(conceptos);
	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<SpringDto> findById(@PathVariable Long id) {
		Optional<SpringDto> dto;
		dto = service.getById(id);
		return ResponseEntity.ok(dto.get());
	}
	
	@PostMapping("/crear")
	public void create(@RequestBody SpringDto java8) {
		 service.create(java8);
	}
	
	@PutMapping("/editar/{id}")
	public void update(@RequestBody SpringDto java8) {
		service.update(java8);
	}
	
	
	@DeleteMapping("/eliminar/{id}")
	public void deleted(@PathVariable Long id) {
		service.delete(id);
	}
	@GetMapping("/configuracion-spring")
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
