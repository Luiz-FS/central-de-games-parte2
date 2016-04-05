package usuarios;

import exceptions.NumeroInvalidoException;
import exceptions.ObjetoinvalidoException;
import jogos.Jogo;

public interface TipoUsuarioIF {
	
	public double comprar(Jogo jogo) throws ObjetoinvalidoException;
	public int recompensar(Jogo jogo)throws ObjetoinvalidoException;
	public int punir(Jogo jogo)throws ObjetoinvalidoException;
	public int calculaXp2Compra(double precoJogo)throws NumeroInvalidoException;
}
