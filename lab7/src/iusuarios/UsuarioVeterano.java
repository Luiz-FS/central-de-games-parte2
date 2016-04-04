package iusuarios;

import banco.dados.JogosComprados;
import enumerations.ExperienciaUsuario;
import exceptions.DadosInvalidosException;
import exceptions.ExcecoesP2cg;
import exceptions.NumeroInvalidoException;
import exceptions.StringException;
import jogos.Jogo;

public class UsuarioVeterano implements TipoUsuarioIF{

	private String nome;
	private String login;
	private JogosComprados jogos;
	private double quantDinheiro;
	private int xp2;
	
	
	public UsuarioVeterano(String nome, String login) throws StringException{
		ExcecoesP2cg.verificaNome(nome);
		ExcecoesP2cg.verificaLogin(login);

		this.nome = nome;
		this.login = login;
		this.jogos = new JogosComprados();
		this.quantDinheiro = 0.0;
		this.xp2 = 0;
	}
	@Override
	public boolean comprar(Jogo jogo) throws DadosInvalidosException{
		
		ExcecoesP2cg.verificaJogo(jogo);
		
		if(quantDinheiro >= jogo.getPreco()){
			
			retiraDinheiro(calculaDesconto(jogo));
			
			calculaXp2Compra(jogo);
			
			return jogos.adicionaJogo(jogo);
		}
		return false;
	}
	
	private void calculaXp2Compra(Jogo jogo) throws NumeroInvalidoException{
		
		int pontosxp2Noob = ExperienciaUsuario.VETERANO.getPontuacao();
		int xp2Recebido = (int) (jogo.getPreco() * pontosxp2Noob);
		
		aumentaXp2(xp2Recebido);
	}
	
	
	private void aumentaXp2(int xp2){
		this.xp2 += xp2;
	}
	private void retiraDinheiro(double dinheiro){
		this.quantDinheiro -= dinheiro;
	}

	private double calculaDesconto(Jogo jogo){
		double porcentdesconto = ExperienciaUsuario.VETERANO.getDesconto();

		double desconto = jogo.getPreco() * porcentdesconto;
		return desconto;
	}
	@Override
	public void recompensar(String nomeJogo, int score, boolean zerou) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void punir(String nomeJogo, int score, boolean zerou) {
		// TODO Auto-generated method stub
		
	}
}