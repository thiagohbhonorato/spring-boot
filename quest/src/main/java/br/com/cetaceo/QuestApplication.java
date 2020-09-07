package br.com.cetaceo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.client.RestTemplate;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableResourceServer
public class QuestApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	/**
	 * O Feign não consegue repassar os headers da requisição de origem para o microserviço de destino.
	 * Nesse caso, quando a requisição chega em um endpoint protegido, é causada um erro de unauthorized (não autorizado).
	 * 
	 * Para resolver esse problema, é criado um Interceptor faz esse trabalho, pois ele é executado antes de cada requisição
	 * que utiliza o Feign.
	 * 
	 * @return RequestInterceptor
	 */
	@Bean
	public RequestInterceptor getRequestInterceptor() {
		return new RequestInterceptor() {
			
			@Override
			public void apply(RequestTemplate template) {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if ( authentication == null ) {
					return;
				}
				
				if ( !(authentication.getDetails() instanceof OAuth2AuthenticationDetails) ) {
					return;
				}
				
				OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
				template.header("Authorization", details.getTokenType() + details.getTokenValue());
			}
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(QuestApplication.class, args);
	}
}
