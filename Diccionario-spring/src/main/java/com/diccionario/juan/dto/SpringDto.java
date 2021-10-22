package com.diccionario.juan.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class SpringDto extends AbstractDto<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String concepto;
	private String definicion;
}
