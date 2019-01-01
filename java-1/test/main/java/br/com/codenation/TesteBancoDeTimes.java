package br.com.codenation;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.codenation.desafio.BancoDeTimes;
import br.com.codenation.desafio.Time;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class TesteBancoDeTimes {
	private static BancoDeTimes banco;
	
	@Before
	public void setUp() {
		banco = new BancoDeTimes();
	}
	
	@After
	public void tearDown() {
		banco.limpaTodos();
	}

	@Test
	public void testeAdicionaNovoTime() {
		Time novoTime = new Time(0L, "Novo Time", LocalDate.now());
		novoTime.setCorUniformePrincipal("Branco");
		novoTime.setCorUniformeSecundario("Preto");

		banco.adicionarTime(novoTime);

		Time gotTime = banco.getTime(0L);

		assertEquals(gotTime, novoTime);
	}

	@Test
	public void testeAdicionaOutroTime() {
		Time novoTime = new Time(0L, "Novo Time", LocalDate.now());
		novoTime.setCorUniformePrincipal("Branco");
		novoTime.setCorUniformeSecundario("Preto");

		Time outroTime = new Time(1L, "Outro Time", LocalDate.now());
		novoTime.setCorUniformePrincipal("Azul");
		novoTime.setCorUniformeSecundario("Amarelo");

		banco.adicionarTime(novoTime);
		banco.adicionarTime(outroTime);

		Time gotNovoTime = banco.getTime(0L);
		Time gotOutroTime = banco.getTime(1L);

		assertEquals(gotNovoTime, novoTime);
		assertEquals(gotOutroTime, outroTime);
	}

	@Test(expected = IdentificadorUtilizadoException.class)
	public void testeAdicionaOutroTimeMesmoID() {
		Time novoTime = new Time(0L, "Novo Time", LocalDate.now());
		novoTime.setCorUniformePrincipal("Branco");
		novoTime.setCorUniformeSecundario("Preto");

		Time outroTime = new Time(0L, "Outro Time", LocalDate.now());
		novoTime.setCorUniformePrincipal("Azul");
		novoTime.setCorUniformeSecundario("Amarelo");

		banco.adicionarTime(novoTime);
		banco.adicionarTime(outroTime);
	}

	@Test(expected = TimeNaoEncontradoException.class)
	public void testeBuscaPorIDInexistente() {
		banco.getTime(0L);
	}
}
