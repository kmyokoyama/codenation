package br.com.codenation.desafio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

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
	
	public void setTime(Long idTime) {
		this.idTime = idTime;
	}
	
	public void setNivelHabilidade(Integer nivelHabilidade) {
		this.nivelHabilidade = nivelHabilidade;
	}
	
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	
	public Long getID() {
		return this.id;
	}
	
	public Long getTimeID() {
		return this.idTime;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	private LocalDate getDataNascimento() {
		return this.dataNascimento;
	}
	
	public Integer getNivelHabilidade() {
		return this.nivelHabilidade;
	}
	
	public BigDecimal getSalario() {
		return this.salario;
	}
	
	public int comparaDataNascimento(Jogador outroJogador) {
		if (this.dataNascimento == outroJogador.dataNascimento) {
			return (int) (this.id - outroJogador.id);
		}
		
		return this.dataNascimento.compareTo(outroJogador.dataNascimento);
	}
	
	public int comparaSalario(Jogador outroJogador) {
		if (this.salario == outroJogador.salario) {
			return (int) (this.id - outroJogador.id);
		}
		
		return this.salario.compareTo(outroJogador.salario);
	}
	
	public int comparaNivelHabilidade(Jogador outroJogador) {
		if (this.nivelHabilidade == outroJogador.nivelHabilidade) {
			return (int) (this.id - outroJogador.id);
		}
		
		return this.nivelHabilidade.compareTo(outroJogador.nivelHabilidade);
	}
}
