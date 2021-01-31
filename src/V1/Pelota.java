package V1;


import java.awt.Color;
import java.awt.Graphics;




public class Pelota extends Actores {
	private String nombre;
	private int velocidadX = -8;
	private int velocidadY = -8;

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.PINK);
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
