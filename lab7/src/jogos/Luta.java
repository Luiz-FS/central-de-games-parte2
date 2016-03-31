/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package jogos;

import exceptions.DadosInvalidosException;
import exceptions.ExcecoesP2cg;
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
	 * @param preco - receb o preco do jogo
	 * @throws DadosInvalidosException - gera uma exception caso as entradas sejam invalidas
	 */
	public Luta(String nome, double preco)throws DadosInvalidosException{
		super(nome, preco);
	}

	/**
	 * Esse metodo sobrescreve o metodo registra jogada da classe Jogo
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
	 * O nome e o toString da super classe jogo
	 */
	@Override
	public String toString(){
		final String FIM_DE_LINHA = System.lineSeparator();

		String saida = "+ " + super.getNome() + " - LUTA:" + FIM_DE_LINHA
				+ super.toString();
		return saida;
	}
}
