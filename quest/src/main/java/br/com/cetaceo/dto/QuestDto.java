package br.com.cetaceo.dto;

import java.time.LocalDateTime;

import br.com.cetaceo.model.Quest;
import lombok.Getter;

@Getter
public class QuestDto {

	private String nome;

	private String descricao;

	private LocalDateTime dataCriacao;

	public QuestDto(Quest quest) {
		this.nome = quest.getNome();
		this.descricao = quest.getDescricao();
		this.dataCriacao = quest.getDataCriacao();
	}
}
