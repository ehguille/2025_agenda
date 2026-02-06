package agenda;

import java.util.ArrayList;

import agenda.excepciones.*;
import depurador.Depurador;
import enumeraciones.Provincias;

public class Agenda {
	
	private ArrayList<Contacto> contactos;
	private Provincias p;
	
	public Agenda() {
		Depurador.trazar("Creando lista de contactos");
		contactos=new ArrayList<>();
	}
	
	/**
	 * Añade un contacto a la agenda.
	 * @param c Contacto a añadir.
	 * @throws NombreVacioException Se lanza si el nombre del contacto está vacío.
	 * @throws NombreDuplicadoException Se lanza si el nombre ya existe.
	 */
	public void addContacto(Contacto c) throws NombreVacioException, NombreDuplicadoException {
		Depurador.trazar("Añadiendo contacto a la agenda");
		String nombreNuevoContacto = c.getName();
		if (nombreNuevoContacto.length() == 0 || nombreNuevoContacto == null)
			throw new NombreVacioException("El nombre del nuevo contacto está vacío.");
		for (int i = 0; i < contactos.size(); i++)
			if (contactos.get(i).getName().equals(nombreNuevoContacto))
				throw new NombreDuplicadoException(nombreNuevoContacto + " ya existe en la agenda.");
		contactos.add(c);
	}

	/**
	 * Busca el nombre "nombre" en el campo "nombre" de la lista de
	 * contactos. Lo añade a la lista de coincidencias si el nombre
	 * pasado como parámetro forma parte de alguno de estos nombres,
	 * aunque no coincida exactamente. No distingue entre mayúsculas
	 * y minúsculas.
	 * @param nombre Nombre a buscar.
	 * @return ArrayList de contactos que cumplen la condición de 
	 * búsqueda.
	 */
	public ArrayList<Contacto> buscarContacto(String nombreBuscado) {
		Depurador.trazar("Buscando "+nombreBuscado);
		ArrayList<Contacto> coincidencias=new ArrayList<>();
		for(int i=0;i<contactos.size();i++) {
			Depurador.trazar("Comparando parámetro "+nombreBuscado.toUpperCase()+" con "+contactos.get(i).getName());
			if(contactos.get(i).equals(nombreBuscado)) {
				Depurador.trazar("Los nombres coinciden, se devolverá el contacto.");
				coincidencias.add(contactos.get(i));
			}
		}
		return coincidencias;		
	}
	
	
	/*
	 * TODO: buscarContacto() con otros parámetros.
	 */	
	
	public String toString() {
		return contactos.toString();
	}

}
