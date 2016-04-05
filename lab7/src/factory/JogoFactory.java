/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 7 - Turma 3 */
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
	
	/**
	 * Esse metodo cria um determinado tipo de jogo de acordo com o especificado no parametro
	 * 
	 * @param nomeJogo - recebe o nome do jogo
	 * @param preco - recebe o preco do jogo
	 * @param tipo - receb o tipo do jogo
	 * @param jogabilidades - recebe as jogabilidades que serao adiciondads
	 * @return - retorna o jogo criado
	 * @throws SteamException - gera umas exception caso as entradas sejam invalidas ou o tipo nao exista
	 */
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
	
	/**
	 * Esse metodo cria um jogo de rpg
	 * 
	 * @param nomeJogo - recebe o nome do jogo
	 * @param preco - recebe o preco do jogo
	 * @param jogabilidades - recebe as jogabilidades a serem adicionadas
	 * @return - retorna o jogo criado
	 * @throws DadosInvalidosException - gera umas exception caso as entradas sejam invalidas
	 */
	public Jogo criaJogoRpg(String nomeJogo, double preco, List<Jogabilidade> jogabilidades)throws DadosInvalidosException{
		Jogo jogo = new Rpg(nomeJogo, preco, jogabilidades);
		
		return jogo;
	}
	
	/**
	 * Esse metodo cria um jogo de luta
	 * 
	 * @param nomeJogo - recebe o nome do jogo
	 * @param preco - recebe o preco do jogo
	 * @param jogabilidades - recebe as jogabilidades a serem adicionadas
	 * @return - retorna o jogo criado
	 * @throws DadosInvalidosException - gera umas exception caso as entradas sejam invalidas
	 */
	public Jogo criaJogoLuta(String nomeJogo, double preco, List<Jogabilidade> jogabilidades)throws DadosInvalidosException{
		Jogo jogo = new Luta(nomeJogo, preco, jogabilidades);
		
		return jogo;
	}
	
	/**
	 * Esse metodo cria um jogo de plataforma
	 * 
	 * @param nomeJogo - recebe o nome do jogo
	 * @param preco - recebe o preco do jogo
	 * @param jogabilidades - recebe as jogabilidades a serem adicionadas
	 * @return - retorna o jogo criado
	 * @throws DadosInvalidosException - gera umas exception caso as entradas sejam invalidas
	 */
	public Jogo criaJogoPlataforma(String nomeJogo, double preco, List<Jogabilidade> jogabilidades)throws DadosInvalidosException{
		Jogo jogo = new Plataforma(nomeJogo, preco, jogabilidades);
		
		return jogo;
	}
}
