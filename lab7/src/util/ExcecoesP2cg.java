/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package util;

import enumerations.ExperienciaUsuario;
import enumerations.Jogabilidade;
import exceptions.ConstanteException;
import exceptions.LogicaException;
import exceptions.NumeroInvalidoException;
import exceptions.ObjetoinvalidoException;
import exceptions.StringException;
import jogos.Jogo;
import usuarios.TipoUsuarioIF;
import usuarios.Usuario;

public class ExcecoesP2cg {

	/**
	 * Esse metodo verifica se o nome pasado eh invalido
	 * 
	 * @param nome - recebe o nome
	 * @throws StringException - gera uma exception caso o nome seja invalido
	 */
	public static void verificaNome(String nome)throws StringException{

		if(nome == null || nome.trim().isEmpty()){
			throw new StringException("Nome nao pode ser nulo ou vazio");
		}
	}

	/**
	 * Esse metodo verifica o preco passado
	 * 
	 * @param preco - recebe o preco
	 * @throws NumeroInvalidoException - gera uma exception caso o valor numerico seja invalido
	 */
	public static void verificaPreco(double preco)throws NumeroInvalidoException{

		if(preco < 0){
			throw new NumeroInvalidoException("Preco nao pode ser negativo");
		}
	}

	/**
	 * Esse metodo verifica o score passado
	 * 
	 * @param score - recebe o score
	 * @throws NumeroInvalidoException - gera uma exception caso o score seja invalido
	 */
	public static void verificaScore(int score)throws NumeroInvalidoException{
		if(score < 0){
			throw new NumeroInvalidoException("Score nao pode ser negativo");
		}
	}

	/**
	 * Esse metodo verifica se o score estourou o limite permitido
	 * 
	 * @param score - recebe o score
	 * @throws LogicaException - gera uma exceptio caso o score estoure o limite
	 */
	public static void verificaLimiteScore(int score)throws LogicaException{
		final int SCOREMAXIMO = 100000;

		if(score > SCOREMAXIMO){
			throw new LogicaException("Score maximo atingido.");
		}
	}

	/**
	 * Esse metodo verifica se a jogabilidade passada eh valida
	 * 
	 * @param jogabilidade - recebe a jogabilidade
	 * @throws ConstanteException - gera uma exception casos a jogabilidade passada seja nula
	 */
	public static void verificaJogabilidade(Jogabilidade jogabilidade)throws ConstanteException{
		if(jogabilidade == null){
			throw new ConstanteException("Jogabilidade nao pode ser nula");
		}
	}

	/**
	 * Esse metodo verifica se o login passado eh valido
	 * 
	 * @param login - recebe o login do usuario
	 * @throws StringException - gera uma exception caso o login seja invalido
	 */
	public static void verificaLogin(String login)throws StringException{

		if(login == null || login.trim().isEmpty()){
			throw new StringException("login nao pode ser nulo ou vazio");
		}
	}

	/**
	 *Esse metodo verifica se a quantidade de dinheiro passada eh valida
	 *
	 * @param dinheiro - recebe a quantidade de dinheiro
	 * @throws NumeroInvalidoException - gera uma exception caso o dinheiro passado seja invalido
	 */
	public static void verificaDinheiro(double dinheiro)throws NumeroInvalidoException{

		if(dinheiro < 0){
			throw new NumeroInvalidoException("Quantidade de dinheiro nao pode ser negativa."); 
		}
	}
	
	public static void verificaCompra(double dinheiro, double precoJogo)throws LogicaException{
		
		if(dinheiro < precoJogo){
			throw new LogicaException("Dinheiro insuficiente!");
		}
	}

	/**
	 * Esse metodo verifica se o jogo passado eh valido
	 * 
	 * @param jogo - recebe o jogo
	 * @throws ObjetoinvalidoException - gera uma exception caso o jogo seja nulo
	 */
	public static void verificaJogo(Jogo jogo)throws ObjetoinvalidoException{
		if(jogo == null){
			throw new ObjetoinvalidoException("Jogo nao pode ser nulo");
		}
	}

	/**
	 * Esse metodo verifica se um usuario eh valido ou nao
	 * 
	 * @param usuario recebe o usuaio que sera verificado
	 * @throws ObjetoinvalidoException - gera uma exception caso o usuario seja invalido
	 */
	public static void verificaUsuario(Usuario usuario)throws ObjetoinvalidoException{
		
		if(usuario == null){
			throw new ObjetoinvalidoException("Usuario nao pode ser nulo");
		}
	}

	/**
	 * Esse metedo verifica se um determinado usuario eh veterano
	 * 
	 * @param usuario - recebe o tipo do usuario
	 * @return - retorna um bolean indicando se o usuario eh veterano ou nao
	 */
	public static boolean verificaUsuarioVeterano(TipoUsuarioIF usuario){
		
		String esperiencia = usuario.getClass().getSimpleName();
		
		if(esperiencia.equalsIgnoreCase("usuarioveterano")){
			return true;
			
		}return false;
	}

	/**
	 * Esse metedo verifica se um determinado usuario eh noob
	 * 
	 * @param usuario - recebe o tipo do usuario
	 * @return - retorna um bolean indicando se o usuario eh noob ou nao
	 */
	public static boolean verificaUsuarioNoob(TipoUsuarioIF usuario){
		
		String esperiencia = usuario.getClass().getSimpleName();
		
		if(esperiencia.equalsIgnoreCase("usuarionoob")){
			return true;
			
		}else{
			return false;
		}
	}
	
	/**
	 * Esse metodo verifica se o xp2 esta na faixa minima para fazer upgrade
	 * 
	 * @param xp2 - recebe o xp2
	 * @throws NumeroInvalidoException - gera uma exception caso o xp2 seja negativo
	 */
	public static boolean verificaMinXp2(int xp2){
		
		if(xp2 < 1000){
			return false;
			
		}else{
			return true;
		}
	}

	/**
	 * Esse metodo verifica se o xp2 esta na faixa maxima para fazer downgrade
	 * 
	 * @param xp2 - recebe o xp2
	 * @throws NumeroInvalidoException - gera uma exception caso o xp2 seja negativo
	 */
	public static boolean verificaMaxXp2(int xp2){
		
		if(xp2 >= 1000){
			return true;
			
		}else{
			return false;
		}
	}
	
	/**
	 * Esse metodo verifica se a experiencia passada eh valida
	 * 
	 * @param experiencia - recebe a experiencia
	 * @throws ConstanteException - gera uma exception caso a experiencia seja nula
	 */
	public static void verificaExperiencia(ExperienciaUsuario experiencia)throws ConstanteException{
		if(experiencia == null){
			throw new ConstanteException("Exsperiencia nao existe");
		}
	}
}
