package V1;


import java.awt.Color;
import java.awt.Graphics;






public class Pelota extends Actor {
	private String nombre;
	private int velocidadX = -6;
	private int velocidadY = -6;

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.magenta);
		g.fillOval(this.x, this.y, this.ancho, this.alto);

	}

	@Override
	public void actua() {
		this.x += this.velocidadX;
		if (this.x <0 || (this.x + this.ancho) > Arkanoid.getInstance().getCanvas().getWidth()) {
			this.velocidadX = - this.velocidadX;
		}
		this.y += this.velocidadY;
		if (this.y < 0 || (this.y + this.alto) > Arkanoid.getInstance().getCanvas().getHeight()) {
			this.velocidadY = -this.velocidadY;
		}
	}

	public Pelota() {
		super();

	}

	public Pelota(int x, int y, int ancho, int alto, String nombre) {
		super(x, y, ancho, alto);
		this.nombre = nombre;
		
	}
	public void colisionaCon(Actor a) {
		super.colisionaCon(a);
		// Si colisionamos con un player o un disparo, eliminamos al monstruo
		if (a instanceof Nave) {
			Arkanoid.pelota.velocidadY=  -6;
		}
		if (a instanceof Ladrillo) {
			Arkanoid.pelota.velocidadY=  +6;
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}

	public int getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

	@Override
	public String toString() {
		return "Pelota [nombre=" + nombre + ", velocidadX=" + velocidadX + ", velocidadY=" + velocidadY + ", x=" + x
				+ ", y=" + y + ", ancho=" + ancho + ", alto=" + alto + "]";
	}
	
}
