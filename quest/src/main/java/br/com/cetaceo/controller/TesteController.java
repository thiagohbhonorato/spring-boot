package br.com.cetaceo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.cetaceo.dto.BA2EduTesteDto;
import br.com.cetaceo.service.BA2EduService;

@RestController
public class TesteController {

	@Autowired
	private BA2EduService ba2eduService;
	
	@GetMapping("/")
	public String oi() {
		return "Oi, eu sou a API Quest";
	}
	
	@GetMapping("/myendpoint")
	public String myendpointPub() {
		return "REST Quest público";
	}
	
	@GetMapping("/a/myendpoint")
	public String myendpointAut() {
		return "REST Quest autenticado";
	}
	
	@GetMapping(path = {"/ba2edu","/ba2edu/{value}"})
	public BA2EduTesteDto ba2EduPub(@PathVariable(required = false) String value) {
		return ba2eduService.ba2EduPub(value);
	}
	
	@GetMapping(path = {"/a/ba2edu","/a/ba2edu/{value}"})
	public BA2EduTesteDto ba2EduAut(@PathVariable(required = false) String value) {
		return ba2eduService.ba2EduAut(value);
	}
}
