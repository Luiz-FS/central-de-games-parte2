package banco.dados;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import exceptions.LogicaException;
import jogos.Jogo;

public class JogosComprados {
	
	private Set<Jogo> jogos;
	
	public JogosComprados(){
		
		jogos = new HashSet<Jogo>();
	}
	
	public boolean adicionaJogo(Jogo jogo){
		return jogos.add(jogo);
	}
	
	public boolean containJogo(String nomeJogo){
		
		for(Jogo jogo : jogos){
			
			if(jogo.getNome().equalsIgnoreCase(nomeJogo)){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean containJogo(Jogo jogo){
		return this.jogos.contains(jogo);
	}
	
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
	
	public double totalPrecoJogos(){
		
		double totalPreco = 0;
		
		for(Jogo jogo : jogos){
			totalPreco += jogo.getPreco();
		}
		
		return totalPreco;
	}
	
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
