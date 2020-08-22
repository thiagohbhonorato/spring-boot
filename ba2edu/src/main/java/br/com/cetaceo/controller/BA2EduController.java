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
	
	@GetMapping(path = {"/teste","/teste/{value}"})
	public BA2EduTesteDTO ba2eduTeste(@PathVariable(required = false) String value) {
		BA2EduTesteDTO dto = new BA2EduTesteDTO();
		dto.setProjectName("Business Analytics for Education");
		dto.setParamValue(value);
		dto.setProjectVersion(Long.parseLong("2"));
		LOG.info("Devolvendo informação para API");
		return dto;
	}
}
