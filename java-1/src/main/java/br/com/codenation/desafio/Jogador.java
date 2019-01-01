package br.com.codenation.desafio;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador {
	private Long id;
	private Long idTime;
	private String nome;
	private LocalDate dataNascimento;
	private Integer nivelHabilidade;
	private BigDecimal salario;

	public Jogador(Long id, String nome, LocalDate dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;

		this.nivelHabilidade = 50; // Considerando uma distribuicao uniforme [0,100].
		this.salario = new BigDecimal(0.0);
	}

	public Long getID() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public Long getTimeID() {
		return this.idTime;
	}

	public void setTime(Long idTime) {
		this.idTime = idTime;
	}

	public Integer getNivelHabilidade() {
		return this.nivelHabilidade;
	}

	public void setNivelHabilidade(Integer nivelHabilidade) {
		this.nivelHabilidade = nivelHabilidade;
	}

	public BigDecimal getSalario() {
		return this.salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getDataNascimento() {
		return this.dataNascimento;
	}
}
