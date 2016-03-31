package teste.usuario;

import static org.junit.Assert.*;

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

public class UsuarioNoobTest {

	private Usuario usuarioNoob;

	@Before
	public void Usuario(){
		try {
			usuarioNoob = new UsuarioNoob("Luiz", "luiz.silva");
		} catch (DadosInvalidosException e) {
			fail(); //nao deve gerar exception
		}
	}

	@Test
	public void testCompraJogo() {
		try {

			usuarioNoob.adicionaDinheiro(1000.00);
			Jogo crossfire = new Luta("Coressfire", 100.99);
			Jogo pointBlack = new Plataforma("PointBlack", 200.85);
			Jogo superMario = new Rpg("Super Mario", 150.55);
			Jogo battlefield = new Luta("Battlefield", 700.45);

			assertTrue(usuarioNoob.compraJogo(crossfire));
			assertTrue(usuarioNoob.compraJogo(pointBlack));
			assertTrue(usuarioNoob.compraJogo(superMario));
			assertFalse(usuarioNoob.compraJogo(battlefield));

		} catch (DadosInvalidosException e) {
			fail(); //nao deve gerar exception
		}

		try {

			usuarioNoob.compraJogo(null);
		} catch (DadosInvalidosException e) {
			assertEquals("Jogo nao pode ser nulo",e.getMessage());
		}
	}

	@Test
	public void testUsuarioNoob() {
		try {

			Usuario usuarioNoob = new UsuarioNoob("Luiz", "luiz.silva");

		} catch (DadosInvalidosException e) {
			fail(); // nao deve gerar exception
		}

		try {

			Usuario usuarioNoob = new UsuarioNoob("", "luiz.silva");
			fail();
		} catch (DadosInvalidosException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}

		try {

			Usuario usuarioNoob = new UsuarioNoob(null, "luiz.silva");
			fail();
		} catch (DadosInvalidosException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}

		try {

			Usuario usuarioNoob = new UsuarioNoob("Luiz", "");
			fail();
		} catch (DadosInvalidosException e) {
			assertEquals("login nao pode ser nulo ou vazio", e.getMessage());
		}

		try {

			Usuario usuarioNoob = new UsuarioNoob("Luiz", null);
			fail();
		} catch (DadosInvalidosException e) {
			assertEquals("login nao pode ser nulo ou vazio", e.getMessage());
		}
	}


	@Test
	public void testAdicionaDinrheiro() {
		try {

			usuarioNoob.adicionaDinheiro(1000.55);
			assertEquals(1000.55, usuarioNoob.getQuantDinrheiro(), 0.01);

		} catch (DadosInvalidosException e) {
			fail(); //nao deve gerar exception
		}

		try {

			usuarioNoob.adicionaDinheiro(-1000.55);
			fail();
		} catch (DadosInvalidosException e) {
			assertEquals("Quantidade de dinheiro nao pode ser negativa.", e.getMessage());
		}
	}

	@Test
	public void testAumentaXp2() {
		try {
			usuarioNoob.aumentaXp2(100);

			assertEquals(100, usuarioNoob.getXp2());

		} catch (DadosInvalidosException e) {
			fail(); //nao deve gerar exception
		}

		try {
			usuarioNoob.aumentaXp2(-100);
			fail();			
		} catch (DadosInvalidosException e) {
			assertEquals("Xp2 invalido!", e.getMessage());
		}
	}


	@Test
	public void testAdicionaJogabilidade(){
		try {
			usuarioNoob.adicionaDinheiro(1000.00);
			Jogo crossfire = new Luta("Crossfire", 100.99);
			
			assertTrue(usuarioNoob.compraJogo(crossfire));
			
			usuarioNoob.adicionaJogabilidade("Crossfire", Jogabilidade.COMPETITIVO);
			usuarioNoob.adicionaJogabilidade("Crossfire", Jogabilidade.COOPERATIVO);
			usuarioNoob.adicionaJogabilidade("Crossfire", Jogabilidade.MULTIPLAYER);
			
		} catch (Exception e) {
			fail(); // nao deve gerar exception
		}
		
		try {
			usuarioNoob.adicionaJogabilidade("Crossfire", null);
			fail(); // deve gerar exception
		} catch (SteamException e) {
			assertEquals("Jogabilidade nao pode ser nula", e.getMessage());
		}
		
		try {
			usuarioNoob.adicionaJogabilidade("", Jogabilidade.OFFLINE);
			fail(); // deve gerar exception
		} catch (SteamException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}
		
		try {
			usuarioNoob.adicionaJogabilidade(null, Jogabilidade.OFFLINE);
			fail(); // deve gerar exception
		} catch (SteamException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}
	}
	
	@Test
	public void testRegistraJogada() {
		try {
			usuarioNoob.adicionaDinheiro(1000.00);
			Jogo crossfire = new Luta("Crossfire", 100.99);
			Jogo pointBlack = new Plataforma("PointBlack", 200.85);
			Jogo superMario = new Rpg("Super Mario", 150.55);

			assertTrue(usuarioNoob.compraJogo(crossfire));
			assertTrue(usuarioNoob.compraJogo(pointBlack));
			assertTrue(usuarioNoob.compraJogo(superMario));

			usuarioNoob.registraJogada("Crossfire", 1000, true);
			usuarioNoob.registraJogada("Super Mario", 1000, true);
			usuarioNoob.registraJogada("PointBlack", 1000, true);

			assertEquals(4531, usuarioNoob.getXp2());
		} catch (SteamException e) {
			fail(); //nao deve gerar exception
		}

		try {			
			usuarioNoob.registraJogada("", 1000, true);
			fail(); //deve gerar uma exception
		} catch (SteamException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}

		try {			
			usuarioNoob.registraJogada(null, 1000, true);
			fail(); //deve gerar uma exception
		} catch (SteamException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}

		try {			
			usuarioNoob.registraJogada("PointBlack", -1000, true);
			fail(); //deve gerar uma exception
		} catch (SteamException e) {
			assertEquals("Score nao pode ser negativo", e.getMessage());
		}
	}

	@Test
	public void testGetLogin() {
		assertEquals("luiz.silva", usuarioNoob.getLogin());
	}

	@Test
	public void testGetQuantDinrheiro() {
		try {
			usuarioNoob.adicionaDinheiro(10000.00);
			
			assertEquals(10000.00, usuarioNoob.getQuantDinrheiro(), 0.01);
		} catch (DadosInvalidosException e) {
			fail(); // nao deve gerar exception
		}
	}


	@Test
	public void testGetNome() {
		assertEquals("Luiz", usuarioNoob.getNome());
	}

	@Test
	public void testGetXp2() {
		try {
			usuarioNoob.aumentaXp2(2000);
			
			
		} catch (DadosInvalidosException e) {
			fail(); //nao deve gerar exception
		}
	}


	@Test
	public void testEqualsObject() {
		try {
			Usuario luiz = new UsuarioNoob("Luiz", "luiz.silva");
			Usuario Lucas = new UsuarioVeterano("Luiz", "luiz.silva");
			
			assertTrue(usuarioNoob.equals(luiz));
			assertFalse(usuarioNoob.equals(Lucas));
		} catch (DadosInvalidosException e) {
			fail(); // nao deve gerar exception
		}
	}
	
	@Test
	public void testToString(){
		final String FIM_DE_LINHA = System.lineSeparator();
		try {
		
			usuarioNoob.adicionaDinheiro(300.00);
			Jogo metalGear = new Luta("Metal Gear", 200.85);
			usuarioNoob.compraJogo(metalGear);
			
			usuarioNoob.registraJogada("Metal Gear", 1000, true);
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
		
		assertEquals(comparador, usuarioNoob.toString());
	}
}
