package com.diccionario.juan.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class cacheConfig {
	
	/*
	 * Configuracion de cache
	 * administador de cache es un cache que se ve a ejecutar en la maquina
	 */
	@Bean
	public CacheManager getManager() {
		return new ConcurrentMapCacheManager("spring");
	}
	
}