package agenda;

import java.util.ArrayList;
import depurador.Depurador;

public class Agenda {
	
	private ArrayList<Contacto> contactos;
	
	public Agenda() {
		Depurador.trazar("Creando lista de contactos");
		contactos=new ArrayList<>();
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
