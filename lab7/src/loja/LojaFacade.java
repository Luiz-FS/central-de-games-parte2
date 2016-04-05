/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package loja;

import java.util.List;

import enumerations.ExperienciaUsuario;
import enumerations.Jogabilidade;
import enumerations.TipoDeJogo;
import exceptions.SteamException;
import exceptions.StringException;

/**
 * 
 * @author Luiz Fernando da Silva
 *
 */
public class LojaFacade {

	private LojaController controladorLoja;

	/**
	 * Construtor da classe LojaFacade
	 */
	public LojaFacade(){
		controladorLoja = new LojaController();
	}

	/**
	 * Esse metodo delega a classe lojacontrolle a funcao de adicionar um usuario
	 * 
	 * @param nome - recebe o nome do usuario
	 * @param login - recebe o login do usuario
	 * @param experiencia - recebe a experiencia do usuario
	 * @return - retorna um boolean indicando se o usuario foi adicionado ou nao
	 */
	public boolean addUsuario(String nome, String login, ExperienciaUsuario experiencia) {

		try {
			return controladorLoja.addUsuario(nome, login, experiencia);

		} catch (SteamException e) {
			return false;
		}
	}

	/**
	 * Esse metodo delega a classe lojacontrolle a funcao de adicionar dinheiro ao usuario
	 * 
	 * @param login - recebe o login do usuario
	 * @param quantDinheiro - recebe a quantidade de dinheiro a ser adicionada
	 * @return retorna um boolean indicando se a operacao foi feita com sucesso
	 */
	public boolean addDinheioUsuario(String login, double quantDinheiro) {

		try {
			return controladorLoja.addDinheioUsuario(login, quantDinheiro);

		} catch (SteamException e) {
			return false;
		}
	}

	/**
	 * Esse metodo delega a classe lojacontrolle a funcao de verificar se um usuario existe
	 * 
	 * @param loginUsuario - recebe o login do usuario
	 * @return - retorna um boolean indicando se o usuario existe ou nao
	 */
	public boolean containUsuario(String loginUsuario) {

		try {
			return controladorLoja.containUsuario(loginUsuario);

		} catch (StringException e) {
			return false;
		}
	}

	/**
	 * Esse metodo delega a classe lojacontrolle a funcao de recompensar um usuario
	 * 
	 * @param login - recebe o login do usuario
	 * @param nomeJogo - recebe o nome do jogo
	 * @param score - recebe o score atinjido
	 * @param zerou - recebe um boolean indicando se o jogo foi zerado ou nao
	 * @return - retorna um boolena informando se a operacao foi realizada com sucesso
	 */
	public boolean recompensarUsuario(String login, String nomeJogo, int score, boolean zerou) {

		try {
			return controladorLoja.recompensarUsuario(login, nomeJogo, score, zerou);

		} catch (SteamException e) {
			return false;
		}
	}
	
	/**
	 * Esse metodo delega a classe lojacontrolle a funcao de punir um usuario
	 * 
	 * @param login - recebe o login do usuario
	 * @param nomeJogo - recebe o nome do jogo
	 * @param score - recebe o score atinjido
	 * @param zerou - recebe um boolean indicando se o jogo foi zerado ou nao
	 * @return - retorna um boolean indicando se a operacao foi realizada com sucesso
	 */
	public boolean punirUsuario(String login, String nomeJogo, int score, boolean zerou) {

		try {
			return controladorLoja.punirUsuario(login, nomeJogo, score, zerou);

		} catch (SteamException e) {
			return false;
		}
	}

	/**
	 * Esse metodo delega a classe lojacontrolle a funcao de vender um jogo
	 * 
	 * @param login - recebe o login do usuario
	 * @param nomeJogo - recebe o nome do jogo
	 * @param preco - recebe o preco do jogo
	 * @param tipoJogo - recebe o tipo do jogo
	 * @param jogabilidades - recebe a lista de jogabilidades
	 * @return - retorna um boolean indicando se o jogo foi vendido ou nao
	 */
	public boolean venderJogo(String login, String nomeJogo, double preco, TipoDeJogo tipoJogo,
			List<Jogabilidade> jogabilidades) {

		try {
			return controladorLoja.venderJogo(login, nomeJogo, preco, tipoJogo, jogabilidades);

		} catch (SteamException e) {
			return false;
		}
	}

	/**
	 * Esse metodo delega a classe lojacontrolle a funcao de verificar se um usuario contem um jogo
	 * 
	 * @param loginUsuario - recebe o login do usuario
	 * @param nomeJogo - recebe o nome do jogo
	 * @return - retorna um boolean indicando se o usuario contem ou nao o jogo
	 */
	public boolean usuarioContainsJogo(String loginUsuario, String nomeJogo){

		try {
			return controladorLoja.usuarioContainsJogo(loginUsuario, nomeJogo);

		} catch (SteamException e) {
			return false;
		}
	}


}
