package br.com.cetaceo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OauthAccessToken {

	@Id 
	private String authenticationId;
	private String tokenId;
	@Column(columnDefinition = "bytea")
	private String token;
	private String userName;
	private String clientId;
	@Column(columnDefinition = "bytea")
	private String authentication;
	private String refreshToken;
}
