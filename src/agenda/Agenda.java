package agenda;

import java.util.ArrayList;
import depurador.Depurador;
import enumeraciones.Provincias;

public class Agenda {
	
	private ArrayList<Contacto> contactos;
	private Provincias p;
	
	public Agenda() {
		Depurador.trazar("Creando lista de contactos");
		contactos=new ArrayList<>();
		//TODO: eliminar el siguiente ejemplo aleatorio
		Contacto c = new Contacto("Pepe");
		c.addTelefono("Casa",p.A_CORUNA.prefijo,999999999);
		System.out.println(c);
		//TODO: Fin
	}
	
	/**
	 * Añade un contacto a la agenda.
	 * @param c Contacto que añadir.
	 */
	public void addContacto(Contacto c) {
		Depurador.trazar("Añadiendo contacto a la agenda");
		contactos.add(c);
	}

}
