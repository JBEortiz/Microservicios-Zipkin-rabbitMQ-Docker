package com.diccionario.juan.service;

import java.util.List;
import java.util.Optional;

import com.diccionario.juan.dto.SpringDto;
import com.diccionario.juan.entity.Spring;

public interface SpringService extends AbstractService<SpringDto, Long> {

	List<SpringDto> getAllListPort();

	Optional<SpringDto> getByConcepto(String concepto);
	

	Optional<SpringDto> update(SpringDto java8);


}
