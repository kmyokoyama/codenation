package br.com.codenation;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.codenation.desafio.BancoDeJogadores;
import br.com.codenation.desafio.Jogador;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;

public class TesteBancoDeJogadores {
	private static BancoDeJogadores banco;
	
	@Before
	public void setUp() {
		banco = BancoDeJogadores.getInstance();
	}

	@After
	public void tearDown() {
		banco.limpaTodos();
	}
	
	@Test
	public void testeAdicionaNovoJogador() {
		Jogador novoJogador = new Jogador(0L, "Novo Jogador", LocalDate.now());
		novoJogador.setNivelHabilidade(90);
		novoJogador.setSalario(new BigDecimal(100000));
		
		banco.adicionaJogador(novoJogador);
		
		Jogador gotJogador = banco.getJogador(0L);
		
		assertEquals(gotJogador, novoJogador);
	}
	
	@Test
	public void testeAdicionaOutroJogador() {
		Jogador novoJogador = new Jogador(0L, "Novo Jogador", LocalDate.now());
		novoJogador.setNivelHabilidade(90);
		novoJogador.setSalario(new BigDecimal(100000));
		
		Jogador outroJogador = new Jogador(1L, "Outro Jogador", LocalDate.now());
		outroJogador.setNivelHabilidade(80);
		outroJogador.setSalario(new BigDecimal(70000));
		
		banco.adicionaJogador(novoJogador);
		banco.adicionaJogador(outroJogador);
		
		Jogador gotNovoJogador = banco.getJogador(0L);
		Jogador gotOutroJogador = banco.getJogador(1L);
		
		assertEquals(gotNovoJogador, novoJogador);
		assertEquals(gotOutroJogador, outroJogador);
	}
	
	@Test(expected = IdentificadorUtilizadoException.class)
	public void testeAdicionaOutroJogadorMesmoID() {
		Jogador novoJogador = new Jogador(0L, "Novo Jogador", LocalDate.now());
		novoJogador.setNivelHabilidade(90);
		novoJogador.setSalario(new BigDecimal(100000));
		
		Jogador outroJogador = new Jogador(0L, "Outro Jogador", LocalDate.now());
		outroJogador.setNivelHabilidade(80);
		outroJogador.setSalario(new BigDecimal(70000));
		
		banco.adicionaJogador(novoJogador);
		banco.adicionaJogador(outroJogador);
	}
	
	@Test(expected = JogadorNaoEncontradoException.class)
	public void testeBuscaPorIDInexistente() {
		banco.getJogador(0L);
	}
}
