package br.com.cetaceo.form;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginForm {

	@NotNull
	private String username;
	@NotNull
	private String password;
}
