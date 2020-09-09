package br.com.cetaceo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorFormDto {

	private String field;
	private String message;
}
