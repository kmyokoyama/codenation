package br.com.codenation.desafio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class BancoDeTimes {
	private static BancoDeTimes instance;
	private Map<Long, Time> times;
	
    static{
        try{
            instance = new BancoDeTimes();
        }catch(Exception e){
            throw new RuntimeException("Não foi possível criar uma instância do banco de times");
        }
    }
    
    private BancoDeTimes(){
    	this.times = new HashMap<>();
    }
    
    public static BancoDeTimes getInstance(){
        return instance;
    }
	
	public void adicionarTime(Time novoTime) {
		if (this.times.containsKey(novoTime.getID())) {
			throw new IdentificadorUtilizadoException();
		}
		
		this.times.put(novoTime.getID(), novoTime);
	}
	
	public Time getTime(Long idTime) {
		if (this.times.containsKey(idTime)) {
			return this.times.get(idTime);
		}
		
		throw new TimeNaoEncontradoException();
	}
	
	public List<Long> buscaTimes() {
		return this.times
				.keySet()
				.stream()
				.sorted((o1, o2) -> o1.compareTo(o2))
				.collect(Collectors.toList());
	}

	public void limpaTodos() {
		this.times = new HashMap<>();
	}
}
