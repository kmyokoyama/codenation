package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.codenation.desafio.BancoDeJogadores;
import br.com.codenation.desafio.BancoDeTimes;
import br.com.codenation.desafio.Jogador;
import br.com.codenation.desafio.Time;
import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	
	BancoDeTimes bancoDeTimes = new BancoDeTimes();
	BancoDeJogadores bancoDeJogadores = new BancoDeJogadores();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario) {
		Time novoTime = new Time(id, nome, dataCriacao);
		novoTime.setCorUniformePrincipal(corUniformePrincipal);
		novoTime.setCorUniformeSecundario(corUniformeSecundario);

		bancoDeTimes.adicionarTime(novoTime);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
			BigDecimal salario) {
		Jogador novoJogador = new Jogador(id, nome, dataNascimento);
		novoJogador.setNivelHabilidade(nivelHabilidade);
		novoJogador.setSalario(salario);

		bancoDeJogadores.adicionaJogador(novoJogador);

		Time timeDoJogador = bancoDeTimes.getTime(idTime);
		novoJogador.setTime(timeDoJogador.getID());

		timeDoJogador.adicionaJogador(novoJogador);
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador novoCapitao = bancoDeJogadores.getJogador(idJogador);
		Time timeDoNovoCapitao = bancoDeTimes.getTime(novoCapitao.getTimeID());

		timeDoNovoCapitao.setCapitao(novoCapitao.getID());
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Time time = bancoDeTimes.getTime(idTime);

		return time.getCapitao();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		return bancoDeJogadores.getJogador(idJogador).getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		return bancoDeTimes.getTime(idTime).getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		Time time = bancoDeTimes.getTime(idTime);

		return time.buscaJogadores();
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		Time time = bancoDeTimes.getTime(idTime);

		return time.buscaMelhorJogador();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		Time time = bancoDeTimes.getTime(idTime);

		return time.buscaJogadorMaisVelho();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return bancoDeTimes.buscaTimes();
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		Time time = bancoDeTimes.getTime(idTime);

		return time.buscaJogadorMaiorSalario();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return bancoDeJogadores.getJogador(idJogador).getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		return bancoDeJogadores.buscaTopJogadores(top);
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Time timeCasa = bancoDeTimes.getTime(timeDaCasa);
		Time timeFora = bancoDeTimes.getTime(timeDeFora);

		if (timeCasa.getCorUniformePrincipal() == timeFora.getCorUniformePrincipal()) {
			return timeFora.getCorUniformeSecundario();
		}

		return timeFora.getCorUniformePrincipal();
	}

}
