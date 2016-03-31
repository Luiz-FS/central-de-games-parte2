package banco.usuarios;

import java.util.HashMap;
import java.util.Map;

import exceptions.ExcecoesP2cg;
import exceptions.LogicaException;
import exceptions.ObjetoinvalidoException;
import exceptions.SteamException;
import exceptions.StringException;
import usuarios.Usuario;

public class BancoUsuarios {
	
	private Map<String, Usuario> usuarios;
	
	public BancoUsuarios(){
		this.usuarios = new HashMap<String, Usuario>();
	}
	
	public boolean adicionaUsuario(Usuario usuario)throws ObjetoinvalidoException{
		
		ExcecoesP2cg.verificaUsuario(usuario);
		
		if(! containUsuario(usuario)){
			usuarios.put(usuario.getLogin(), usuario);
			return true;
		}
		
		return false;
	}
	
	private boolean containUsuario(Usuario usuario){
		return usuarios.containsValue(usuario);
	}
	
	public boolean containUsuario(String loginUsuario) throws StringException{
		
		ExcecoesP2cg.verificaLogin(loginUsuario);
		return usuarios.containsKey(loginUsuario);
	}
	
	public Usuario getUsuario(String loginUsuario)throws SteamException{
		
		if(containUsuario(loginUsuario)){
			return usuarios.get(loginUsuario);
			
		}else{
			throw new LogicaException("Usuario nao existe");
		}
	}
	
	public void modificaUsuario(Usuario antigo, Usuario novo)throws ObjetoinvalidoException{
		
		ExcecoesP2cg.verificaUsuario(antigo);
		ExcecoesP2cg.verificaUsuario(novo);
		
		usuarios.replace(antigo.getLogin(), antigo, novo);
	}
}
