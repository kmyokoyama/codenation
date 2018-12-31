package br.com.codenation;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.codenation.desafio.Jogador;
import br.com.codenation.desafio.Time;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class TesteTime {
	@Test(expected = IdentificadorUtilizadoException.class)
	public void testeAdicionaJogadorComMesmoID() {
		Time time = new Time(0L, "novo time", LocalDate.now());

		time.adicionaJogador(new Jogador(0L, "jogador 1", LocalDate.now()));
		time.adicionaJogador(new Jogador(0L, "jogador 2", LocalDate.now()));
	}

	@Test
	public void testeAdicionaCincoJogadores() {
		Time time = new Time(0L, "novo time", LocalDate.now());

		time.adicionaJogador(new Jogador(0L, "jogador 1", LocalDate.now()));
		time.adicionaJogador(new Jogador(1L, "jogador 2", LocalDate.now()));
		time.adicionaJogador(new Jogador(2L, "jogador 3", LocalDate.now()));
		time.adicionaJogador(new Jogador(3L, "jogador 4", LocalDate.now()));
		time.adicionaJogador(new Jogador(4L, "jogador 5", LocalDate.now()));

		assertEquals(new Integer(5), time.tamanhoDoTime());
	}

	@Test
	public void testeDefineCapitao() {
		Time time = new Time(0L, "novo time", LocalDate.now());

		time.adicionaJogador(new Jogador(0L, "jogador 1", LocalDate.now()));
		time.adicionaJogador(new Jogador(1L, "jogador 2", LocalDate.now()));

		time.setCapitao(0L);

		assertEquals(new Long(0), time.getCapitao());
	}

	@Test(expected = JogadorNaoEncontradoException.class)
	public void testeDefineCapitaoInexistente() {
		Time time = new Time(0L, "novo time", LocalDate.now());

		time.adicionaJogador(new Jogador(0L, "jogador 1", LocalDate.now()));
		time.adicionaJogador(new Jogador(1L, "jogador 2", LocalDate.now()));

		time.setCapitao(2L);
	}

	@Test
	public void testeBuscaCapitao() {
		Time time = new Time(0L, "novo time", LocalDate.now());

		time.adicionaJogador(new Jogador(0L, "jogador 1", LocalDate.now()));
		time.adicionaJogador(new Jogador(1L, "jogador 2", LocalDate.now()));

		time.setCapitao(0L);

		assertEquals(new Long(0), time.getCapitao());
	}

	@Test
	public void testeTrocaCapitao() {
		Time time = new Time(0L, "novo time", LocalDate.now());

		time.adicionaJogador(new Jogador(0L, "jogador 1", LocalDate.now()));
		time.adicionaJogador(new Jogador(1L, "jogador 2", LocalDate.now()));

		time.setCapitao(0L);

		assertEquals(new Long(0), time.getCapitao());

		time.setCapitao(1L);

		assertEquals(new Long(1), time.getCapitao());
	}

	@Test(expected = CapitaoNaoInformadoException.class)
	public void testeCapitaoIndefinido() {
		Time time = new Time(0L, "novo time", LocalDate.now());

		time.adicionaJogador(new Jogador(0L, "jogador 1", LocalDate.now()));
		time.adicionaJogador(new Jogador(1L, "jogador 2", LocalDate.now()));

		time.getCapitao();
	}

	@Test
	public void testeBuscaJogadoresDoTime() {
		Time time = new Time(0L, "novo time", LocalDate.now());

		List<Long> indices = new ArrayList<>();

		int tamanhoDoTime = 5;
		for (int i = 0; i < tamanhoDoTime; i++) {
			time.adicionaJogador(new Jogador(new Long(i), "jogador" + i, LocalDate.now()));
			indices.add(new Long(i));
		}

		List<Long> idJogadores = time.buscaJogadores();

		assertEquals(tamanhoDoTime, idJogadores.size());

		assertEquals(indices, idJogadores);
	}

	@Test
	public void testeBuscaMelhorJogadorDoTime() {
		Time time = new Time(0L, "novo time", LocalDate.now());

		Jogador novoJogador;
		int tamanhoDoTime = 5;
		for (int i = 0; i < tamanhoDoTime; i++) {
			novoJogador = new Jogador(new Long(i), "jogador" + i, LocalDate.now());
			novoJogador.setNivelHabilidade(100 - 10 * i);
			time.adicionaJogador(novoJogador);
		}

		assertEquals(new Long(0), time.buscaMelhorJogador());
	}

	@Test
	public void testeBuscaJogadorMaisVelhoDoTime() {
		Time novoTime = new Time(0L, "novo time", LocalDate.now());

		novoTime.adicionaJogador(new Jogador(0L, "jogador 1", LocalDate.of(1991, 01, 01)));
		novoTime.adicionaJogador(new Jogador(1L, "jogador 2", LocalDate.of(1992, 01, 05)));
		novoTime.adicionaJogador(new Jogador(2L, "jogador 3", LocalDate.of(1993, 10, 01)));
		novoTime.adicionaJogador(new Jogador(3L, "jogador 4", LocalDate.of(1993, 05, 01)));
		novoTime.adicionaJogador(new Jogador(4L, "jogador 5", LocalDate.of(1992, 01, 05)));

		assertEquals(new Long(2), novoTime.buscaJogadorMaisVelho());
	}
}
