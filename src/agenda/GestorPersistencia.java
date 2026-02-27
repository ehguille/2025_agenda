package agenda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import agenda.excepciones.NombreDuplicadoException;
import agenda.excepciones.NombreVacioException;
import depurador.Depurador;
import enumeraciones.Pais;

public class GestorPersistencia {

	Path fichero;
	
	public GestorPersistencia(String fichero) {
		this.fichero=Path.of(fichero);
	}
	
	public void guardar(Agenda a) {
		try {
			Depurador.trazar("Guardando agenda en "+fichero);
			Files.writeString(fichero, a.toString(), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void recuperar(Agenda a) {
		try {
			Depurador.trazar("Recuperando agenda de "+fichero);
			String agenda=Files.readString(fichero);
			String[] agendaPorLineas=agenda.split("\n");
			ArrayList<String> unContactoEnTexto=new ArrayList<String>();
			//OJO: Esto supone que existe el fichero con las etiquetas <agenda></agenda>
			//Inicializando i=1 y terminando en .lenght-1, elimino la primera y última etiquetas.
			for(int i=1;i<agendaPorLineas.length-1;i++) {
				if(agendaPorLineas[i].equals("<contacto>")) {
					Depurador.trazar("Se ha encontrado la etiqueta <contacto>");
					unContactoEnTexto=new ArrayList<String>();
				}
				else if(agendaPorLineas[i].equals("</contacto>")) {
					Depurador.trazar("Se ha encontrado la etiqueta </contacto>");
					procesarContactoEnTexto(unContactoEnTexto, a);
				}
				else
					unContactoEnTexto.add(agendaPorLineas[i]);
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	//Tremenda chambonada.
	private void procesarContactoEnTexto(ArrayList<String> unContactoEnTexto, Agenda a) {
		Depurador.trazar("Se procesa un contacto en texto");
		String nombre="";
		boolean procesandoTelefonos=false;
		boolean procesandoCorreos=false;
		for (String texto : unContactoEnTexto) {
			String textoSinEspacios = texto.trim();
			//Para crear contacto (etiqueta nombre)
			if (textoSinEspacios.startsWith("<nombre>")) {
				nombre = textoSinEspacios.substring(8, textoSinEspacios.length() - 9);
				try {
					a.addContacto(nombre);
				} catch (Exception e) {
					System.err.println("Error al crear un contacto desde fichero.");
					e.printStackTrace();
				}
			}
			//Para apellidos
			else if (textoSinEspacios.startsWith("<apellidos>")) {
				String apellidos=textoSinEspacios.substring(11,textoSinEspacios.length()-12);
				a.setApellidos(nombre, apellidos);
			}
			//Para la dirección postal
			else if (textoSinEspacios.startsWith("<direccionPostal>")) {
				String direccionPostal=textoSinEspacios.substring(17,textoSinEspacios.length()-18);
				a.setDireccionPostal(nombre, direccionPostal);
			}
			//Para los teléfonos:
			else if(textoSinEspacios.equals("<telefonos>")) {
				Depurador.trazar("Procesando los teléfonos");
				procesandoTelefonos=true;
			}
			else if(textoSinEspacios.equals("</telefonos>")) {
				Depurador.trazar("Fin del procesamiento de los teléfonos");
				procesandoTelefonos=false;
			}
			if(procesandoTelefonos&&!textoSinEspacios.equals("<telefonos>")&&!textoSinEspacios.equals("</telefonos>")) {
				Depurador.trazar("Se ha encontrado un teléfono");
				String[] telefono=textoSinEspacios.split(">");
				String descripcionTelefono=telefono[0].substring(1);
				String[] numeroTelefono=telefono[1].split("<")[0].split("-");
				try {
					a.addTelefono(nombre, descripcionTelefono, Pais.lookUp(Integer.parseInt(numeroTelefono[0])), Integer.parseInt(numeroTelefono[1]));
				} catch (Exception e) {
					System.err.println("Error al procesar el prefijo de un teléfono.");
					e.printStackTrace();
				}
			}
			//Para los correos (igual que los teléfonos)
			else if(textoSinEspacios.equals("<mails>")) {
				Depurador.trazar("Procesando las direcciones de correo.");
				procesandoCorreos=true;
			}
			else if(textoSinEspacios.equals("</mails>")) {
				Depurador.trazar("Fin del procesamiento de las direcciones de correo.");
				procesandoCorreos=false;
			}
			if(procesandoCorreos&&!textoSinEspacios.equals("<mails>")&&!textoSinEspacios.equals("</mails>")) {
				Depurador.trazar("Se ha encontrado una dirección de correo.");
				String[] correo=textoSinEspacios.split(">");
				String descripcionCorreo=correo[0].substring(1);
				String direccionCorreo=correo[1].split("<")[0];
				a.addCorreo(nombre, descripcionCorreo, direccionCorreo);
			}
		}
	}

}
