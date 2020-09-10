package br.com.cetaceo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cetaceo.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Value("${security.oauth2.client.client-id}")
	private String clientId;
	
	@Value("${security.oauth2.client.client-secret}")
	private String secret;
	
	@Value("${security.oauth2.client.authorized-grant-types}")
	private String grantType;
	
	@Value("${security.oauth2.client.scope}")
	private String scope;
	
	@PostMapping
	public Object auth(@RequestBody @Valid LoginForm login) {
		
		Map<String, String> params = new HashMap<String, String>();
	    
		params.put("grant_type", grantType);
	    params.put("client_id", clientId);
	    params.put("scope", scope);
	    
	    params.put("username", login.getUsername());
	    params.put("password", login.getPassword());
	    
//	    Response response = RestAssured.given().auth().preemptive()
//	      .basic(clientId, secret).and().with().params(params).when()
//	      .post("http://localhost:8081/spring-security-oauth-server/oauth/token");
//	    
	    //return response.jsonPath().getString("access_token");
	    
	    return params;
	}
}
