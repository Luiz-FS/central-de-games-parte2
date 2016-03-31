package teste.loja;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import loja.Loja;

import org.junit.Before;
import org.junit.Test;

import enumerations.ExperienciaUsuario;
import enumerations.Jogabilidade;
import enumerations.TipoDeJogo;

public class lojaTest {

	private Loja centralP2;
	
	@Before
	public void Loja() {
		centralP2 = new Loja();
	}

	@Test
	public void testAddUsuario() {
		assertTrue(centralP2.addUsuario("Luiz", "luiz.silva", ExperienciaUsuario.VETERANO));
		assertTrue(centralP2.addUsuario("Maiza", "maiza.leal", ExperienciaUsuario.NOOB));
		assertFalse(centralP2.addUsuario("Luiz", "luiz.silva", ExperienciaUsuario.VETERANO));
		
	}

	@Test
	public void testAddDinheioUsuario() {
		assertTrue(centralP2.addUsuario("Luiz", "luiz.silva", ExperienciaUsuario.VETERANO));
		
		assertTrue(centralP2.addDinheioUsuario("luiz.silva", 1000.55));
	}


	@Test
	public void testAddJogabilidade() {
		assertTrue(centralP2.addUsuario("Luiz", "luiz.silva", ExperienciaUsuario.VETERANO));
		assertTrue(centralP2.addDinheioUsuario("luiz.silva", 1000.55));
		assertTrue(centralP2.venderJogo("luiz.silva", "Metal Gear", 100.55, TipoDeJogo.LUTA));
		
		assertTrue(centralP2.addJogabilidade("luiz.silva","Metal Gear", Jogabilidade.COMPETITIVO));
		assertTrue(centralP2.addJogabilidade("luiz.silva","Metal Gear", Jogabilidade.MULTIPLAYER));
		assertTrue(centralP2.addJogabilidade("luiz.silva","Metal Gear", Jogabilidade.COOPERATIVO));
		assertTrue(centralP2.addJogabilidade("luiz.silva","Metal Gear", Jogabilidade.OFFLINE));
		assertFalse(centralP2.addJogabilidade("maiza.leal","Metal Gear", Jogabilidade.COMPETITIVO));
	}


	@Test
	public void testContainUsuario() {
		assertTrue(centralP2.addUsuario("Luiz", "luiz.silva", ExperienciaUsuario.VETERANO));
		assertTrue(centralP2.containUsuario("luiz.silva"));
		assertFalse(centralP2.containUsuario("maiza.leal"));
	}

	@Test
	public void testRegistraJogada() {
		
		assertTrue(centralP2.addUsuario("Luiz", "luiz.silva", ExperienciaUsuario.VETERANO));
		assertTrue(centralP2.addDinheioUsuario("luiz.silva", 1000.55));
		assertTrue(centralP2.venderJogo("luiz.silva", "Metal Gear", 100.55, TipoDeJogo.LUTA));
		
		assertTrue(centralP2.registraJogada("luiz.silva", "Metal Gear", 20000, true));
		assertFalse(centralP2.registraJogada("maiza.leal", "Metal Gear", 20000, true));
	}


	@Test
	public void testUpgrade() {
		assertTrue(centralP2.addUsuario("Luiz", "luiz.silva", ExperienciaUsuario.NOOB));
		assertTrue(centralP2.addUsuario("Maiza", "maiza.leal", ExperienciaUsuario.VETERANO));
		assertTrue(centralP2.addUsuario("Lucas", "lucas.silva", ExperienciaUsuario.NOOB));
		
		assertTrue(centralP2.addDinheioUsuario("luiz.silva", 1000000.55));
		
		assertTrue(centralP2.venderJogo("luiz.silva", "Metal Gear", 1000.55, TipoDeJogo.LUTA));
		assertTrue(centralP2.venderJogo("luiz.silva", "PointBlack", 1000.55, TipoDeJogo.LUTA));
		
		assertTrue(centralP2.registraJogada("luiz.silva", "Metal Gear", 20000, true));
		assertTrue(centralP2.registraJogada("luiz.silva", "PointBlack", 20000, true));
		
		assertTrue(centralP2.upgradeUsuario("luiz.silva"));
		assertFalse(centralP2.upgradeUsuario("maiza.leal"));
		assertFalse(centralP2.upgradeUsuario("lucas.silva"));
	}


	@Test
	public void testVenderJogo() {
		assertTrue(centralP2.addUsuario("Luiz", "luiz.silva", ExperienciaUsuario.NOOB));
		
		assertTrue(centralP2.addDinheioUsuario("luiz.silva", 1000000.55));
		
		assertTrue(centralP2.venderJogo("luiz.silva", "Metal Gear", 1000.55, TipoDeJogo.LUTA));
		assertTrue(centralP2.venderJogo("luiz.silva", "PointBlack", 1000.55, TipoDeJogo.LUTA));
	}


	@Test
	public void testUsucarioContainsJogo() {
		assertTrue(centralP2.addUsuario("Luiz", "luiz.silva", ExperienciaUsuario.NOOB));
		
		assertTrue(centralP2.addDinheioUsuario("luiz.silva", 1000000.55));
		
		assertTrue(centralP2.venderJogo("luiz.silva", "Metal Gear", 1000.55, TipoDeJogo.LUTA));
		assertTrue(centralP2.venderJogo("luiz.silva", "PointBlack", 1000.55, TipoDeJogo.LUTA));
		
		assertTrue(centralP2.usuarioContainsJogo("luiz.silva", "Metal Gear"));
		assertTrue(centralP2.usuarioContainsJogo("luiz.silva", "PointBlack"));
		assertFalse(centralP2.usuarioContainsJogo("luiz.silva", "LOL"));
	}
	
	@Test
	public void testEquals(){
		assertTrue(centralP2.addUsuario("Luiz", "luiz.silva", ExperienciaUsuario.VETERANO));
		assertTrue(centralP2.addUsuario("Maiza", "maiza.leal", ExperienciaUsuario.NOOB));
		
		Loja outraLoja = new Loja();
		assertTrue(outraLoja.addUsuario("Luiz", "luiz.silva", ExperienciaUsuario.VETERANO));
		assertTrue(outraLoja.addUsuario("Maiza", "maiza.leal", ExperienciaUsuario.NOOB));
		
		Loja terceiraLoja = new Loja();
		assertTrue(terceiraLoja.addUsuario("Lucas", "lucas.silva", ExperienciaUsuario.VETERANO));
		assertTrue(terceiraLoja.addUsuario("Maiza", "maiza.leal", ExperienciaUsuario.NOOB));
		
		assertTrue(centralP2.equals(outraLoja));
		assertFalse(centralP2.equals(terceiraLoja));
	}

	@Test
	public void testToString() {
		centralP2.addUsuario("Luiz", "luiz.silva", ExperienciaUsuario.NOOB);
		centralP2.addDinheioUsuario("luiz.silva", 1000.55);
		centralP2.venderJogo("luiz.silva", "Metal Gear", 200.85, TipoDeJogo.LUTA);
		centralP2.registraJogada("luiz.silva", "Metal Gear", 1000, true);
		
		final String FIM_DE_LINHA = System.lineSeparator();
		
		String comparador = "=== Central P2-CG ===" + FIM_DE_LINHA + FIM_DE_LINHA
							+ "luiz.silva" + FIM_DE_LINHA
							+"Luiz - Jogador Noob" + FIM_DE_LINHA
							+"Lista de jogos:" + FIM_DE_LINHA
							+"+ Metal Gear - LUTA:" + FIM_DE_LINHA
							+"==> Jogou 1 vez(es)" + FIM_DE_LINHA
							+"==> Zerou 1 vez(es)" + FIM_DE_LINHA
							+"==> Maior Score: 1000" + FIM_DE_LINHA + FIM_DE_LINHA
							+"Total de preco dos jogos: R$ 200,85" + FIM_DE_LINHA + FIM_DE_LINHA
							+ "-------------------------------------------------------";
		
		assertEquals(comparador, centralP2.toString());
		
	}
}
