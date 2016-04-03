package iusuarios;

import exceptions.DadosInvalidosException;
import jogos.Jogo;

public interface IUsuario {
	
	public boolean comprar(Jogo jogo) throws DadosInvalidosException;
	public void recompensar(String nomeJogo, int score, boolean zerou);
	public void punir(String nomeJogo, int score, boolean zerou);
	public boolean containJogo(String nomeJogo);
	public String getLogin();
	public String getNome();
	public int getXp2();
	public double getQuantDinheiro();
}
