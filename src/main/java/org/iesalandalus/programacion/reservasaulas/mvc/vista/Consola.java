package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private final static DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/M/yyyy");

	public Consola() {

	}

	public static void mostrarMenu() {

		mostrarCabecera("Gestión de reservas de aulas");

		for (Opcion opcion : Opcion.values()) {
			System.out.println(opcion);
		}
	}

	public static void mostrarCabecera(String mensaje) {

		System.out.printf("%n%s%n", mensaje);
		String formatoStr = "%0" + mensaje.length() + "d%n";
		System.out.println(String.format(formatoStr, 0).replace("0", "-"));
	}

	public static int elegirOpcion() {

		int opcionElegida;
		do {
			System.out.println("Elija una opción: ");
			opcionElegida = Entrada.entero();
		} while (opcionElegida < 0 || opcionElegida > Opcion.values().length);

		return opcionElegida;
	}

	public static Profesor leerProfesor() {

		System.out.print("Introduce el nombre del profesor: ");
		String nombre = Entrada.cadena();
		System.out.print("Introduce el correo del profesor: ");
		String correo = Entrada.cadena();
		System.out.print("Introduce el teléfono del profesor: ");
		String telefono = Entrada.cadena();
		Profesor profesor = null;
		if (telefono == null || telefono.trim().equals("")) {

			profesor = new Profesor(nombre, correo);
		} else {

			profesor = new Profesor(nombre, correo, telefono);
		}
		return profesor;
	}

	public static String leerNombreProfesor() {

		String nombre = Entrada.cadena();
		return nombre;
	}

	public static Aula leerAula() {

		System.out.print("Introduce el nombre del aula: ");
		String nombre = Entrada.cadena();
		return new Aula(nombre);
	}

	public static String leerNombreAula() {

		String nombre = Entrada.cadena();
		return nombre;
	}

	public static LocalDate leerDia() {

		System.out.println("Por favor, intrdozuca una fecha: ");
		return LocalDate.parse(Entrada.cadena(), FORMATO_DIA);

	}

	public static Tramo leerTramo() {

		System.out.print("Introduce el tramo de la reserva (0.- Mañana, 1.- Tarde): ");
		int tramoLeido = Entrada.entero();
		Tramo tramo = null;
		if (tramoLeido < 0 || tramoLeido >= Tramo.values().length) {
			System.out.println("ERROR: La opción elegida no corresponde con ningún tramo.");
		} else {
			tramo = Tramo.values()[tramoLeido];
		}
		return tramo;
	}

 }
