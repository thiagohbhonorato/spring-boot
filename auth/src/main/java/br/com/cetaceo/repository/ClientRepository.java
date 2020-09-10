package br.com.cetaceo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.cetaceo.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

	Optional<Client> findByUsername(String clientId);

}
