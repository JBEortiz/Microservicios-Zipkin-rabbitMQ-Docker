package com.diccionario.juan.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "java8")
public class Java8 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Long id;
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private String concepto;
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private String definicion;
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private String serverPort;

}
