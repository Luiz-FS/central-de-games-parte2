package banco.usuarios;

import java.util.HashMap;
import java.util.Map;

import usuarios.Usuario;

public class BancoUsuarios {
	
	private Map<String, Usuario> usuarios;
	
	public BancoUsuarios(){
		this.usuarios = new HashMap<String, Usuario>();
	}
}
