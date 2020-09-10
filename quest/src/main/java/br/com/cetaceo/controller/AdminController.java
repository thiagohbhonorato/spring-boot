package br.com.cetaceo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cetaceo.model.User;
import br.com.cetaceo.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/adm/user/all")
	public List<User> getAllUser() {
		return adminService.getAllUser();
	}
}
