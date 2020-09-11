package br.com.cetaceo.controller;

import java.util.Base64;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.cetaceo.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Value("${security.oauth2.resource.oauth-token-uri}")
	private String oauthTokenUri;
	
	@Value("${security.oauth2.client.client-id}")
	private String clientId;
	
	@Value("${security.oauth2.client.client-secret}")
	private String secret;
	
	@Value("${security.oauth2.client.authorized-grant-types}")
	private String grantType;
	
	@Value("${security.oauth2.client.scope}")
	private String scope;
	
	@PostMapping
	public ResponseEntity<Object> auth(@RequestBody @Valid LoginForm login) {
		
		String authorization = Base64.getEncoder().encodeToString( (clientId + ":" + secret).getBytes() );
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic " + authorization);
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
		form.add("scope", scope);
		form.add("grant_type", grantType);
	    form.add("username", login.getUsername());
	    form.add("password", login.getPassword());
	    
	    HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(form, headers);
	    
		RestTemplate client = new RestTemplate();
		ResponseEntity<Object> auth = client.postForEntity(oauthTokenUri, entity, Object.class);
	    
	    return auth;
	}
}
