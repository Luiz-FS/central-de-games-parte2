/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package jogos;

import exceptions.DadosInvalidosException;
import exceptions.SteamException;

/**
 * 
 * @author Luiz Fernando da Silva
 *
 */
public class Rpg extends Jogo{

	private final int XP2EXTRA = 10;
	
	/**
	 * Contrutor da classe jogo de Rpg
	 * 
	 * @param nome - recebe o nome do jogo
	 * @param preco - recebe o preco do jogo
	 * @throws DadosInvalidosException - gera uma exception caso as entradas sejam invalidas
	 */
	public Rpg(String nome, double preco)throws DadosInvalidosException {
		super(nome, preco);
	}
	
	/**
	 * Esse metodo sobrescreve o metodo registra jogada da super classe jogo
	 */
	@Override
	public int registraJogada(int score, boolean zerou)throws SteamException{
		super.registraJogada(score, zerou);
		
		return XP2EXTRA;
	}
	
	/**
	 * Metodo toString que retorna um string contendo
	 * O nome e o toString da super classe jogo
	 */
	@Override
	public String toString(){
		final String FIM_DE_LINHA = System.lineSeparator();
		
		String saida = "+ " + super.getNome() + " - RPG:" + FIM_DE_LINHA
						+ super.toString();
		return saida;
	}
}