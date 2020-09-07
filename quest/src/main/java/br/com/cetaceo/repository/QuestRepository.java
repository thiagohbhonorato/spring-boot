package br.com.cetaceo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cetaceo.model.Quest;

@Repository
public interface QuestRepository extends CrudRepository<Quest, Long> {

}
