package factory;

import enumerations.TipoDeJogo;
import exceptions.ConstanteException;
import exceptions.SteamException;
import jogos.Jogo;
import jogos.Luta;
import jogos.Plataforma;
import jogos.Rpg;

public class JogoFactory {
	
	public Jogo criaJogo(String nomeJogo, double preco, TipoDeJogo tipo)throws SteamException{
		
		switch(tipo){
		
		case RPG:
			Jogo jogoRpg = new Rpg(nomeJogo, preco);
			return jogoRpg;
			
		case PLATAFORMA:
			Jogo jogoPlataforma = new Plataforma(nomeJogo, preco);
			return jogoPlataforma;
			
		case LUTA:
			Jogo jogoLuta = new Luta(nomeJogo, preco);
			return jogoLuta;
			
		default:
			throw new ConstanteException("Tipo de jogo inexistente!");
		}
	}
}
