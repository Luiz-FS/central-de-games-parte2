/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 7 - Turma 3 */
package jogos;

import java.util.Set;

import enumerations.Jogabilidade;
import exceptions.SteamException;

/**
 * 
 * @author Luiz Fernando da Silva
 *
 */
public class Plataforma extends Jogo{

	private final int XP2EXTRA = 20;
	
	/**
	 * Construtor da classe jogo de plataforma
	 * 
	 * @param nome - recebe o nome do jogo
	 * @param preco - recebe o preco do jogo
	 * @param jogabilidades recebe as jogabilidades a serem adicionadas
	 * @throws SteamException - gera uma exception caso as entradas sejam invalidas
	 */
	public Plataforma(String nome, double preco, Set<Jogabilidade> jogabilidades)throws SteamException {
		super(nome, preco, jogabilidades);
	}
	
	/**
	 * Esse metodo sobrescreve a classe registra jogada da super classe jogo (chamada polimorfica)
	 */
	@Override
	public int registraJogada(int score, boolean zerou)throws SteamException{
		super.registraJogada(score, zerou);
		
		if(zerou){
			return XP2EXTRA;
			
		} else{
			int xp2 = 0;
			return xp2;
		}
	}
	
	/**
	 * Metodo toString que retorna um string contendo
	 * O nome e o toString da super classe jogo (chamada plimorfica)
	 */
	@Override
	public String toString(){
		final String FIM_DE_LINHA = System.lineSeparator();
		
		String saida = "+ " + super.getNome() + " - PLATAFORMA:" + FIM_DE_LINHA
						+ super.toString();
		return saida;
	}
}
