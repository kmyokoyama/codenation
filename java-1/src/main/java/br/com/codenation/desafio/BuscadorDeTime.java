package br.com.codenation.desafio;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Map.Entry.comparingByValue;

public class BuscadorDeTime {
	private Map<Long, Jogador> jogadores;
	
	public BuscadorDeTime(final Map<Long, Jogador> jogadores) {
		this.jogadores = jogadores;
	}
	public List<Long> buscaJogadores() {
		return this.jogadores.keySet().stream().sorted(Long::compareTo).collect(Collectors.toList());
	}

	public Long buscaMelhorJogador() {
		return this.jogadores.entrySet().stream()
				.max(comparingByValue(
						comparing(Jogador::getNivelHabilidade))
				)
				.get().getKey();
	}

	public Long buscaJogadorMaisVelho() {
		return this.jogadores.entrySet().stream()
				.min(comparingByValue(
						comparing(Jogador::getDataNascimento)
						.thenComparing(Comparator.comparing(Jogador::getID))
					)
				)
				.get().getKey();
	}

	public Long buscaJogadorMaiorSalario() {
		return this.jogadores.entrySet().stream()
				.min(comparingByValue(
						comparing(Jogador::getSalario).reversed()
						.thenComparing(Comparator.comparing(Jogador::getID))
					)
				)
				.get().getKey();
	}
}
