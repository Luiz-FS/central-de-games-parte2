/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package usuarios;

import util.ExcecoesP2cg;
import enumerations.Jogabilidade;
import exceptions.NumeroInvalidoException;
import exceptions.ObjetoinvalidoException;
import jogos.Jogo;

/**
 * 
 * @author Luiz Fernando da Silva
 *
 */
public class UsuarioNoob implements TipoUsuarioIF{

	private final int RECOMPENSA_OFFLINE;
	private final int RECOMPENSA_MULTIPLAYER;

	private final int PUNICAO_ONLINE;
	private final int PUNICAO_COOPERATIVO;
	private final int PUNICAO_COMPETITIVO;
	
	private final double DESCONTO;
	private final int XP2_EXTRA;
	
	/**
	 * contrutor da classe Usuario noob
	 */
	public UsuarioNoob(){

		this.RECOMPENSA_OFFLINE = 30;
		this.RECOMPENSA_MULTIPLAYER = 10;

		this.PUNICAO_ONLINE = 10;
		this.PUNICAO_COOPERATIVO = 50;
		this.PUNICAO_COMPETITIVO = 20;
		
		this.DESCONTO = 0.9;
		this.XP2_EXTRA = 10;
	}

	/**
	 * Esse metodo sobrescreve o metodo comprar da interface TipoUsuarioIF (chamada polimorfica)
	 */
	@Override
	public double comprar(Jogo jogo) throws ObjetoinvalidoException{

		ExcecoesP2cg.verificaJogo(jogo);

		return calculaDesconto(jogo);
	}

	/**
	 * Esse metodo sobrescreve o metodo recompensar da interface TipoUsuarioIF (chamada polimorfica)
	 */
	@Override
	public int recompensar(Jogo jogo) throws ObjetoinvalidoException{

		ExcecoesP2cg.verificaJogo(jogo);

		int xp2 = calculaRecompensa(jogo);
		
		return xp2;
	}

	/**
	 * Esse metodo sobrescreve o punir comprar da interface TipoUsuarioIF (chamada polimorfica)
	 */
	@Override
	public int punir(Jogo jogo) throws ObjetoinvalidoException{
		ExcecoesP2cg.verificaJogo(jogo);

		int xp2 = calculaPunicao(jogo);

		return xp2;
	}

	/**
	 * Esse metodo sobrescreve o metodo calculaXp2Compra da interface TipoUsuarioIF (chamada polimorfica)
	 */
	public int calculaXp2Compra(double precoJogo) throws NumeroInvalidoException{

		int pontosxp2Noob = XP2_EXTRA;
		int xp2Recebido = (int) (precoJogo * pontosxp2Noob);
		
		return xp2Recebido;
	}



	/**
	 * Esse metodo calcula o desconto do jogo
	 * 
	 * @param jogo - recebe o jogo que sera comprado
	 * @return - retorna o desconto do jogo
	 */
	private double calculaDesconto(Jogo jogo){
		double porcentdesconto = DESCONTO;

		double desconto = jogo.getPreco() * porcentdesconto;
		return desconto;
	}

	/**
	 * Esse metodo calcula a recompensa do usuario durante uma jogada
	 * 
	 * @param jogo - recebe o jogo
	 * @return - retorna um int indicando a recompensa
	 */
	private int calculaRecompensa(Jogo jogo){

		int recompensa = 0;

		if(jogo.containJogabilidade(Jogabilidade.OFFLINE)){
			recompensa += RECOMPENSA_OFFLINE;
		}

		if(jogo.containJogabilidade(Jogabilidade.MULTIPLAYER)){
			recompensa += RECOMPENSA_MULTIPLAYER;
		}

		return recompensa;
	}

	/**
	 * Esse metodo calcula a punicao do usuario durante uma jogada
	 * 
	 * @param jogo - recebe o jogo
	 * @return - retorna um int indicando a punicao
	 */
	private int calculaPunicao(Jogo jogo){
		int punicao = 0;

		if(jogo.containJogabilidade(Jogabilidade.ONLLINE)){
			punicao += PUNICAO_ONLINE;
		}

		if(jogo.containJogabilidade(Jogabilidade.COOPERATIVO)){
			punicao += PUNICAO_COOPERATIVO;
		}

		if(jogo.containJogabilidade(Jogabilidade.COMPETITIVO)){
			punicao += PUNICAO_COMPETITIVO;
		}

		return punicao;
	}

	/**
	 * Esse metodo retorna um string informando o tipo do usuario (chamada polimorfica)
	 */
	@Override
	public String toString(){
		String saida = "Jogador Noob";
		
		return saida;
	}
}
