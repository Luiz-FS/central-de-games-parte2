package teste.usuario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import jogos.Jogo;
import jogos.Luta;
import jogos.Plataforma;
import jogos.Rpg;

import org.junit.Before;
import org.junit.Test;

import usuarios.Usuario;
import usuarios.UsuarioNoob;
import usuarios.UsuarioVeterano;
import enumerations.Jogabilidade;
import exceptions.DadosInvalidosException;
import exceptions.SteamException;

public class UsuarioVeteranoTest {

	private Usuario usuarioVeterano;

	@Before
	public void Usuario(){
		try {
			usuarioVeterano = new UsuarioVeterano("Luiz", "luiz.silva");
		} catch (DadosInvalidosException e) {
			fail(); //nao deve gerar exception
		}
	}

	@Test
	public void testCompraJogo() {
		try {

			usuarioVeterano.adicionaDinheiro(1000.00);
			Jogo crossfire = new Luta("Coressfire", 100.99);
			Jogo pointBlack = new Plataforma("PointBlack", 200.85);
			Jogo superMario = new Rpg("Super Mario", 150.55);
			Jogo battlefield = new Luta("Battlefield", 700.45);

			assertTrue(usuarioVeterano.compraJogo(crossfire));
			assertTrue(usuarioVeterano.compraJogo(pointBlack));
			assertTrue(usuarioVeterano.compraJogo(superMario));
			assertFalse(usuarioVeterano.compraJogo(battlefield));

		} catch (DadosInvalidosException e) {
			fail(); //nao deve gerar exception
		}

		try {

			usuarioVeterano.compraJogo(null);
		} catch (DadosInvalidosException e) {
			assertEquals("Jogo nao pode ser nulo",e.getMessage());
		}
	}

	@Test
	public void testUsuarioNoob() {
		try {

			Usuario usuarioVeterano = new UsuarioVeterano("Luiz", "luiz.silva");

		} catch (DadosInvalidosException e) {
			fail(); // nao deve gerar exception
		}

		try {

			Usuario usuarioVeterano = new UsuarioVeterano("", "luiz.silva");
			fail();
		} catch (DadosInvalidosException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}

		try {

			Usuario usuarioVeterano = new UsuarioVeterano(null, "luiz.silva");
			fail();
		} catch (DadosInvalidosException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}

		try {

			Usuario usuarioVeterano = new UsuarioVeterano("Luiz", "");
			fail();
		} catch (DadosInvalidosException e) {
			assertEquals("login nao pode ser nulo ou vazio", e.getMessage());
		}

		try {

			Usuario usuarioVeterano = new UsuarioVeterano("Luiz", null);
			fail();
		} catch (DadosInvalidosException e) {
			assertEquals("login nao pode ser nulo ou vazio", e.getMessage());
		}
	}


	@Test
	public void testAdicionaDinrheiro() {
		try {

			usuarioVeterano.adicionaDinheiro(1000.55);
			assertEquals(1000.55, usuarioVeterano.getQuantDinrheiro(), 0.01);

		} catch (DadosInvalidosException e) {
			fail(); //nao deve gerar exception
		}

		try {

			usuarioVeterano.adicionaDinheiro(-1000.55);
			fail();
		} catch (DadosInvalidosException e) {
			assertEquals("Quantidade de dinheiro nao pode ser negativa.", e.getMessage());
		}
	}

	@Test
	public void testAumentaXp2() {
		try {
			usuarioVeterano.aumentaXp2(100);

			assertEquals(100, usuarioVeterano.getXp2());

		} catch (DadosInvalidosException e) {
			fail(); //nao deve gerar exception
		}

		try {
			usuarioVeterano.aumentaXp2(-100);
			fail();			
		} catch (DadosInvalidosException e) {
			assertEquals("Xp2 invalido!", e.getMessage());
		}
	}


	@Test
	public void testAdicionaJogabilidade(){
		try {
			usuarioVeterano.adicionaDinheiro(1000.00);
			Jogo crossfire = new Luta("Crossfire", 100.99);
			
			assertTrue(usuarioVeterano.compraJogo(crossfire));
			
			usuarioVeterano.adicionaJogabilidade("Crossfire", Jogabilidade.COMPETITIVO);
			usuarioVeterano.adicionaJogabilidade("Crossfire", Jogabilidade.COOPERATIVO);
			usuarioVeterano.adicionaJogabilidade("Crossfire", Jogabilidade.MULTIPLAYER);
			
		} catch (Exception e) {
			fail(); // nao deve gerar exception
		}
		
		try {
			usuarioVeterano.adicionaJogabilidade("Crossfire", null);
			fail(); // deve gerar exception
		} catch (SteamException e) {
			assertEquals("Jogabilidade nao pode ser nula", e.getMessage());
		}
		
		try {
			usuarioVeterano.adicionaJogabilidade("", Jogabilidade.OFFLINE);
			fail(); // deve gerar exception
		} catch (SteamException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}
		
		try {
			usuarioVeterano.adicionaJogabilidade(null, Jogabilidade.OFFLINE);
			fail(); // deve gerar exception
		} catch (SteamException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}
	}
	
	@Test
	public void testRegistraJogada() {
		try {
			usuarioVeterano.adicionaDinheiro(1000.00);
			Jogo crossfire = new Luta("Crossfire", 100.99);
			Jogo pointBlack = new Plataforma("PointBlack", 200.85);
			Jogo superMario = new Rpg("Super Mario", 150.55);

			assertTrue(usuarioVeterano.compraJogo(crossfire));
			assertTrue(usuarioVeterano.compraJogo(pointBlack));
			assertTrue(usuarioVeterano.compraJogo(superMario));

			usuarioVeterano.registraJogada("Crossfire", 1000, true);
			usuarioVeterano.registraJogada("Super Mario", 1000, true);
			usuarioVeterano.registraJogada("PointBlack", 1000, true);
			
			assertEquals(6781, usuarioVeterano.getXp2());
		} catch (Exception e) {
			fail(); //nao deve gerar exception
		}

		try {			
			usuarioVeterano.registraJogada("", 1000, true);
			fail(); //deve gerar uma exception
		} catch (SteamException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}

		try {			
			usuarioVeterano.registraJogada(null, 1000, true);
			fail(); //deve gerar uma exception
		} catch (SteamException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}

		try {			
			usuarioVeterano.registraJogada("PointBlack", -1000, true);
			fail(); //deve gerar uma exception
		} catch (SteamException e) {
			assertEquals("Score nao pode ser negativo", e.getMessage());
		}
	}

	@Test
	public void testGetLogin() {
		assertEquals("luiz.silva", usuarioVeterano.getLogin());
	}

	@Test
	public void testGetQuantDinrheiro() {
		try {
			usuarioVeterano.adicionaDinheiro(10000.00);
			
			assertEquals(10000.00, usuarioVeterano.getQuantDinrheiro(), 0.01);
		} catch (DadosInvalidosException e) {
			fail(); // nao deve gerar exception
		}
	}


	@Test
	public void testGetNome() {
		assertEquals("Luiz", usuarioVeterano.getNome());
	}

	@Test
	public void testGetXp2() {
		try {
			usuarioVeterano.aumentaXp2(2000);
			
			
		} catch (DadosInvalidosException e) {
			fail(); //nao deve gerar exception
		}
	}


	@Test
	public void testEqualsObject() {
		try {
			Usuario lucas = new UsuarioNoob("Luiz", "luiz.silva");
			Usuario luiz = new UsuarioVeterano("Luiz", "luiz.silva");
			
			assertTrue(usuarioVeterano.equals(luiz));
			assertFalse(usuarioVeterano.equals(lucas));
		} catch (DadosInvalidosException e) {
			fail(); // nao deve gerar exception
		}
	}
	
	@Test
	public void testToString(){
		final String FIM_DE_LINHA = System.lineSeparator();
		try {
		
			usuarioVeterano.adicionaDinheiro(300.00);
			Jogo metalGear = new Luta("Metal Gear", 200.85);
			usuarioVeterano.compraJogo(metalGear);
			
			usuarioVeterano.registraJogada("Metal Gear", 1000, true);
		} catch (SteamException e) {
			fail(); //nao deve gerar exception
		}
		String comparador = "luiz.silva" + FIM_DE_LINHA
				+ "Luiz - Jogador Veterano" + FIM_DE_LINHA
				+ "Lista de jogos:" + FIM_DE_LINHA
				+ "+ Metal Gear - LUTA:" + FIM_DE_LINHA
				+ "==> Jogou 1 vez(es)" + FIM_DE_LINHA
				+ "==> Zerou 1 vez(es)" + FIM_DE_LINHA
				+ "==> Maior Score: 1000" + FIM_DE_LINHA + FIM_DE_LINHA
				+ "Total de preco dos jogos: R$ 200,85" + FIM_DE_LINHA;
		
		assertEquals(comparador, usuarioVeterano.toString());
	}
}
