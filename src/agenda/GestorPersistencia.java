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
	
}
