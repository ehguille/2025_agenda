package agenda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class GestorPersistencia {

	Path fichero;
	
	public GestorPersistencia(String fichero) {
		this.fichero=Path.of(fichero);
	}
	
	public void guardar(Agenda a) {
		try {
			Files.writeString(fichero, a.toString(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void recuperar(Agenda b) {
		try {
			String agenda=Files.readString(fichero);
			String[] contactos=agenda.split("</contacto>");
			for(String contacto:contactos) {
				System.err.println("CONTACTO");
				System.out.println(contacto);
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
}
