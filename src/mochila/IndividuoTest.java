package mochila;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class IndividuoTest
{
	private Instancia _instancia;
	
	@Before
	public void inicializarInstancia()
	{
		_instancia = new Instancia(10);
		_instancia.agregar(new Objeto("Obj1", 1, 10));
		_instancia.agregar(new Objeto("Obj2", 2, 20));
		_instancia.agregar(new Objeto("Obj3", 3, 30));
		_instancia.agregar(new Objeto("Obj4", 4, 40));
		_instancia.agregar(new Objeto("Obj5", 5, 50));
	}
	
	@Test
	public void constructorTest()
	{
		testear("C(00000) = 00000");
		testear("C(11010) = 11010");
		testear("C(11111) = 11111");
	}
	
	@Test
	public void mutarTest()
	{
		testear("M(11001, 0) = 01001");
		testear("M(11001, 2) = 11101");
		testear("M(11001, 4) = 11000");
	}
	
	@Test
	public void recombinarTest()
	{
		testear("R(11001, 00110, 3) = (11010, 00101)");
		testear("R(11001, 00110, 0) = (00110, 11001)");
		testear("R(11001, 00110, 4) = (11000, 00111)");
		testear("R(11001, 00110, 5) = (11001, 00110)");
	}
	
	@Test
	public void pesoTest()
	{
		Individuo individuo = construir("11001");
		assertEquals(8, individuo.peso(), 1e-5);
	}
	
	@Test
	public void beneficioTest()
	{
		Individuo individuo = construir("11001");
		assertEquals(80, individuo.beneficio(), 1e-5);
	}

	private void testear(String comando)
	{
		if( comando.startsWith("C"))
			testearConstruccion(comando);
		
		if( comando.startsWith("M"))
			testearMutacion(comando);

		if( comando.startsWith("R"))
			testearRecombinacion(comando);
	}
	
	private void testearConstruccion(String comando)
	{
		Pattern pattern = Pattern.compile("C\\(([01]+)\\) = ([01]+)");
		Matcher matcher = pattern.matcher(comando);
		
		assertTrue(matcher.matches());
		
		Individuo individuo = construir( matcher.group(1) );
		assertIguales(matcher.group(2), individuo);
	}
	
	private void testearMutacion(String comando)
	{
		Pattern pattern = Pattern.compile("M\\(([01]+), ([0-9]+)\\) = ([01]+)");
		Matcher matcher = pattern.matcher(comando);
		
		assertTrue(matcher.matches());
		
		Individuo individuo = construir( matcher.group(1) );
		mutar(individuo, Integer.parseInt(matcher.group(2)));
		assertIguales(matcher.group(3), individuo);
	}
	
	private void testearRecombinacion(String comando)
	{
		Pattern pattern = Pattern.compile("R\\(([01]+), ([01]+), ([0-9]+)\\) = \\(([01]+), ([01]+)\\)");
		Matcher matcher = pattern.matcher(comando);
		
		assertTrue(matcher.matches());
		
		Individuo padre1 = construir( matcher.group(1) );
		Individuo padre2 = construir( matcher.group(2) );
		
		Individuo[] hijos = recombinar(padre1, padre2, Integer.parseInt(matcher.group(3)));
		
		assertIguales(matcher.group(4), hijos[0]);
		assertIguales(matcher.group(5), hijos[1]);
	}
	
	private Individuo construir(String bits)
	{
		// Construimos la secuencia de bits
		boolean[] valores = new boolean[ bits.length() ];
		
		for(int i=0; i<bits.length(); ++i)
			valores[i] = bits.charAt(i) == '1';
		
		// Generamos un individuo con estos valores
		Generador generador = new GeneradorPrefijado(valores, 0);
		Individuo.setGenerador(generador);
		
		return new Individuo(_instancia);
	}
	
	private void mutar(Individuo individuo, int bit)
	{
		Generador generador = new GeneradorPrefijado(null, bit);
		Individuo.setGenerador(generador);
		individuo.mutar();
	}
	
	private Individuo[] recombinar(Individuo padre1, Individuo padre2, int bit)
	{
		Generador generador = new GeneradorPrefijado(null, bit);
		Individuo.setGenerador(generador);
		
		return padre1.recombinar(padre2);
	}
	
	private void assertIguales(String bits, Individuo individuo)
	{
		for(int i=0; i<bits.length(); ++i)
		{
			boolean valor = bits.charAt(i) == '1';
			assertEquals(valor, individuo.get(i));
		}
	}
}
