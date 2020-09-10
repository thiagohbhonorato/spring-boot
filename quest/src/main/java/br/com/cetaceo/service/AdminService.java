package br.com.cetaceo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cetaceo.model.User;

@Service
public class AdminService {

	public List<User> getAllUser() {
		return new ArrayList<User>();
	}
}
