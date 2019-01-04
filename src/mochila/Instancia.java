package mochila;

import java.util.ArrayList;

public class Instancia
{
	private double _capacidad;
	private ArrayList<Objeto> _objetos;
	
	public Instancia(double capacidad)
	{
		_capacidad = capacidad;
		_objetos = new ArrayList<Objeto>();
	}
	
	public void agregar(Objeto objeto)
	{
		_objetos.add(objeto);		
	}
	
	public double getCapacidad()
	{
		return _capacidad;
	}
	
	public int tamano()
	{
		return _objetos.size();
	}
	
	// Acceso O(1) a un objeto
	public Objeto getObjeto(int i)
	{
		if( i < 0 || i >= tamano() )
			throw new IllegalArgumentException("Se consultó un objeto inexistente! i = " + i);

		return _objetos.get(i);
	}
	
	// Acceso O(n) a la lista (copiada) de objetos
	@SuppressWarnings("unchecked")
	public ArrayList<Objeto> getObjetos()
	{
		return (ArrayList<Objeto>) _objetos.clone();
	}
}














