package br.com.cetaceo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cetaceo.dto.BA2EduTesteDTO;
import br.com.cetaceo.form.QuestForm;
import br.com.cetaceo.model.Quest;
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
	
	@GetMapping("/a/questionarios")
	public Iterable<Quest> getAll() {
		return questService.getQuestionarios();
	}
	
	@PostMapping("/a/questionario/")
	public void getById(@RequestBody QuestForm questForm) {
		questService.saveQuestionario(questForm);
	}
	
	@GetMapping("/a/questionario/{id}")
	public Quest getById(@PathVariable Long id) {
		return questService.getQuestionario(id);
	}
	
	@DeleteMapping("/a/questionario/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		questService.deleteQuestionario(id);
		return ResponseEntity.ok().build();
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
