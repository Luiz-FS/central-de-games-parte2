/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package exceptions;

/**
 * 
 * @author Luiz Fernando da SIlva
 *
 * Classe das excecoes de strings
 */
public class StringException extends DadosInvalidosException {

	private static final long serialVersionUID = 1L;
	
	public StringException(){
		super("O parametro passado nao pode se nulo ou vazio");
	}
	
	public StringException(String mensagem){
		super(mensagem);
	}
}
