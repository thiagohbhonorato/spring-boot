package br.com.cetaceo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cetaceo.client.BA2EduClient;
import br.com.cetaceo.dto.BA2EduTesteDTO;

@Service
public class QuestService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BA2EduClient ba2EduClient;
	
	public BA2EduTesteDTO ba2EduTeste(String value) {
		LOG.info("Buscando informação no BA2Edu");
		BA2EduTesteDTO ba2EduTeste = ba2EduClient.getBA2EduTeste(value);
		
		LOG.info("Informação recebida do BA2Edu");
		return ba2EduTeste;
	}
}
