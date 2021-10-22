package com.diccionario.juan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diccionario.juan.entity.Java8;
public interface Java8Repository extends JpaRepository<Java8, Long> {
	
	
	Optional<Java8> findByConcepto(String concepto);

}
