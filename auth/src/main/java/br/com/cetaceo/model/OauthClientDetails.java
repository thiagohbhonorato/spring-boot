package br.com.cetaceo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OauthClientDetails {

	@Id
	private String clientId;
	private String resourceIds;
	private String clientSecret;
	private String scope;
	private String authorizedGrantTypes;
	private String webServerRedirectUri;
	private String authorities;
	private Long accessTokenValidity;
	private Long refreshTokenValidity;
	private String additionalInformation;
	private String autoapprove;
}
