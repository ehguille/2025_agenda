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
	
	public ArrayList<Contacto> buscarContacto(String nombre) {
		Depurador.trazar("Buscando "+nombre);
		ArrayList<Contacto> coincidencias=new ArrayList<>();
		for(int i=0;i<contactos.size();i++) {
			String nombreActual=contactos.get(i).getName();
			Depurador.trazar("Comparando parámetro "+nombre+" con "+nombreActual);
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
