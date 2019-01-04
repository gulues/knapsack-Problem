package mochila;

public class SolverExacto
{
	// Instancia a resolver
	private Instancia _instancia;
	
	// Auxiliares para el algoritmo recursivo
	private Conjunto _conjunto;
	private Conjunto _candidato;
	
	// Tipo de algoritmo
	public enum Algoritmo { fuerzaBruta, backtracking };
	private Algoritmo _algoritmo;
	
	// Estadisticas
	private int _hojas;
	
	public SolverExacto(Instancia instancia)
	{
		_instancia = instancia;
		_algoritmo = Algoritmo.fuerzaBruta;
	}

	public SolverExacto(Instancia instancia, Algoritmo algoritmo)
	{
		_instancia = instancia;
		_algoritmo = algoritmo;
	}
	
	public Conjunto resolver()
	{
		_conjunto = new Conjunto();
		_candidato = null;
		_hojas = 0;
		
		construir(0);
		
		return _candidato;
	}
	
	private void construir(int indice)
	{
		if( indice == _instancia.tamano() )
		{
			// Caso base
			actualizarCandidato();
			_hojas += 1;
		}
		else if( _algoritmo == Algoritmo.fuerzaBruta
			  || _conjunto.peso() <= _instancia.getCapacidad() )
		{
			// Caso recursivo
			Objeto actual = _instancia.getObjeto(indice);
			
			_conjunto.agregar(actual);
			construir(indice+1);
			
			_conjunto.eliminar(actual);
			construir(indice+1);
		}
	}

	private void actualizarCandidato()
	{
		if( _conjunto.peso() <= _instancia.getCapacidad() )
		{
			if( _candidato == null || _conjunto.beneficio() > _candidato.beneficio() )
			{
				_candidato = _conjunto.clonar();
			}
		}
	}
	
	public int getHojas()
	{
		return _hojas;
	}
}













