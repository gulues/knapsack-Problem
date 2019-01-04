package mochila;

import java.util.HashSet;
import java.util.Set;

public class Conjunto
{
	private Set<Objeto> _objetos;
	
	public Conjunto()
	{
		_objetos = new HashSet<Objeto>();
	}

	public void agregar(Objeto actual)
	{
		_objetos.add(actual);
	}

	public void eliminar(Objeto actual)
	{
		_objetos.remove(actual);
	}
	
	@Override
	public String toString() {
		return "Conjunto [_objetos=" + _objetos + "]";
	}

	public double peso()
	{
		double ret = 0;
		for(Objeto objeto: _objetos)
			ret += objeto.getPeso();
		
		return ret;
	}

	public double beneficio()
	{
		double ret = 0;
		for(Objeto objeto: _objetos)
			ret += objeto.getBeneficio();
		
		return ret;
	}

	public Conjunto clonar()
	{
		// Deep copy
		Conjunto ret = new Conjunto();
		for(Objeto objeto: _objetos)
			ret.agregar(objeto);
		
		return ret;
	}

	public int tamano()
	{
		return _objetos.size();
	}

	public boolean contiene(Objeto objeto)
	{
		return _objetos.contains(objeto);
	}
	
}





