package factory;

import java.util.List;

import enumerations.Jogabilidade;
import enumerations.TipoDeJogo;
import exceptions.ConstanteException;
import exceptions.SteamException;
import jogos.Jogo;
import jogos.Luta;
import jogos.Plataforma;
import jogos.Rpg;

public class JogoFactory {
	
	public Jogo criaJogo(String nomeJogo, double preco, TipoDeJogo tipo, List<Jogabilidade> jogabilidades)throws SteamException{
		
		switch(tipo){
		
		case RPG:
			Jogo jogoRpg = new Rpg(nomeJogo, preco);
			adicionaJogabilidade(jogoRpg, jogabilidades);
			return jogoRpg;
			
		case PLATAFORMA:
			Jogo jogoPlataforma = new Plataforma(nomeJogo, preco);
			adicionaJogabilidade(jogoPlataforma, jogabilidades);
			return jogoPlataforma;
			
		case LUTA:
			Jogo jogoLuta = new Luta(nomeJogo, preco);
			adicionaJogabilidade(jogoLuta, jogabilidades);
			return jogoLuta;
			
		default:
			throw new ConstanteException("Tipo de jogo inexistente!");
		}
	}
	
	private void adicionaJogabilidade(Jogo jogo, List<Jogabilidade> jogabilidades)throws ConstanteException{
		
		for(Jogabilidade jogabilidade : jogabilidades){
			if(jogabilidade != null){
				jogo.adicionaJogabilidade(jogabilidade);
			}
		}
	}
}
