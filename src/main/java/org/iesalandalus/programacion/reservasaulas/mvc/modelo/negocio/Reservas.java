package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {

	private int capacidad;
	private int tamano;
	private Reserva[] coleccionReservas;

	public Reservas(int numeroReservas){
		
		if(numeroReservas <= 0) {
			
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		capacidad = numeroReservas;
		tamano = 0;
		coleccionReservas = new Reserva[numeroReservas];
		
	}
	public Reserva[] get() {

		return copiaProfundaReservas();
	}

	private Reserva[] copiaProfundaReservas() {
		
		Reserva[] copiaReservas = new Reserva[capacidad];
		for(int i = 0; i < tamano; i++) {
			copiaReservas[i] = new Reserva(coleccionReservas[i]);
		}
		return copiaReservas;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	public void insertar(Reserva insertarReserva) throws OperationNotSupportedException {
		
		if (insertarReserva == null) {
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		}
		int indice = buscarIndice(insertarReserva);

		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
		}

		if (tamanoSuperado(indice)) {
			coleccionReservas[indice] = new Reserva(insertarReserva);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una reserva con ese nombre.");
		}

	}

	private int buscarIndice(Reserva buscarReserva) {
		
		int indice = 0;
		boolean reservaEncontrada = false;
		while (!tamanoSuperado(indice) && !reservaEncontrada) {
			if (coleccionReservas[indice].equals(buscarReserva)) {
				reservaEncontrada = true;
			} else {
				indice++;
			}
		}
		return indice;
	}

	private boolean tamanoSuperado(int indice) {

		return (indice >= tamano);
	}

	private boolean capacidadSuperada(int indice) {

		return (indice >= capacidad);
	}

	public Reserva buscar(Reserva Reserva) {
		if (Reserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}
		int indice = buscarIndice(Reserva);
		if (tamanoSuperado(indice)) {
			
			return null;
			
		} else {
			return new Reserva(Reserva);
		}
	}

	public void borrar(Reserva Reserva) throws OperationNotSupportedException {
		if (Reserva == null) {
			throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
		}
		
		int indice = buscarIndice(Reserva);
		
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			
		} else {
			
			throw new OperationNotSupportedException("ERROR: No existe ninguna reserva con ese nombre.");
		}

	}

	
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		
		for (int i = indice; !tamanoSuperado(i); i++) {
			coleccionReservas[i] = coleccionReservas[i + 1];
		}
		
		tamano--;
	}

	public String[] representar() {
		
		String[] representacion = new String[tamano];
		
		for (int i = 0; i < tamano; i++) {
			representacion[i] = coleccionReservas[i].toString();
		}
		
		return representacion;
	}

	public Reserva[] getReservasProfesor(Profesor profesor) {
		
		if (profesor == null) {
			throw new NullPointerException("ERROR: El profesor no puede ser nulo.");
		}

		Reserva[] reservasCoincidentes = new Reserva[capacidad];

		int indiceReservas = 0;
		for (int i = 0; i < tamano; i++) {
			if (coleccionReservas[i].getProfesor().equals(profesor)) {
				reservasCoincidentes[indiceReservas] = coleccionReservas[i];
				indiceReservas++;
			}
		}

		return reservasCoincidentes;
	}

	public Reserva[] getReservasAula(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: El aula no puede ser nula.");
		}

		Reserva[] reservasCoincidentes = new Reserva[capacidad];

		int indiceReservas = 0;
		for (int i = 0; i < tamano; i++) {
			if (coleccionReservas[i].getAula().equals(aula)) {
				reservasCoincidentes[indiceReservas] = coleccionReservas[i];
				indiceReservas++;
			}
		}

		return reservasCoincidentes;
	}

	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		if (permanencia == null) {
			throw new NullPointerException("ERROR: La permanencia no puede ser nula.");
		}

		Reserva[] reservasCoincidentes = new Reserva[capacidad];

		int indiceReservas = 0;
		for (int i = 0; i < tamano; i++) {
			if (coleccionReservas[i].getPermanencia().equals(permanencia)) {
				reservasCoincidentes[indiceReservas] = coleccionReservas[i];
				indiceReservas++;
			}
		}

		return reservasCoincidentes;
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {

		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		}

		if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}

		for (int i = 0; i < tamano; i++) {
			if (coleccionReservas[i].getAula().equals(aula)
					&& coleccionReservas[i].getPermanencia().equals(permanencia)) {
				return false;
			}
		}

		return true;
	}

}