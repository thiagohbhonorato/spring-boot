package br.com.cetaceo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cetaceo.client.BA2EduClient;
import br.com.cetaceo.dto.BA2EduTesteDTO;
import br.com.cetaceo.form.QuestForm;
import br.com.cetaceo.model.Quest;
import br.com.cetaceo.repository.QuestRepository;

@Service
public class QuestService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private QuestRepository questRepository;
	
	@Autowired
	private BA2EduClient ba2EduClient;
	
	public BA2EduTesteDTO ba2EduPub(String value) {
		BA2EduTesteDTO ba2Edu = ba2EduClient.getBA2EduPub(value);
		return ba2Edu;
	}
	
	public BA2EduTesteDTO ba2EduAut(String value) {
		BA2EduTesteDTO ba2Edu = ba2EduClient.getBA2EduAut(value);
		return ba2Edu;
	}

	public Quest getQuestionario(Long id) {
		return questRepository.findById(id).orElse(new Quest());
	}

	public Iterable<Quest> getQuestionarios() {
		return questRepository.findAll();
	}

	public void deleteQuestionario(Long id) {
		questRepository.deleteById(id);
	}

	public void saveQuestionario(QuestForm questForm) {
		questRepository.save(questForm.converter());
	}
}
