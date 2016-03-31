/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package enumerations;

/**
 * 
 * @author Luiz Fernando da Silva
 *
 * Essa enumeration contem os tipos de usuario(Noob e veterano)
 */
public enum ExperienciaUsuario {
	NOOB("Noob", 0.9, 10), VETERANO("Veterano", 0.8, 15);
	
	private String tipoUsuario;
	private double desconto;
	private int pontuacao;
	
	private ExperienciaUsuario(String tipoUsuario, double desconto, int pontuacao){
		this.tipoUsuario = tipoUsuario;
		this.desconto = desconto;
		this.pontuacao = pontuacao;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public double getDesconto() {
		return desconto;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	
}
