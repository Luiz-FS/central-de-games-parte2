package loja;

import java.util.List;

import enumerations.ExperienciaUsuario;
import enumerations.Jogabilidade;
import enumerations.TipoDeJogo;
import exceptions.SteamException;
import exceptions.StringException;

public class LojaFacade {

	private LojaController controladorLoja;

	public LojaFacade(){
		controladorLoja = new LojaController();
	}

	public boolean addUsuario(String nome, String login, ExperienciaUsuario experiencia) {

		try {
			return controladorLoja.addUsuario(nome, login, experiencia);

		} catch (SteamException e) {
			return false;
		}
	}

	public boolean addDinheioUsuario(String login, double quantDinheiro) {

		try {
			return controladorLoja.addDinheioUsuario(login, quantDinheiro);

		} catch (SteamException e) {
			return false;
		}
	}

	public boolean containUsuario(String loginUsuario) {

		try {
			return controladorLoja.containUsuario(loginUsuario);

		} catch (StringException e) {
			return false;
		}
	}

	public boolean registraJogada(String login, String nomeJogo, int score, boolean zerou) {

		try {
			return controladorLoja.registraJogada(login, nomeJogo, score, zerou);

		} catch (SteamException e) {
			return false;
		}
	}

	public boolean venderJogo(String login, String nomeJogo, double preco, TipoDeJogo tipoJogo,
			List<Jogabilidade> jogabilidades) {

		try {
			return controladorLoja.venderJogo(login, nomeJogo, preco, tipoJogo, jogabilidades);

		} catch (SteamException e) {
			return false;
		}
	}

	public boolean usuarioContainsJogo(String loginUsuario, String nomeJogo) throws SteamException {

		try {
			return controladorLoja.usuarioContainsJogo(loginUsuario, nomeJogo);

		} catch (SteamException e) {
			return false;
		}
	}


}
