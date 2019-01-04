package mochila;

// Representa un individuo de una población dentro de un
// algoritmo genético para el problema de la mochila

public class Individuo implements Comparable<Individuo>
{
	// Un individuo se representa por una secuencia de bits
	private boolean[] _bits;
	
	// Cada individuo está asociado a una instancia
	private Instancia _instancia;
	
	// Generador de números aleatorios
	private static Generador _generador;
	
	// Setter del generador de números aleatorios
	public static void setGenerador(Generador generador)
	{
		_generador = generador;
	}
	
	// Algunos individuos se construyen aleatoriamente
	public Individuo(Instancia instancia)
	{
		_instancia = instancia;
		_bits = new boolean[ _instancia.tamano() ];
		
		for(int i=0; i<_instancia.tamano(); ++i)
			_bits[i] = _generador.nextBoolean();
	}
	
	// Otros individuos se construyen a partir de una secuencia de bits
	private Individuo(Instancia instancia, boolean[] bits)
	{
		if( bits.length != instancia.tamano() )
			throw new IllegalArgumentException("Se intentó construir un individuo"
				+ "con una cantidad errónea de bits! bits = " + bits.length);

		_instancia = instancia;
		_bits = bits;
	}
	
	// Mutación
	public void mutar()
	{
		int i = _generador.nextInt( _bits.length );
		_bits[i] = _bits[i] ? false : true;
	}
	
	// Recombinación
	public Individuo[] recombinar(Individuo otro)
	{
		// Punto de recombinación
		int k = _generador.nextInt( _bits.length );
		
		// Bits de los dos hijos
		boolean[] bits1 = new boolean[_instancia.tamano()];
		boolean[] bits2 = new boolean[_instancia.tamano()];
		
		for(int i=0; i<k; ++i)
		{
			bits1[i] = this.get(i);
			bits2[i] = otro.get(i);
		}
		
		for(int i=k; i<_instancia.tamano(); ++i)
		{
			bits1[i] = otro.get(i);
			bits2[i] = this.get(i);
		}
		
		// Generamos los dos hijos
		Individuo hijo1 = new Individuo(_instancia, bits1);
		Individuo hijo2 = new Individuo(_instancia, bits2);
		
		// Y los retornamos
		return new Individuo[] { hijo1, hijo2 };
	}
	
	// Función de fitness
	public double fitness()
	{
		if( peso() <= _instancia.getCapacidad() )
			return beneficio();
		else
			return -(peso() - _instancia.getCapacidad());
	}
	
	// Peso total del individuo
	public double peso()
	{
		double ret = 0;
		for(int i=0; i<_instancia.tamano(); ++i) if( get(i) == true )
			ret += _instancia.getObjeto(i).getPeso();
		
		return ret;
	}

	// Beneficio total del individuo
	public double beneficio()
	{
		double ret = 0;
		for(int i=0; i<_instancia.tamano(); ++i) if( get(i) == true )
			ret += _instancia.getObjeto(i).getBeneficio();
		
		return ret;
	}

	// Getter sobre los bits (package private para los tests)
	boolean get(int i)
	{
		return _bits[i];
	}

	// Comparación
	@Override
	public int compareTo(Individuo otro)
	{
		if( this.fitness() < otro.fitness() )
			return -1;
		
		if( this.fitness() > otro.fitness() )
			return 1;
		
		return 0;
	}
}




















