/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package usuarios;

import exceptions.NumeroInvalidoException;
import exceptions.ObjetoinvalidoException;
import jogos.Jogo;

/**
 * 
 * @author Luiz Fernando da Silva
 *
 */
public interface TipoUsuarioIF {
	
	/**
	 * Esse eh um metodo abstrato de comprar jogo
	 * 
	 * @param jogo recebe o nome do jogo
	 * @return retorna um double indicando o valor do desconto do jogo
	 * @throws ObjetoinvalidoException - gera umas exception caso a entrada seja invalida
	 */
	public double comprar(Jogo jogo) throws ObjetoinvalidoException;
	
	/**
	 * Esse eh um metodo abstrato de recompensar o usuario
	 * 
	 * @param jogo - recebe o jogo
	 * @return - retorna a recompensa do usuario
	 * @throws ObjetoinvalidoException - gera uma exception caso a entrada seja invalida
	 */
	public int recompensar(Jogo jogo)throws ObjetoinvalidoException;
	
	/**
	 * Esse eh um metodo abstrato de punir o usuario
	 * 
	 * @param jogo - recebe o jogo
	 * @return - retorna a punicao do usuario
	 * @throws ObjetoinvalidoException - gera uma exception caso a entrada seja invalida
	 */
	public int punir(Jogo jogo)throws ObjetoinvalidoException;
	
	/**
	 * Esse eh um metodo abstrato que calcula o xp2 que o usuario vai receber na hora da compra
	 * 
	 * @param precoJogo - recebe o preco do jogo
	 * @return - retorna o xp2 que sera adicionado ao usuario
	 * @throws NumeroInvalidoException - gera uma exception caso o valor seja invalido
	 */
	public int calculaXp2Compra(double precoJogo)throws NumeroInvalidoException;
}
