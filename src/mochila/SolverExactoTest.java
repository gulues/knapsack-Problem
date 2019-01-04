package mochila;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SolverExactoTest
{
	@Before
	public void inicializar()
	{
		_carpa = new Objeto("Carpa", 4, 10);
		_linterna = new Objeto("Linterna", 1, 3);
		_hacha = new Objeto("Hacha", 6, 8);
		_campera = new Objeto("Campera", 4, 7);
		_bolsa = new Objeto("B. Dormir", 3, 8);
		
	}
	
	private Objeto _carpa;
	private Objeto _linterna;
	private Objeto _hacha;
	private Objeto _campera;
	private Objeto _bolsa;
	
	@Test
	public void generacionTest()
	{
		SolverExacto solver = new SolverExacto(construir(10));
		Conjunto optimo = solver.resolver();
		
		assertIguales(new Objeto[] { _carpa, _linterna, _bolsa }, optimo);
	}
	
	@Test
	public void capacidadCeroTest()
	{
		SolverExacto solver = new SolverExacto(construir(0));
		Conjunto optimo = solver.resolver();
		
		assertIguales(new Objeto[] { }, optimo);
	}
	
	@Test
	public void capacidadCompletaTest()
	{
		SolverExacto solver = new SolverExacto(construir(18));
		Conjunto optimo = solver.resolver();
		
		assertIguales(new Objeto[] { _carpa, _linterna, _bolsa, _hacha, _campera }, optimo);
	}
	
	@Test
	public void unoSoloTest()
	{
		SolverExacto solver = new SolverExacto(construir(1));
		Conjunto optimo = solver.resolver();
		
		assertIguales(new Objeto[] { _linterna }, optimo);
	}
	
	public Instancia construir(double capacidad)
	{
		Instancia ret = new Instancia(capacidad);
		
		ret.agregar(_carpa);
		ret.agregar(_linterna);
		ret.agregar(_hacha);
		ret.agregar(_campera);
		ret.agregar(_bolsa);
		
		return ret;
	}

	// Compara que los conjuntos sean iguales
	private void assertIguales(Objeto[] objetos, Conjunto optimo)
	{
		assertEquals(objetos.length, optimo.tamano());
		
		for(Objeto objeto: objetos)
			assertTrue(optimo.contiene(objeto));
	}
}




