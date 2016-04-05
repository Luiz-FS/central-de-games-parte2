/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package exceptions;

/**
 * 
 * @author Luiz Fernando da Silva
 *
 * Classe das excecoes gerais da central de games
 */
public class SteamException extends Exception{

	private static final long serialVersionUID = 1L;

	public SteamException(){
		super("Logica errada ou dados invalidos!");
	}
	
	public SteamException(String mensagem){
		super(mensagem);
	}
}
