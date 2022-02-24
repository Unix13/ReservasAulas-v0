package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Aulas;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Profesores;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Reservas;

public class Modelo {

	private static final int CAPACIDAD = 10;

	Profesores profesores;
	Aulas aulas;
	Reservas reservas;

	public Modelo() {
		profesores = new Profesores(CAPACIDAD);
		aulas = new Aulas(CAPACIDAD);
		reservas = new Reservas(CAPACIDAD);
	}

	public Aula[] getAulas() {
		return aulas.get();
	}

	public int getNumAulas() {
		return aulas.getTamano();
	}

	public String[] representarAulas() {

		String[] listaAulas = aulas.representar();
		boolean vacio = true;
		for (String s : listaAulas) {
			if (s != null) {
				vacio = false;
			}
		}
		if (vacio == true) {
			return null;
		}
		return listaAulas;
	}

	public Aula buscarAula(Aula aula) {

		Aula aulaEncontrada = aulas.buscar(aula);
		if (aulaEncontrada == null) {
			return null;
		} else {
			return new Aula(aulaEncontrada);
		}
	}

	public void insertarAula(Aula aula) throws OperationNotSupportedException {
		aulas.insertar(aula);
	}

	public void borrarAula(Aula aula) throws OperationNotSupportedException {
		aulas.borrar(aula);
	}

	public Profesor[] getProfesores() {
		return profesores.get();
	}

	public int getNumProfesores() {
		return profesores.getTamano();
	}

	public String[] representarProfesores() {

		String[] listaProfesores = profesores.representar();
		boolean vacio = true;
		for (String s : listaProfesores) {
			if (s != null) {
				vacio = false;
			}
		}
		if (vacio == true) {
			return null;
		}
		return listaProfesores;
	}

	public Profesor buscarProfesor(Profesor profesor) {

		Profesor profesorEncontrado = profesores.buscar(profesor);
		if (profesorEncontrado == null) {
			return null;
		}
		return new Profesor(profesorEncontrado);
	}

	public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {

		profesores.insertar(profesor);
	}

	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {
		profesores.borrar(profesor);
	}

	public Reserva[] getReservas() {
		return reservas.get();
	}

	public int getNumReservas() {
		return reservas.getTamano();
	}

	public String[] respresentarReservas() {

		String[] listaReservas = reservas.representar();
		boolean vacio = true;
		for (String s : listaReservas) {
			if (s != null) {
				vacio = false;
			}
		}
		if (vacio == true) {
			return null;
		}
		return listaReservas;
	}

	public Reserva buscarReserva(Reserva reserva) {

		Reserva reservaEncontrada = reservas.buscar(reserva);
		if (reservaEncontrada == null) {
			return null;
		}
		return new Reserva(reservaEncontrada);
	}

	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {
		reservas.insertar(reserva);
	}

	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {
		reservas.borrar(reserva);
	}

	public Reserva[] getReservasAula(Aula aula) {

		Reserva[] reservasAula = reservas.getReservasAula(aula);
		boolean vacio = true;
		for (Reserva r : reservasAula) {
			if (r != null) {
				vacio = false;
			}
		}
		if (vacio == true) {
			return null;
		}
		return reservasAula;
	}

	public Reserva[] getReservasProfesor(Profesor profesor) {

		Reserva[] reservasProfesor = reservas.getReservasProfesor(profesor);
		boolean vacio = true;
		for (Reserva r : reservasProfesor) {
			if (r != null) {
				vacio = false;
			}
		}
		if (vacio == true) {
			return null;
		}
		return reservasProfesor;
	}

	public Reserva[] getReservasPermanencia(Permanencia permanencia) {

		Reserva[] reservasPermanencia = reservas.getReservasPermanencia(permanencia);
		boolean vacio = true;
		for (Reserva r : reservasPermanencia) {
			if (r != null) {
				vacio = false;
			}
		}
		if (vacio == true) {
			return null;
		}
		return reservasPermanencia;
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		return reservas.consultarDisponibilidad(aula, permanencia);
	}
}
