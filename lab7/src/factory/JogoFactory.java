package factory;

import java.util.List;

import enumerations.Jogabilidade;
import enumerations.TipoDeJogo;
import exceptions.ConstanteException;
import exceptions.DadosInvalidosException;
import exceptions.SteamException;
import jogos.Jogo;
import jogos.Luta;
import jogos.Plataforma;
import jogos.Rpg;

public class JogoFactory {
	
	public Jogo criaJogo(String nomeJogo, double preco, TipoDeJogo tipo, List<Jogabilidade> jogabilidades)throws SteamException{
		
		switch(tipo){
		
		case RPG:
			Jogo jogoRpg = criaJogoRpg(nomeJogo, preco, jogabilidades);
			return jogoRpg;
			
		case PLATAFORMA:
			Jogo jogoPlataforma = criaJogoPlataforma(nomeJogo, preco, jogabilidades);
			return jogoPlataforma;
			
		case LUTA:
			Jogo jogoLuta = criaJogoLuta(nomeJogo, preco, jogabilidades);
			return jogoLuta;
			
		default:
			throw new ConstanteException("Tipo de jogo inexistente!");
		}
	}
	
	public Jogo criaJogoRpg(String nomeJogo, double preco, List<Jogabilidade> jogabilidades)throws DadosInvalidosException{
		Jogo jogo = new Rpg(nomeJogo, preco, jogabilidades);
		
		return jogo;
	}
	
	public Jogo criaJogoLuta(String nomeJogo, double preco, List<Jogabilidade> jogabilidades)throws DadosInvalidosException{
		Jogo jogo = new Luta(nomeJogo, preco, jogabilidades);
		
		return jogo;
	}
	
	public Jogo criaJogoPlataforma(String nomeJogo, double preco, List<Jogabilidade> jogabilidades)throws DadosInvalidosException{
		Jogo jogo = new Plataforma(nomeJogo, preco, jogabilidades);
		
		return jogo;
	}
}
