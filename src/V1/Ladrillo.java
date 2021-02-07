package V1;


import java.awt.Color;
import java.awt.Graphics;





public class Ladrillo extends Actor {
	private String nombre;
	private Color colores;
	@Override
	public void paint(Graphics g) {
		g.setColor(colores);
		g.fillRect(this.x, this.y, this.ancho, this.alto);
		
	}

	@Override
	public void actua() {
		// TODO Auto-generated method stub
		
	}


	public Ladrillo(int x, int y, int ancho, int alto, String nombre, Color colores) {
		super(x, y, ancho, alto);
		this.nombre = nombre;
		this.colores = colores;
		
	}
	public void colisionaCon(Actor a) {
		super.colisionaCon(a);
		// Si colisionamos con un player o un disparo, eliminamos al monstruo
		if (a instanceof Pelota) {
			Arkanoid.getInstance().eliminaActor(this);
			ResourcesCache.getInstance().playSonido("explosion.wav");
			Arkanoid.getInstance().incorporaNuevoActor(new Explosion(this.x, this.y));
		}
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

	/**
	 * @return the colores
	 */
	public Color getColores() {
		return colores;
	}

	/**
	 * @param colores the colores to set
	 */
	public void setColores(Color colores) {
		this.colores = colores;
	}

	
	

}
