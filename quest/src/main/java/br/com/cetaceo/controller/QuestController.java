package br.com.cetaceo.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cetaceo.dto.BA2EduTesteDto;
import br.com.cetaceo.dto.QuestDto;
import br.com.cetaceo.form.QuestForm;
import br.com.cetaceo.model.Quest;
import br.com.cetaceo.service.QuestService;

@RestController
public class QuestController {

	@Autowired
	private QuestService questService;
	
	@GetMapping("/a/questionarios")
	public Iterable<Quest> getAll() {
		return questService.getQuestionarios();
	}
	
	@GetMapping("/a/questionario/{id}")
	public ResponseEntity<Quest> getById(@PathVariable Long id) {
		Quest quest = questService.getQuestionario(id);
		if ( quest != null )
			return ResponseEntity.ok(quest);
		else
			return ResponseEntity.notFound().build();
	}
	
	@Transactional
	@PostMapping("/a/questionario/")
	public ResponseEntity<QuestDto> save(@RequestBody @Valid QuestForm questForm, UriComponentsBuilder uriBuilder) {
		Quest quest = questService.saveQuestionario(questForm);
		URI uri = uriBuilder.path("/a/questionario/{id}").buildAndExpand(quest.getId()).toUri();
		return ResponseEntity.created(uri).body(new QuestDto(quest));
	}
	
	@Transactional
	@PutMapping("/a/questionario/{id}")
	public ResponseEntity<QuestDto> update(@PathVariable Long id, @RequestBody @Valid QuestForm questForm) {
		Quest quest = questService.getQuestionario(id);
		if ( quest != null ) {
			quest = questForm.update(id, questService);
			return ResponseEntity.ok(new QuestDto(quest));
		} else
			return ResponseEntity.notFound().build();
	}
	
	@Transactional
	@DeleteMapping("/a/questionario/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		Quest quest = questService.getQuestionario(id);
		if ( quest != null ) {
			questService.deleteQuestionario(id);
			return ResponseEntity.ok().build();
		} else
			return ResponseEntity.notFound().build();
	}
}
