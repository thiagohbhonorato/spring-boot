package br.com.cetaceo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.cetaceo.dto.BA2EduTesteDTO;
import br.com.cetaceo.service.QuestService;

@RestController
public class QuestController {

	@Autowired
	private QuestService questService;
	
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
	public BA2EduTesteDTO ba2EduPub(@PathVariable(required = false) String value) {
		return questService.ba2EduPub(value);
	}
	
	@GetMapping(path = {"/a/ba2edu","/a/ba2edu/{value}"})
	public BA2EduTesteDTO ba2EduAut(@PathVariable(required = false) String value) {
		return questService.ba2EduAut(value);
	}
}
