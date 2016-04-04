package iusuarios;

import exceptions.DadosInvalidosException;

public class Usuario {

	private TipoUsuarioIF tipo;
	
	public Usuario()throws DadosInvalidosException{
		this.tipo = new UsuarioNoob("Luiz", "luiz.silva");
	}
	
	public void torcaDinamica(){
		
	}
}
