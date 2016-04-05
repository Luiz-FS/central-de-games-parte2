/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package banco.dados;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import exceptions.LogicaException;
import jogos.Jogo;

/**
 * 
 * @author Luiz Fernando da Silva
 *
 */
public class JogosComprados {
	
	private Set<Jogo> jogos;
	
	/**
	 * Contrutor da classe jogos comprados
	 */
	public JogosComprados(){
		
		jogos = new HashSet<Jogo>();
	}
	
	/**
	 * Esse metodo adiciona um jogo a lista de jogos comprados
	 * 
	 * @param jogo - recebe o jogo que será adicionado
	 * @return - retorna um boolean indicando se o jogo foi adicionado ou nao
	 */
	public boolean adicionaJogo(Jogo jogo){
		return jogos.add(jogo);
	}
	
	/**
	 * Esse metodo verifica se um jogo existe na lista de jogos
	 * 
	 * @param nomeJogo - recebe o nome do jogo
	 * @return - retorna um boolean indicando se o jogo existe ou nao
	 */
	public boolean containJogo(String nomeJogo){
		
		for(Jogo jogo : jogos){
			
			if(jogo.getNome().equalsIgnoreCase(nomeJogo)){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Esse metodo verifica se um jogo existe na lista de jogos atraves de um obejeto jogo
	 * passado como parametro
	 * 
	 * @param nomeJogo - recebe o nome do jogo
	 * @return - retorna um boolean indicando se o jogo existe ou nao
	 */
	public boolean containJogo(Jogo jogo){
		return this.jogos.contains(jogo);
	}
	
	/**
	 * Esse metodo retorna um jogo(caso exista)
	 * 
	 * @param nomeJogo -  recebe o nome do jogo
	 * @return - retorna o jogo caso exista
	 * @throws LogicaException - gera uma exception caso o jogo nao exista
	 */
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
	
	/**
	 * Esse metodo calcula o total de precos dos jogos
	 * 
	 * @return - retorna um double indicando o preco total dos jogos
	 */
	public double totalPrecoJogos(){
		
		double totalPreco = 0;
		
		for(Jogo jogo : jogos){
			totalPreco += jogo.getPreco();
		}
		
		return totalPreco;
	}
	
	/**
	 * Esse metodo retorna uma string mostrando todos os jogos comprados
	 */
	@Override
	public String toString(){

		final String FIM_DE_LINHA = System.lineSeparator();
		DecimalFormat df = new DecimalFormat("0.00");

		String saida = "Lista de jogos:" + FIM_DE_LINHA;

		for(Jogo jogo : this.jogos){

			saida += jogo + FIM_DE_LINHA
					+ "Total de preco dos jogos: R$ " + df.format(totalPrecoJogos())
					+ FIM_DE_LINHA + FIM_DE_LINHA
					+ "--------------------------------------------------------------------"
					+ FIM_DE_LINHA;
		}
		return saida;
	}
}
