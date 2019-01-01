package br.com.codenation.desafio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Map.Entry.comparingByValue;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;

public class BancoDeJogadores {
	private Map<Long, Jogador> jogadores;

	public BancoDeJogadores() {
		this.jogadores = new HashMap<>();
	}

	public void adicionaJogador(Jogador novoJogador) {
		if (this.jogadores.containsKey(novoJogador.getID())) {
			throw new IdentificadorUtilizadoException();
		}

		this.jogadores.put(novoJogador.getID(), novoJogador);
	}

	public Jogador getJogador(Long idJogador) {
		if (this.jogadores.containsKey(idJogador)) {
			return this.jogadores.get(idJogador);
		}

		throw new JogadorNaoEncontradoException();
	}

	public List<Long> buscaTopJogadores(int top) {
		int numeroDeJogadores = this.jogadores.size();

		return this.jogadores.entrySet().stream()
				.sorted(comparingByValue(comparing(Jogador::getNivelHabilidade)))
				.skip(numeroDeJogadores >= top ? numeroDeJogadores - top : 0)
				.map(e -> e.getKey())
				.collect(Collectors.toList());
	}

	public void limpaTodos() {
		this.jogadores = new HashMap<>();
	}
}
