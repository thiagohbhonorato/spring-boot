package br.com.cetaceo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cetaceo.client.BA2EduClient;
import br.com.cetaceo.dto.BA2EduTesteDto;

@Service
public class BA2EduService {

	@Autowired
	private BA2EduClient ba2EduClient;
	
	public BA2EduTesteDto ba2EduPub(String value) {
		BA2EduTesteDto ba2Edu = ba2EduClient.getBA2EduPub(value);
		return ba2Edu;
	}
	
	public BA2EduTesteDto ba2EduAut(String value) {
		BA2EduTesteDto ba2Edu = ba2EduClient.getBA2EduAut(value);
		return ba2Edu;
	}
}
