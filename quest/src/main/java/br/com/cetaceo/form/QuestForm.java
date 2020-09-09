package br.com.cetaceo.form;

import javax.validation.constraints.NotNull;

import br.com.cetaceo.model.Quest;
import br.com.cetaceo.service.QuestService;

public class QuestForm {

	@NotNull
	private String nome;
	
	@NotNull
	private String descricao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Quest converter() {
		return Quest.builder()
				.nome(nome)
				.descricao(descricao)
				.build();
	}

	public Quest update(Long id, QuestService questService) {
		Quest quest = questService.getQuestionario(id);
		quest.setNome(this.nome);
		quest.setDescricao(this.descricao);
		return quest;
	}
}
