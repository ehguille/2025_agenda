//TODO: Documentar la clase Agenda.java
package agenda;

import java.util.Scanner;

import agenda.excepciones.NombreDuplicadoException;
import agenda.excepciones.NombreVacioException;
import java.io.File;
import depurador.Depurador;

/**
 * Interfaz de la aplicación
 * @author guillermogb
 * @version 1.0
 */
public class Interfaz {
	
	/** Agenda propiamente dicha*/
	private Agenda a;
	private Scanner s;
	
	/**
	 * Lanza la interfaz
	 */
	public Interfaz() {
		s=new Scanner(System.in);
		a=new Agenda();
		GestorPersistencia g=new GestorPersistencia("agenda.dat");
		try { //TODO: Eliminar cuando no se use.
			Cargador.cargarContactos(a);
		} catch (NombreVacioException | NombreDuplicadoException e) {
			e.printStackTrace();
		}
		mostrarMenuPrincipal();
		s.close();
		g.guardar(a);
	}
	
	/**
	 * Imprime el menú principal de la aplicación.
	 * TODO: Gestionar excepciones si se introduce algo distinto a un entero.
	 */
	private void mostrarMenuPrincipal() {
		int opcion=-1;
		do {
			System.out.println("[Aplicacion agenda]");
			System.out.println("- Escoge una opción:");
			System.out.println("1.- Añadir un contacto.");
			System.out.println("2.- Borrar un contacto.");
			System.out.println("3.- Modificar un contacto.");
			System.out.println("4.- Buscar (y mostrar) un contacto.");
			System.out.println("5.- Imprimir toda la agenda.");
			System.out.println("0.- Salir.");
			try {
				opcion=Integer.parseInt(s.nextLine());
			} catch(NumberFormatException e) {
				System.err.println("Error al seleccionar la opción: "+e.getMessage());
			}
			switch(opcion) {
			case 0:
				System.out.println("Saliendo de la aplicación...");
				break;
			case 1:
				mostrarMenuAddContacto();
				break;
			case 2:
				mostrarMenuBorrarContacto();
				break;
			case 3:
				mostrarMenuEditarContacto();
				break;
			case 4:
				mostrarMenuBuscar();
				break;
			case 5:
				imprimirAgenda();
				break;
			default:
				System.out.println("** Opción incorrecta; escoge otra opción.");
			}
		} while(opcion!=0);
	}
	
	private void mostrarMenuBuscar() {
		String nombre;
		System.out.println("[Introduzca un nombre a buscar]");
		nombre=s.nextLine();
		System.out.println(a.buscarContacto(nombre));
	}
	
	private void mostrarMenuEditarContacto() {
		System.out.println("[EDITAR CONTACTO]");
		System.out.println("- Seleccione un nombre de la lista: ");
		System.out.println("["+a.getListadoNombres()+"]");
		String nombreContacto=s.nextLine();
		if(a.existe(nombreContacto))
			mostrarSubMenuEditarContacto(nombreContacto);
		else
			System.out.println("No existe ningún contacto con nombre="+nombreContacto);
	}
	
	//TODO: Pedir confirmación antes de realizar el cambio.
	private void mostrarSubMenuEditarContacto(String nombreContacto) {
		int opcion=0;
		do {
			System.out.println("- Se ha encontrado el siguiente contacto:");
			System.out.println(a.buscarContacto(nombreContacto));
			System.out.println("- Seleccione el campo a editar:");
			System.out.println("1.- Nombre.");
			System.out.println("2.- Apellidos.");
			System.out.println("3.- Dirección postal.");
			//OJO: editar la lista de teléfonos es añadir, borrar o modificar un teléfono.
			System.out.println("4.- Lista de teléfonos.");
			//OJO: lo mismo con los mails.
			System.out.println("5.- Lista de correos electrónicos.");
			opcion=Integer.parseInt(s.nextLine());
			switch(opcion) {
			case 1:	
				System.out.println("- Introduzca un nuevo nombre:");
				String nuevoNombre=s.nextLine();
				try {
					a.renombrarContacto(nombreContacto, nuevoNombre);
				} catch (NombreDuplicadoException e) {
					System.err.println(e.getMessage());
				}
			break;
			case 2:
				System.out.println("- Introduzca unos nuevos apellidos:");
				String nuevosApellidos=s.nextLine();
				a.setApellidos(nombreContacto, nuevosApellidos);
				break;
			case 3:
				System.out.println("- Introduzca una dirección postal nueva:");
				a.setApellidos(nombreContacto, s.nextLine());
				break;
			case 4:
				System.out.println(a.getTelefonos(nombreContacto));
				//20260209: TODO (Seguir)
				//Dar opciones:
				//borrar teléfono
				//modificar teléfono
				//añadir teléfono
				break;
			case 5:
				//misma casuística que con los teléfonos
				break;
			default:
				System.out.println("Opción incorrecta.");
			}
		} while(opcion<1||opcion>5);
	}
	
	/**
	 * Solo borrará el contacto con el nombre exactamente igual
	 * al que consta en la agenda. Si existen Luis y Luisa, y se
	 * pide borrar a Luis, se borrará solo a Luis, no a Luisa.
	 * 
	 * No es sensible a mayúsculas.
	 */
	private void mostrarMenuBorrarContacto() {
		System.out.println("[BORRAR CONTACTO]");
		System.out.println("- Seleccione un nombre de la lista: ");
		System.out.println("["+a.getListadoNombres()+"]");
		String nombreParaBorrar=s.nextLine();
		boolean contactoBorrado=a.borrarContacto(nombreParaBorrar);
		if(contactoBorrado)
			System.out.println("- El contacto "+nombreParaBorrar+" ha sido borrado.");
		else
			System.out.println("- El contacto "+nombreParaBorrar+" no existía en la agenda.");
		//TODO: Pedir confirmación antes de borrar. Implicaría cambiar el método a.borrarContacto().
	}
	
	private void mostrarMenuAddContacto()  {
		System.out.println("[Crear nuevo contacto]");
		System.out.println("- Introduzca nombre del contacto:");
		String nombre=s.nextLine();
		try {
			a.addContacto(nombre);
			//TODO: Faltarían un montón de excepciones (control de campos).
			//Pide apellidos
			System.out.println("- Introduzca apellidos del contacto:");
			a.setApellidos(nombre, s.nextLine());
			//Pide dirección
			System.out.println("- Introduzca dirección postal del contacto:");
			a.setDireccionPostal(nombre,s.nextLine());
			//Bucle para añadir teléfonos. Podría sacarse a un método.
			String descripcionTelefono;
			do { //Pido teléfonos hasta que introduzca una descripción vacía.
				System.out.println("- Introduzca descripción para un telefono:");
				descripcionTelefono=s.nextLine();
				if(descripcionTelefono=="")			
					break;
				/**TODO: Gestionar con paises, no prefijos
				System.out.println("- Introduzca el prefijo del teléfono para dicha localización:");
				int prefijo=Integer.parseInt(s.nextLine());
				System.out.println("- Introduzca el teléfono:");
				int telefono=Integer.parseInt(s.nextLine());
				a.addTelefono(nombre, descripcionTelefono, prefijo, telefono);*/
			} while(descripcionTelefono!="");
			//
			//Bucle para añadir mails.
			String descripcionMail;
			do {
				System.out.println("- Introduzca descripción para un e-mail:");
				descripcionMail=s.nextLine();
				if(descripcionMail=="")
					break;
				System.out.println("- Introduzca la dirección de e-mail:");
				String direccionMail=s.nextLine();
				a.addCorreo(nombre, descripcionMail, direccionMail);
			} while(descripcionMail!="");
			//Lo siguiente es una ñapa para explicar las excepciones.
		} catch(NombreVacioException e) {
			System.err.println("No puede añadirse un contacto con nombre vacío.");
		} catch(NombreDuplicadoException e) {
			System.err.println("El contacto "+nombre+" ya existe. No se añadirá a la agenda.");
		} catch(NullPointerException e) {
			//Esta excepción nunca se ejecutará. Es solo un ejemplo.
			System.err.println("Estás intentando añadir un contacto que no existe.");
		} catch(Exception e) {
			//Esto captura cualquier otra excepción.
			System.err.println("Excepción genérica.");
		}		
	}
	
	private void imprimirAgenda() {
		System.out.println(a);
	}
	
	/**
	 * Método main
	 */
	public static void main(String[] args) {
		Depurador.encender();
		Depurador.trazar("Aplicación iniciada");
		Interfaz i=new Interfaz();
		Depurador.trazar("Aplicación cerrada");
	}
	
}