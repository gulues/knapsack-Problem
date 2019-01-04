package mochila;

import java.util.ArrayList;
import java.util.Collections;

public class Poblacion
{
	private ArrayList<Individuo> _individuos;
	private Instancia _instancia;

	// Parámetros de la población
	private static int _tamano = 1000;
	private static int _iteraciones = 1000;
	private static int _mutadosPorIteracion = 300;
	private static int _recombinadosPorIteracion = 200;
	private static int _eliminadosPorIteracion = 500;
	
	// Estadísticas
	private int _iteracion;
	
	// Generador de números aleatorios
	private static Generador _generador;
	public static void setGenerador(Generador generador)
	{
		_generador = generador;
	}
	
	// Interfaz (inner) para los observadores de la población
	public static interface Observador
	{
		void notificar(Poblacion poblacion);
	}
	
	// Lista de observadores
	private ArrayList<Observador> _observadores;
	
	// Constructor
	public Poblacion(Instancia instancia)
	{
		_individuos = new ArrayList<Individuo>(_tamano);
		_instancia = instancia;
		_observadores = new ArrayList<Observador>();
		
		for(int i=0; i<_tamano; ++i)
			_individuos.add(new Individuo(_instancia));
	}
	
	// Registra un nuevo observador
	public void registrar(Observador observador)
	{
		_observadores.add(observador);
	}

	// Proceso de simulación
	public Individuo simular()
	{
		for(_iteracion=0; _iteracion<_iteraciones; _iteracion++)
		{
			mutarAlgunos();
			recombinarAlgunos();
			eliminarPeores();
			agregarNuevos();
			notificarObservadores();
		}
		
		return mejorIndividuo();
	}

	// Auxiliares para la simulación
	private void mutarAlgunos()
	{
		for(int i=0; i<_mutadosPorIteracion; ++i)
		{
			int j = _generador.nextInt(_individuos.size());
			_individuos.get(j).mutar();
		}
	}
	private void recombinarAlgunos()
	{
		for(int i=0; i<_recombinadosPorIteracion; ++i)
		{
			int j1 = _generador.nextInt(_individuos.size());
			int j2 = _generador.nextInt(_individuos.size());
			
			Individuo padre1 = _individuos.get(j1);
			Individuo padre2 = _individuos.get(j2);
			
			for(Individuo hijo: padre1.recombinar(padre2))
				_individuos.add(hijo);
		}
	}
	private void eliminarPeores()
	{
		Collections.sort(_individuos);
		Collections.reverse(_individuos);
		
		for(int i=0; i<_eliminadosPorIteracion; ++i)
			_individuos.remove( _individuos.size()-1 );
	}
	private void agregarNuevos()
	{
		while( _individuos.size() < _tamano )
			_individuos.add(new Individuo(_instancia));
	}
	private void notificarObservadores()
	{
		for(Observador observador: _observadores)
			observador.notificar(this);
	}
	
	// Mejor individuo
	private Individuo mejorIndividuo()
	{
		return Collections.max(_individuos);
	}
	
	// Estadísticas
	public double mejorFitness()
	{
		return Collections.max(_individuos).fitness();
	}
	public double peorFitness()
	{
		return Collections.min(_individuos).fitness();
	}
	public double fitnessPromedio()
	{
		double suma = 0;
		for(Individuo individuo: _individuos)
			suma += individuo.fitness();
		
		return suma / _individuos.size();
	}
	public int tamano()
	{
		return _individuos.size();
	}
	public int iteracion()
	{
		return _iteracion;
	}
}



















