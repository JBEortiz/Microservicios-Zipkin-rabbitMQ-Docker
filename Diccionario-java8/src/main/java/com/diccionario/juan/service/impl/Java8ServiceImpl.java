package com.diccionario.juan.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.diccionario.juan.dto.Java8Dto;
import com.diccionario.juan.entity.Java8;
import com.diccionario.juan.mapper.JavaMapper;
import com.diccionario.juan.repository.Java8Repository;
import com.diccionario.juan.service.Java8Service;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Java8ServiceImpl extends AbstractServiceImpl<Java8Dto, Java8, Long> implements Java8Service {

	@Autowired
	private Java8Repository repository;

	@Autowired
	private JavaMapper javaMapper;

	@Value(value = "${server.port}")
	private String serverPort;

	@Autowired
	private Tracer trace;

	@Override
	public Optional<Java8Dto> getByConcepto(String concepto) {
		Java8 etity = repository.findByConcepto(concepto).orElseThrow(EntityNotFoundException::new);
		Java8Dto etityDto = javaMapper.entityToDto(etity);
		return Optional.of(etityDto);
	}

	@Override
	public Optional<Java8Dto> update(Java8Dto javaDto8) {
		Java8 entityUpdate = repository.findById(javaDto8.getId()).orElseThrow(EntityNotFoundException::new);
		entityUpdate.setConcepto(javaDto8.getConcepto());
		entityUpdate.setDefinicion(javaDto8.getDefinicion());
		return Optional.of(mapper.entityToDto(repository.save(entityUpdate)));
	}

	@Override
	public List<Java8> getAllListPort() {
		List<Java8> entityList = null;
		try {
			entityList = repository.findAll();
		} catch (Exception e) {
			log.error("java8repository.findAll() ", e);
			trace.currentSpan().tag("java8repository.findAll() ", e.toString());
		}
		entityList.forEach(e -> {
			e.setServerPort(serverPort);
		});
		return repository.findAll();
	}

}
