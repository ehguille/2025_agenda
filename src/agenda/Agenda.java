package agenda;

import java.util.ArrayList;

import agenda.excepciones.*;
import depurador.Depurador;
import enumeraciones.Paises;
import enumeraciones.Provincias;

//TODO: Cambiar bucles for por iteradores.
public class Agenda {
	
	private ArrayList<Contacto> contactos;
	
	public Agenda() {
		Depurador.trazar("Creando lista de contactos");
		contactos=new ArrayList<>();
	}

	
	public String buscarContacto(String nombreBuscado) {
		Depurador.trazar("Buscando "+nombreBuscado);
		for(int i=0;i<contactos.size();i++) {
			Depurador.trazar("Comparando parámetro "+nombreBuscado.toUpperCase()+" con "+contactos.get(i).getNombre());
			if(contactos.get(i).equals(nombreBuscado)) {
				Depurador.trazar("Los nombres coinciden, se devolverá el contacto.");
				return contactos.get(i).toString();
			}
		}
		return null;		
	}

	public String getTelefonos(String nombre) {
		for(int i=0;i<contactos.size();i++) 
			if(contactos.get(i).getNombre().toUpperCase().equals(nombre.toUpperCase())) 
				return contactos.get(i).getTelefonos().toString();
		return null;
	}

	public String getCorreos(String nombre) {
		for(int i=0;i<contactos.size();i++) 
			if(contactos.get(i).getNombre().toUpperCase().equals(nombre.toUpperCase())) 
				return contactos.get(i).getMails().toString();
		return null;
	}
	
	public boolean borrarContacto(String nombreParaBorrar) {
		Depurador.trazar("Recorriendo la lista de contactos para borrar "+nombreParaBorrar);
		for(int i=0;i<contactos.size();i++) 
			if(contactos.get(i).getNombre().toUpperCase().equals(nombreParaBorrar.toUpperCase())) {
				contactos.remove(i);
				Depurador.trazar("Borrado el contacto "+nombreParaBorrar);
				return true;
			}
		Depurador.trazar("No se ha encontrado el contacto "+nombreParaBorrar);
		return false;
	}
	
	public boolean existe(String nombre) {
		for(int i=0;i<contactos.size();i++)
			if(contactos.get(i).getNombre().toUpperCase().equals(nombre.toUpperCase()))
				return true;
		return false;
	}
	
	public String getListadoNombres() {
		Depurador.trazar("Recuperando lista de nombres de la agenda.");
		String listadoNombres="";
		for(int i=0;i<contactos.size();i++) {
			listadoNombres+=contactos.get(i).getNombre();
			if(i<contactos.size()-1)
				listadoNombres+=",";
		}
		return listadoNombres;
	}
	
	public boolean setApellidos(String nombreContacto, String nuevosApellidos) {
		for(int i=0;i<contactos.size();i++)
			if(contactos.get(i).getNombre().toUpperCase().equals(nombreContacto.toUpperCase())) {
				Depurador.trazar("Cambiando apellidos "+contactos.get(i).getApellidos()+" por "+nuevosApellidos);
				contactos.get(i).setApellidos(nuevosApellidos);
				return true;
			}
		return false;
	}
		
	public boolean renombrarContacto(String nombreAntiguo, String nombreNuevo) throws NombreDuplicadoException {
		if(existe(nombreNuevo))
			throw new NombreDuplicadoException("No se puede cambiar un nombre a otro ya existente.");
		for(int i=0;i<contactos.size();i++)
			if(contactos.get(i).getNombre().toUpperCase().equals(nombreAntiguo.toUpperCase())) {
				Depurador.trazar("Cambiando nombre "+nombreAntiguo+" por "+nombreNuevo);
				contactos.get(i).setNombre(nombreNuevo);
				return true;
			}
		return false;
	}
	
	public boolean setDireccionPostal(String nombreContacto, String nuevaDireccion) {
		for(int i=0;i<contactos.size();i++)
			if(contactos.get(i).getNombre().toUpperCase().equals(nombreContacto.toUpperCase())) {
				Depurador.trazar("Cambiando dirección "+contactos.get(i).getApellidos()+" por "+nuevaDireccion);
				contactos.get(i).setDireccionPostal(nuevaDireccion);
				return true;
			}
		return false;
	}
	
	//TODO: Comprobar que se mete un prefijo válido
	public boolean addTelefono(String nombreContacto, String descripcionTelefono, Paises pais, int telefono) {
		for(int i=0;i<contactos.size();i++)
			if(contactos.get(i).getNombre().toUpperCase().equals(nombreContacto.toUpperCase())) {
				Depurador.trazar("Añadiendo teléfono a "+nombreContacto);
				contactos.get(i).addTelefono(descripcionTelefono, pais, telefono);
				return true;
			}
		return false;
	}
	
	public boolean addCorreo(String nombreContacto, String descripcionCorreo, String direccionCorreo) {
		for(int i=0;i<contactos.size();i++)
			if(contactos.get(i).getNombre().toUpperCase().equals(nombreContacto.toUpperCase())) {
				Depurador.trazar("Añadiendo teléfono a "+nombreContacto);
				contactos.get(i).addCorreo(descripcionCorreo, direccionCorreo);
				return true;
			}
		return false;	
	}

	public void addContacto(String nombre) throws NombreVacioException, NombreDuplicadoException {
		Depurador.trazar("Añadiendo contacto a la agenda.");
		if(nombre.length()==0||nombre==null)
			throw new NombreVacioException("El nombre del nuevo contacto está vacío.");
		for(int i=0;i<contactos.size();i++)
			if(contactos.get(i).getNombre().toUpperCase().equals(nombre.toUpperCase()))
				throw new NombreDuplicadoException(nombre + " ya existe en la agenda.");
		Contacto c=new Contacto(nombre);
		contactos.add(c);
	}
	
	public void addContacto(String nombre, String apellidos) throws NombreVacioException, NombreDuplicadoException {
		addContacto(nombre);
		setApellidos(nombre, apellidos);
	}
	
	
	/*
	 * TODO: buscarContacto() con otros parámetros.
	 */		
	public String toString() {
		String cadena="<agenda>\n";
		for(int i=0;i<contactos.size();i++)
			cadena+=contactos.get(i);
		cadena+="</agenda>";
		return cadena;
	}

}
