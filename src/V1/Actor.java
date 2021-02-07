package V1;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import V1.Actor;

public abstract class Actor {
	protected int x, y; // Coordenadas x e y del actor
	protected int ancho, alto; // ancho y alto que ocupa el actor en pantalla
	protected BufferedImage spriteActual;
	protected boolean marcadoParaEliminacion = false;
	// Posibilidad de que el actor sea animado, a trav�s del siguiente array de sprites y las variables
	// velocidadDeCambioDeSprite y unidadDeTiempo
	protected List<BufferedImage> spritesDeAnimacion = new ArrayList<BufferedImage>();
	protected int velocidadDeCambioDeSprite = 0;
	private int unidadDeTiempo = 0;
	
	public Actor() {
		
	}

	public Actor(int x, int y, int ancho, int alto) {
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
	
	public void paint(Graphics g) {
		g.drawImage(this.spriteActual, this.x, this.y, null);
	}
	
	/**
	 * 
	 */
	public void colisionaCon(Actor a) {
	}
	public BufferedImage getSpriteActual() {
		return this.spriteActual;
	}

	/**
	 * @param img the img to set
	 */
	public void setSpriteActual(BufferedImage spriteActual) {
		this.spriteActual = spriteActual;
		this.ancho = this.spriteActual.getWidth();
		this.alto = this.spriteActual.getHeight();
	}
	
	public  void actua () {
		// En el caso de que exista un array de sprites el actor actual se tratar� de una animaci�n, para eso llevaremos a cabo los siguientes pasos
				if (this.spritesDeAnimacion != null && this.spritesDeAnimacion.size() > 0) {
					unidadDeTiempo++;
					if (unidadDeTiempo % velocidadDeCambioDeSprite == 0){
						unidadDeTiempo = 0;
						int indiceSpriteActual = spritesDeAnimacion.indexOf(this.spriteActual);
						int indiceSiguienteSprite = (indiceSpriteActual + 1) % spritesDeAnimacion.size();
						this.spriteActual = spritesDeAnimacion.get(indiceSiguienteSprite);
					}
				}
	}

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
	public List<BufferedImage> getSpritesDeAnimacion() {
		return spritesDeAnimacion;
	}

	/**
	 * @param spritesDeAnimacion the spritesDeAnimacion to set
	 */
	public void setSpritesDeAnimacion(List<BufferedImage> spritesDeAnimacion) {
		this.spritesDeAnimacion = spritesDeAnimacion;
	}
	
	
	
}
