package V1;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;


/**
 * 
 * @author R
 *
 */
public class ResourcesCache {
	
	// HashMap que actúa como almacén de imágenes
	private HashMap<String, Object> hmRecursos = new HashMap<String, Object>();
	// Carpetas en la que se encuentran todos los recursos
	private String nombreCarpetaParaFile = "./src/V1/resources/";
	private String nombreCarpetaParaURL = "resources/";

	
	
	// Instancia Singleton
	private static ResourcesCache instance= null;
	
	
	/**
	 * Getter Singleton
	 * @return
	 */
	public static ResourcesCache getInstance () {
		if (instance == null) {
			instance = new ResourcesCache();
		}
		return instance;
	}


	/**
	 * 
	 */
	public void cargarRecursosEnMemoria () {
		File carpeta = new File(nombreCarpetaParaFile);
		for (File fichero : carpeta.listFiles()) {
	        if (fichero.isFile()) {
	        	cargarFicheroEnHashMap(fichero.getName());
	        }
	    }
	}

	
	/**
	 * 
	 * @param nombreFichero
	 */
	private void cargarFicheroEnHashMap (String nombreFichero) {
		// Obtengo un objeto URL para localizar el recurso
		URL url = null;
		url = getClass().getResource(nombreCarpetaParaURL + nombreFichero);
		// Discriminará el caso de que intento cargar un sonido del caso de cargar imágenes
		try {
			if (nombreFichero.endsWith(".wav") || nombreFichero.endsWith(".mp3")) {
				this.hmRecursos.put(nombreFichero, Applet.newAudioClip(url));
			} 
			else { // Si no es un sonido entiendo que se trata de una imagen
				this.hmRecursos.put(nombreFichero, ImageIO.read(url));
			}
		}
		catch (Exception ex) {
			System.out.println("No se pudo cargar el recurso " + nombreFichero);
			ex.printStackTrace();
		}
	}

	
	
	/**
	 * Mediante este método casteamos a imagen el recurso que nos proporciona el supertipo
	 * @param name
	 * @return
	 */
	public BufferedImage getImagen(String nombreFichero) {
		return (BufferedImage) hmRecursos.get(nombreFichero);
	}

	
	/**
	 * Ejecuta un archivo de sonido de forma aislada
	 * @param name
	 */
	public void playSonido(String nombreFichero) {
		((AudioClip)hmRecursos.get(nombreFichero)).play();
	}
	
	/**
	 * Reproduce un archivo de sonido en bucle
	 * @param name
	 */
	public void loopSonido(final String nombreFichero) {
		((AudioClip)hmRecursos.get(nombreFichero)).loop();
	}

	
}
