package br.com.cetaceo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.cetaceo.dto.BA2EduTesteDTO;

@RestController
public class BA2EduController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@GetMapping(path = {"/myendpoint","/myendpoint/{value}"})
	public BA2EduTesteDTO ba2eduPub(@PathVariable(required = false) String value) {
		return createDto(value,"público");
	}
	
	@GetMapping(path = {"/u/myendpoint","/u/myendpoint/{value}"})
	public BA2EduTesteDTO ba2eduAut(@PathVariable(required = false) String value) {
		return createDto(value,"autenticado");
	}
	
	private BA2EduTesteDTO createDto(String value, String type) {
		BA2EduTesteDTO dto = new BA2EduTesteDTO();
		dto.setProjectName("Business Analytics for Education");
		dto.setParamValue(value);
		dto.setProjectVersion(Long.parseLong("2"));
		dto.setType(type);
		return dto;
	}
}
