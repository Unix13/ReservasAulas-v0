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

	Controlador controlador;
	private final static String ERROR = "No existen reservas para el parámetro proporcionado";
	private final static String NOMBRE_VALIDO = "Peter";
	private final static String CORREO_VALIDO = "peter1984@yahoo.com";

	public Vista() {

		Opcion.setVista(this);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void comenzar() {

		int ordinalOpcion = 0;
		do {
			try {
				Consola.mostrarMenu();
				ordinalOpcion = Consola.elegirOpcion();
				Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
				opcion.ejecutar();
			} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	public void salir() {

		System.out.println("Se ha terminado el programa.");
		controlador.terminar();
	}

	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar Profesor");
		try {
			controlador.insertar(Consola.leerProfesor());
			System.out.println("Profesor insertado correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarProfesor() {

		Consola.mostrarCabecera("Buscar Profesor");
		Profesor profesor = null;
		boolean problema = false;
		String[] listaProfesores = controlador.representarProfesores();
		if (listaProfesores == null) {
			System.out.println("No existen profesores que buscar");
		} else {
			do {
				try {
					Profesor profesorABuscar = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
					profesor = controlador.buscar(profesorABuscar);
					problema = false;
				} catch (NullPointerException | IllegalArgumentException e) {
					System.out.println(e.getMessage());
					problema = true;
				}
			} while (problema == true);
			if (profesor == null) {
				System.out.println("El profesor buscado no existe");
			} else {
				System.out.println(profesor.toString());
			}
		}
	}

	public void borrarProfesor() {

		Consola.mostrarCabecera("Borrar Profesor");
		String[] listaProfesores = controlador.representarProfesores();
		if (listaProfesores == null) {
			System.out.println("No existen profesores que borrar");
		} else {
			boolean problema = false;
			do {
				try {
					Profesor profesorABorrar = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
					controlador.borrar(profesorABorrar);
					problema = false;
				} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
					System.out.println(e.getMessage());
					problema = true;
				}
			} while (problema == true);
			System.out.println("Profesor borrado correctamente.");
		}
	}

	public void listarProfesores() {

		Consola.mostrarCabecera("Listado de Profesores");
		String[] listaProfesores = null;
		try {
			listaProfesores = controlador.representarProfesores();
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (listaProfesores == null) {
			System.out.println("No hay profesores que mostrar");
		} else {
			for (String r : listaProfesores) {
				if (r != null) {
					System.out.println(r.toString());
				}
			}
		}
	}

	public void insertarAula() {

		Consola.mostrarCabecera("Insertar Aula");
		try {
			controlador.insertar(Consola.leerAula());
			System.out.println("Aula insertada correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarAula() {

		Consola.mostrarCabecera("Buscar Aula");
		Aula aula = null;
		boolean problema = false;
		String[] listaAulas = controlador.representarAulas();
		if (listaAulas == null) {
			System.out.println("No existen aulas que buscar");
		} else {
			do {
				try {
					aula = controlador.buscar(Consola.leerAula());
					problema = false;
				} catch (NullPointerException | IllegalArgumentException e) {
					System.out.println(e.getMessage());
					problema = true;
				}
			} while (problema == true);
			if (aula == null) {
				System.out.println("El aula buscada no existe");
			} else {
				System.out.println(aula.toString());
			}
		}
	}

	public void borrarAula() {

		Consola.mostrarCabecera("Borrar Aula");
		boolean problema = false;
		String[] listaAulas = controlador.representarAulas();
		if (listaAulas == null) {
			System.out.println("No existen aulas que borrar");
		} else {
			do {
				try {
					controlador.borrar(Consola.leerAula());
					problema = false;
				} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
					System.out.println(e.getMessage());
					problema = true;
				}
			} while (problema == true);
			System.out.println("Aula borrada correctamente.");
		}
	}

	public void listarAulas() {

		Consola.mostrarCabecera("Listado de Aulas");
		String[] listaAulas = null;
		try {
			listaAulas = controlador.representarAulas();
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (listaAulas == null) {
			System.out.println("No hay aulas que mostrar");
		} else {
			for (String r : listaAulas) {
				if (r != null) {
					System.out.println(r.toString());
				}
			}
		}
	}

	public void realizarReserva() {

		Reserva reservaARealizar = null;
		try {
			reservaARealizar = leerReserva(Consola.leerProfesor());
			if (controlador.buscar(reservaARealizar.getProfesor()) == null
					|| controlador.buscar(reservaARealizar.getAula()) == null) {
				System.out.println(
						"El profesor y/o aula introducidos no existen. Por favor, creélos antes de intentar realizar una reserva con ellos.");
			} else {
				controlador.realizarReserva(reservaARealizar);
				System.out.println("Reserva realizada correctamente.");
			}
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	private Reserva leerReserva(Profesor profesor) {

		boolean problema = false;
		Reserva reserva = null;
		Aula aula = null;
		Tramo tramo = null;
		LocalDate dia = null;
		Permanencia permanencia = null;
		do {
			try {
				aula = Consola.leerAula();
				tramo = Consola.leerTramo();
				dia = Consola.leerDia();
				permanencia = new Permanencia(dia, tramo);
				reserva = new Reserva(profesor, aula, permanencia);
				problema = false;
			} catch (NullPointerException | IllegalArgumentException e) {
				System.out.println(e.getMessage());
				problema = true;
			}
		} while (problema == true);
		return new Reserva(reserva);
	}

	public void anularReserva() throws OperationNotSupportedException {

		boolean problema = false;
		Reserva reserva = null;
		Profesor profesor = new Profesor(NOMBRE_VALIDO, CORREO_VALIDO);
		String[] listaReservas = controlador.representarReservas();
		if (listaReservas == null) {
			System.out.println("No existen reserrvas que borrar");
		} else {

			do {
				try {
					reserva = leerReserva(profesor);
					controlador.anularReserva(reserva);
					problema = false;
				} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
					System.out.println(e.getMessage());
					problema = true;
				}
			} while (problema == true);
			System.out.println("Reserva anulada correctamente.");
		}
	}

	public void listarReservas() {

		String[] listaReservas = null;
		try {
			listaReservas = controlador.representarReservas();
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (listaReservas == null) {
			System.out.println("No hay reservas que mostrar");
		} else {
			for (String r : listaReservas) {
				if (r != null) {
					System.out.println(r.toString());
				}
			}
		}
	}

	public void listarReservasAula() {

		Reserva[] listaReservasAula = null;
		try {
			listaReservasAula = controlador.getReservasAula(Consola.leerAula());
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (listaReservasAula == null) {
			System.out.println(ERROR);
		} else {
			for (Reserva r : listaReservasAula) {
				if (r != null) {
					System.out.println(r.toString());
				}
			}
		}
	}

	public void listarReservaProfesor() {

		Reserva[] listaReservasProfesor = null;
		try {
			Profesor profesorABuscar = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
			listaReservasProfesor = controlador.getReservasProfesor(profesorABuscar);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (listaReservasProfesor == null) {
			System.out.println(ERROR);
		} else {
			for (Reserva r : listaReservasProfesor) {
				if (r != null) {
					System.out.println(r.toString());
				}
			}
		}
	}

	public void listarReservaPermanencia() {

		Reserva[] listaReservasPermanencia = null;
		Permanencia permanencia = null;
		Tramo tramo = null;
		LocalDate dia = null;
		try {
			tramo = Consola.leerTramo();
			dia = Consola.leerDia();
			permanencia = new Permanencia(dia, tramo);
			listaReservasPermanencia = controlador.getReservasPermanencia(permanencia);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (listaReservasPermanencia == null) {
			System.out.println(ERROR);
		} else {
			for (Reserva r : listaReservasPermanencia) {
				if (r != null) {
					System.out.println(r.toString());
				}
			}
		}
	}

	public void consultarDisponibilidad() {

		boolean disponible = true;
		Permanencia permanencia = null;
		Tramo tramo = null;
		LocalDate dia = null;
		Aula aula = null;
		try {
			tramo = Consola.leerTramo();
			dia = Consola.leerDia();
			permanencia = new Permanencia(dia, tramo);
			aula = Consola.leerAula();
			disponible = controlador.consultarDisponibilidad(aula, permanencia);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (controlador.buscar(aula) == null) {
			System.out.println("El aula introducida no existe");
		} else if (disponible == true) {
			System.out.println("El aula se encuentra disponible para el tramo y día introducidos");
		} else {
			System.out.println("El aula NO se encuentra disponible para el tramo y día introducidos");
		}
	}

}
