package agenda;

import agenda.excepciones.NombreDuplicadoException;
import agenda.excepciones.NombreVacioException;
import enumeraciones.Pais;

public abstract class Cargador {
	
	public static void cargarContactos(Agenda a) throws NombreVacioException, NombreDuplicadoException {
		a.addContacto("Manolo","Pérez");
		a.setDireccionPostal("Manolo", "Calle uno");
		a.addTelefono("Manolo", "Casa",Pais.ESPANA, 987654);
		a.addCorreo("Manolo","Trabajo","direccion1@dominio.es");
		
		a.addContacto("Luisa","Pérez");
		a.setDireccionPostal("Luisa", "Calle dos");
		a.addTelefono("Luisa", "Casa",Pais.CANADA, 987654);
		a.addCorreo("Luisa","Trabajo","direccion2@dominio.es");
		
		a.addContacto("Lola","Pérez");
		a.setDireccionPostal("Lola", "Calle tres");
		a.addTelefono("Lola", "Casa",Pais.MEXICO, 987654);
		a.addCorreo("Lola","Trabajo","direccion3@dominio.es");

		/* Método antiguo:
		Contacto c1=new Contacto("Luisa","Garcñua");
		c1.setDireccionPostal("Calle Dos");
		c1.addTelefono("Trabajo", Provincias.A_CORUNA.prefijo, 612124);
		c1.addCorreo("Personal", "direccion2@dominio.es");
		a.addContacto(c1);
		*/
	}
}
