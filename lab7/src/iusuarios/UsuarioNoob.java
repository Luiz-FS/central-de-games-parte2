package iusuarios;

import banco.dados.JogosComprados;
import enumerations.ExperienciaUsuario;
import enumerations.Jogabilidade;
import exceptions.DadosInvalidosException;
import exceptions.ExcecoesP2cg;
import exceptions.NumeroInvalidoException;
import exceptions.SteamException;
import exceptions.StringException;
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
	public boolean comprar(Jogo jogo) throws DadosInvalidosException{
		
		ExcecoesP2cg.verificaJogo(jogo);
			
			calculaDesconto(jogo));
			
			calculaXp2Compra(jogo);
			
			return jogos.adicionaJogo(jogo);
		}
		return false;
	}
	
	@Override
	public void recompensar(String nomeJogo, int score, boolean zerou) throws SteamException{
		
		ExcecoesP2cg.verificaNome(nomeJogo);
		ExcecoesP2cg.verificaScore(score);
		
		Jogo jogo = jogos.pegaJogo(nomeJogo);
		
		int xp2 = calculaRecompensa(jogo);
		xp2 += jogo.registraJogada(score, zerou);
		
		aumentaReduzXp2(xp2);
		
	}

	@Override
	public int punir(String nomeJogo, int score, boolean zerou) throws SteamException{
		ExcecoesP2cg.verificaNome(nomeJogo);
		ExcecoesP2cg.verificaScore(score);
		
		Jogo jogo = jogos.pegaJogo(nomeJogo);
		
		int xp2 = jogo.registraJogada(score, zerou);
		xp2 -= calculaPunicao(jogo);
		
		return xp2;
	}

	private void calculaXp2Compra(Jogo jogo) throws NumeroInvalidoException{
		
		int pontosxp2Noob = ExperienciaUsuario.NOOB.getPontuacao();
		int xp2Recebido = (int) (jogo.getPreco() * pontosxp2Noob);
		
		aumentaReduzXp2(xp2Recebido);
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
