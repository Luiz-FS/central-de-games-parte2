/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package exceptions;

/**
 * 
 * 
 * @author Luiz Fernando da Silva
 * 
 *  Classe das excecoes de objetos
 *
 */
public class ObjetoinvalidoException extends DadosInvalidosException{
	
	private static final long serialVersionUID = 1L;

	public ObjetoinvalidoException(){
		super("O objeto passado nao pode ser nulo.");
	}
	
	public ObjetoinvalidoException(String mensagem){
		super(mensagem);
	}
}
