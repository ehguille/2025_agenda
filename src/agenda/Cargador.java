package agenda;

import agenda.excepciones.NombreDuplicadoException;
import agenda.excepciones.NombreVacioException;

public abstract class Cargador {
	
	public static void cargarContactos(Agenda a) throws NombreVacioException, NombreDuplicadoException {
		a.addContacto(new Contacto("Pepe"));
		a.addContacto(new Contacto("María"));
		a.addContacto(new Contacto("Carmen"));
		a.addContacto(new Contacto("Rosa"));
		a.addContacto(new Contacto("Josefa"));
		a.addContacto(new Contacto("Mariano"));
		a.addContacto(new Contacto("Carlos"));
		a.addContacto(new Contacto("Terelu"));
		a.addContacto(new Contacto("Manolín"));
	}
}
