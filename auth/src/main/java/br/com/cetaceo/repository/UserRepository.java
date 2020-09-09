package br.com.cetaceo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cetaceo.model.CtrUser;

public interface UserRepository extends JpaRepository<CtrUser, Long>{

	Optional<CtrUser> findByUsername(String username);
}
