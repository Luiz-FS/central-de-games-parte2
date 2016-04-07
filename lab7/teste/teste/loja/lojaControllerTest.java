package teste.loja;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import loja.LojaController;

import org.junit.Before;
import org.junit.Test;

import enumerations.EsperienciaUsuario;
import enumerations.Jogabilidade;
import enumerations.TipoDeJogo;
import exceptions.SteamException;

public class lojaControllerTest {

	private LojaController centralP2;
	private Set<Jogabilidade> jogabilidades;

	@Before
	public void Loja() {
		this.jogabilidades = new HashSet<Jogabilidade>();
		jogabilidades.add(Jogabilidade.COOPERATIVO);
		jogabilidades.add(Jogabilidade.COMPETITIVO);
		jogabilidades.add(Jogabilidade.MULTIPLAYER);
		jogabilidades.add(Jogabilidade.ONLLINE);

		centralP2 = new LojaController();
	}

	@Test
	public void testAddUsuario() {

		try{
			assertTrue(centralP2.addUsuario("Luiz", "luiz.silva", EsperienciaUsuario.NOOB));
			assertTrue(centralP2.addUsuario("Maiza", "maiza.leal", EsperienciaUsuario.NOOB));
			assertFalse(centralP2.addUsuario("Luiz", "luiz.silva", EsperienciaUsuario.NOOB));

		}catch(SteamException e){
			fail(); // nao deve gerar exception
		}
	}


	@Test
	public void testAddDinheioUsuario() {
		try{
			assertTrue(centralP2.addUsuario("Luiz", "luiz.silva", EsperienciaUsuario.NOOB));
			assertTrue(centralP2.addDinheioUsuario("luiz.silva", 1000.55));

		}catch(SteamException e){
			fail(); //nao deve gerar excpetion
		}
	}

	@Test
	public void testContainUsuario() {

		try{
			assertTrue(centralP2.addUsuario("Luiz", "luiz.silva", EsperienciaUsuario.NOOB));
			assertTrue(centralP2.containUsuario("luiz.silva"));
			assertFalse(centralP2.containUsuario("maiza.leal"));

		}catch(SteamException e){
			fail(); // nao deve gerar exception
		}
	}

	@Test
	public void testRecompensarUsuario() {

		try{
			assertTrue(centralP2.addUsuario("Luiz", "luiz.silva", EsperienciaUsuario.NOOB));
			assertTrue(centralP2.addDinheioUsuario("luiz.silva", 1000.55));
			assertTrue(centralP2.venderJogo("luiz.silva", "Metal Gear", 100.55,TipoDeJogo.LUTA, jogabilidades));

			assertTrue(centralP2.recompensarUsuario("luiz.silva", "Metal Gear", 20000, true));
			assertFalse(centralP2.recompensarUsuario("maiza.leal", "Metal Gear", 20000, true));

		}catch(SteamException e){
			fail(); // nao deve gerar exception
		}
	}

	@Test
	public void testPunirUsuario() {

		try{
			assertTrue(centralP2.addUsuario("Luiz", "luiz.silva", EsperienciaUsuario.NOOB));
			assertTrue(centralP2.addDinheioUsuario("luiz.silva", 1000.55));
			assertTrue(centralP2.venderJogo("luiz.silva", "Metal Gear", 100.55,TipoDeJogo.LUTA, jogabilidades));

			assertTrue(centralP2.punirUsuario("luiz.silva", "Metal Gear", 20000, true));
			assertFalse(centralP2.punirUsuario("maiza.leal", "Metal Gear", 20000, true));

		}catch(SteamException e){
			fail(); // nao deve gerar exception
		}
	}

	@Test
	public void testVenderJogo() {
		try{
			assertTrue(centralP2.addUsuario("Luiz", "luiz.silva", EsperienciaUsuario.NOOB));

			assertTrue(centralP2.addDinheioUsuario("luiz.silva", 1000000.55));

			assertTrue(centralP2.venderJogo("luiz.silva", "Metal Gear", 1000.55, TipoDeJogo.LUTA, jogabilidades));
			assertTrue(centralP2.venderJogo("luiz.silva", "PointBlack", 1000.55, TipoDeJogo.LUTA, jogabilidades));
		}catch(SteamException e){
			fail(); // nao deve gerar exception
		}
	}


	@Test
	public void testUsucarioContainsJogo() {
		try{
			assertTrue(centralP2.addUsuario("Luiz", "luiz.silva", EsperienciaUsuario.NOOB));

			assertTrue(centralP2.addDinheioUsuario("luiz.silva", 1000000.55));

			assertTrue(centralP2.venderJogo("luiz.silva", "Metal Gear", 1000.55, TipoDeJogo.LUTA, jogabilidades));
			assertTrue(centralP2.venderJogo("luiz.silva", "PointBlack", 1000.55, TipoDeJogo.LUTA, jogabilidades));

			assertTrue(centralP2.usuarioContainsJogo("luiz.silva", "Metal Gear"));
			assertTrue(centralP2.usuarioContainsJogo("luiz.silva", "PointBlack"));
			assertFalse(centralP2.usuarioContainsJogo("luiz.silva", "LOL"));
			
		}catch(SteamException e){
			fail(); // nao deve gerar exception
		}
	}

	@Test
	public void testEquals(){
		
		try{
		assertTrue(centralP2.addUsuario("Luiz", "luiz.silva", EsperienciaUsuario.NOOB));
		assertTrue(centralP2.addUsuario("Maiza", "maiza.leal", EsperienciaUsuario.NOOB));

		LojaController outraLoja = new LojaController();
		assertTrue(outraLoja.addUsuario("Luiz", "luiz.silva", EsperienciaUsuario.NOOB));
		assertTrue(outraLoja.addUsuario("Maiza", "maiza.leal", EsperienciaUsuario.NOOB));

		LojaController terceiraLoja = new LojaController();
		assertTrue(terceiraLoja.addUsuario("Lucas", "lucas.silva", EsperienciaUsuario.NOOB));
		assertTrue(terceiraLoja.addUsuario("Maiza", "maiza.leal", EsperienciaUsuario.NOOB));

		assertTrue(centralP2.equals(outraLoja));
		assertFalse(centralP2.equals(terceiraLoja));
		
		}catch(SteamException e){
			fail(); // nao deve gerar exception
		}
	}

	@Test
	public void testInfoUsuario() {
		try{
		centralP2.addUsuario("Luiz", "luiz.silva", EsperienciaUsuario.NOOB);
		centralP2.addDinheioUsuario("luiz.silva", 1000.55);
		centralP2.venderJogo("luiz.silva", "Metal Gear", 200.85, TipoDeJogo.LUTA, jogabilidades);
		centralP2.recompensarUsuario("luiz.silva", "Metal Gear", 1000, true);
		
		}catch(SteamException e){
			fail(); // nao deve gerar exception
		}
		final String FIM_DE_LINHA = System.lineSeparator();

		String comparador = "=== Central P2-CG ===" + FIM_DE_LINHA + FIM_DE_LINHA
							+ "Jogador Veterano: luiz.silva" + FIM_DE_LINHA
							+"Luiz - 2039 x2p" + FIM_DE_LINHA
							+"Lista de jogos:" + FIM_DE_LINHA
							+"+ Metal Gear - LUTA:" + FIM_DE_LINHA
							+"==> Jogou 1 vez(es)" + FIM_DE_LINHA
							+"==> Zerou 1 vez(es)" + FIM_DE_LINHA
							+"==> Maior Score: 1000" + FIM_DE_LINHA + FIM_DE_LINHA
							+"Total de preco dos jogos: R$ 200,85" + FIM_DE_LINHA + FIM_DE_LINHA
							+ "--------------------------------------------------------------------" + FIM_DE_LINHA;
		
		System.out.println(centralP2.infoUsuarios());
		assertEquals(comparador, centralP2.infoUsuarios());

	}
}
