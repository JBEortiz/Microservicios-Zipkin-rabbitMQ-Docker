package com.diccionario.juan.service;

import java.util.List;
import java.util.Optional;

import com.diccionario.juan.dto.AbstractDto;

public interface AbstractService <D extends AbstractDto<?>, K> {

	List<D> getAll();

	Optional<D> getById(K id);
	
	void create(D dto);

	void delete(K id);

}
