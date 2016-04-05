package iusuarios;

import banco.dados.JogosComprados;
import exceptions.DadosInvalidosException;
import exceptions.ExcecoesP2cg;
import exceptions.NumeroInvalidoException;
import exceptions.SteamException;
import exceptions.StringException;
import jogos.Jogo;

public class Usuario {


	private String nome;
	private String login;
	private TipoUsuarioIF tipoUsuario;
	private JogosComprados jogos;
	private double quantDinheiro;
	private int xp2;

	/**
	 * Construtor da classe usuario
	 * 
	 * @param nome - recebe o nome do usuario
	 * @param login - recebe o login do usuario
	 * @throws DadosInvalidosException - gera uma exception caso as entradas sejam invalidas
	 */
	public Usuario(String nome, String login) throws StringException{

		ExcecoesP2cg.verificaNome(nome);
		ExcecoesP2cg.verificaLogin(login);

		this.nome = nome;
		this.login = login;
		this.tipoUsuario = new UsuarioNoob();
		this.jogos = new JogosComprados();
		this.quantDinheiro = 0.0;
		this.xp2 = 0;
	}

	/**
	 * Metodo abstrato que compra um jogo para ser adicionado na colecao de jogos do usuario
	 * 
	 * @param jogo - recebe o jogo que sera comprado
	 * @return - retorna um boolean indicando se o jogo foi comprado ou nao
	 * @throws DadosInvalidosException - gera uma exception caso a entrada seja invalida
	 */
	public boolean compraJogo(Jogo jogo)throws SteamException{

		if(! jogos.containJogo(jogo)){

			double precoJogo = tipoUsuario.comprar(jogo);
			ExcecoesP2cg.verificaCompra(this.quantDinheiro, precoJogo);

			retiraDinehiro(precoJogo);
			int xp2 = tipoUsuario.calculaXp2Compra(jogo.getPreco());
			auteraXp2(xp2);

			jogos.adicionaJogo(jogo);
			return true;

		}else{
			return false;
		}
	}

	/**
	 * Esse metodo adiciona dinheiro a conta do usuario
	 * 
	 * @param quantDinheiro - recebe a quantidade de dinheiro
	 * @throws DadosInvalidosException - gera uma exception caso as entradas sejam invalidas
	 */
	public void adicionaDinheiro(double quantDinheiro)throws DadosInvalidosException {

		ExcecoesP2cg.verificaDinheiro(quantDinheiro);
		this.quantDinheiro += quantDinheiro;
	}

	/**
	 * Esse metodo verifica se uma jogo existe na lista de jogos
	 * 
	 * @param nomeJogo - recebe o nome do jogo
	 * @return - retorna um boolean indicando se o jogo existe ou nao
	 * @throws StringException - gera uma exception caso as entradas sejam invalidas
	 */
	public boolean containJogo(String nomeJogo){
		return jogos.containJogo(nomeJogo);
	}

	/**
	 * Esse metodo remove dinheiro da conta do usuario
	 * 
	 * @param quantDinheiro - recebe q quantidade de dinheiro que sera removida
	 * @throws DadosInvalidosException - - gera uma exception caso as entradas sejam invalidas
	 */
	private void retiraDinehiro(double quantDinheiro)throws DadosInvalidosException{

		ExcecoesP2cg.verificaDinheiro(quantDinheiro);
		this.quantDinheiro -= quantDinheiro;
	}

	/**
	 * Esse metodo aumenta o xp2(experiencia acumulada) do usuario 
	 * 
	 * @param xp2 - recebe o xp2 a ser adicionado
	 * @throws NumeroInvalidoException - gera uma exception caso as entradas sejam invalidas
	 */
	private void auteraXp2(int xp2)throws NumeroInvalidoException{
		this.xp2 += xp2;
	}

	public boolean recompensar(String nomeJogo, int score, boolean zerou)throws SteamException{
		ExcecoesP2cg.verificaNome(nomeJogo);
		ExcecoesP2cg.verificaScore(score);

		if(containJogo(nomeJogo)){
			
			Jogo jogo = jogos.pegaJogo(nomeJogo);
			
			int xp2 = jogo.registraJogada(score, zerou);
			xp2 += tipoUsuario.recompensar(jogo);
			
			auteraXp2(xp2);
			
			statusUsuario();
			
			return true;
		}
		return false;
	}

	private void statusUsuario() throws SteamException {
		if(this.xp2 >= 1000){
			upgrade();
		}
	}

	public boolean punir(String nomeJogo, int score, boolean zerou)throws SteamException{
		ExcecoesP2cg.verificaNome(nomeJogo);
		ExcecoesP2cg.verificaScore(score);

		if(containJogo(nomeJogo)){
			
			Jogo jogo = jogos.pegaJogo(nomeJogo);
			
			int xp2 = jogo.registraJogada(score, zerou);
			xp2 -= tipoUsuario.punir(jogo);
			
			auteraXp2(xp2);
			
			return true;
		}
		return false;
	}

	/**
	 * Esse metodo calcula o total de preco dos jogos comprados pelo usuario
	 * 
	 * @return - retorna um double indicando o total de preco dos jogos
	 */
	public double calculaTotalPrecoJogos(){
		return jogos.totalPrecoJogos();
	}
	
	public boolean upgrade()throws SteamException{
		ExcecoesP2cg.verificaUsuarioVeterano(tipoUsuario);
		ExcecoesP2cg.verificaMinXp2(xp2);;
		
		tipoUsuario = new UsuarioVeterano();
		return true;
	}
	
	public boolean downgrade()throws SteamException{
		
		ExcecoesP2cg.verificaUsuarioNoob(this.tipoUsuario);
		ExcecoesP2cg.verificaMaxXp2(this.xp2);
		
		tipoUsuario = new UsuarioNoob();
		return true;
	}

	/**
	 * Esse metodo retorna o login do usuario
	 * 
	 * @return - retorna uma string mostrando o login do usuario
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Esse metodo retorna o saldo do usuario
	 * 
	 * @return - retorna um double indicando o saldo
	 */
	public double getQuantDinrheiro() {
		return quantDinheiro;
	}

	/**
	 * Esse metodo retorna o nome do usuario
	 * 
	 * @return - retorna uma string que mostra o nome do usuario
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Esse metodo retorna o xp2(experiencia acumulada) do usuario
	 * 
	 * @return - retorna um int informando o xp2 do usuario
	 */
	public int getXp2(){
		return this.xp2;
	}

	/**
	 * Esse metodo retorna a experiencia do usuario(noob ou veterano)
	 * 
	 * @return - retorna uma string informando a experiencia do usuario
	 */
	//public String getExperiencia(){
	//return this.experiencia;
	//}


	/**
	 * Metodo hashcode que verifica se dois usuarios sao iguais atraves do nome e login
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * Metodo hashcode que verifica se dois usuarios sao iguais atraves do nome e login
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	/**
	 * Metodo toString que retorna uma string contendo a lista de jogos do usuario
	 * 
	 */
	@Override
	public String toString(){

		String saida = jogos.toString();

		return saida;
	}
	
	
}
