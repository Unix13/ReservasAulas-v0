package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {

	private int capacidad;
	private int tamano;
	Reserva[] coleccionReservas;

	public Reserva[] get() {
		
		return copiaProfundaReservas();
	}

	private Reserva[] copiaProfundaReservas() {
		
		int indice = 0;
		Reserva[] copiaProfunda = new Reserva[tamano];
		for (int i = indice; i <= tamano - 1; ++i) {
			copiaProfunda[indice] = new Reserva(coleccionReservas[i]);
			indice++;
		}
		return copiaProfunda;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	public Reservas(int capacidad) {
		
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		coleccionReservas = new Reserva[capacidad];
		this.capacidad = capacidad;
		this.tamano = 0;
	}

	private int buscarIndice(Reserva reserva) {
		
		boolean reservaEncontrada = false;
		int resultado = 0;
		for (int i = 0; i <= tamano - 1; i++) {
			if (reserva.equals(coleccionReservas[i])) {
				reservaEncontrada = true;
				resultado = i;
			}
		}
		if (reservaEncontrada) {
			return resultado;
		} else {
			return tamano + 1;
		}
	}

	private boolean tamanoSuperado(int indice) {
		
		if (indice > tamano) {
			return true;
		}
		return false;
	}

	private boolean capacidadSuperada(int tamano) {
		
		if (tamano >= capacidad) {
			return true;
		}
		return false;
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		}
		int espacioPosible = buscarIndice(reserva);
		if (capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
		} else if (tamanoSuperado(espacioPosible)) {
			coleccionReservas[tamano] = new Reserva(reserva);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un reserva con ese nombre.");
		}
	}

	public Reserva buscar(Reserva reserva) {

		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}
		int indice = buscarIndice(reserva);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Reserva(coleccionReservas[indice]);
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {

		for (int i = indice; i < tamano - 1; ++i) {
			coleccionReservas[i] = new Reserva(coleccionReservas[i + 1]);
		}
	}

	public void borrar(Reserva reserva) throws OperationNotSupportedException {

		int indice = 0;
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
		}
		indice = buscarIndice(reserva);
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			coleccionReservas[tamano - 1] = null;
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna reserva con ese nombre.");
		}
	}

	public String[] representar() {
		String[] representacion = new String[tamano];
		int indice = 0;
		for (int i = indice; i <= tamano - 1; ++i) {
			representacion[indice] = coleccionReservas[i].toString();
			indice++;
		}
		return representacion;
	}

	public Reserva[] getReservasProfesor(Profesor profesor) {

		Reserva[] reservasProfesor = new Reserva[capacidad];

		int indice = 0;
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}
		for (int i = 0; i <= tamano - 1; i++) {

			if (profesor.equals(coleccionReservas[i].getProfesor())) {
				reservasProfesor[indice] = new Reserva(coleccionReservas[i]);
				indice++;
			}
		}
		return reservasProfesor;
	}

	public Reserva[] getReservasAula(Aula aula) {

		Reserva[] reservasAula = new Reserva[capacidad];

		int indice = 0;
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}
		for (int i = 0; i <= tamano - 1; i++) {
			if (aula.equals(coleccionReservas[i].getAula())) {
				reservasAula[indice] = new Reserva(coleccionReservas[i]);
				indice++;
			}
		}
		return reservasAula;
	}

	public Reserva[] getReservasPermanencia(Permanencia permanencia) {

		Reserva[] reservasPermanencia = new Reserva[capacidad];
		int indice = 0;
		if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}
		for (int i = 0; i <= tamano - 1; i++) {
			if (permanencia.equals(coleccionReservas[i].getPermanencia())) {
				reservasPermanencia[indice] = new Reserva(coleccionReservas[i]);
				indice++;
			}
		}
		return reservasPermanencia;
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {

		boolean disponibilidad = true;
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		} else if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}
		for (int i = 0; i <= tamano - 1; i++) {
			if (aula.equals(coleccionReservas[i].getAula())
					&& permanencia.equals(coleccionReservas[i].getPermanencia())) {
				disponibilidad = false;
			}
		}
		return disponibilidad;
	}

}