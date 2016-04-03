package banco.dados;

import java.util.HashSet;
import java.util.Set;

import jogos.Jogo;

public class JogosComprados {
	
	private Set<Jogo> jogos;
	
	public JogosComprados(){
		
		jogos = new HashSet<Jogo>();
	}
	
	public boolean adicionaJogo(Jogo jogo){
		return jogos.add(jogo);
	}
	
	private boolean containJogo(Jogo jogo){
		return jogos.contains(jogo);
	}
}
