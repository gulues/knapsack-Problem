package mochila;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SolverGoloso
{
	// Instancia a resolver
	private Instancia _instancia;
	
	public SolverGoloso(Instancia instancia)
	{
		_instancia = instancia;
	}
	
	// Heurística golosa por peso
	public Conjunto resolver(Comparator<Objeto> comparador)
	{
		Conjunto ret = new Conjunto();
		ArrayList<Objeto> ordenados = ordenarObjetos(comparador);
		
		for(Objeto objeto: ordenados)
		{
			if( ret.peso() + objeto.getPeso() <= _instancia.getCapacidad() )
				ret.agregar(objeto);
		}
		
		return ret;
	}
	
	// Ordenamiento por peso
	private ArrayList<Objeto> ordenarObjetos(Comparator<Objeto> comparador)
	{
		ArrayList<Objeto> ret = _instancia.getObjetos();
		Collections.sort(ret, comparador);
		
		return ret;
	}
}












