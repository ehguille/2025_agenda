package agenda;

/** Utilizado para los teléfonos y los mails*/
import java.util.HashMap;

import depurador.Depurador;

/**
 * Implementación de un contacto
 */
public class Contacto  {
	
	private class Telefono {
		
		private int prefijo;
		private int numero;
		
		private Telefono(int prefijo, int numero) {
			this.prefijo=prefijo;
			this.numero=numero;
		}
		
		private void setPrefijo(int prefijo) {
			this.prefijo=prefijo;
		}
		
		private int getPrefijo() {
			return prefijo;
		}
		
		private void setNumero(int numero) {
			this.numero=numero;
		}
		
		private int getNumero() {
			return numero;
		}
		
		public String toString() {
			String prefijo=Integer.toString(this.prefijo);
			String numero=Integer.toString(this.numero);
			return prefijo+numero;
		}
	}
	
	/** Un contacto solo tiene un nombre, apellidos y dirección postal*/
	private String nombre, apellidos, direccionPostal;
	/** Tabla con la relación descripción-telefono */
	private HashMap<String,Telefono> telefonos;
	/** Tabla con la relación descripcion-mail */
	private HashMap<String,String> mails;
	
	/**
	 * Constructor básico de un contacto. Todo contacto debe tener, al menos, un nombre.
	 * @param nombre Nombre del contacto.
	 */
	public Contacto(String nombre) {
		Depurador.trazar("Creando contacto. nombre="+nombre);
		this.nombre=nombre;
		telefonos=new HashMap<String, Telefono>();
		mails=new HashMap<String, String>();
	}
	
	/**
	 * Añade un teléfono al contacto
	 * @param descripcion Tipo de teléfono (casa, trabajo, móvil, ...)
	 * @param numero Número de teléfono
	 */
	public void addTelefono(String descripcion, int prefijo, int numero) {
		//Se añade la descripción en mayúsculas para evitar confusiones del usuario.
		telefonos.put(descripcion.toUpperCase(), new Telefono(prefijo,numero));
	}
	
	public String getName() {
		return nombre;
	}
	
	/**
	 * Devuelve una descripción en formato parecido a XML del contacto.
	 */
	public String toString() {
		String resultado="<contacto>\n";
		resultado+="  <nombre>"+nombre+"</nombre>\n";
		resultado+="  <telefonos>\n";
		resultado+="    "+telefonos.toString()+"\n";
		resultado+="  <\\telefonos>\n";
		resultado+="  <mails>\n";
		resultado+="    "+mails+"\n";
		resultado+="  <\\mails>\n";
		resultado+="</contacto>\n";
		return resultado;
	}

	public boolean equals(String nombre) {
		if (this.nombre.toUpperCase().contains(nombre.toUpperCase()))
			return true;
		else
			return false;
	}

}
