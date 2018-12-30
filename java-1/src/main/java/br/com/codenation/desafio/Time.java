package br.com.codenation.desafio;

import java.time.LocalDate;

// Fazer uma factory de times.
public class Time {
	private Long id;
	private String nome;
	private LocalDate dataCriacao;
	private String corUniformePrincipal;
	private String corUniformeSecundario;
	
	public Time(Long id, String nome, LocalDate dataCriacao) {
		this.id = id;
		this.nome = nome;
		this.dataCriacao = dataCriacao;
	}
	
	public void setCorUniformePrincipal(String cor) {
		this.corUniformePrincipal = cor;
	}
	
	public void setCorUniformeSecundario(String cor) {
		this.corUniformeSecundario = cor;
	}
	
	public Long getID() {
		return this.id;
	}
}
