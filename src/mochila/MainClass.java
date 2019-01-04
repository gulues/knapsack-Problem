package mochila;

import java.util.Comparator;

public class MainClass 
{
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Instancia instancia = new Instancia(10);
		// ...
		
		SolverGoloso solver = new SolverGoloso(instancia);
		//Comparator<Objeto> comparador = new ComparadorPorPeso();
		
		//Heuristica por peso
		Conjunto solucion = solver.resolver(new Comparator<Objeto>()
		{
			@Override
			public int compare(Objeto uno, Objeto otro) {
				if( uno.getPeso() < otro.getPeso() )
					return -1;
				else if( uno.getPeso() > otro.getPeso() )
					return 1;
				else
					return 0;			
		}});
		
		// Heuristica por Beneficio
		Conjunto solucion2 = solver.resolver(new Comparator<Objeto>()
		{
			@Override
			public int compare(Objeto uno, Objeto otro) 
			{
				if( uno.getBeneficio() > otro.getBeneficio() )
					return -1;
				else if( uno.getBeneficio() < otro.getBeneficio() )
					return 1;
				else
					return 0;
			}});
		
		//Heuristica por Cociente		
		Conjunto solucion3 = solver.resolver(new Comparator<Objeto>(){

			@Override
			public int compare(Objeto uno, Objeto otro) 
			{
				double cocienteUno = uno.getBeneficio() / uno.getPeso();
				double cocienteOtro = otro.getBeneficio() / otro.getPeso();
				
				if( cocienteUno > cocienteOtro )
					return -1;
				else if( cocienteUno < cocienteOtro )
					return 1;
				else
					return 0;
			}});		
	}
}
