package iusuarios;

import enumerations.ExperienciaUsuario;
import enumerations.Jogabilidade;
import exceptions.ExcecoesP2cg;
import exceptions.NumeroInvalidoException;
import exceptions.ObjetoinvalidoException;
import jogos.Jogo;

public class UsuarioNoob implements TipoUsuarioIF{

	private final int RECOMPENSA_OFFLINE;
	private final int RECOMPENSA_MULTIPLAYER;

	private final int PUNICAO_ONLINE;
	private final int PUNICAO_COOPERATIVO;
	private final int PUNICAO_COMPETITIVO;

	public UsuarioNoob(){

		this.RECOMPENSA_OFFLINE = 30;
		this.RECOMPENSA_MULTIPLAYER = 10;

		this.PUNICAO_ONLINE = 10;
		this.PUNICAO_COOPERATIVO = 50;
		this.PUNICAO_COMPETITIVO = 20;
	}

	@Override
	public double comprar(Jogo jogo) throws ObjetoinvalidoException{

		ExcecoesP2cg.verificaJogo(jogo);

		return calculaDesconto(jogo);
	}

	@Override
	public int recompensar(Jogo jogo) throws ObjetoinvalidoException{

		ExcecoesP2cg.verificaJogo(jogo);

		int xp2 = calculaRecompensa(jogo);
		
		return xp2;
	}

	@Override
	public int punir(Jogo jogo) throws ObjetoinvalidoException{
		ExcecoesP2cg.verificaJogo(jogo);

		int xp2 = calculaPunicao(jogo);

		return xp2;
	}

	public int calculaXp2Compra(double precoJogo) throws NumeroInvalidoException{

		int pontosxp2Noob = ExperienciaUsuario.NOOB.getPontuacao();
		int xp2Recebido = (int) (precoJogo * pontosxp2Noob);
		
		return xp2Recebido;
	}



	private double calculaDesconto(Jogo jogo){
		double porcentdesconto = ExperienciaUsuario.NOOB.getDesconto();

		double desconto = jogo.getPreco() * porcentdesconto;
		return desconto;
	}

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

}
