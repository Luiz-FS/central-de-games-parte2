package iusuarios;

import enumerations.ExperienciaUsuario;
import enumerations.Jogabilidade;
import exceptions.ExcecoesP2cg;
import exceptions.NumeroInvalidoException;
import exceptions.ObjetoinvalidoException;
import jogos.Jogo;

public class UsuarioVeterano implements TipoUsuarioIF{
	
	private final int RECOMPENSA_ONLINE;
	private final int RECOMPENSA_COOPERATIVO;
	
	private final int PUNICAO_COOPERATIVO;
	private final int PUNICAO_COMPETITIVO;
	
	public UsuarioVeterano(){
		
		this.RECOMPENSA_ONLINE = 10;
		this.RECOMPENSA_COOPERATIVO = 20;
		
		this.PUNICAO_COMPETITIVO = 20;
		this.PUNICAO_COOPERATIVO = 20;
	}
	
	@Override
	public double comprar(Jogo jogo) throws ObjetoinvalidoException{

		ExcecoesP2cg.verificaJogo(jogo);

		return calculaDesconto(jogo);
	}
	
	public int calculaXp2Compra(double precoJogo) throws NumeroInvalidoException{
		
		int pontosxp2Noob = ExperienciaUsuario.VETERANO.getPontuacao();
		int xp2Recebido = (int) (precoJogo * pontosxp2Noob);
		
		return xp2Recebido;
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

	private double calculaDesconto(Jogo jogo){
		double porcentdesconto = ExperienciaUsuario.VETERANO.getDesconto();

		double desconto = jogo.getPreco() * porcentdesconto;
		return desconto;
	}
	

	private int calculaRecompensa(Jogo jogo){

		int recompensa = 0;

		if(jogo.containJogabilidade(Jogabilidade.ONLLINE)){
			recompensa += RECOMPENSA_ONLINE;
		}

		if(jogo.containJogabilidade(Jogabilidade.COOPERATIVO)){
			recompensa += RECOMPENSA_COOPERATIVO;
		}

		return recompensa;
	}

	private int calculaPunicao(Jogo jogo){
		int punicao = 0;

		if(jogo.containJogabilidade(Jogabilidade.COOPERATIVO)){
			punicao += PUNICAO_COOPERATIVO;
		}

		if(jogo.containJogabilidade(Jogabilidade.COMPETITIVO)){
			punicao += PUNICAO_COMPETITIVO;
		}

		return punicao;
	}
}
