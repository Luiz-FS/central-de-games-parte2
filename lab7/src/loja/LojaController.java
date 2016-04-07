/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 7 - Turma 3 */
package loja;

import java.util.Set;

import jogos.Jogo;
import usuarios.Usuario;
import banco.dados.BancoUsuarios;
import enumerations.EsperienciaUsuario;
import enumerations.Jogabilidade;
import enumerations.TipoDeJogo;
import exceptions.DadosInvalidosException;
import exceptions.SteamException;
import exceptions.StringException;
import factory.JogoFactory;
import factory.UsuarioFactory;

/**
 * 
 * @author Luiz Fernando da Silva
 *
 */
public class LojaController {

	private UsuarioFactory fabricaUsuario;
	private JogoFactory fabricaJogo;
	private BancoUsuarios bancoUsuarios;

	/**
	 * Construtor da loja
	 */
	public LojaController(){

		this.fabricaUsuario = new UsuarioFactory();
		this.fabricaJogo = new JogoFactory();
		this.bancoUsuarios = new BancoUsuarios();
	}

	/**
	 * Esse metodo adiciona um usuario na colecao de usuarios da loja
	 * 
	 * @param nome - recebe o nome do usuario
	 * @param login - recebe o login do usuario
	 * @param experiencia recebe a experiencia do usuario
	 * @return - retorna um boolean indicando se o usuario foi adicionado ou nao
	 */
	public boolean addUsuario(String nome, String login, EsperienciaUsuario experiencia)throws SteamException{
		Usuario usuario = criaUsuario(nome, login, experiencia);

		return bancoUsuarios.adicionaUsuario(usuario);
	}

	/**
	 * Esse metodo cria um novo usuario do tipo especificado
	 * 
	 * @param nomeUsuario -recebe o nome do usuario
	 * @param loginUsuario - recebe o login do usuario
	 * @param experiencia - recebe a experiencia do usuario
	 * @return - retorna o usuario criado
	 * @throws SteamException - gera um exception caso as entradas sejam invalidas
	 */
	private Usuario criaUsuario(String nomeUsuario, String loginUsuario, EsperienciaUsuario experiencia)throws SteamException{
		return fabricaUsuario.criaUsuario(nomeUsuario, loginUsuario, experiencia);
	}


	/**
	 * Esse metodo adiciona dinheiro a um usuario
	 * 
	 * @param login - recebe o login do usuario
	 * @param quantDinheiro - recebe a quantidade de dinheiro que será adicionada
	 * @return - retorna um boolean informando se a operacao foi efetuada com sucesso
	 * @throws SteamException - gera uma exception caso as entradas seja invalidas
	 */
	public boolean addDinheioUsuario(String login,double quantDinheiro)throws SteamException{

		if(containUsuario(login)){

			Usuario usuario = bancoUsuarios.getUsuario(login);
			usuario.adicionaDinheiro(quantDinheiro);
			return true;

		}else{
			return false;
		}

	}

	/**
	 * Esse metodo verifica se um determinando usuario existe no banco de usuarios
	 * 
	 * @param loginUsuario - recebe o login do usuario
	 * @return - retorna um boolean indicando se o usuario existe ou nao
	 * @throws StringException - gera uma exception caso a entrada seja invalida
	 */
	public boolean containUsuario(String loginUsuario)throws StringException{
		return this.bancoUsuarios.containUsuario(loginUsuario);
	}

	/**
	 * Esse metodo cria um jogo para ser comprado
	 * 
	 * @param nomeJogo - recebe o nome do jogo
	 * @param preco - recebe o preco do jogo
	 * @param tipo - recebe o tipo do jogo
	 * @param jogablidades - recebe uma lista de jogabiliades a ser adicionada
	 * @return - retorna o jogo criado
	 * @throws DadosInvalidosException - gera uma exception caso as entradas sejam invalidas
	 */
	private Jogo criaJogo(String nomeJogo, double preco, TipoDeJogo tipo, Set<Jogabilidade> jogabilidades) throws SteamException {
		return fabricaJogo.criaJogo(nomeJogo, preco, tipo, jogabilidades);
	}


	/**
	 * Esse metodo recompensa o usuario por uma determinada jogada
	 * 
	 * @param login - recebe o login do usuario
	 * @param nomeJogo - recebe o nome do jogo
	 * @param score - recebe o score atingido
	 * @param zerou - recebe um boolean indicando se zerou ou nao
	 * @return - retorna um boolean indicando se a operacao foi realizada com sucesso
	 * @throws SteamException - gera uma exceptio caso as entradas seja invalidas
	 */
	public boolean recompensarUsuario(String login, String nomeJogo, int score, boolean zerou)throws SteamException{

		if(containUsuario(login)){
			Usuario usuario = bancoUsuarios.getUsuario(login);

			return usuario.recompensar(nomeJogo, score, zerou);

		}else{
			return false;
		}

	}
	
	/**
	 * Esse metodo puni o usuario por uma determinada jogada
	 * 
	 * @param login - recebe o login do usuario
	 * @param nomeJogo - recebe o nome do jogo
	 * @param score - recebe o score atingido
	 * @param zerou - recebe um boolean indicando se zerou ou nao
	 * @return - retorna um boolean indicando se a operacao foi realizada com sucesso
	 * @throws SteamException - gera uma exceptio caso as entradas seja invalidas
	 */
	public boolean punirUsuario(String login, String nomeJogo, int score, boolean zerou)throws SteamException{
		
		if(containUsuario(login)){
			Usuario usuario = bancoUsuarios.getUsuario(login);

			return usuario.punir(nomeJogo, score, zerou);

		}else{
			return false;
		}

	}

	/**
	 * Esse metodo vende um jogo ao usuario
	 * 
	 * @param login - recebe o login do usuario
	 * @param nomeJogo - recebe o nome do jogo
	 * @param preco - recebe o preco do jogo
	 * @param tipoJogo - recebe tipo do jogo
	 * @param jogablidades - recebe uma lista de jogabiliades a ser adicionada
	 * @return - retorna um boolean indicando se o jogo foi vendido com sucesso
	 */
	public boolean venderJogo(String login, String nomeJogo, double preco, TipoDeJogo tipoJogo, 
			Set<Jogabilidade> jogabilidades)throws SteamException{

		if(containUsuario(login)){

			Usuario usuario = bancoUsuarios.getUsuario(login);

			Jogo novoJogo = criaJogo(nomeJogo, preco, tipoJogo, jogabilidades);
			return usuario.compraJogo(novoJogo);
		}
		return false;
	}

	/**
	 * Esse metodo verifica se um determinado usuario contem um determinado jogo
	 * 
	 * @param loginUsuario - recebe o login do usuario
	 * @param nomeJogo - recebe o nome do jogo
	 * @return - retorna um boolean indicando se o usuario possui o jogo ou nao
	 */
	public boolean usuarioContainsJogo(String loginUsuario, String nomeJogo)throws SteamException{

		if(bancoUsuarios.containUsuario(loginUsuario)){

			Usuario usuario = bancoUsuarios.getUsuario(loginUsuario);
			return usuario.containJogo(nomeJogo);
		}
		return false;
	}

	/**
	 * Metodo infoUsuarios que retorna a lista de usuarios da loja (chamada polimorfica)
	 */
	public String infoUsuarios(){
		final String FIM_DE_LINHA = System.lineSeparator();
		String saida = "=== Central P2-CG ===" + FIM_DE_LINHA + FIM_DE_LINHA
					 + bancoUsuarios;

		return saida;
	}

	/**
	 * Esse metodo compara se dois controller sao iguais atraves de se banco de usuarios
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bancoUsuarios == null) ? 0 : bancoUsuarios.hashCode());
		return result;
	}

	/**
	 * Esse metodo compara se dois controller sao iguais atraves de se banco de usuarios
	 */
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof LojaController){
			LojaController outroController = (LojaController) obj;
			
			return outroController.bancoUsuarios.equals(bancoUsuarios);
		}
		return true;
	}


}
