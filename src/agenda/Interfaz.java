//TODO: Documentar la clase Agenda.java
package agenda;

import java.util.Scanner;

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
		Cargador.cargarContactos(a);
		mostrarMenuPrincipal();
		s.close();
	}
	
	/**
	 * Imprime el menú principal de la aplicación.
	 * TODO: Gestionar excepciones si se introduce algo distinto a un entero.
	 */
	public void mostrarMenuPrincipal() {
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
			opcion=Integer.parseInt(s.nextLine());
			switch(opcion) {
			case 0:
				System.out.println("Saliendo de la aplicación...");
				break;
			case 1:
				mostrarMenuAddContacto();
				break;
			case 2:
				break;
			case 3:
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
		} while(opcion>0&&opcion<6);
	}
	
	public void mostrarMenuBuscar() {
		String nombre;
		System.out.println("[Introduzca un nombre a buscar]");
		nombre=s.nextLine();
		System.out.println(a.buscarContacto(nombre));
	}
	
	public void mostrarMenuAddContacto() {
		String nombre;
		System.out.println("[Crear nuevo contacto]");
		System.out.println("- Introduzca nombre del contacto:");
		nombre=s.nextLine();
		Contacto c = new Contacto(nombre);
		a.addContacto(c);
		//TODO: Meter resto de datos
	}
	
	public void imprimirAgenda() {
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