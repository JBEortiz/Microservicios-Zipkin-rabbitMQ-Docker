package com.diccionario.juan.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.diccionario.juan.dto.SpringDto;
import com.diccionario.juan.entity.Spring;

@Mapper
public interface SpringAMapper extends GenericMapper<Spring,SpringDto> {

	@Override
	@Mapping(source = "java8.definicion", target = "definicion")
	SpringDto entityToDto(Spring entity);

	
	@Override
	@InheritInverseConfiguration
	Spring dtoToEntity(SpringDto dto);
}
