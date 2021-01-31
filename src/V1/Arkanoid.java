package V1;



import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private static Arkanoid instance = null;
	static Nave nave = null;
	
	
	
	
	
	public static Arkanoid getInstance() {
		if (instance == null) { 
			instance = new Arkanoid();
		}
		return instance;
	}
	/**
	 * Main
	 * @param args
	 */
	public Arkanoid () {
		ventana = new JFrame("Arkanoid V1");
		ventana.setBounds(0, 0, 600, 800);
		
		// Para colocar objetos sobre la ventana debo asignarle un "layout" (plantilla) al panel principal de la ventana
		ventana.getContentPane().setLayout(new BorderLayout());
		
		// Creo una lista de actores que intervendrá en el juego.
		actores = creaActores();
		
		// Creo y agrego un canvas, es un objeto que permitirá dibujar sobre él
		canvas = new Micanvas(actores);
		canvas.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				nave.mover(e.getX());
			}			
		});
		
		// Desvío los eventos de teclado hasta la Nave
		canvas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				nave.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				nave.keyReleased(e);
			}
		});
		ventana.getContentPane().add(canvas, BorderLayout.CENTER);
		// No se redibuja la pantalla con eventos de windows.
		ventana.setIgnoreRepaint(true);
		// Ventana visible
		ventana.setVisible(true);
		
	}
	
	
	public static void main (String[] args) {
		// Comienzo un bucle, que consistirá en el juego completo.
		Arkanoid.getInstance().juego();
	}
	
	/**
	 * Bucle del juego principal
	 */
	public  void juego () {
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
		// Lista de actores.
		// En esta version solo añado un ladrillo, la bola y una nave.
		List<Actores> actores = new ArrayList<Actores>();
		int x = 20, y = 30;
		for (int i = 0; i < 6; i++) {
			
			for ( int j  = 0; j < 10; j++) {
				Ladrillo ladrillo = new Ladrillo(x, y, 50, 20, "ladrillo", Color.red);
				actores.add(ladrillo);
				x += 55;
				if (y == 30) ladrillo.setColores(Color.cyan);
				if (y == 55) ladrillo.setColores(Color.blue);
				if (y == 80) ladrillo.setColores(Color.pink);
				if (y == 130) ladrillo.setColores(Color.orange);
				if (y == 155) ladrillo.setColores(Color.white);
				}
			y+=25; x = 20;
			}
		
		Pelota pelota = new Pelota (400, 300, 25, 25, "Pelota");
		actores.add(pelota);
		
		nave = new Nave (400, 725, 100, 20, "Nave");
		actores.add(nave);
		
		
		// Devuelvo la lista con todos los actores del juego
		return actores;
	}

	
	public Micanvas getCanvas() {
		return canvas;
	}
	

}
