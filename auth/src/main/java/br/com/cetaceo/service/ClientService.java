package br.com.cetaceo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import br.com.cetaceo.model.Client;
import br.com.cetaceo.repository.ClientRepository;

@Service
public class ClientService implements ClientDetailsService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		Optional<Client> optional = clientRepository.findByUsername(clientId);
		if ( optional.isPresent() )
			return optional.get();
		else
			throw new UsernameNotFoundException("Usuário ou senha do client inválidos");
	}

}
