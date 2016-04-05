/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package exceptions;

/**
 * 
 * 
 * @author Luiz Fernando da Silva
 * 
 * Classe das excecoes de constantes
 *
 */
public class ConstanteException extends SteamException{

	private static final long serialVersionUID = 1L;
	
	public ConstanteException(){
		super("Constante invalida!");
	}
	
	public ConstanteException(String mensagem){
		super(mensagem);
	}
}
