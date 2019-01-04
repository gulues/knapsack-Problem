package mochila;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

import java.awt.BorderLayout;

import javax.swing.JButton;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainForm implements Poblacion.Observador
{
	private MainForm _this; // Para acceder desde los action listeners
	private JFrame frame;
	private XYSeriesCollection dataset;
	private XYSeries promedio;
	private XYSeries mejor;
	private XYSeries peor;
	private JFreeChart chart;
	private ChartPanel chartPanel;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainForm window = new MainForm();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public MainForm()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		_this = this;
		initialize();
	}

	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (! btnEjecutar.getText().equals("Pause"))
					;
				SwingWorker<Object, Object> worker = new SwingWorker<Object, Object>()
				{
					@Override
					protected Object doInBackground() throws Exception
					{
						Instancia instancia = new Instancia(10);
						instancia.agregar(new Objeto("A", 3, 5));
						instancia.agregar(new Objeto("B", 7, 15));
						instancia.agregar(new Objeto("A", 1, 2));
						instancia.agregar(new Objeto("C", 5, 20));
						instancia.agregar(new Objeto("D", 3, 5));
						instancia.agregar(new Objeto("X", 3, 5));
						instancia.agregar(new Objeto("X", 3, 5));
						instancia.agregar(new Objeto("X", 3, 5));
						instancia.agregar(new Objeto("X", 3, 5));
						instancia.agregar(new Objeto("X", 3, 5));
						
						SolverExacto bf = new SolverExacto(instancia);
						Conjunto solucion = bf.resolver();
						System.out.println("solver: "+ solucion.toString());
						System.out.println("Size: "+ bf.getHojas() );
						StressTest stressTest = new StressTest();
						stressTest.inicializar(200);
						stressTest.registrar(_this);
						stressTest.ejecutar();
						
						return null;
					}
				};
				
				worker.execute();
				btnEjecutar.setText("Pause");
				//btnEjecutar.setEnabled(false);
			}
		});
		frame.getContentPane().add(btnEjecutar, BorderLayout.SOUTH);
		
		dataset = new XYSeriesCollection();
		promedio = new XYSeries("Promedio");
		mejor = new XYSeries("Mejor");
		peor = new XYSeries("Peor");
		
		dataset.addSeries(promedio);
		dataset.addSeries(mejor);
		dataset.addSeries(peor);
		
		chart = ChartFactory.createXYLineChart("", "", "",
				dataset, PlotOrientation.VERTICAL, true, true, false);
		
		chartPanel = new ChartPanel(chart);
		frame.getContentPane().add(chartPanel, BorderLayout.CENTER);
	}
	
	@Override
	public void notificar(Poblacion poblacion)
	{
		int x = poblacion.iteracion();
		
		promedio.add(x, poblacion.fitnessPromedio());
		mejor.add(x, poblacion.mejorFitness());
		peor.add(x, poblacion.peorFitness());
		
		chartPanel.repaint();
		frame.repaint();
	}
}













