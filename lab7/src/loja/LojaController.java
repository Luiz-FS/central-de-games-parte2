/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package loja;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import banco.dados.BancoUsuarios;
import enumerations.ExperienciaUsuario;
import enumerations.Jogabilidade;
import enumerations.TipoDeJogo;
import exceptions.DadosInvalidosException;
import exceptions.ExcecoesP2cg;
import exceptions.SteamException;
import exceptions.StringException;
import factory.JogoFactory;
import factory.UsuarioFactory;
import jogos.Jogo;
import usuarios.Usuario;
import usuarios.UsuarioVeterano;

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
	 * @return - retorna um boolean indicando se o usuario foi adicionado ou nao
	 */
	public boolean addUsuario(String nome, String login, ExperienciaUsuario experiencia)throws SteamException{
		Usuario usuario = criaUsuario(nome, login, experiencia);

		return bancoUsuarios.adicionaUsuario(usuario);
	}

	private Usuario criaUsuario(String nomeUsuario, String loginUsuario, ExperienciaUsuario experiencia)throws SteamException{
		return fabricaUsuario.criaUsuario(nomeUsuario, loginUsuario, experiencia);
	}


	public boolean addDinheioUsuario(String login,double quantDinheiro)throws SteamException{

		if(containUsuario(login)){

			Usuario usuario = bancoUsuarios.getUsuario(login);
			usuario.adicionaDinheiro(quantDinheiro);
			return true;

		}else{
			return false;
		}

	}

	/*
	/**
	 * Esse metodo adiciona uma jogabilidade a um determinado jogo
	 * 
	 * @param login - recebe o login do usuario
	 * @param nomeJogo - recebe o nome do jogo
	 * @param jogabilidade - recebe o nome do jogo
	 * @return - retorna um boolean indicando se a jogabilidade foi adicionada ou nao

	public boolean addJogabilidade(String login, String nomeJogo, Jogabilidade jogabilidade){

		if(containUsuario(login)){
			try{
				Usuario usuario = pegaUsuario(login);

				usuario.adicionaJogabilidade(nomeJogo, jogabilidade);
				return true;

			}catch(SteamException e){
				System.out.println("Usuario ou jogo nao existe");
				return false;
			}
		}else{
			return false;
		}
	}
	 */

	public boolean containUsuario(String loginUsuario)throws StringException{
		return this.bancoUsuarios.containUsuario(loginUsuario);
	}

	/**
	 * Esse metodo cria um jogo para ser comprado
	 * 
	 * @param nomeJogo - recebe o nome do jogo
	 * @param preco - receb o preco do jogo
	 * @param tipo - receb o tipo do jogo
	 * @param jogablidades - recebe uma lista de jogabiliades a ser adicionada
	 * @return - retorna o jogo criado
	 * @throws DadosInvalidosException - gera uma exception caso as entradas sejam invalidas
	 */
	private Jogo criaJogo(String nomeJogo, double preco, TipoDeJogo tipo, List<Jogabilidade> jogabilidades) throws SteamException {
		return fabricaJogo.criaJogo(nomeJogo, preco, tipo, jogabilidades);
	}


	public boolean registraJogada(String login, String nomeJogo, int score, boolean zerou)throws SteamException{

		if(containUsuario(login)){
			Usuario usuario = bancoUsuarios.getUsuario(login);

			return usuario.registraJogada(nomeJogo, score, zerou);

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
	public boolean venderJogo(String login, String nomeJogo, double preco, TipoDeJogo tipoJogo, List<Jogabilidade> jogabilidades)throws SteamException{

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
	 * Metodo toString que retorna a lista de usuarios da loja
	 */
	@Override
	public String toString(){
		final String FIM_DE_LINHA = System.lineSeparator();
		String saida = "=== Central P2-CG ===" + FIM_DE_LINHA + FIM_DE_LINHA;

		return saida;
	}

	/**
	 * Metodo hashCode que compara se duas lojas sao iguais atraves da quantidade de usuarios
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((usuarios == null) ? 0 : usuarios.hashCode());
		return result;
	}

	/**
	 * Metodo equals que compara se duas lojas sao iguais atraves da quantidade de usuarios
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LojaController other = (LojaController) obj;
		if (usuarios == null) {
			if (other.usuarios != null)
				return false;
		} else if (!usuarios.equals(other.usuarios))
			return false;
		return true;
	}


}
