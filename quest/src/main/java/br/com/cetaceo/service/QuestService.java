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
	
	public BA2EduTesteDTO ba2EduPub(String value) {
		BA2EduTesteDTO ba2Edu = ba2EduClient.getBA2EduPub(value);
		return ba2Edu;
	}
	
	public BA2EduTesteDTO ba2EduAut(String value) {
		BA2EduTesteDTO ba2Edu = ba2EduClient.getBA2EduAut(value);
		return ba2Edu;
	}
}
