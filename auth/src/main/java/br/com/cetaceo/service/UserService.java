package br.com.cetaceo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cetaceo.model.CtrUser;
import br.com.cetaceo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("UserService.loadUserByUsername: " + username);
		Optional<CtrUser> optional = userRepository.findByUsername(username);
		if ( optional.isPresent() )
			return optional.get();
		else
			throw new UsernameNotFoundException("Usuário ou senha inválidos");
	}

}
