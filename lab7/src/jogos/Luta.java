/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 7 - Turma 3 */
package jogos;

import java.util.Set;

import util.ExcecoesP2cg;
import enumerations.Jogabilidade;
import exceptions.SteamException;

/**
 * 
 * @author Luiz Fernando da Silva
 *
 */
public class Luta extends Jogo{
	
	private final int PONTOS_PARA_XP2 = 1000;

	/**
	 * Contrutor da classe jogo de luta
	 * 
	 * @param nome - recebe o nome de jogo
	 * @param preco - recebe o preco do jogo
	 * @param jogabilidades recebe as jogabilidades a serem adicionadas
	 * @throws SteamException - gera uma exception caso as entradas sejam invalidas
	 */
	public Luta(String nome, double preco, Set<Jogabilidade> jogabilidades)throws SteamException{
		super(nome, preco, jogabilidades);
	}

	/**
	 * Esse metodo sobrescreve o metodo registra jogada da classe Jogo (chamada pilomorfica)
	 */
	@Override
	public int registraJogada(int score, boolean zerou)throws SteamException{
		int xp2extra = 0;

		ExcecoesP2cg.verificaLimiteScore(score);
		super.registraJogada(score, zerou);
		
		xp2extra = score / PONTOS_PARA_XP2;

		return xp2extra;
	}

	/**
	 * Metodo toString que retorna um string contendo
	 * O nome e o toString da super classe jogo (chamada polimorfica)
	 */
	@Override
	public String toString(){
		final String FIM_DE_LINHA = System.lineSeparator();

		String saida = "+ " + super.getNome() + " - LUTA:" + FIM_DE_LINHA
				+ super.toString();
		return saida;
	}
}
