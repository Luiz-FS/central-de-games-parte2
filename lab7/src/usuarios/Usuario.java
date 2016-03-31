/* 115111424 - LUIZ FERNANDO DA SILVA: LAB 6 - Turma 3 */
package usuarios;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import jogos.Jogo;
import enumerations.Jogabilidade;
import exceptions.DadosInvalidosException;
import exceptions.ExcecoesP2cg;
import exceptions.NumeroInvalidoException;
import exceptions.ObjetoinvalidoException;
import exceptions.SteamException;
import exceptions.StringException;

/**
 * 
 * 
 * @author Luiz Fernando da Silva
 *
 */
public abstract class Usuario{

	private String nome;
	private String login;
	private Set<Jogo> jogosComprados;
	private double quantDinheiro;
	private int xp2;
	private String experiencia;

	/**
	 * Construtor da classe usuario
	 * 
	 * @param nome - recebe o nome do usuario
	 * @param login - recebe o login do usuario
	 * @throws DadosInvalidosException - gera uma exception caso as entradas sejam invalidas
	 */
	public Usuario(String nome, String login) throws DadosInvalidosException{

		ExcecoesP2cg.verificaNome(nome);
		ExcecoesP2cg.verificaLogin(login);

		this.nome = nome;
		this.login = login;
		this.jogosComprados = new HashSet<Jogo>();
		this.quantDinheiro = 0.0;
		this.xp2 = 0;
		this.experiencia = "indefinida";
	}

	/**
	 * Esse metodo adiciona um jogo a colecao de jogos do usuario
	 * 
	 * @param jogo - recebe o jogo que sera adicionado
	 * @return - retorna um boolean indicando se o jogo foi adicionado ou nao
	 * @throws ObjetoinvalidoException - gera uma exception caso a entrada seja invalida
	 */
	public boolean addJogo(Jogo jogo)throws ObjetoinvalidoException{

		ExcecoesP2cg.verificaJogo(jogo);
		return this.jogosComprados.add(jogo);
	}

	/**
	 * Metodo abstrato que compra um jogo para ser adicionado na colecao de jogos do usuario
	 * 
	 * @param jogo - recebe o jogo que sera comprado
	 * @return - retorna um boolean indicando se o jogo foi comprado ou nao
	 * @throws DadosInvalidosException - gera uma exception caso a entrada seja invalida
	 */
	public abstract boolean compraJogo(Jogo jogo)throws DadosInvalidosException;

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
	 * Esse metodo adiciona uma jogabilidade ao um determinado jogo da colecao de jogos do usuario
	 * 
	 * @param nomeJogo - recebe o nome do jogo
	 * @param jogabilidade - recebe a jogabilidade que sera adicionada
	 * @throws SteamException - - gera uma exception caso as entradas sejam invalidas
	 */
	public void adicionaJogabilidade(String nomeJogo, Jogabilidade jogabilidade)throws SteamException{
		if(containJogo(nomeJogo)){

			Jogo jogo = pegaJogo(nomeJogo);

			ExcecoesP2cg.verificaJogabilidade(jogabilidade);
			jogo.adicionaJogabilidade(jogabilidade);
		}
	}

	/**
	 * Esse metodo pega um jogo atraves do nome do mesmo
	 * 
	 * @param nomeJogo - recebe o nome do jogo
	 * @return - retorna o jogo caso exista
	 * @throws DadosInvalidosException - gera uma exception caso as entradas sejam invalidas
	 */
	private Jogo pegaJogo(String nomeJogo)throws DadosInvalidosException{
		ExcecoesP2cg.verificaNome(nomeJogo);

		for(Jogo jogo : this.jogosComprados){
			if(nomeJogo.equalsIgnoreCase(jogo.getNome())){
				return jogo;
			}
		}

		throw new ObjetoinvalidoException("Jogo nao existe!");
	}
	
	/**
	 * Esse metodo verifica se uma jogo existe na lista de jogos
	 * 
	 * @param nomeJogo - recebe o nome do jogo
	 * @return - retorna um boolean indicando se o jogo existe ou nao
	 * @throws StringException - gera uma exception caso as entradas sejam invalidas
	 */
	public boolean containJogo(String nomeJogo)throws StringException{
		ExcecoesP2cg.verificaNome(nomeJogo);
		for(Jogo jogo : this.jogosComprados){

			if(nomeJogo.equalsIgnoreCase(jogo.getNome())){
				return true;
			}
		}
		return false;
	}

	/**
	 * Esse metodo retorna a lista de jogos do usuario
	 * 
	 * @return  - retorna uma copia da lista de jogos
	 */
	public Set<Jogo> getJogos(){
		Set<Jogo> jogos = new HashSet<Jogo>(this.jogosComprados);
		return jogos;
	}

	/**
	 * Esse metodo remove dinheiro da conta do usuario
	 * 
	 * @param quantDinheiro - recebe q quantidade de dinheiro que sera removida
	 * @throws DadosInvalidosException - - gera uma exception caso as entradas sejam invalidas
	 */
	protected void retiraDinehiro(double quantDinheiro)throws DadosInvalidosException{

		ExcecoesP2cg.verificaDinheiro(quantDinheiro);
		this.quantDinheiro -= quantDinheiro;
	}

	/**
	 * Esse metodo aumenta o xp2(experiencia acumulada) do usuario 
	 * 
	 * @param xp2 - recebe o xp2 a ser adicionado
	 * @throws NumeroInvalidoException - gera uma exception caso as entradas sejam invalidas
	 */
	public void aumentaXp2(int xp2)throws NumeroInvalidoException{
		ExcecoesP2cg.verificaXp2(xp2);
		this.xp2 += xp2;
	}

	/**
	 * Esse metodo registra uma jogada feita pelo usuario
	 * 
	 * @param nomeJogo - recebe o nome do jogo
	 * @param score - recebe o score da jogada
	 * @param zerou - recebe um boolean indicando se zerou ou nao
	 * @return - retorna um boolean informando se a jogada foi registrada com sucesso
	 * @throws SteamException
	 */
	public boolean registraJogada(String nomeJogo, int score, boolean zerou)throws SteamException{

		ExcecoesP2cg.verificaNome(nomeJogo);
		ExcecoesP2cg.verificaScore(score);
		
		if(containJogo(nomeJogo)){
			
			for(Jogo jogo : this.jogosComprados){

				if(nomeJogo.equalsIgnoreCase(jogo.getNome())){
					int xp2 = jogo.registraJogada(score, zerou);

					aumentaXp2(xp2);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Esse metodo calcula o total de preco dos jogos comprados pelo usuario
	 * 
	 * @return - retorna um double indicando o total de preco dos jogos
	 */
	public double calculaTotalPrecoJogos(){
		double totalPreco = 0.0;

		for(Jogo jogo : this.jogosComprados){
			totalPreco += jogo.getPreco();
		}

		return totalPreco;
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
	public String getExperiencia(){
		return this.experiencia;
	}
	
	/**
	 * Esse metodo altera a experiencia do usuario(noob ou veterano)
	 * 
	 * @param experiencia - recebe a experiencia do usuario
	 */
	protected void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

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

		final String FIM_DE_LINHA = System.lineSeparator();
		DecimalFormat df = new DecimalFormat("0.00");

		String saida = "Lista de jogos:" + FIM_DE_LINHA;

		for(Jogo jogo : this.jogosComprados){

			saida += jogo + FIM_DE_LINHA
					+ "Total de preco dos jogos: R$ " + df.format(calculaTotalPrecoJogos())
					+ FIM_DE_LINHA;
		}
		return saida;
	}
}
