package br.com.codenation;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.codenation.desafio.BancoDeJogadores;
import br.com.codenation.desafio.BancoDeTimes;
import br.com.codenation.desafio.Jogador;
import br.com.codenation.desafio.Time;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;

public class TesteDesafioMeuTimeApplication {

	private static DesafioMeuTimeApplication desafio;
	private static BancoDeTimes bancoDeTimes;
	private static BancoDeJogadores bancoDeJogadores;

	@Before
	public void setUp() {
		desafio = new DesafioMeuTimeApplication();

		bancoDeTimes = BancoDeTimes.getInstance();
		bancoDeJogadores = BancoDeJogadores.getInstance();
	}

	@After
	public void tearDown() {
		bancoDeTimes.limpaTodos();
		bancoDeJogadores.limpaTodos();
	}

	@Test(expected = IdentificadorUtilizadoException.class)
	public void testIncluirTimesComMesmoID() {
		desafio.incluirTime(0L, "novo time", LocalDate.now(), "Branco", "Preto");
		desafio.incluirTime(0L, "outro time", LocalDate.now(), "Preto", "Branco");
	}

	@Test(expected = TimeNaoEncontradoException.class)
	public void testeIncluirJogadorEmTimeInexistente() {
		desafio.incluirJogador(0L, 0L, "novo jogador", LocalDate.now(), 90, new BigDecimal(100000));
	}

	@Test(expected = IdentificadorUtilizadoException.class)
	public void testeIncluirJogadoresComMesmoID() {
		bancoDeTimes.adicionarTime(new Time(0L, "novo time", LocalDate.now()));

		desafio.incluirJogador(0L, 0L, "novo jogador", LocalDate.now(), 90, new BigDecimal(100000));
		desafio.incluirJogador(0L, 0L, "outro jogador", LocalDate.now(), 80, new BigDecimal(70000));
	}

	@Test(expected = JogadorNaoEncontradoException.class)
	public void testeDefineCapitaoInexistente() {
		desafio.definirCapitao(0L);
	}

	@Test
	public void testeBuscaCapitao() {
		Time novoTime = new Time(0L, "novo time", LocalDate.now());

		bancoDeTimes.adicionarTime(novoTime);

		desafio.incluirJogador(0L, 0L, "novo jogador", LocalDate.now(), 90, new BigDecimal(100000));

		novoTime.setCapitao(0L);

		assertEquals(new Long(0), desafio.buscarCapitaoDoTime(0L));
	}

	@Test(expected = TimeNaoEncontradoException.class)
	public void testeBuscaCapitaoEmTimeInexistente() {
		desafio.buscarCapitaoDoTime(0L);
	}

	@Test(expected = CapitaoNaoInformadoException.class)
	public void testeDefineCapitaoComJogadorInexistente() {
		Time novoTime = new Time(0L, "novo time", LocalDate.now());

		bancoDeTimes.adicionarTime(novoTime);

		desafio.buscarCapitaoDoTime(0L);
	}

	@Test
	public void testeBuscaNomeDoJogador() {
		Jogador novoJogador = new Jogador(0L, "novo jogador", LocalDate.now());

		bancoDeJogadores.adicionaJogador(novoJogador);

		String nomeDoJogador = desafio.buscarNomeJogador(0L);

		assertEquals("novo jogador", nomeDoJogador);
	}

	@Test(expected = JogadorNaoEncontradoException.class)
	public void testeBuscaNomeDeJogadorInexistente() {
		desafio.buscarNomeJogador(0L);
	}

	@Test
	public void testeBuscaNomeDoTime() {
		Time novoTime = new Time(0L, "novo time", LocalDate.now());

		bancoDeTimes.adicionarTime(novoTime);

		String nomeDoTime = desafio.buscarNomeTime(0L);

		assertEquals("novo time", nomeDoTime);
	}

	@Test(expected = TimeNaoEncontradoException.class)
	public void testeBuscaNomeDeTimeInexistente() {
		desafio.buscarNomeTime(0L);
	}

	@Test
	public void testeBuscaJogadoresDoTime() {
		Time novoTime = new Time(0L, "novo time", LocalDate.now());
		bancoDeTimes.adicionarTime(novoTime);

		List<Long> indices = new ArrayList<>();

		int tamanhoDoTime = 5;
		for (int i = 0; i < tamanhoDoTime; i++) {
			novoTime.adicionaJogador(new Jogador(new Long(i), "jogador" + i, LocalDate.now()));
			indices.add(new Long(i));
		}

		List<Long> idJogadores = desafio.buscarJogadoresDoTime(0L);

		assertEquals(tamanhoDoTime, idJogadores.size());

		assertEquals(indices, idJogadores);
	}

	@Test(expected = TimeNaoEncontradoException.class)
	public void testeBuscaJogadoresDeTimeInexistente() {
		desafio.buscarJogadoresDoTime(0L);
	}

	@Test
	public void testeBuscaMelhorJogadorDoTime() {
		Time novoTime = new Time(0L, "novo time", LocalDate.now());
		bancoDeTimes.adicionarTime(novoTime);

		Jogador novoJogador;
		int tamanhoDoTime = 5;
		for (int i = 0; i < tamanhoDoTime; i++) {
			novoJogador = new Jogador(new Long(i), "jogador" + i, LocalDate.now());
			novoJogador.setNivelHabilidade(100 - 10 * i);
			novoTime.adicionaJogador(novoJogador);
		}

		assertEquals(new Long(0), desafio.buscarMelhorJogadorDoTime(0L));
	}

	@Test(expected = TimeNaoEncontradoException.class)
	public void testeBuscaMelhorJogadorDeTimeInexistente() {
		desafio.buscarMelhorJogadorDoTime(0L);
	}

	@Test
	public void testeBuscaJogadorMaisVelhoDoTime() {
		Time novoTime = new Time(0L, "novo time", LocalDate.now());
		bancoDeTimes.adicionarTime(novoTime);

		novoTime.adicionaJogador(new Jogador(0L, "jogador 1", LocalDate.of(1991, 01, 01)));
		novoTime.adicionaJogador(new Jogador(1L, "jogador 2", LocalDate.of(1992, 01, 05)));
		novoTime.adicionaJogador(new Jogador(2L, "jogador 3", LocalDate.of(1993, 10, 01)));
		novoTime.adicionaJogador(new Jogador(3L, "jogador 4", LocalDate.of(1993, 05, 01)));
		novoTime.adicionaJogador(new Jogador(4L, "jogador 5", LocalDate.of(1992, 01, 05)));

		assertEquals(new Long(2), desafio.buscarJogadorMaisVelho(0L));
	}

	@Test(expected = TimeNaoEncontradoException.class)
	public void testeBuscaJogadorMaisVelhoDeTimeInexistente() {
		desafio.buscarJogadorMaisVelho(0L);
	}

	@Test
	public void testeBuscarTimes() {
		List<Long> indices = new ArrayList<>();

		Time novoTime;
		int numeroTimes = 5;
		for (int i = 0; i < numeroTimes; i++) {
			novoTime = new Time(new Long(i), "time" + i, LocalDate.now());
			bancoDeTimes.adicionarTime(novoTime);
			indices.add(new Long(i));
		}

		List<Long> idTimes = desafio.buscarTimes();

		assertEquals(numeroTimes, idTimes.size());

		assertEquals(indices, idTimes);
	}

	@Test
	public void testeBuscarTimesVazio() {
		assertTrue(desafio.buscarTimes().isEmpty());
	}

	@Test
	public void testeBuscaJogadorMaiorSalario() {
		Time novoTime = new Time(0L, "novo time", LocalDate.now());
		bancoDeTimes.adicionarTime(novoTime);

		Jogador novoJogador;
		int tamanhoDoTime = 5;
		for (int i = 0; i < tamanhoDoTime; i++) {
			novoJogador = new Jogador(new Long(i), "jogador" + i, LocalDate.now());
			novoJogador.setSalario(new BigDecimal(100000 - 1000 * i));
			novoTime.adicionaJogador(novoJogador);
		}

		assertEquals(new Long(0), desafio.buscarJogadorMaiorSalario(0L));
	}

	@Test
	public void testeBuscaJogadorMaiorSalarioComEmpate() {
		Time novoTime = new Time(0L, "novo time", LocalDate.now());
		bancoDeTimes.adicionarTime(novoTime);

		Jogador novoJogador;
		novoJogador = new Jogador(0L, "jogador 1", LocalDate.now());
		novoJogador.setSalario(new BigDecimal(100000));
		novoTime.adicionaJogador(novoJogador);

		novoJogador = new Jogador(1L, "jogador 2", LocalDate.now());
		novoJogador.setSalario(new BigDecimal(110000));
		novoTime.adicionaJogador(novoJogador);

		novoJogador = new Jogador(2L, "jogador 3", LocalDate.now());
		novoJogador.setSalario(new BigDecimal(110000));
		novoTime.adicionaJogador(novoJogador);

		assertEquals(new Long(1), desafio.buscarJogadorMaiorSalario(0L));
	}

	@Test(expected = TimeNaoEncontradoException.class)
	public void testeBuscaJogadorMaiorSalarioDeTimeInexistente() {
		desafio.buscarMelhorJogadorDoTime(0L);
	}

	@Test
	public void testeBuscaSalarioDoJogador() {
		Jogador novoJogador = new Jogador(0L, "novo jogador", LocalDate.now());
		novoJogador.setSalario(new BigDecimal(100000));

		bancoDeJogadores.adicionaJogador(novoJogador);

		BigDecimal salarioDoJogador = desafio.buscarSalarioDoJogador(0L);

		assertEquals(new BigDecimal(100000), salarioDoJogador);
	}

	@Test(expected = JogadorNaoEncontradoException.class)
	public void testeBuscaSalarioDeJogadorInexistente() {
		desafio.buscarSalarioDoJogador(0L);
	}

	@Test
	public void testeBuscaTopJogadores() {
		Jogador novoJogador;
		novoJogador = new Jogador(0L, "jogador 1", LocalDate.now());
		novoJogador.setNivelHabilidade(100);
		bancoDeJogadores.adicionaJogador(novoJogador);

		novoJogador = new Jogador(1L, "jogador 2", LocalDate.now());
		novoJogador.setNivelHabilidade(90);
		bancoDeJogadores.adicionaJogador(novoJogador);

		novoJogador = new Jogador(2L, "jogador 3", LocalDate.now());
		novoJogador.setNivelHabilidade(100);
		bancoDeJogadores.adicionaJogador(novoJogador);

		assertEquals(Arrays.asList(0L, 2L), desafio.buscarTopJogadores(2));
	}

	@Test
	public void testeBuscarTopJogadoresVazio() {
		assertTrue(desafio.buscarTopJogadores(10).isEmpty());
	}

	@Test
	public void testeCamisaDoTimeDeForaDiferentes() {
		Time timeDaCasa = new Time(0L, "novo time", LocalDate.now());
		timeDaCasa.setCorUniformePrincipal("Preto");
		Time timeDeFora = new Time(1L, "outro time", LocalDate.now());
		timeDeFora.setCorUniformePrincipal("Branco");

		bancoDeTimes.adicionarTime(timeDaCasa);
		bancoDeTimes.adicionarTime(timeDeFora);

		assertEquals("Branco", desafio.buscarCorCamisaTimeDeFora(0L, 1L));
	}

	@Test
	public void testeCamisaDoTimeDeForaIguais() {
		Time timeDaCasa = new Time(0L, "novo time", LocalDate.now());
		timeDaCasa.setCorUniformePrincipal("Preto");
		Time timeDeFora = new Time(1L, "outro time", LocalDate.now());
		timeDeFora.setCorUniformePrincipal("Preto");
		timeDeFora.setCorUniformeSecundario("Branco");

		bancoDeTimes.adicionarTime(timeDaCasa);
		bancoDeTimes.adicionarTime(timeDeFora);

		assertEquals("Branco", desafio.buscarCorCamisaTimeDeFora(0L, 1L));
	}
}