package V1;



import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;



public class Micanvas extends Canvas {
	List<Actores> actores = null;

	/**
	 * Constructor
	 * @param actores
	 */
	public Micanvas (List<Actores> actores) {
		this.actores = actores;
	}
	/**
	 * Sobrescritura del méotod paint(), aquí tengo el control sobre aquello que se va a pintar en pantalla.
	 */
	@Override
	public void paint(Graphics g) {
		// Pinto el fondo
		this.setBackground(Color.BLACK);
		
		// Pinto cada uno de los actores
		for (Actores a : this.actores) {
			a.paint(g);
		}
}
}
