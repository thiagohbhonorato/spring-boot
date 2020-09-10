package br.com.cetaceo.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		
		// TUDO QUE ESTIVER DENTRO DE "u" DEVE SER AUTENTICADO
		// E POSSUIR UMA ROLE DE USER
		.antMatchers("/u/**")
		.hasRole("USER")
		
		// TUDO QUE ESTIVER DENTRO DE "adm" DEVE SER AUTENTICADO
		// E POSSUIR UMA ROLE DE ADMIN
		.antMatchers("/adm/**")
		.hasRole("ADMIN")
		
		// CSRF É UMA ABREVIAÇÃO PARA CROSS-SITE REQUEST FORGERY
		// É UM TIPO DE ATAQUE HACKER QUE ACONTECE EM APLICAÇÕES WEB.
		// COMO ESSA API É AUTENTICADA VIA TOKEN, AUTOMATICAMENTE ESTÁ LIVRE DESSE TIPO DE ATAQUE.
		// PORTANTO ESTÁ DESABILITADO PARA QUE O SPRING SECURITY NÃO FAÇA A VALIDAÇÃO DO TOKEN DO CSRF.
		.and().csrf().disable()
		
		// NÃO CRIA SESSÃO
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		;
		
		// TODAS AS REQUISIÇÔES DEVEM SER AUTENTICADAS
		//.anyRequest().authenticated();
	}
}
