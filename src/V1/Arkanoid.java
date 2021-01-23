package V1;



import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Arkanoid {
	private static int FPS = 60;
	private static JFrame ventana = null;
	private static List<Actores> actores = new ArrayList<Actores>();
	private static Micanvas canvas = null;
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		ventana = new JFrame("Arkanoid V1");
		ventana.setBounds(0, 0, 800, 600);
		
		// Para colocar objetos sobre la ventana debo asignarle un "layout" (plantilla) al panel principal de la ventana
		ventana.getContentPane().setLayout(new BorderLayout());
		
		// Creo una lista de actores que intervendrá en el juego.
		actores = creaActores();
		
		// Creo y agrego un canvas, es un objeto que permitirá dibujar sobre él
		canvas = new Micanvas(actores);
		ventana.getContentPane().add(canvas, BorderLayout.CENTER);
		// Consigo que la ventana no se redibuje por los eventos de Windows
		ventana.setIgnoreRepaint(true);
		// Hago que la ventana sea visible
		ventana.setVisible(true);
		
		// Comienzo un bucle, que consistirá en el juego completo.
		juego();
	}
	
	
	/**
	 * Bucle del juego principal
	 */
	public static void juego () {
		int millisPorCadaFrame = 1000 / FPS;
		do {
			// Redibujo la escena tantas veces por segundo como indique la variable FPS
			// Tomo los millis actuales
			long millisAntesDeProcesarEscena = new Date().getTime();
			
			// Redibujo la escena
			canvas.repaint();
			
			// Recorro todos los actores, consiguiendo que cada uno de ellos actúe
			for (Actores a : actores) {
				a.actua();
			}
			
			// Calculo los millis que debemos parar el proceso, generando 60 FPS.
			long millisDespuesDeProcesarEscena = new Date().getTime();
			int millisDeProcesamientoDeEscena = (int) (millisDespuesDeProcesarEscena - millisAntesDeProcesarEscena);
			int millisPausa = millisPorCadaFrame - millisDeProcesamientoDeEscena;
			millisPausa = (millisPausa < 0)? 0 : millisPausa;
			// "Duermo" el proceso principal durante los milllis calculados.
			try {
				Thread.sleep(millisPausa);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (true);
	}
	private static List<Actores> creaActores () {
		List<Actores> actores = new ArrayList<Actores>();
		
		Ladrillo ladrillo = new Ladrillo (1, 1, 75, 30, "1");
		actores.add(ladrillo);
		
		Pelota pelota = new Pelota (400, 300, 25, 25, "Pelota");
		actores.add(pelota);
		
		Nave nave = new Nave (400, 525, 100, 20, "Nave");
		actores.add(nave);
		
		
		// Devuelvo la lista con todos los actores del juego
		return actores;
	}
	

}
