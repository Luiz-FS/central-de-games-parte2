package teste.jogo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ObjectStreamException;

import jogos.Plataforma;

import org.junit.Before;
import org.junit.Test;

import enumerations.Jogabilidade;
import exceptions.ConstanteException;
import exceptions.DadosInvalidosException;
import exceptions.NumeroInvalidoException;
import exceptions.ObjetoinvalidoException;
import exceptions.SteamException;
import exceptions.StringException;

public class JogoPlataformaTest {

	private Plataforma metalGear;

	@Before
	public void contrutor()throws Exception{
		metalGear = new Plataforma("Metal Gear", 100.99);
	}

	@Test
	public void testPlataforma() {
		try {
			Plataforma jogoPlataforma = new Plataforma("Metal Gear", 200.00);
		} catch (Exception e) {
			fail(); //nao deve gerar exception
		}

		try {
			Plataforma jogoPlataforma = new Plataforma("", 200.00);
			fail(); //deve gerar exception
			
		}catch(StringException exception){
			assertEquals("Nome nao pode ser nulo ou vazio", exception.getMessage());
			
		}catch (DadosInvalidosException exception){
			assertEquals("Nome nao pode ser nulo ou vazio", exception.getMessage());
			
		}catch (Exception e) {
			fail();
		}

		try {
			Plataforma jogoPlataforma = new Plataforma(null, 200.00);
			fail(); //deve gerar exception
			
		}catch(StringException exception){
			assertEquals("Nome nao pode ser nulo ou vazio", exception.getMessage());
			
		}catch (DadosInvalidosException exception){
			assertEquals("Nome nao pode ser nulo ou vazio", exception.getMessage());
			
		}catch (Exception e) {
			fail();
		}

		try {
			Plataforma jogoPlataforma = new Plataforma("Metal Gear", -200.00);
			fail();
			
		} catch (NumeroInvalidoException exception){
			assertEquals("Preco nao pode ser negativo", exception.getMessage());

		}catch (DadosInvalidosException exception){
			assertEquals("Preco nao pode ser negativo", exception.getMessage());

		}catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testRegistraJogada() {
		try {
			assertEquals(20, metalGear.registraJogada(20000, true));
		} catch (Exception e) {
			fail(); //nao deve gerar exception
		}

		try {
			metalGear.registraJogada(-20000, true);
			fail(); //deve gerar uma exception
			
		}catch(NumeroInvalidoException exception){
			assertEquals("Score nao pode ser negativo", exception.getMessage());
			
		}catch (DadosInvalidosException exception){
			assertEquals("Score nao pode ser negativo", exception.getMessage());
			
		}catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testAdicionaJogabilidade() {
		try {

			assertTrue(metalGear.adicionaJogabilidade(Jogabilidade.COMPETITIVO));
			assertTrue(metalGear.adicionaJogabilidade(Jogabilidade.MULTIPLAYER));
			assertTrue(metalGear.adicionaJogabilidade(Jogabilidade.OFFLINE));
			assertTrue(metalGear.adicionaJogabilidade(Jogabilidade.ONLLINE));
			assertFalse(metalGear.adicionaJogabilidade(Jogabilidade.COMPETITIVO));
		} catch (Exception e) {
			fail(); // nao deve gerar uma exception
		}

		try {

			metalGear.adicionaJogabilidade(null);
			fail(); // deve gerar uma excepiton
			
		}catch (ConstanteException exception){
			assertEquals("Jogabilidade nao pode ser nula", exception.getMessage());
			
		}catch (SteamException exception){
			assertEquals("Jogabilidade nao pode ser nula", exception.getMessage());
			
		}catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetNome() {
		assertEquals("Metal Gear", metalGear.getNome());
	}

	@Test
	public void testGetPreco() {
		assertEquals(100.99, metalGear.getPreco(), 0.01);
	}

	@Test
	public void testSetPreco() {
		try {
			metalGear.setPreco(200.99);
			assertEquals(200.99, metalGear.getPreco(), 0.01);
		} catch (Exception e) {
			fail(); //nao deve gerar uma exception
		}

		try {
			metalGear.setPreco(-200.99);
			fail();

		} catch (NumeroInvalidoException exception){
			assertEquals("Preco nao pode ser negativo", exception.getMessage());

		}catch (DadosInvalidosException exception){
			assertEquals("Preco nao pode ser negativo", exception.getMessage());

		}catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetMaiorScore() {
		try {
			metalGear.registraJogada(20, false);
			assertEquals(20, metalGear.getMaiorScore());
		} catch (Exception e) {
			fail(); //nao deve gerar exception
		}
	}

	@Test
	public void testGetQuantidadeVezesJogadas() {
		try {
			metalGear.registraJogada(10, true);
			metalGear.registraJogada(20, true);

			assertEquals(2, metalGear.getVezesZeradas());
			assertEquals(2, metalGear.getQuantidadeVezesJogadas());

			assertEquals(2, metalGear.getQuantidadeVezesJogadas());
		} catch (Exception e) {
			fail(); //nao deve gerar exception
		}
	}

	@Test
	public void testGetVezesZeradas() {
		try {
			metalGear.registraJogada(10, true);
			metalGear.registraJogada(20, true);

			assertEquals(2, metalGear.getVezesZeradas());
		} catch (Exception e) {
			fail(); //nao deve gerar exception
		}
	}

	@Test
	public void testEqualsObject() {
		try {

			Plataforma crossFire = new Plataforma("Metal Gear", 100.99);
			Plataforma pointBlack = new Plataforma("Point Black", 100.99);

			assertTrue(metalGear.equals(crossFire));
			assertFalse(metalGear.equals(pointBlack));
		} catch (Exception e) {
			fail(); //nao deve gerar exception
		}
	}

	@Test
	public void testToString() {
		try {
			metalGear.registraJogada(1000, true);
		} catch (SteamException e) {
			fail(); // nao deve gerar exception
		}
		
		final String FIM_DE_LINHA = System.lineSeparator();
		
		String comparacao = "+ Metal Gear - PLATAFORMA:" + FIM_DE_LINHA 
							+ "==> Jogou 1 vez(es)" + FIM_DE_LINHA
							+ "==> Zerou 1 vez(es)" + FIM_DE_LINHA
							+ "==> Maior Score: 1000" + FIM_DE_LINHA;
		
		
		assertEquals(comparacao, metalGear.toString());
	}
}
