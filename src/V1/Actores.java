package V1;


import java.awt.Graphics;

public abstract class Actores {
	protected int x, y; // Coordenadas x e y del actor
	protected int ancho, alto; // ancho y alto que ocupa el actor en pantalla
	
	public Actores() {
		
	}

	public Actores(int x, int y, int ancho, int alto) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
	}
	
	/**
	 * 
	 * @param g
	 */
	
	public abstract void paint(Graphics g); 
	
	/**
	 * 
	 */
	
	public abstract void actua ();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}
	
	
	
}
