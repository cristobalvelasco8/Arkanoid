package V1;


import java.awt.Color;
import java.awt.Graphics;

public class Ladrillo extends Actores {
	private String nombre;
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(this.x, this.y, this.ancho, this.alto);
		
	}

	@Override
	public void actua() {
		// TODO Auto-generated method stub
		
	}

	public Ladrillo() {
		super();
	}

	public Ladrillo(int x, int y, int ancho, int alto, String nombre) {
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
		return "Ladrillo [nombre=" + nombre + ", x=" + x + ", y=" + y + ", ancho=" + ancho + ", alto=" + alto + "]";
	}
	
	

}
