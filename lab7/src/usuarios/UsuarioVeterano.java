/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package usuarios;


import enumerations.ExperienciaUsuario;
import exceptions.DadosInvalidosException;
import exceptions.ExcecoesP2cg;
import exceptions.NumeroInvalidoException;
import jogos.Jogo;

/**
 * 
 * @author Luiz Fernando da Silva
 *
 */
public class UsuarioVeterano extends Usuario{


	/**
	 * Construtor da classe usuario veterano
	 * 
	 * @param nome - recebe o nome do usuario
	 * @param login - receb o login do usuario
	 * @throws DadosInvalidosException - gera uma exception caso as entradas sejam invalidas
	 */
	public UsuarioVeterano(String nome, String login) throws DadosInvalidosException{
		super(nome, login);
		super.setExperiencia(ExperienciaUsuario.VETERANO.getTipoUsuario());
	}
	
	/**
	 * Esse metodo sebrescreve o metodo compraJogo da super classe usuario
	 */
	public boolean compraJogo(Jogo jogo)throws DadosInvalidosException{
		
		ExcecoesP2cg.verificaJogo(jogo);
		
		if(super.getQuantDinrheiro() >= jogo.getPreco()){
			
			super.retiraDinehiro(calculaDesconto(jogo));
			
			calculaXp2Compra(jogo);
			
			return super.addJogo(jogo);
		}
		return false;
	}

	/**
	 * Esse metodo calcula o xp2(experiencia) que sera adicionado ao usuario na hora da compra
	 * 
	 * @param jogo - recebe o jogo que sera comprado
	 * @throws NumeroInvalidoException - gera uma exception caso a entrada seja invalida
	 */
	private void calculaXp2Compra(Jogo jogo) throws NumeroInvalidoException{
		
		int pontosxp2Veterano = ExperienciaUsuario.VETERANO.getPontuacao();
		int xp2Recebido = (int)jogo.getPreco() * pontosxp2Veterano;
		
		super.aumentaXp2(xp2Recebido);
	}
	
	/**
	 * Esse metodo calcula o desconto do usuario na hora da compra
	 * 
	 * @param jogo - receb o jogo que sera comprado
	 * @return - gera uma exception caso as entradas sejam invalidas
	 */
	private double calculaDesconto(Jogo jogo){
		
		double porcentdesconto = ExperienciaUsuario.VETERANO.getDesconto();
		
		double desconto = jogo.getPreco() * porcentdesconto;
		return desconto;
	}
	
	/**
	 * Metodo toString que retorna um string contendo o login
	 * O nome e o toString da super classe Usuario
	 */
	@Override
	public String toString(){
		
		final String FIM_DE_LINHA = System.lineSeparator();
		String saida = super.getLogin() + FIM_DE_LINHA
				       + super.getNome() + " - Jogador Veterano" + FIM_DE_LINHA
				       + super.toString();
		return saida;
	}
}
