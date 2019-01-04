package mochila;

// Representa un generador de n�meros aleatorios para
// el algoritmo gen�tico

public interface Generador
{
	// Retorna un boolean "aleatorio"
	boolean nextBoolean();
	
	// Retorna in entero "aleatorio" entre 0 y n-1
	int nextInt(int n);
}
