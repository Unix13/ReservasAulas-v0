package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

public class Profesores {

	private int capacidad;
	private int tamano;
	Profesor[] coleccionProfesores;

	public Profesor[] get() {
		return copiaProfundaProfesores();
	}

	private Profesor[] copiaProfundaProfesores() {

		int indice = 0;
		Profesor[] copiaProfunda = new Profesor[tamano];
		for (int i = indice; i <= tamano - 1; ++i) {
			copiaProfunda[indice] = new Profesor(coleccionProfesores[i]);
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

	public Profesores(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		coleccionProfesores = new Profesor[capacidad];
		this.capacidad = capacidad;
		this.tamano = 0;
	}

	private int buscarIndice(Profesor profesor) {
		boolean profesorEncontrado = false;
		int resultado = 0;
		for (int i = 0; i <= tamano - 1; i++) {
			if (profesor.equals(coleccionProfesores[i])) {
				profesorEncontrado = true;
				resultado = i;
			}
		}
		if (profesorEncontrado) {
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

	public void insertar(Profesor profesor) throws OperationNotSupportedException {

		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}
		int espacioPosible = buscarIndice(profesor);
		if (capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más profesores.");
		} else if (tamanoSuperado(espacioPosible)) {
			coleccionProfesores[tamano] = new Profesor(profesor);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese nombre.");
		}
	}

	public Profesor buscar(Profesor profesor) {
		
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		}
		int indice = buscarIndice(profesor);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Profesor(coleccionProfesores[indice]);
		}
	}


	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < tamano - 1; ++i) {
			coleccionProfesores[i] = new Profesor(coleccionProfesores[i + 1]);
		}
	}


	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		
		int indice = 0;
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		}
		
		indice = buscarIndice(profesor);
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			coleccionProfesores[tamano - 1] = null;
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
		}
	}


	public String[] representar() {
		String[] representacion = new String[tamano];
		int indice = 0;
		for (int i = indice; i <= tamano - 1; ++i) {
			representacion[indice] = coleccionProfesores[i].toString();
			indice++;
		}
		return representacion;
	}
}