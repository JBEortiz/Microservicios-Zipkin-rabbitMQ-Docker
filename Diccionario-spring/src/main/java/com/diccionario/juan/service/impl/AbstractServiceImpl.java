package com.diccionario.juan.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.diccionario.juan.dto.AbstractDto;
import com.diccionario.juan.mapper.GenericMapper;

import brave.Tracer;

public class AbstractServiceImpl<D extends AbstractDto<?>,E,K>  {

	@Autowired
	protected JpaRepository<E, K> repository;

	@Autowired
	protected GenericMapper<E, D> mapper;

	
	public List<D> getAll() {
		List<D> genericList= repository.findAll().stream().map(mapper::entityToDto).collect(Collectors.toList());
		return genericList;
	}
	@Cacheable("spring")
	public Optional<D> getById(K id) {
		E entity= repository.findById(id).orElse(null);
		D dto= mapper.entityToDto(entity);
		return Optional.of(dto);
	}
	public void create(D dto) {
		E entity= mapper.dtoToEntity(dto);
		Optional.of(repository.save(entity));
		
	}
	@CacheEvict("spring")
	public void delete(K id) {
		 Optional<E> entity= repository.findById(id);
		 if (entity.isPresent()) {
			 repository.delete(entity.get());
			
		}
	}
}
