/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package exceptions;

/**
 * 
 * 
 * @author Luiz Fernando da Silva
 * 
 * Classe das excecoes de valores numericos
 *
 */
public class NumeroInvalidoException extends DadosInvalidosException {

	private static final long serialVersionUID = 1L;
	
	public NumeroInvalidoException(){
		super("O valor passado eh invalido.");
	}
	
	public NumeroInvalidoException(String mensagem){
		super(mensagem);
	}
}
