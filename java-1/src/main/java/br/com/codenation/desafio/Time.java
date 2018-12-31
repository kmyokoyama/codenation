package br.com.codenation.desafio;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;

public class Time {
	private Long id;
	private String nome;
	private @SuppressWarnings("unused") LocalDate dataCriacao;
	private String corUniformePrincipal;
	private String corUniformeSecundario;
	private Map<Long, Jogador> jogadores;
	private Long idCapitao;

	public Time(Long id, String nome, LocalDate dataCriacao) {
		this.id = id;
		this.nome = nome;
		this.dataCriacao = dataCriacao;
		this.jogadores = new HashMap<>();
	}

	public Long getID() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public String getCorUniformePrincipal() {
		return this.corUniformePrincipal;
	}

	public void setCorUniformePrincipal(String cor) {
		this.corUniformePrincipal = cor;
	}

	public String getCorUniformeSecundario() {
		return this.corUniformeSecundario;
	}

	public void setCorUniformeSecundario(String cor) {
		this.corUniformeSecundario = cor;
	}

	public Long getCapitao() {
		if (this.idCapitao == null) {
			throw new CapitaoNaoInformadoException();
		}

		return this.idCapitao;
	}

	public void setCapitao(Long idNovoCapitao) {
		if (!this.jogadores.containsKey(idNovoCapitao)) {
			throw new JogadorNaoEncontradoException();
		}

		this.idCapitao = idNovoCapitao;
	}

	public void adicionaJogador(Jogador novoJogador) {
		if (this.jogadores.containsKey(novoJogador.getID())) {
			throw new IdentificadorUtilizadoException();
		}

		this.jogadores.put(novoJogador.getID(), novoJogador);
	}

	public Integer tamanhoDoTime() {
		return this.jogadores.size();
	}

	public List<Long> buscaJogadores() {
		return this.jogadores.keySet().stream().sorted((o1, o2) -> o1.compareTo(o2)).collect(Collectors.toList());
	}

	public Long buscaMelhorJogador() {
		return this.jogadores.entrySet().stream().max(Map.Entry
				.comparingByValue((jog1, jog2) -> jog1.getNivelHabilidade().compareTo(jog2.getNivelHabilidade()))).get()
				.getKey();
	}

	public Long buscaJogadorMaisVelho() {
		return this.jogadores.entrySet().stream()
				.max(Map.Entry.comparingByValue((jog1, jog2) -> jog1.comparaDataNascimento(jog2))).get().getKey();
	}

	public Long buscaJogadorMaiorSalario() {
		return this.jogadores.entrySet().stream()
				.max(Map.Entry.comparingByValue((jog1, jog2) -> jog1.comparaSalario(jog2))).get().getKey();
	}
}