package br.com.cetaceo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OauthRefreshToken {
	
	@Id 
	private String tokenId;
	@Column(columnDefinition = "bytea")
	private String token;
	@Column(columnDefinition = "bytea")
	private String authentication;
}
