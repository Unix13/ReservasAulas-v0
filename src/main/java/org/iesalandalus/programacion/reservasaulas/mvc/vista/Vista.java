package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;

public class Vista {
	private static final String ERROR = "ERROR: ";
	private static final String NOMBRE_VALIDO = "Nombre válido";
	private static final String CORREO_VALIDO = "Correo válido";

	private Controlador controlador;

	public Vista() {
		Opcion.setVista(this);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void comenzar() {
		Consola.mostrarCabecera("Gestión de las Reservas de Aulas del IES Al-Ándalus");
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegundoOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	public void salir() {
		controlador.terminar();
	}

	public void insertarAula() {
		Consola.mostrarCabecera("Insertar Aula");
		try {
			controlador.insertarAula(Consola.leerAula());
			System.out.println("Aula insertada correctamente.");
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarAula() {
		Consola.mostrarCabecera("Borrar Aula");
		try {
			controlador.borrarAula(Consola.leerAula());
			System.out.println("Aula borrada correctamente.");
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarAula() {
		Consola.mostrarCabecera("Buscar Aula");
		Aula aula;
		try {
			aula = controlador.buscarAula(Consola.leerAula());
			String mensaje = (aula != null) ? aula.toString() : "Aula no registrada en el sistema.";
			System.out.println(mensaje);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAulas() {

		Consola.mostrarCabecera("Listado de Aulas");

		String[] aulas = controlador.representarAulas();
		if (aulas.length > 0) {
			for (String aula : aulas) {
				System.out.println(aula);
			}
		} else {
			System.out.println(ERROR + "No hay aulas que listar.Debe insertar primero un aula en el sistema.");
		}
	}

	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar Profesor");
		try {
			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("Profesor insertado correctamente.");
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar Profesor");
		try {
			controlador.borrarProfesor(Consola.leerProfesor());
			System.out.println("Profesor borrado correctamente.");
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar Profesor");
		Profesor profesor;
		try {
			profesor = controlador.buscarProfesor(Consola.leerProfesor());
			String mensaje = (profesor != null) ? profesor.toString()
					: ERROR + "El profesor no está registrado en el sistema.";
			System.out.println(mensaje);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarProfesores() {
		Consola.mostrarCabecera("Listado de Profesores");

		String[] profesores = controlador.representarProfesores();
		if (profesores.length > 0) {
			for (String profesor : profesores) {
				System.out.println(profesor);
			}
		} else {
			System.out
					.println(ERROR + "No hay profesores que listar. Debe insertar primero un profesor en el sistema.");
		}
	}

	public void realizarReserva() {

		try {

			Profesor profesor = null;
			controlador.realizarReserva(leerReserva(profesor));
			System.out.println("Reserva insertada correctamente, " + NOMBRE_VALIDO + "/" + CORREO_VALIDO + ".");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private Reserva leerReserva(Profesor profesor) {
		Consola.mostrarCabecera("Realizar Reserva");

		String nombreAula;
		String nombreProfesor;
		String[] profesores = controlador.representarProfesores();
		String[] aulas = controlador.representarAulas();
		String correoProfesor = new String();
		String correoProfesorLimpio = new String();

		Reserva reserva = null;
		Aula aula = null;
		Permanencia permanencia = null;
		boolean aulaRegistrada = false;
		boolean profesorRegistrado = false;
		try {
			nombreProfesor = Consola.leerNombreProfesor();
			nombreAula = Consola.leerNombreAula();

			for (int i = 0; i < profesores.length; i++) {
				String datosProfesores = profesores[i].toString();

				if (nombreProfesor.equals(
						datosProfesores.substring(datosProfesores.indexOf('=') + 1, datosProfesores.indexOf(',')))) {
					profesorRegistrado = true;

					correoProfesor = datosProfesores.substring(datosProfesores.indexOf('=') + 1,
							datosProfesores.lastIndexOf(','));
					correoProfesorLimpio = correoProfesor.replace(nombreProfesor + ", correo=", "");

				}
			}
			for (int j = 0; j < aulas.length; j++) {
				if (aulas[j].toString().replace("nombre Aula=", "").equals(nombreAula)) {
					aula = new Aula(nombreAula);
					aulaRegistrada = true;

				}
			}

			if (!profesorRegistrado) {
				System.out.println(ERROR + "No está registrado el profesor " + nombreProfesor + " en el sistema.");

			} else if (!aulaRegistrada) {
				System.out.println(ERROR + "No está registrada el aula" + nombreAula + " en el sistema.");

			}

			permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());

			Profesor profesorALeer = new Profesor(nombreProfesor, correoProfesorLimpio);

			reserva = new Reserva(profesorALeer, aula, permanencia);
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

		return reserva;

	}

	public void anularReserva() {

		Consola.mostrarCabecera("Anular Reserva");

		try {

			Profesor profesor = null;
			controlador.anularReserva(leerReserva(profesor));
			System.out.println("Reserva anulada correctamente, " + NOMBRE_VALIDO + CORREO_VALIDO + ".");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarReservas() {

		Consola.mostrarCabecera("Listado de Reservas");

		String[] reservas = controlador.representarReservas();
		if (reservas.length > 0) {
			for (String reserva : reservas) {
				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas que listar. Debe insertar primero una reserva.");
		}
	}

	public void listarReservasAula() {
		Consola.mostrarCabecera("Listado de Reservas por Aula");
		Reserva[] reservas = controlador.getReservasAula(Consola.leerAula());
		if (reservas[0] != null) {
			for (Reserva reserva : reservas) {
				if (reserva != null)
					System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas, para dicha aula.");
		}
	}

	public void listarReservasProfesor() {
		Consola.mostrarCabecera("Listado de Reservas por Profesor");
		Reserva[] reservas = controlador.getReservasProfesor(Consola.leerProfesor());
		if (reservas[0] != null) {
			for (Reserva reserva : reservas) {
				if (reserva != null)
					System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas, para dicho profesor.");
		}
	}

	public void listarReservasPermanencia() {
		Consola.mostrarCabecera("Listado de Reservas por Permanencia");
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		Reserva[] reservas = controlador.getReservasPermanencia(permanencia);
		if (reservas[0] != null) {
			for (Reserva reserva : reservas) {
				if (reserva != null)
					System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas, para dicha permanencia.");
		}
	}

	public void consultarDisponibilidad() {

		Consola.mostrarCabecera("Consultar Disponibilidad");
		String nombreAula;
		String[] aulas = controlador.representarAulas();
		LocalDate dia;
		Tramo tramo;

		boolean aulaRegistrada = false;

		try {

			nombreAula = Consola.leerNombreAula();
			// recorro el array
			for (int i = 0; i < aulas.length; i++) {
				if (aulas[i].toString().replace("nombre Aula=", "").equals(nombreAula)) {
					aulaRegistrada = true;

					Aula aulaAConsultar = new Aula(nombreAula);
					dia = Consola.leerDia();
					tramo = Consola.leerTramo();
					Permanencia permanencia = new Permanencia(dia, tramo);

					if (controlador.consultarDisponibilidad(aulaAConsultar, permanencia) == true) {
						System.out.println("Disponible el aula " + nombreAula + " para reservar el día " + dia
								+ " en el tramo de " + tramo + ".");
					} else {
						System.out.println("No Disponible el aula " + nombreAula + " para reservar el día " + dia
								+ " en el tramo de " + tramo + ".");
					}

				}
			}

			if (!aulaRegistrada) {
				System.out.println(ERROR + "No está registrada el aula " + nombreAula + " en el sistema.");

			}

		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());

		}

	}
}