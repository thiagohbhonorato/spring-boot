package br.com.cetaceo.form;

import br.com.cetaceo.model.Quest;

public class QuestForm {

	private String nome;
	
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
		return null;
	}
}
