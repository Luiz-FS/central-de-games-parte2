/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 7 - Turma 3 */
package banco.dados;

import java.util.HashMap;
import java.util.Map;

import exceptions.LogicaException;
import exceptions.ObjetoinvalidoException;
import exceptions.SteamException;
import exceptions.StringException;
import usuarios.Usuario;
import util.ExcecoesP2cg;

/**
 * 
 * @author Luiz Fernando da Silva
 *
 */
public class BancoUsuarios {
	
	private Map<String, Usuario> usuarios;
	
	/**
	 * Construtor da classe Banco de Usuarios
	 */
	public BancoUsuarios(){
		this.usuarios = new HashMap<String, Usuario>();
	}
	
	/**
	 * Esse metodo adiciona um usuario ao banco de usuarios (caso nao exista)
	 * 
	 * @param usuario - recebe o usuario a ser adicionado
	 * @return - retorna um boolean indicando se o usuario foi adicionado ou nao
	 * @throws ObjetoinvalidoException - gera uma exceprio caso o usuario seja invalido
	 */
	public boolean adicionaUsuario(Usuario usuario)throws ObjetoinvalidoException{
		
		ExcecoesP2cg.verificaUsuario(usuario);
		
		if(! containUsuario(usuario)){
			usuarios.put(usuario.getLogin(), usuario);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Esse metodo verifica se um usuario existe no banco de usuarios atraves de um objeto usuario
	 * passado como parametro
	 * 
	 * @param usuario recebe o usuario que sera usado com parametro para a busca
	 * @return - retorna um boolena indicando se o usuario existe ou nao
	 */
	private boolean containUsuario(Usuario usuario){
		return usuarios.containsValue(usuario);
	}
	
	/**
	 * Esse metodo verifica se um usuario existe no banco de usuarios atraves do login do usuario
	 * 
	 * @param usuario recebe o usuario que sera usado com parametro para a busca
	 * @return - retorna um boolena indicando se o usuario existe ou nao
	 */
	public boolean containUsuario(String loginUsuario) throws StringException{
		
		ExcecoesP2cg.verificaLogin(loginUsuario);
		return usuarios.containsKey(loginUsuario);
	}
	
	/**
	 * Esse metodo retorna um usuario do banco de usuarios
	 * 
	 * @param loginUsuario - recebe o login do usuarios
	 * @return - retorna o usario caso exista
	 * @throws SteamException - gera uma exception caso o usuario nao exista
	 */
	public Usuario pegaUsuario(String loginUsuario)throws SteamException{
		
		if(containUsuario(loginUsuario)){
			return usuarios.get(loginUsuario);
			
		}else{
			throw new LogicaException("Usuario nao existe");
		}
	}
	
	/**
	 * Esse metodo retorna uma sring mostrando todos os usuarios existentes
	 */
	public String toString(){
		String saida = "";
		
		for(Usuario usuario : usuarios.values()){
			saida += usuario.toString();
		}
		
		return saida;
	}

	/**
	 * Esse metodo compara dois bancos de usuarios
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
	 * Esse metodo compara dois bancos de usuarios
	 */
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof BancoUsuarios){
			BancoUsuarios outroBanco = (BancoUsuarios) obj;
			
			
			return this.usuarios.equals(outroBanco.usuarios);
		}
		
		return false;
	}
	
	
}
