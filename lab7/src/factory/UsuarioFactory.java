/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 7 - Turma 3 */
package factory;

import enumerations.ExperienciaUsuario;
import exceptions.ConstanteException;
import exceptions.SteamException;
import exceptions.StringException;
import usuarios.Usuario;
import util.ExcecoesP2cg;

public class UsuarioFactory {

	/**
	 * Esse metodo cria um usuario de acordo com o tipo especificado
	 * 
	 * @param nomeUsuario - recebe o nome do usuario
	 * @param loginUsuario - recebe o login do usuario
	 * @param experiencia - recebe a experiencia do usuario
	 * @return retorna o usuario criado
	 * @throws SteamException - gera uma exception caso as entradas sejam invalidas
	 */
	public Usuario criaUsuario(String nomeUsuario, String loginUsuario, ExperienciaUsuario experiencia)throws SteamException{

		ExcecoesP2cg.verificaExperiencia(experiencia);

		switch(experiencia){

		case NOOB:
			Usuario novoUsuarioNoob = criaUsuarioNoob(nomeUsuario, loginUsuario);
			return novoUsuarioNoob;

		default:
			throw new ConstanteException("Tipo de usuario nao existe!");
		}
	}
	
	/**
	 * Esse metodo cria um usuario noob
	 * 
	 * @param nomeUsuario - recebe o nome do usuario
	 * @param loginUsuario - recebe o login do usuario
	 * @return - retorna o usuario criado
	 * @throws StringException - gera uma exeption caso as entradas sejam invalidas
	 */
	public Usuario criaUsuarioNoob(String nomeUsuario, String loginUsuario)throws StringException{
		Usuario novoUsuarioNoob = new Usuario(nomeUsuario, loginUsuario);
		
		return novoUsuarioNoob;
	}
}
