package br.com.cetaceo.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		
		// TUDO QUE ESTIVER DENTRO DE "a" DEVE SER AUTENTICADO
		.antMatchers("/a/**")
		.hasRole("USER");
		
		// TODAS AS REQUISIÇÔES DEVEM SER AUTENTICADAS
		//.anyRequest().authenticated();
	}
}
