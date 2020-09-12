package br.com.cetaceo;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
	
	@Resource(name="tokenStore")
	TokenStore tokenStore;
	
	@RequestMapping(method = RequestMethod.GET, value = "/tokens/{clientId}")
	@ResponseBody
	public ResponseEntity<List<String>> getTokens(@PathVariable String clientId, Principal user) {
	    boolean isAdmin = false;
		Collection<GrantedAuthority> authorities = ((OAuth2Authentication) user).getAuthorities();
	    for ( GrantedAuthority role : authorities ) {
	    	isAdmin = "ROLE_ADMIN".equals(role.getAuthority());
	    	if ( isAdmin ) break;
	    }
	    
	    if ( !isAdmin )
	    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    
	    List<String> tokenValues = new ArrayList<String>();
	    Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId(clientId); 
	    if ( tokens != null ) {
	        for ( OAuth2AccessToken token : tokens ) {
	            tokenValues.add(token.getValue());
	        }
	    }
	    return ResponseEntity.ok(tokenValues);
	}
	
	@Resource(name="tokenServices")
	ConsumerTokenServices tokenServices;
		
	@RequestMapping(method = RequestMethod.POST, value = {"/tokens/revoke","/tokens/revoke/{tokenId}"})
	@ResponseBody
	public ResponseEntity<?> revokeToken(@PathVariable(required = false) String tokenId, Principal user) {
		if ( tokenId == null ) {
			OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) ((OAuth2Authentication) user).getDetails();
			tokenId = details.getTokenValue();
		}
		
	    if ( tokenServices.revokeToken(tokenId) )
	    	return ResponseEntity.ok().build();
	    else
	    	return ResponseEntity.notFound().build();
	}
}
