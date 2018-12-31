package br.com.codenation.desafio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;

public class BancoDeJogadores {
	private static BancoDeJogadores instance;
	private Map<Long, Jogador> jogadores;
	
    static{
        try{
            instance = new BancoDeJogadores();
        }catch(Exception e){
            throw new RuntimeException("Não foi possível criar uma instância do banco de Jogadores");
        }
    }
    
    private BancoDeJogadores() {
    	this.jogadores = new HashMap<>();
    }
    
    public static BancoDeJogadores getInstance() {
        return instance;
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

	public void limpaTodos() {
		this.jogadores = new HashMap<>();
	}
	
	public List<Long> buscaTopJogadores(int top) {
		int numeroDeJogadores = this.jogadores.size();
		
		return this.jogadores
				.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue((jog1, jog2) -> jog1.comparaNivelHabilidade(jog2)))
				.map(e -> e.getKey())
				.skip(numeroDeJogadores >= top ? numeroDeJogadores-top : 0)
				.collect(Collectors.toList());
	}
}
