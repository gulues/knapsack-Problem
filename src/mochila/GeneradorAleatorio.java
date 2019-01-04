package mochila;

import java.util.Random;

public class GeneradorAleatorio implements Generador
{
	private Random _random;
	
	public GeneradorAleatorio()
	{
		_random = new Random();
	}

	public GeneradorAleatorio(int semilla)
	{
		_random = new Random(semilla);
	}

	@Override
	public boolean nextBoolean()
	{
		return _random.nextBoolean();
	}

	@Override
	public int nextInt(int n)
	{
		return _random.nextInt(n);
	}
}
