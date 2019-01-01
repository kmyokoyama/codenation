package br.com.codenation;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;

public class TesteDesafio {

	private static DesafioMeuTimeApplication desafio;

	@Before
	public void setUp() {
		desafio = new DesafioMeuTimeApplication();
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
		desafio.incluirTime(0L, "novo time", LocalDate.now(), "Branco", "Preto");
		desafio.incluirTime(1L, "outro time", LocalDate.now(), "Preto", "Branco");

		desafio.incluirJogador(0L, 0L, "novo jogador", LocalDate.now(), 90, new BigDecimal(100000));
		desafio.incluirJogador(0L, 1L, "outro jogador", LocalDate.now(), 80, new BigDecimal(70000));
	}

	@Test
	public void testeDefinirCapitao() {
		desafio.incluirTime(0L, "novo time", LocalDate.now(), "Branco", "Preto");

		desafio.incluirJogador(0L, 0L, "novo jogador", LocalDate.now(), 90, new BigDecimal(100000));
		
		desafio.definirCapitao(0L);
	}

	@Test(expected = JogadorNaoEncontradoException.class)
	public void testeDefineCapitaoInexistente() {
		desafio.definirCapitao(0L);
	}

	@Test
	public void testeBuscaCapitao() {
		desafio.incluirTime(0L, "novo time", LocalDate.now(), "Branco", "Preto");
		desafio.incluirJogador(0L, 0L, "novo jogador", LocalDate.now(), 90, new BigDecimal(100000));

		desafio.definirCapitao(0L);

		assertEquals(new Long(0), desafio.buscarCapitaoDoTime(0L));
	}

	@Test(expected = TimeNaoEncontradoException.class)
	public void testeBuscaCapitaoEmTimeInexistente() {
		desafio.buscarCapitaoDoTime(0L);
	}

	@Test(expected = CapitaoNaoInformadoException.class)
	public void testeDefineCapitaoComJogadorInexistente() {
		desafio.incluirTime(0L, "novo time", LocalDate.now(), "Branco", "Preto");

		desafio.buscarCapitaoDoTime(0L);
	}

	@Test
	public void testeBuscaNomeDoJogador() {
		desafio.incluirTime(0L, "novo time", LocalDate.now(), "Branco", "Preto");
		desafio.incluirJogador(0L, 0L, "novo jogador", LocalDate.now(), new Integer(90), new BigDecimal(100000));

		String nomeDoJogador = desafio.buscarNomeJogador(0L);

		assertEquals("novo jogador", nomeDoJogador);
	}

	@Test(expected = JogadorNaoEncontradoException.class)
	public void testeBuscaNomeDeJogadorInexistente() {
		desafio.buscarNomeJogador(0L);
	}

	@Test
	public void testeBuscaNomeDoTime() {
		desafio.incluirTime(0L, "novo time", LocalDate.now(), "Branco", "Preto");

		String nomeDoTime = desafio.buscarNomeTime(0L);

		assertEquals("novo time", nomeDoTime);
	}

	@Test(expected = TimeNaoEncontradoException.class)
	public void testeBuscaNomeDeTimeInexistente() {
		desafio.buscarNomeTime(0L);
	}

	@Test
	public void testeBuscaJogadoresDoTime() {
		desafio.incluirTime(0L, "novo time", LocalDate.now(), "Branco", "Preto");

		List<Long> indices = new ArrayList<>();

		int tamanhoDoTime = 5;
		for (int i = 0; i < tamanhoDoTime; i++) {
			desafio.incluirJogador(new Long(i), 0L, "jogador" + i, LocalDate.now(), new Integer(90), new BigDecimal(100000));
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
		desafio.incluirTime(0L, "novo time", LocalDate.now(), "Branco", "Preto");

		int tamanhoDoTime = 5;
		for (int i = 0; i < tamanhoDoTime; i++) {
			desafio.incluirJogador(new Long(i), 0L, "jogador" + i, LocalDate.now(), new Integer(100 - 10 * i), new BigDecimal(100000));
		}

		assertEquals(new Long(0), desafio.buscarMelhorJogadorDoTime(0L));
	}

	@Test(expected = TimeNaoEncontradoException.class)
	public void testeBuscaMelhorJogadorDeTimeInexistente() {
		desafio.buscarMelhorJogadorDoTime(0L);
	}

	@Test
	public void testeBuscaJogadorMaisVelhoDoTime() {
		desafio.incluirTime(0L, "novo time", LocalDate.now(), "Branco", "Preto");

		desafio.incluirJogador(0L, 0L, "jogador 1", LocalDate.of(1991, 01, 01), new Integer(100), new BigDecimal(100000));
		desafio.incluirJogador(1L, 0L, "jogador 2", LocalDate.of(1992, 01, 05), new Integer(100), new BigDecimal(100000));
		desafio.incluirJogador(2L, 0L, "jogador 3", LocalDate.of(1990, 10, 01), new Integer(100), new BigDecimal(100000));
		desafio.incluirJogador(3L, 0L, "jogador 4", LocalDate.of(1990, 10, 01), new Integer(100), new BigDecimal(100000));
		desafio.incluirJogador(4L, 0L, "jogador 5", LocalDate.of(1992, 01, 05), new Integer(100), new BigDecimal(100000));

		assertEquals(new Long(2), desafio.buscarJogadorMaisVelho(0L));
	}

	@Test(expected = TimeNaoEncontradoException.class)
	public void testeBuscaJogadorMaisVelhoDeTimeInexistente() {
		desafio.buscarJogadorMaisVelho(0L);
	}

	@Test
	public void testeBuscarTimes() {
		List<Long> indices = new ArrayList<>();

		int numeroTimes = 5;
		for (int i = 0; i < numeroTimes; i++) {
			desafio.incluirTime(new Long(i), "time" + i, LocalDate.now(), "Branco", "Preto");
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
		desafio.incluirTime(0L, "novo time", LocalDate.now(), "Branco", "Preto");

		int tamanhoDoTime = 5;
		for (int i = 0; i < tamanhoDoTime; i++) {
			desafio.incluirJogador(
				new Long(i), 0L, "jogador" + i, LocalDate.now(),
				new Integer(100), new BigDecimal(100000 - 1000 * i)
			);
		}

		assertEquals(new Long(0), desafio.buscarJogadorMaiorSalario(0L));
	}

	@Test
	public void testeBuscaJogadorMaiorSalarioComEmpate() {
		desafio.incluirTime(0L, "novo time", LocalDate.now(), "Branco", "Preto");

		desafio.incluirJogador(0L, 0L, "jogador 1", LocalDate.now(), new Integer(100), new BigDecimal(100000));
		desafio.incluirJogador(1L, 0L, "jogador 2", LocalDate.now(), new Integer(100), new BigDecimal(110000));
		desafio.incluirJogador(2L, 0L, "jogador 3", LocalDate.now(), new Integer(100), new BigDecimal(110000));

		assertEquals(new Long(1), desafio.buscarJogadorMaiorSalario(0L));
	}

	@Test(expected = TimeNaoEncontradoException.class)
	public void testeBuscaJogadorMaiorSalarioDeTimeInexistente() {
		desafio.buscarMelhorJogadorDoTime(0L);
	}

	@Test
	public void testeBuscaSalarioDoJogador() {
		desafio.incluirTime(0L, "novo time", LocalDate.now(), "Branco", "Preto");
		desafio.incluirJogador(0L, 0L, "novo jogador", LocalDate.now(), new Integer(100), new BigDecimal(100000));

		BigDecimal salarioDoJogador = desafio.buscarSalarioDoJogador(0L);

		assertEquals(new BigDecimal(100000), salarioDoJogador);
	}

	@Test(expected = JogadorNaoEncontradoException.class)
	public void testeBuscaSalarioDeJogadorInexistente() {
		desafio.buscarSalarioDoJogador(0L);
	}

	@Test
	public void testeBuscaTopJogadores() {
		desafio.incluirTime(0L, "novo time", LocalDate.now(), "Branco", "Preto");
		
		desafio.incluirJogador(0L, 0L, "jogador 1", LocalDate.now(), new Integer(100), new BigDecimal(100000));
		desafio.incluirJogador(1L, 0L, "jogador 2", LocalDate.now(), new Integer(90), new BigDecimal(100000));
		desafio.incluirJogador(2L, 0L, "jogador 3", LocalDate.now(), new Integer(100), new BigDecimal(100000));
		
		assertEquals(Arrays.asList(0L, 2L), desafio.buscarTopJogadores(2));
	}

	@Test
	public void testeBuscarTopJogadoresVazio() {
		assertTrue(desafio.buscarTopJogadores(10).isEmpty());
	}

	@Test
	public void testeCamisaDoTimeDeForaDiferentes() {
		desafio.incluirTime(0L, "novo time", LocalDate.now(), "Preto", "Branco");
		desafio.incluirTime(1L, "outro time", LocalDate.now(), "Branco", "Preto");
		
		assertEquals("Branco", desafio.buscarCorCamisaTimeDeFora(0L, 1L));
	}

	@Test
	public void testeCamisaDoTimeDeForaIguais() {
		desafio.incluirTime(0L, "novo time", LocalDate.now(), "Preto", "Branco");
		desafio.incluirTime(1L, "outro time", LocalDate.now(), "Preto", "Branco");
		
		assertEquals("Branco", desafio.buscarCorCamisaTimeDeFora(0L, 1L));
	}
}