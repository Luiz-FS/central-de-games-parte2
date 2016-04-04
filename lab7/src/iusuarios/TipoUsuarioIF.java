package iusuarios;

import exceptions.DadosInvalidosException;
import exceptions.SteamException;
import jogos.Jogo;

public interface TipoUsuarioIF {
	
	public boolean comprar(Jogo jogo) throws DadosInvalidosException;
	public void recompensar(String nomeJogo, int score, boolean zerou)throws SteamException;
	public void punir(String nomeJogo, int score, boolean zerou)throws SteamException;
}
