package factory;

import enumerations.ExperienciaUsuario;
import exceptions.ConstanteException;
import exceptions.ExcecoesP2cg;
import exceptions.SteamException;
import usuarios.Usuario;
import usuarios.UsuarioNoob;

public class UsuarioFactory {
	
	
	public Usuario criaUsuario(String nomeUsuario, String loginUsuario, ExperienciaUsuario experiencia)throws SteamException{
		
			ExcecoesP2cg.verificaExperiencia(experiencia);
			
			switch(experiencia){
			
			case NOOB:
				Usuario novoUsuarioNoob = new UsuarioNoob(nomeUsuario, loginUsuario);
				return novoUsuarioNoob;
				
			default:
				throw new ConstanteException("Tipo de usuario nao existe!");
			}
	}
}