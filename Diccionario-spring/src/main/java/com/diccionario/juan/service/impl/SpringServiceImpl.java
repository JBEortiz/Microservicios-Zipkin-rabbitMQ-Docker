package com.diccionario.juan.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.diccionario.juan.dto.SpringDto;
import com.diccionario.juan.entity.Spring;
import com.diccionario.juan.mapper.SpringAMapper;
import com.diccionario.juan.repository.SpringRepository;
import com.diccionario.juan.service.SpringService;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class SpringServiceImpl extends AbstractServiceImpl<SpringDto, Spring, Long> implements SpringService{
 
	@Autowired
	private SpringRepository repository;
	
	@Autowired
	private SpringAMapper mapper;
	
	@Value(value = "${server.port}")
	private String serverPort;
	
	@Autowired
	private Tracer trace;
	

	@Override
	public Optional<SpringDto> getById(Long id) {
		Spring spring=null;
		try {
			spring= repository.getById(id);
		} catch (Exception e) {
			log.error("java8repository.getById(id) -no encuentra ese id",e);
			trace.currentSpan().tag("java8repository.getById(id) -no encuentra ese id", e.toString());
		}
		SpringDto dto= mapper.entityToDto(spring);
		return Optional.of(dto);
	}

	@Override
	public List<SpringDto> getAllListPort() {
		List<Spring> springList=null;
		try {
			springList=repository.findAll();
		} catch (Exception e) {
			log.error("java8repository.findAll() ",e);
			trace.currentSpan().tag("java8repository.findAll() ", e.toString());
		}
		springList.forEach(e->{
			e.setServerPort(serverPort);
		});
		return repository.findAll().stream().map(mapper::entityToDto).collect(Collectors.toList());
	}

	@Override
	public Optional<SpringDto> getByConcepto(String concepto) {
		Spring entity= repository.findByConcepto(concepto).orElseThrow(EntityNotFoundException::new);
		SpringDto dto= mapper.entityToDto(entity);
		return Optional.of(dto);
	}

	@Override
	public Optional<SpringDto> update(SpringDto java8) {
		Spring entityUpdate = repository.findById(java8.getId()).orElseThrow(EntityNotFoundException::new);
		entityUpdate.setConcepto(java8.getConcepto());
		entityUpdate.setDefinicion(java8.getDefinicion());
		return Optional.of(mapper.entityToDto(repository.save(entityUpdate)));
	}

}
