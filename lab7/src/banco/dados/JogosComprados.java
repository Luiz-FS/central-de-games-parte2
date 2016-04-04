package banco.dados;

import java.util.HashSet;
import java.util.Set;

import exceptions.LogicaException;
import jogos.Jogo;

public class JogosComprados {
	
	private Set<Jogo> jogos;
	
	public JogosComprados(){
		
		jogos = new HashSet<Jogo>();
	}
	
	public boolean adicionaJogo(Jogo jogo){
		return jogos.add(jogo);
	}
	
	public boolean containJogo(String nomeJogo){
		
		for(Jogo jogo : jogos){
			
			if(jogo.getNome().equalsIgnoreCase(nomeJogo)){
				return true;
			}
		}
		
		return false;
	}
	
	public Jogo pegaJogo(String nomeJogo)throws LogicaException{
		
		if(containJogo(nomeJogo)){
			
			for(Jogo jogo : jogos){
				
				if(jogo.getNome().equalsIgnoreCase(nomeJogo)){
					return jogo;
				}
			}
		}
		
		throw new LogicaException("Jogo nao existe!");
	}
}
