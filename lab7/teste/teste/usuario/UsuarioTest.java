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
	public void testAdicionaDinheiro() {
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
	public void testRecompensar() {
		try {
			usuario.adicionaDinheiro(1000.00);
			Jogo crossfire = new Luta("Crossfire", 100.99, jogabilidades);
			Jogo pointBlack = new Plataforma("PointBlack", 200.85, jogabilidades);
			Jogo superMario = new Rpg("Super Mario", 150.55, jogabilidades);

			assertTrue(usuario.compraJogo(crossfire));
			assertTrue(usuario.compraJogo(pointBlack));
			assertTrue(usuario.compraJogo(superMario));
			assertEquals(6279, usuario.getXp2());

			usuario.recompensar("Crossfire", 1000, true);
			usuario.recompensar("Super Mario", 1000, true);
			usuario.recompensar("PointBlack", 1000, true);

			assertEquals(6400, usuario.getXp2());
		} catch (SteamException e) {
			fail(); //nao deve gerar exception
		}

		try {			
			usuario.recompensar("", 1000, true);
			fail(); //deve gerar uma exception
		} catch (SteamException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}

		try {			
			usuario.recompensar(null, 1000, true);
			fail(); //deve gerar uma exception
		} catch (SteamException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}

		try {			
			usuario.recompensar("PointBlack", -1000, true);
			fail(); //deve gerar uma exception
		} catch (SteamException e) {
			assertEquals("Score nao pode ser negativo", e.getMessage());
		}
	}
	
	@Test
	public void testPunir() {
		try {
			usuario.adicionaDinheiro(1000.00);
			Jogo crossfire = new Luta("Crossfire", 100.99, jogabilidades);
			Jogo pointBlack = new Plataforma("PointBlack", 200.85, jogabilidades);
			Jogo superMario = new Rpg("Super Mario", 150.55, jogabilidades);

			assertTrue(usuario.compraJogo(crossfire));
			assertTrue(usuario.compraJogo(pointBlack));
			assertTrue(usuario.compraJogo(superMario));
			assertEquals(6279, usuario.getXp2());
			
			usuario.punir("Crossfire", 1000, true);
			usuario.punir("Super Mario", 1000, true);
			usuario.punir("PointBlack", 1000, true);

			assertEquals(6190, usuario.getXp2());
		} catch (SteamException e) {
			fail(); //nao deve gerar exception
		}

		try {			
			usuario.punir("", 1000, true);
			fail(); //deve gerar uma exception
		} catch (SteamException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}

		try {			
			usuario.punir(null, 1000, true);
			fail(); //deve gerar uma exception
		} catch (SteamException e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}

		try {			
			usuario.punir("PointBlack", -1000, true);
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
			
			assertEquals(1009, usuario.getXp2());
			
		} catch (SteamException e) {
			fail(); //nao deve gerar exception
		}
	}


	@Test
	public void testEqualsObject() {
		try {
			Usuario luiz = new Usuario("Luiz", "luiz.silva");
			Usuario maiza = new Usuario("Maiza", "maiza.leal");
			
			assertTrue(usuario.equals(luiz));
			assertFalse(usuario.equals(maiza));
		} catch (DadosInvalidosException e) {
			fail(); // nao deve gerar exception
		}
	}
	
	@Test
	public void testToString(){
		final String FIM_DE_LINHA = System.lineSeparator();
		try {
		
			usuario.adicionaDinheiro(300.00);
			Jogo metalGear = new Luta("Metal Gear", 99.85, jogabilidades);
			usuario.compraJogo(metalGear);
			
			usuario.recompensar("Metal Gear", 1000, true);
		} catch (SteamException e) {
			fail(); //nao deve gerar exception
		}
		
		String comparador = "Jogador Veterano: luiz.silva" + FIM_DE_LINHA
				+ "Luiz - 1009 x2p" + FIM_DE_LINHA
				+ "Lista de jogos:" + FIM_DE_LINHA
				+ "+ Metal Gear - LUTA:" + FIM_DE_LINHA
				+ "==> Jogou 1 vez(es)" + FIM_DE_LINHA
				+ "==> Zerou 1 vez(es)" + FIM_DE_LINHA
				+ "==> Maior Score: 1000" + FIM_DE_LINHA + FIM_DE_LINHA
				+ "Total de preco dos jogos: R$ 99,85" + FIM_DE_LINHA + FIM_DE_LINHA
				+ "--------------------------------------------------------------------" 
				+ FIM_DE_LINHA;
		
		assertEquals(comparador, usuario.toString());
		
		try {
			
			usuario.punir("Metal Gear", 3000, true);
		} catch (SteamException e) {
			fail(); //nao deve gerar exception
		}
		
		String comparador2 = "Jogador Noob: luiz.silva" + FIM_DE_LINHA
				+ "Luiz - 972 x2p" + FIM_DE_LINHA
				+ "Lista de jogos:" + FIM_DE_LINHA
				+ "+ Metal Gear - LUTA:" + FIM_DE_LINHA
				+ "==> Jogou 2 vez(es)" + FIM_DE_LINHA
				+ "==> Zerou 2 vez(es)" + FIM_DE_LINHA
				+ "==> Maior Score: 3000" + FIM_DE_LINHA + FIM_DE_LINHA
				+ "Total de preco dos jogos: R$ 99,85" + FIM_DE_LINHA + FIM_DE_LINHA
				+ "--------------------------------------------------------------------" 
				+ FIM_DE_LINHA;
		
		assertEquals(comparador2, usuario.toString());
	}
}
