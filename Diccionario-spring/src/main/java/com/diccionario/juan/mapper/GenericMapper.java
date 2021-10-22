package com.diccionario.juan.mapper;

public interface GenericMapper <E, D>  {
	
	D entityToDto(E entity);

	E dtoToEntity(D dto);
}
