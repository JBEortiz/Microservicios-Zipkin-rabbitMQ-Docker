package com.diccionario.juan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diccionario.juan.entity.Spring;

public interface SpringRepository extends JpaRepository<Spring, Long> {

	Optional<Spring> findByConcepto(String concepto);

}
