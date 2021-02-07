package V1;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
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
	private static List<Actor> actores = new ArrayList<Actor>();
	private static Micanvas canvas = null;
	private static Arkanoid instance = null;
	static Nave nave = null;
	static Pelota pelota = null;
	private List<Actor> actoresParaIncorporar = new ArrayList<Actor>();
	private List<Actor> actoresParaEliminar = new ArrayList<Actor>();

	// Creamos la instancia del Arkanoid (Singleton)
	public static Arkanoid getInstance() {
		if (instance == null) {
			instance = new Arkanoid();
		}
		return instance;
	}

	/**
	 * Main
	 * 
	 * @param args
	 */
	public Arkanoid() {
		ventana = new JFrame("Arkanoid"); // Nombre de la Ventana
		ventana.setBounds(0, 0, 380, 600); // Resolución de la ventana

		// Para colocar objetos sobre la ventana debo asignarle un "layout" (plantilla)
		// al panel principal de la ventana
		ventana.getContentPane().setLayout(new BorderLayout());

		// Creo una lista de actores que intervendrá en el juego.
		actores = creaActores();

		// Creo y agrego un canvas, es un objeto que permitirá dibujar sobre él
		canvas = new Micanvas(actores);

		// Creo el mouse listener con el adapter para que registre los movimientos del
		// ratón y se los paso
		// a la nave para que se mueva
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
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		ventana.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		cerrarAplicacion();
		}

		});
		canvas.requestFocus();

	}
	private static void cerrarAplicacion() {
		String[] opciones = { "Aceptar", "Cancelar" };
		int eleccion = JOptionPane.showOptionDialog(ventana, "¿Desea cerrar la aplicación?", "Salir de la aplicación",
		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		if (eleccion == JOptionPane.YES_OPTION) {
		System.exit(0);
		}
	}

	public static void main(String[] args) {
		ResourcesCache.getInstance().cargarRecursosEnMemoria();
		// Comienzo un bucle, que consistirá en el juego completo.
		Arkanoid.getInstance().juego();
	}

	/**
	 * Bucle del juego principal
	 */
	public void juego() {
		int millisPorCadaFrame = 1000 / FPS;
		do {
			// Condición para que el foco del teclado se posicione en la ventana aunque
			// pueda tardar en abrirse
			if (ventana.getFocusOwner() != null && !ventana.getFocusOwner().equals(canvas)) {
				canvas.requestFocus();
			}
			// Redibujo la escena tantas veces por segundo como indique la variable FPS
			// Tomo los millis actuales
			long millisAntesDeProcesarEscena = new Date().getTime();

			// Redibujo la escena
			canvas.pintaEscena();

			// Recorro todos los actores, consiguiendo que cada uno de ellos actúe
			for (Actor a : actores) {
				a.actua();
			}
			detectaColisiones();

			actualizaActores();

			// Calculo los millis que debemos parar el proceso, generando 60 FPS.
			long millisDespuesDeProcesarEscena = new Date().getTime();
			int millisDeProcesamientoDeEscena = (int) (millisDespuesDeProcesarEscena - millisAntesDeProcesarEscena);
			int millisPausa = millisPorCadaFrame - millisDeProcesamientoDeEscena;
			millisPausa = (millisPausa < 0) ? 0 : millisPausa;
			// "Duermo" el proceso principal durante los milllis calculados.
			try {
				Thread.sleep(millisPausa);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (true);
	}

	private static List<Actor> creaActores() {
		// Lista de actores.
		// En esta version solo añado un ladrillo, la bola y una nave.
		List<Actor> actores = new ArrayList<Actor>();

		// Bucle para crear ladrillos en la pantalla con distinto color y lo agregamos a
		// la lista de actores.
		int x = 20, y = 10;
		for (int i = 0; i < 6; i++) {

			for (int j = 0; j < 6; j++) {
				Ladrillo ladrillo = new Ladrillo(x, y, 50, 20, "ladrillo", Color.magenta);
				actores.add(ladrillo);
				x += 55;
				if (y == 10)
					ladrillo.setColores(Color.red);
				if (y == 35)
					ladrillo.setColores(Color.yellow);
				if (y == 60)
					ladrillo.setColores(Color.green);
				if (y == 110)
					ladrillo.setColores(Color.blue);
				if (y == 135)
					ladrillo.setColores(Color.pink);
			}
			y += 25;
			x = 20;
		}
		// Añadimos los valores de la pelota para que pueda pintarse
		pelota = new Pelota(200, 300, 25, 25, "Pelota");
		actores.add(pelota);
		// Añadimos los valores de la nave para que pueda pintarse
		nave = new Nave(100, 500, 100, 20, "Nave");
		actores.add(nave);

		// Devuelvo la lista con todos los actores del juego
		return actores;
	}

	/**
	 * 
	 * @param a
	 */
	public void incorporaNuevoActor(Actor a) {
		this.actoresParaIncorporar.add(a);
	}

	/**
	 * 
	 * @param a
	 */
	public void eliminaActor(Actor a) {
		this.actoresParaEliminar.add(a);
	}

	/**
	 * 
	 */
	private void actualizaActores() {
		// Incorporo los nuevos actores
		for (Actor a : this.actoresParaIncorporar) {
			Arkanoid.actores.add(a);
		}
		this.actoresParaIncorporar.clear(); // Limpio la lista de actores a incorporar, ya están incorporados

		// Elimino los actores que se deben eliminar
		for (Actor a : this.actoresParaEliminar) {
			Arkanoid.actores.remove(a);
		}
		this.actoresParaEliminar.clear(); // Limpio la lista de actores a eliminar, ya los he eliminado
	}

	/**
	 * Detecta colisiones entre actores e informa a los dos
	 */
	private void detectaColisiones() {
		// Una vez que cada actor ha actuado, intento detectar colisiones entre los
		// actores y notificarlas. Para detectar
		// estas colisiones, no nos queda más remedio que intentar detectar la colisión
		// de cualquier actor con cualquier otro
		// sólo con la excepción de no comparar un actor consigo mismo.
		// La detección de colisiones se va a baser en formar un rectángulo con las
		// medidas que ocupa cada actor en pantalla,
		// De esa manera, las colisiones se traducirán en intersecciones entre
		// rectángulos.
		for (Actor actor1 : Arkanoid.actores) {
			// Creo un rectángulo para este actor.
			Rectangle rect1 = new Rectangle(actor1.getX(), actor1.getY(), actor1.getAncho(), actor1.getAlto());
			// Compruebo un actor con cualquier otro actor
			for (Actor actor2 : Arkanoid.actores) {
				// Evito comparar un actor consigo mismo, ya que eso siempre provocaría una
				// colisión y no tiene sentido
				if (!actor1.equals(actor2)) {
					// Formo el rectángulo del actor 2
					Rectangle rect2 = new Rectangle(actor2.getX(), actor2.getY(), actor2.getAncho(), actor2.getAlto());
					// Si los dos rectángulos tienen alguna intersección, notifico una colisión en
					// los dos actores
					if (rect1.intersects(rect2)) {
						actor1.colisionaCon(actor2); // El actor 1 colisiona con el actor 2
						actor2.colisionaCon(actor1); // El actor 2 colisiona con el actor 1
					}
				}
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public Micanvas getCanvas() {
		return canvas;
	}

}
	


