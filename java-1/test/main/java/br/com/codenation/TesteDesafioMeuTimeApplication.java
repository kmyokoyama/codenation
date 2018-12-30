package br.com.codenation;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class TesteDesafioMeuTimeApplication {

	@Test
	public void testIncluirTime() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		assertEquals(new Long(1), desafio.buscarCapitaoDoTime(0L));
	}

}
