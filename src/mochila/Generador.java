package mochila;

// Representa un generador de números aleatorios para
// el algoritmo genético

public interface Generador
{
	// Retorna un boolean "aleatorio"
	boolean nextBoolean();
	
	// Retorna in entero "aleatorio" entre 0 y n-1
	int nextInt(int n);
}
