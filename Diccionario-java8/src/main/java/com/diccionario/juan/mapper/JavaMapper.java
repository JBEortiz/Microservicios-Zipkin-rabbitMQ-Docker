package com.diccionario.juan.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.diccionario.juan.dto.Java8Dto;
import com.diccionario.juan.entity.Java8;
@Mapper
public interface JavaMapper extends GenericMapper<Java8,Java8Dto> {

	@Override
	@Mapping(source = "java8.definicion", target = "definicion")
	Java8Dto entityToDto(Java8 java8);

	
	@Override
	@InheritInverseConfiguration
	Java8 dtoToEntity(Java8Dto java8Dto);
}
