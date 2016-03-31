/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package jogos;

import java.util.HashSet;
import java.util.Set;

import enumerations.Jogabilidade;
import exceptions.ConstanteException;
import exceptions.DadosInvalidosException;
import exceptions.ExcecoesP2cg;
import exceptions.SteamException;

/**
 * 
 * @author Luiz Fernando da Silva
 *
 */
public abstract class Jogo {

	private String nome;
	private double preco;
	private int maiorScore;
	private int quantidadeVezesJogadas;
	private int vezesZeradas;
	private Set<Jogabilidade> jogabilidades;

	/**
	 * Contrutor da classe jogo
	 * 
	 * @param nome - recebe o nome do jogo
	 * @param preco - recebe o preco do jogo
	 * @throws DadosInvalidosException - gera uma exception caso as entradas sejam invalidas
	 */
	public Jogo(String nome, double preco)throws DadosInvalidosException{
		ExcecoesP2cg.verificaNome(nome);
		ExcecoesP2cg.verificaPreco(preco);
		
		this.nome = nome;
		this.preco = preco;
		this.maiorScore = 0;
		this.quantidadeVezesJogadas = 0;
		this.vezesZeradas = 0;
		this.jogabilidades = new HashSet<Jogabilidade>();
	}

	/**
	 * Esse metodo registra uma jogada feita pelo usuario
	 * 
	 * @param score - recebe o score da jogada
	 * @param zerou - recebe um boolean indicando se o usuario zerou ou nao o jogo
	 * @return - retorna o xp2 que o usuario adiquiriu com a jogada
	 * @throws SteamException - gera uma exception caso as entradas sejam invalidas
	 */
	public int registraJogada(int score, boolean zerou)throws SteamException{

		ExcecoesP2cg.verificaScore(score);
		
		this.quantidadeVezesJogadas += 1;

		if(score > this.maiorScore){
			this.maiorScore = score;
		}
		if(zerou){
			this.vezesZeradas += 1;
		}
		
		int xp2extra = 0;
		return xp2extra;
	}
	
	/**
	 * Esse metodo adiciona jogabilidades ao jogo
	 * 
	 * @param jogabilidade - recebe a jogabilidade que sera adicionada
	 * @return - retorna um boolean indicando se o registro foi feito ou nao
	 * @throws ConstanteException - gera uma exception caso as entradas sejam invalidas
	 */
	public boolean adicionaJogabilidade(Jogabilidade jogabilidade)throws ConstanteException{
		
		ExcecoesP2cg.verificaJogabilidade(jogabilidade);
		return jogabilidades.add(jogabilidade);
	}

	/**
	 * Esse metodo retorna o nome do jogo
	 * 
	 * @return - retorna uma string indicando o nome do jogo
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Esse metodo retorna o preco do jogo
	 * 
	 * @return - retona o double indicando o preco do jogo
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * Esse metodo muda o preco do jogo
	 * 
	 * @param preco - recebe o novo preco
	 * @throws DadosInvalidosException - gera uma exception caso a entrada seja invalida
	 */
	public void setPreco(double preco) throws DadosInvalidosException{
		
		ExcecoesP2cg.verificaPreco(preco);
		this.preco = preco;
	}

	/**
	 * Esse metodo retorna o maior score alcancado pelo usuario
	 * 
	 * @return retorna um inteiro indicando o maior score alcancado
	 */
	public int getMaiorScore() {
		return maiorScore;
	}

	/**
	 * Esse metodo retona a quantidade de vezes que o jogo foi jogado
	 * 
	 * @return - retorna um inteiro indicando a quantidade de vezes jogadas
	 */
	public int getQuantidadeVezesJogadas() {
		return quantidadeVezesJogadas;
	}

	/**
	 * Esse metodo retorna q quantidade de vezes que o jogo foi zerado
	 * 
	 * @return - retorna um int indicando a quantidade de vezes que o jogo foi zerado
	 */
	public int getVezesZeradas() {
		return vezesZeradas;
	}

	/**
	 * Metodo hash code que determina se dois jogos sao iguais pelo nome
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	/**
	 * Metodo que determina se dois jogos sao iguais atraaves do nome
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogo other = (Jogo) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Double.doubleToLongBits(preco) != Double.doubleToLongBits(other.preco))
			return false;
		return true;
	}

	/**
	 * Metodo toString que rotorna uma intring contendo a quantidade de vezes que o jogo foi jogado
	 * A quantidade de vezes que foi zerado
	 * E o maior score
	 */
	@Override
	public String toString(){
		final String FIM_DE_LINHA = System.lineSeparator();
		
		String saida = "==> Jogou " + getQuantidadeVezesJogadas() + " vez(es)" + FIM_DE_LINHA
					 + "==> Zerou " + getVezesZeradas() + " vez(es)" + FIM_DE_LINHA
					 + "==> Maior Score: " + getMaiorScore() + FIM_DE_LINHA;

		return saida;
	}
}
