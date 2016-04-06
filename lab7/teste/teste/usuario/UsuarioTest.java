package teste.usuario;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import enumerations.Jogabilidade;
import exceptions.DadosInvalidosException;
import exceptions.SteamException;
import jogos.Jogo;
import jogos.Luta;
import jogos.Plataforma;
import jogos.Rpg;
import usuarios.Usuario;
import usuarios.UsuarioNoob;
import usuarios.UsuarioVeterano;

public class UsuarioTest {

	private Usuario usuario;
	private List<Jogabilidade> jogabilidades;

	@Before
	public void Usuario(){
		jogabilidades = new ArrayList<Jogabilidade>();
		jogabilidades.add(Jogabilidade.COOPERATIVO);
		jogabilidades.add(Jogabilidade.COMPETITIVO);
		jogabilidades.add(Jogabilidade.MULTIPLAYER);
		jogabilidades.add(Jogabilidade.ONLLINE);
		
		try {
			usuario = new Usuario("Luiz", "luiz.silva");
		} catch (DadosInvalidosException e) {
			fail(); //nao deve gerar exception
		}
	}

	@Test
	public void testCompraJogo() {
		try {

			usuario.adicionaDinheiro(1000.00);
			Jogo crossfire = new Luta("Coressfire", 100.99, jogabilidades);
			Jogo pointBlack = new Plataforma("PointBlack", 200.85, jogabilidades);
			Jogo superMario = new Rpg("Super Mario", 150.55, jogabilidades);
			Jogo battlefield = new Luta("Battlefield", 700.45, jogabilidades);

			assertTrue(usuario.compraJogo(crossfire));
			assertTrue(usuario.compraJogo(pointBlack));
			assertTrue(usuario.compraJogo(superMario));
			assertFalse(usuario.compraJogo(battlefield));

		} catch (SteamException e) {
			fail(); //nao deve gerar exception
		}

		try {

			usuario.compraJogo(null);
		} catch (SteamException e) {
			assertEquals("Jogo nao pode ser nulo",e.getMessage());
		}
	}

	@Test
	public void testUsuario() {
		try {

			Usuario usuario = new Usuario("Luiz", "luiz.silva");

		} catch (DadosInvalidosException e) {
			fail(); // nao deve gerar exception
		}

		try {

			Usuario usuario = new Usuario("", "luiz.silva");
			fail();
		} catch (DadosInvalidosException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}

		try {

			Usuario usuario = new Usuario(null, "luiz.silva");
			fail();
		} catch (DadosInvalidosException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}

		try {

			Usuario usuario = new Usuario("Luiz", "");
			fail();
		} catch (DadosInvalidosException e) {
			assertEquals("login nao pode ser nulo ou vazio", e.getMessage());
		}

		try {

			Usuario usuario = new Usuario("Luiz", null);
			fail();
		} catch (DadosInvalidosException e) {
			assertEquals("login nao pode ser nulo ou vazio", e.getMessage());
		}
	}


	@Test
	public void testAdicionaDinrheiro() {
		try {

			usuario.adicionaDinheiro(1000.55);
			assertEquals(1000.55, usuario.getQuantDinrheiro(), 0.01);

		} catch (DadosInvalidosException e) {
			fail(); //nao deve gerar exception
		}

		try {

			usuario.adicionaDinheiro(-1000.55);
			fail();
		} catch (DadosInvalidosException e) {
			assertEquals("Quantidade de dinheiro nao pode ser negativa.", e.getMessage());
		}
	}
	
	@Test
	public void testRegistraJogada() {
		try {
			usuario.adicionaDinheiro(1000.00);
			Jogo crossfire = new Luta("Crossfire", 100.99);
			Jogo pointBlack = new Plataforma("PointBlack", 200.85);
			Jogo superMario = new Rpg("Super Mario", 150.55);

			assertTrue(usuario.compraJogo(crossfire));
			assertTrue(usuario.compraJogo(pointBlack));
			assertTrue(usuario.compraJogo(superMario));

			usuario.registraJogada("Crossfire", 1000, true);
			usuario.registraJogada("Super Mario", 1000, true);
			usuario.registraJogada("PointBlack", 1000, true);

			assertEquals(4531, usuario.getXp2());
		} catch (SteamException e) {
			fail(); //nao deve gerar exception
		}

		try {			
			usuario.registraJogada("", 1000, true);
			fail(); //deve gerar uma exception
		} catch (SteamException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}

		try {			
			usuario.registraJogada(null, 1000, true);
			fail(); //deve gerar uma exception
		} catch (SteamException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}

		try {			
			usuario.registraJogada("PointBlack", -1000, true);
			fail(); //deve gerar uma exception
		} catch (SteamException e) {
			assertEquals("Score nao pode ser negativo", e.getMessage());
		}
	}

	@Test
	public void testGetLogin() {
		assertEquals("luiz.silva", usuario.getLogin());
	}

	@Test
	public void testGetQuantDinrheiro() {
		try {
			usuario.adicionaDinheiro(10000.00);
			
			assertEquals(10000.00, usuario.getQuantDinrheiro(), 0.01);
		} catch (DadosInvalidosException e) {
			fail(); // nao deve gerar exception
		}
	}


	@Test
	public void testGetNome() {
		assertEquals("Luiz", usuario.getNome());
	}

	@Test
	public void testGetXp2() {
		try {
			usuario.adicionaDinheiro(1000.00);
			Jogo crossfire = new Luta("Coressfire", 100.99, jogabilidades);
			assertTrue(usuario.compraJogo(crossfire));
			
			System.out.println(usuario.getXp2());
			
		} catch (SteamException e) {
			fail(); //nao deve gerar exception
		}
	}


	@Test
	public void testEqualsObject() {
		try {
			Usuario luiz = new Usuario("Luiz", "luiz.silva");
			Usuario Lucas = new Usuario("Luiz", "luiz.silva");
			
			assertTrue(usuario.equals(luiz));
			assertFalse(usuario.equals(Lucas));
		} catch (DadosInvalidosException e) {
			fail(); // nao deve gerar exception
		}
	}
	
	@Test
	public void testToString(){
		final String FIM_DE_LINHA = System.lineSeparator();
		try {
		
			usuario.adicionaDinheiro(300.00);
			Jogo metalGear = new Luta("Metal Gear", 200.85, jogabilidades);
			usuario.compraJogo(metalGear);
			
			usuario.registraJogada("Metal Gear", 1000, true);
		} catch (SteamException e) {
			fail(); //nao deve gerar exception
		}
		String comparador = "luiz.silva" + FIM_DE_LINHA
				+ "Luiz - Jogador Noob" + FIM_DE_LINHA
				+ "Lista de jogos:" + FIM_DE_LINHA
				+ "+ Metal Gear - LUTA:" + FIM_DE_LINHA
				+ "==> Jogou 1 vez(es)" + FIM_DE_LINHA
				+ "==> Zerou 1 vez(es)" + FIM_DE_LINHA
				+ "==> Maior Score: 1000" + FIM_DE_LINHA + FIM_DE_LINHA
				+ "Total de preco dos jogos: R$ 200,85" + FIM_DE_LINHA;
		
		assertEquals(comparador, usuario.toString());
	}
}
