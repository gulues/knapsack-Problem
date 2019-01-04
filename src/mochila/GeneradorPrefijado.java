package mochila;

public class GeneradorPrefijado implements Generador
{
	// Secuencia de valores "aleatorios" booleanos para responder
	private boolean[] _booleanos;
	
	// Indice al próximo booleano a retornar
	private int _indice;
	
	// Valor entero (siempre responde esto)
	private int _entero;
	
	public GeneradorPrefijado(boolean[] booleanos, int entero)
	{
		_booleanos = booleanos;
		_indice = 0;
		_entero = entero;
	}
	
	@Override
	public boolean nextBoolean()
	{
		return _booleanos[_indice++];
	}

	@Override
	public int nextInt(int n)
	{
		return _entero;
	}
}





