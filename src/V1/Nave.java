package V1;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Nave extends Actores {
	private String nombre;
	private boolean izquierda = false, derecha = false;
	public static int VELOCIDAD = 5;
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(this.x, this.y, this.ancho, this.alto);
		
	}

	@Override
	public void actua() {
		if (izquierda) this.x -= VELOCIDAD;
		if (derecha) this.x += VELOCIDAD;
		
		// Compruebo si la nave sale del canvas por la izquierda o derecha
		mover(this.x);
	}
		

	public Nave() {
		super();
		
	}

	public Nave(int x, int y, int ancho, int alto, String nombre) {
		super(x, y, ancho, alto);
		this.nombre = nombre;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Nave [nombre=" + nombre + ", x=" + x + ", y=" + y + ", ancho=" + ancho + ", alto=" + alto + "]";
	}
	public   void mover(int x) {
		this.x = x;
		// Controlo los casos en los que la nave pueda salir del Canvas
		Micanvas canvas = Arkanoid.getInstance().getCanvas(); // Referencia al objeto Canvas usado
		
		// Compruebo si el jugador sale por la derecha
		if (this.x > (canvas.getWidth() - this.ancho)) {
			this.x = canvas.getWidth() - this.ancho;
		}

		// Compruebo si la nave sale por la izquierda
		if (this.x < 0) {
			this.x = 0;
		}
	}

	/**
	 * Se ejecuta al recibir un evento del teclado: tecla presionada
	 * @param e
	 */
	public void keyPressed (KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			izquierda = true; break;
		case KeyEvent.VK_RIGHT:
			derecha = true; break;
		}
	}
	
	/**
	 * Se ejecuta al recibir un evento del teclado: tecla liberada
	 * @param e
	 */
	public void keyReleased (KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			izquierda = false; break;
		case KeyEvent.VK_RIGHT:
			derecha = false; break;
		}
	}

	
}
