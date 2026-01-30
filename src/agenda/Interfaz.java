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
	
	/**
	 * Lanza la interfaz
	 */
	public Interfaz() {
		a=new Agenda();
		mostrarMenuPrincipal();
	}
	
	/**
	 * Imprime el menú principal de la aplicación.
	 * TODO: Gestionar excepciones si se introduce algo distinto a un entero.
	 */
	public void mostrarMenuPrincipal() {
		Scanner s=new Scanner(System.in);
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
			opcion=s.nextInt();
			switch(opcion) {
			case 0:
				System.out.println("Saliendo de la aplicación...");
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			default:
				System.out.println("** Opción incorrecta; escoge otra opción.");
			}
		} while(opcion>0&&opcion<6);
		s.close();
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