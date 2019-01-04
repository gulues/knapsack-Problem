package mochila;

import java.util.Random;

// Representa un test de stress del algoritmo genético
public class StressTest implements Poblacion.Observador
{
	private Instancia instancia;
	private Poblacion poblacion;
	
	public void inicializar(int objetos)
	{
		Individuo.setGenerador(new GeneradorAleatorio());
		Poblacion.setGenerador(new GeneradorAleatorio());
		
		instancia = construir(objetos);
		poblacion = new Poblacion(instancia);
		poblacion.registrar(this);
	}
	
	public void registrar(Poblacion.Observador observador)
	{
		poblacion.registrar(observador);
	}
	
	public void ejecutar()
	{
		poblacion.simular();
	}
	
	@Override
	public void notificar(Poblacion poblacion)
	{
		System.out.print("Tamaño: " + poblacion.tamano() + ", ");
		System.out.print("Peor: " + poblacion.peorFitness() + ", ");
		System.out.print("Prom: " + poblacion.fitnessPromedio() + ", ");
		System.out.print("Mejor: " + poblacion.mejorFitness());
		System.out.println();
	}

	private Instancia construir(int n)
	{
		Random random = new Random(0);
		Instancia ret = new Instancia(n);
		
		for(int i=0; i<n; ++i)
		{
			double peso = random.nextInt(10) + 1;
			double beneficio = random.nextInt(10) + 1;

			ret.agregar(new Objeto("Obj " + i, peso, beneficio));
		}
		
		return ret;
	}
}
