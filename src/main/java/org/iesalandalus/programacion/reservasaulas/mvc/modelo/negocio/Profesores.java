package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

public class Profesores {

	private Profesor[] coleccionProfesores;
	private int tamano;
	private int capacidad;

	public Profesores(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}

		this.capacidad = capacidad;
		coleccionProfesores = new Profesor[capacidad];

	}

	public Profesor[] get() {
		return copiaProfundaProfesores();
	}

	private Profesor[] copiaProfundaProfesores() {
		Profesor[] copiaProfesores = new Profesor[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			copiaProfesores[i] = new Profesor(coleccionProfesores[i]);
		}
		return copiaProfesores;
	}

	public int getTamano() {
		return tamano;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void insertar(Profesor profesor) throws OperationNotSupportedException {

		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}
		if (capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más profesores.");
		}
		int indice = buscarIndice(profesor);
		if (tamanoSuperado(indice)) {
			coleccionProfesores[tamano] = new Profesor(profesor);
			tamano++;

		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una profesor con ese nombre.");

		}

	}

	private int buscarIndice(Profesor profesor) {
		int indice = 0;
		boolean encontrado = false;
		while (!tamanoSuperado(indice) && !encontrado) {
			if (coleccionProfesores[indice].equals(profesor)) {
				encontrado = true;
			} else {
				indice++;
			}
		}
		return indice;
	}

	private boolean tamanoSuperado(int indice) {
		return indice >= tamano;
	}

	private boolean capacidadSuperada(int indice) {
		return indice >= capacidad;
	}

	public Profesor buscar(Profesor profesor) {
		int indice = buscarIndice(profesor);

		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		}

		if (!tamanoSuperado(indice)) {

			return new Profesor(coleccionProfesores[indice]);
		} else {

			return null;
		}

	}

	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		}
		int indice = buscarIndice(profesor);
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			tamano--;

		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
		}

	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < coleccionProfesores.length - 1; i++) {
			coleccionProfesores[i] = coleccionProfesores[i + 1];
		}

	}

	public String[] representar() {

		String[] representacion = new String[tamano];
		for (int i = 0; !tamanoSuperado(i); i++) {
			representacion[i] = coleccionProfesores[i].toString();
		}
		return representacion;
	}
}