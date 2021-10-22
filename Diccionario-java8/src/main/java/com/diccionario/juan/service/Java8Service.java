package com.diccionario.juan.service;

import java.util.List;
import java.util.Optional;

import com.diccionario.juan.dto.Java8Dto;
import com.diccionario.juan.entity.Java8;

public interface Java8Service extends AbstractService<Java8Dto, Long>{
	

	List<Java8> getAllListPort();
	
	Optional<Java8Dto> getByConcepto(String concepto);
	
	Optional<Java8Dto> update(Java8Dto java8);
}
