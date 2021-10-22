package com.diccionario.juan.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class FiltersGateway implements GlobalFilter, Ordered{

	/**
	 * permitir o rechazar las peticiones segun los parametros 
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.info("ejecutando filtro");
		
	  /*exchange
		.getRequest()
		.mutate()
		.headers(h->h
				.add("token", "48623415"));*/
		
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			log.info("filtro ejecutado");
			
			RequestPath path = exchange.getRequest().getPath();
			String[] pathArray= path.toString().split("/");
			
			System.out.println(pathArray[2].toString());
			String valueCookie=("java".equals(pathArray[1].toString().trim()) ? "java" : "spring");
			
			exchange
			.getResponse()
			.getCookies()
			.add("nombre", ResponseCookie.from("nombre", valueCookie)
					.build());
			exchange
			.getResponse()
			.getHeaders().setContentType(MediaType.TEXT_PLAIN);
			
		} ));
	}

	@Override
	public int getOrder() {
		return 1;
	}
	
	

}
