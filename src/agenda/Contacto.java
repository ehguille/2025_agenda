package agenda;

/** Utilizado para los teléfonos y los mails*/
import java.util.HashMap;

import depurador.Depurador;

/**
 * Implementación de un contacto
 */
public class Contacto {
	
	/** Un contacto solo tiene un nombre, apellidos y dirección postal*/
	private String nombre, apellidos, direccionPostal;
	/** Tabla con la relación descripción-telefono */
	private HashMap<String,String> telefonos;
	/** Tabla con la relación descripcion-mail */
	private HashMap<String,String> mails;
	
	/**
	 * Constructor básico de un contacto. Todo contacto debe tener, al menos, un nombre.
	 * @param nombre Nombre del contacto.
	 */
	public Contacto(String nombre) {
		Depurador.trazar("Creando contacto. nombre="+nombre);
		this.nombre=nombre;
		telefonos=new HashMap<String, String>();
		mails=new HashMap<String, String>();
	}
	
	/**
	 * Añade un teléfono al contacto
	 * @param descripcion Tipo de teléfono (casa, trabajo, móvil, ...)
	 * @param numero Número de teléfono
	 */
	public void addTelefono(String descripcion, String numero) {
		//Se añade la descripción en mayúsculas para evitar confusiones del usuario.
		telefonos.put(descripcion.toUpperCase(), numero);
	}
	
	/**
	 * Devuelve una descripción en formato parecido a XML del contacto.
	 */
	public String toString() {
		String resultado="<contacto>\n";
		resultado+="  <nombre>"+nombre+"</nombre>\n";
		resultado+="  <telefonos>\n";
		resultado+="    "+telefonos+"\n";
		resultado+="  <\\telefonos>\n";
		resultado+="  <mails>\n";
		resultado+="    "+mails+"\n";
		resultado+="  <\\mails>\n";
		resultado+="</contacto>\n";
		return resultado;
	}
	


}
