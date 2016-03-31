/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package loja;

import java.util.HashMap;
import java.util.Map;

import jogos.Jogo;
import jogos.Luta;
import jogos.Plataforma;
import jogos.Rpg;
import usuarios.Usuario;
import usuarios.UsuarioNoob;
import usuarios.UsuarioVeterano;
import enumerations.ExperienciaUsuario;
import enumerations.Jogabilidade;
import enumerations.TipoDeJogo;
import exceptions.ConstanteException;
import exceptions.DadosInvalidosException;
import exceptions.ExcecoesP2cg;
import exceptions.NumeroInvalidoException;
import exceptions.ObjetoinvalidoException;
import exceptions.SteamException;
import exceptions.StringException;

/**
 * 
 * @author Luiz Fernando da Silva
 *
 */
public class Loja {

	private Map<String,Usuario> usuarios;

	/**
	 * Construtor da loja
	 */
	public Loja(){

		this.usuarios = new HashMap<String,Usuario>();
	}

	/**
	 * Esse metodo adiciona um usuario na colecao de usuarios da loja
	 * 
	 * @param nome - recebe o nome do usuario
	 * @param login - recebe o login do usuario
	 * @param experiencia - recebe a experiencia do usuario
	 * @return - retorna um boolean indicando se o usuario foi adicionado ou nao
	 */
	public boolean addUsuario(String nome, String login, ExperienciaUsuario experiencia){
		try {

			ExcecoesP2cg.verificaExperiencia(experiencia);

			switch(experiencia){
			case NOOB:
				Usuario novoUsuarioNoob = new UsuarioNoob(nome, login);

				if(! containUsuario(novoUsuarioNoob)){
					usuarios.put(login, novoUsuarioNoob);

					return true;
				}
				return false;

			case VETERANO:
				Usuario novoUsuarioVeterano = new UsuarioVeterano(nome, login);

				if(! containUsuario(novoUsuarioVeterano)){
					usuarios.put(login, novoUsuarioVeterano);

					return true;
				}
				return false;
			}

		} catch (DadosInvalidosException e) {
			System.out.println("Nome ou login invalido!");

		} catch(ConstanteException e){
			System.out.println("Tipo de usuario nao existe!");
		}
		return true;
	}

	/**
	 * Esse metodo adiciona dinheiro a conta do usuario
	 * 
	 * @param login - recebe o login do usuario
	 * @param quantDinheiro - recebe a quantidade de dinheiro
	 * @return retorna um boolean indicando se o dinheiro foi adicionado ou nao
	 */
	public boolean addDinheioUsuario(String login,double quantDinheiro){
		try {
			ExcecoesP2cg.verificaLogin(login);
			ExcecoesP2cg.verificaDinheiro(quantDinheiro);

			if(containUsuario(login)){

				Usuario usuario = pegaUsuario(login);
				usuario.adicionaDinheiro(quantDinheiro);
				return true;

			}else{
				return false;
			}

		} catch (StringException e) {
			System.out.println("Login invalido!");
			return false;

		}catch (NumeroInvalidoException e){
			System.out.println("Quantidade negativa de dinheiro nao pode ser adicionada!");
			return false;

		}catch (DadosInvalidosException e){
			System.out.println("Dados invalidos!");
			return false;
		}
	}

	
	/**
	 * Esse metodo adiciona uma jogabilidade a um determinado jogo
	 * 
	 * @param login - recebe o login do usuario
	 * @param nomeJogo - recebe o nome do jogo
	 * @param jogabilidade - recebe o nome do jogo
	 * @return - retorna um boolean indicando se a jogabilidade foi adicionada ou nao
	 */
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

	/**
	 * Esse metodo verifica se um usuario existe na loja ou nao atraves de outro usuario passado como parametro
	 * 
	 * @param outroUsuario - recebe o usuario
	 * @return
	 */
	private boolean containUsuario(Usuario outroUsuario){

		for(Usuario usuario : this.usuarios.values()){
			if(usuario.equals(outroUsuario)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Esse metodo verifica se um usuario existe na loja atravez do login do mesmo
	 * 
	 * @param loginUsuario - recebe o login do usuario
	 * @return - retorna um boolean indicando se o usuario existe ou nao
	 */
	public boolean containUsuario(String loginUsuario){
		return this.usuarios.containsKey(loginUsuario);
	}

	/**
	 * Esse metodo cria um jogo para ser comprado
	 * 
	 * @param nomeJogo - recebe o nome do jogo
	 * @param preco - receb o preco do jogo
	 * @param tipo - receb o tipo do jogo
	 * @return - retorna o jogo criado
	 * @throws DadosInvalidosException - gera uma exception caso as entradas sejam invalidas
	 */
	private Jogo criaJogo(String nomeJogo, double preco, TipoDeJogo tipo) throws DadosInvalidosException {

		switch(tipo){
		case RPG:
			Jogo novoJogoRpg = criaJogoRpg(nomeJogo, preco);
			return novoJogoRpg;

		case PLATAFORMA:
			Jogo novoJogoPlataforma = criaJogoPlataforma(nomeJogo, preco);
			return novoJogoPlataforma;

		case LUTA:
			Jogo novoJogoLuta = criaJogoLuta(nomeJogo, preco);
			return novoJogoLuta;

		default:
			throw new StringException("Tipo de jogo inexistente!");
		}
	}

	
	/**
	 * Esse metodo cria um jogo de luta
	 * 
	 * @param nomeJogo - recebe o nome do jogo
	 * @param preco - receb o preco do jogo
	 * @return - retorna o jogo se luta criado
	 */
	private Jogo criaJogoLuta(String nomeJogo, double preco){
		try {
			Jogo novoJogo = new Luta(nomeJogo, preco);
			return novoJogo;

		} catch (DadosInvalidosException e) {
			System.out.println("Dados passdos sao invalidos!");
			return null;
		}
	}

	/**
	 * Esse metodo cria um jogo de plataforma
	 * 
	 * @param nomeJogo - recebe o nome do jogo
	 * @param preco - receb o preco do jogo
	 * @return - retorna o jogo de plataforma criado
	 */
	private Jogo criaJogoPlataforma(String nomeJogo, double preco){
		try {
			Jogo novoJogo = new Plataforma(nomeJogo, preco);
			return novoJogo;

		} catch (DadosInvalidosException e) {
			System.out.println("Dados passdos sao invalidos!");
			return null;
		}
	}

	/**
	 * Esse metodo cria um jogo de rpg
	 * 
	 * @param nomeJogo - recebe o nome do jogo
	 * @param preco - recebe o preco do jogo
	 * @return - retorna o jogo de rpg criado
	 */
	private Jogo criaJogoRpg(String nomeJogo, double preco){
		try {
			Jogo novoJogo = new Rpg(nomeJogo, preco);
			return novoJogo;

		} catch (DadosInvalidosException e) {
			System.out.println("Dados passdos sao invalidos!");
			return null;
		}
	}

	/**
	 * Esse metodo retorna um usuario da loja(se existir)
	 * 
	 * @param login - recebe o login do usuario
	 * @return - retorna o usuario se existir
	 * @throws ObjetoinvalidoException - gera uma exception caso a entrada seja invalida
	 */
	private Usuario pegaUsuario(String login)throws ObjetoinvalidoException{

		if(usuarios.containsKey(login)){
			return usuarios.get(login);
		}
		throw new ObjetoinvalidoException("Usuario nao existe!");
	}

	/**
	 * Esse metodo registra uma jogada de um determinado usuario
	 * 
	 * @param login - recebe o login do usuario
	 * @param nomeJogo - recebe o nome do jogo
	 * @param score - recebe o score da jogada
	 * @param zerou - recebe um boolean indicando se zerou ou nao o jogo
	 * @return - retorna um boolean informando se a jogada foi registrada com sucesso
	 */
	public boolean registraJogada(String login, String nomeJogo, int score, boolean zerou){

		try{
			ExcecoesP2cg.verificaLogin(login);

			if(containUsuario(login)){
				Usuario usuario = pegaUsuario(login);

				return usuario.registraJogada(nomeJogo, score, zerou);

			}else{
				return false;
			}

		} catch(StringException e){
			System.out.println("Login invalido!");
			return false;

		} catch(SteamException e){
			System.out.println("Dados da jogada invalidos!");
			return false;
		}
	}

	/**
	 * Esse metodo transfere as informacoes de um usuario pra outro na hora do upgrade
	 * 
	 * @param noob - recebe o usuario noob
	 * @param veterano - recebe o usuario veterano
	 * @throws DadosInvalidosException - gera uma exception caso as entradas sejam invalidas
	 */
	private void transfereDadosUsuarios(Usuario noob, Usuario veterano) throws DadosInvalidosException{

		veterano.adicionaDinheiro(noob.getQuantDinrheiro());
		veterano.aumentaXp2(noob.getXp2());

		for(Jogo jogo : noob.getJogos()){
			veterano.addJogo(jogo);
		}
	}

	/**
	 * Esse metodo faz o upgrade de usuarios(Noob para veterano)
	 * 
	 * @param login - recebe o login do usuario
	 * @return - retorna um boolean indicando se o upgrade foi feito com sucesso
	 */
	public boolean upgradeUsuario(String login){

		final int XP2_MINIMO = 1000;

		try {
			if(containUsuario(login)){

				Usuario usuario = pegaUsuario(login);
				ExcecoesP2cg.verificaUsuarioVeterano(usuario);

				if(usuario.getXp2() >= XP2_MINIMO){
					Usuario usuarioVeterano = new UsuarioVeterano(usuario.getNome(), usuario.getLogin());

					upgradeUsuario(usuario, usuarioVeterano);
					return true;
				}

			}
		} catch (DadosInvalidosException e) {
			System.out.println("Login fornecido eh invalido, usuario eh veterano ou nao possue xp2 suficiente!");
			return false;
		}
		return false;
	}

	/**
	 * Esse metodo subtitui o usuario noob por um novo usuario veterano nas lista de usuarios
	 * 
	 * @param usuarioNoob - recebe o usuario noob
	 * @param usuarioVeterano - recebe o usuario veterano
	 * @return - retorna um boolean indicando se a troca foi feita com sucesso
	 * @throws DadosInvalidosException - gera uma exception caso as entradas sejam invalidas
	 */
	private boolean upgradeUsuario(Usuario usuarioNoob, Usuario usuarioVeterano)throws DadosInvalidosException{


			transfereDadosUsuarios(usuarioNoob, usuarioVeterano);

			return usuarios.replace(usuarioNoob.getLogin(), usuarioNoob, usuarioVeterano);
	}

	/**
	 * Esse metodo vende um jogo ao usuario
	 * 
	 * @param login - recebe o login do usuario
	 * @param nomeJogo - recebe o nome do jogo
	 * @param preco - recebe o preco do jogo
	 * @param tipoJogo - recebe tipo do jogo
	 * @return - retorna um boolean indicando se o jogo foi vendido com sucesso
	 */
	public boolean venderJogo(String login, String nomeJogo, double preco, TipoDeJogo tipoJogo){

		if(containUsuario(login)){
			try {

				ExcecoesP2cg.verificaLogin(login);
				Usuario usuario = pegaUsuario(login);

				Jogo novoJogo = criaJogo(nomeJogo, preco, tipoJogo);
				return usuario.compraJogo(novoJogo);

			} catch (DadosInvalidosException e) {
				System.out.println("Os dados passados estao incorretos!");
			}
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
	public boolean usuarioContainsJogo(String loginUsuario, String nomeJogo){

		try{
			if(this.usuarios.containsKey(loginUsuario)){

				Usuario usuario = pegaUsuario(loginUsuario);
				return usuario.containJogo(nomeJogo);
			}
		}catch(DadosInvalidosException e){
			System.out.println("Usuario ou jogo nao existe!");
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

		for(Usuario usuario : this.usuarios.values()){

			saida += usuario + FIM_DE_LINHA
					+ "-------------------------------------------------------";
		}

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
		Loja other = (Loja) obj;
		if (usuarios == null) {
			if (other.usuarios != null)
				return false;
		} else if (!usuarios.equals(other.usuarios))
			return false;
		return true;
	}
	
	
}
