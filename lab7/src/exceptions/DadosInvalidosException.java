/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package exceptions;

/**
 * 
 * 
 * @author Luiz Fernando da Silva
 * 
 * Classe das excecoes de dados de entrada
 *
 */
public class DadosInvalidosException extends SteamException{

	private static final long serialVersionUID = 1L;
	
	public DadosInvalidosException(){
		super("Dado inserido eh invalido.");
	}
	
	public DadosInvalidosException(String mensagem){
		super(mensagem);
	}
}
