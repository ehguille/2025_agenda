package agenda;

import java.util.ArrayList;
import java.util.Iterator;

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
	 * @param c Contacto que añadir.
	 */
	public void addContacto(Contacto c) {
		Depurador.trazar("Añadiendo contacto a la agenda");
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
	public ArrayList<Contacto> buscarContacto(String nombre) {
		Depurador.trazar("Buscando "+nombre);
		ArrayList<Contacto> coincidencias=new ArrayList<>();
		for(int i=0;i<contactos.size();i++) {
			String nombreActual=contactos.get(i).getName().toUpperCase();
			Depurador.trazar("Comparando parámetro "+nombre.toUpperCase()+" con "+nombreActual);
			if(nombreActual.contains(nombre)) {
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
