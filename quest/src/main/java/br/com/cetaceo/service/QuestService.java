package br.com.cetaceo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cetaceo.client.BA2EduClient;
import br.com.cetaceo.dto.BA2EduTesteDto;
import br.com.cetaceo.form.QuestForm;
import br.com.cetaceo.model.Quest;
import br.com.cetaceo.repository.QuestRepository;

@Service
public class QuestService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private QuestRepository questRepository;
	
	public Quest getQuestionario(Long id) {
		Optional<Quest> optional = questRepository.findById(id);
		if ( optional.isPresent() )
			return optional.get();
		else return null;
	}

	public Iterable<Quest> getQuestionarios() {
		return questRepository.findAll();
	}

	public void deleteQuestionario(Long id) {
		questRepository.deleteById(id);
	}

	public Quest saveQuestionario(QuestForm questForm) {
		return questRepository.save(questForm.converter());
	}
}
