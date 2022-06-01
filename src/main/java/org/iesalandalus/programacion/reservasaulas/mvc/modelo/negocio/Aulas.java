package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {

	private Aula[] coleccionAulas;
	private int tamano;

	private int capacidad;

	public Aulas(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}

		this.capacidad = capacidad;
		coleccionAulas = new Aula[capacidad];

	}

	public Aula[] get() {
		return copiaProfundaAulas();
	}

	private Aula[] copiaProfundaAulas() {
		Aula[] copiaAulas = new Aula[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			copiaAulas[i] = new Aula(coleccionAulas[i]);
		}
		return copiaAulas;
	}

	public int getTamano() {
		return tamano;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void insertar(Aula aula) throws OperationNotSupportedException {

		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}

		if (capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más aulas.");
		}
		int indice = buscarIndice(aula);
		if (tamanoSuperado(indice)) {
			coleccionAulas[tamano] = new Aula(aula);

			tamano++;

		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");

		}

	}

	private int buscarIndice(Aula aula) {
		int indice = 0;
		boolean encontrado = false;

		while (!tamanoSuperado(indice) && !encontrado) {
			if (coleccionAulas[indice].equals(aula)) {
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

	public Aula buscar(Aula aula) {
		int indice = buscarIndice(aula);

		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}

		if (!tamanoSuperado(indice)) {

			return new Aula(coleccionAulas[indice]);
		} else {

			return null;
		}

	}

	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}
		int indice = buscarIndice(aula);
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			tamano--;

		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}

	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < coleccionAulas.length - 1; i++) {

			coleccionAulas[i] = coleccionAulas[i + 1];
		}

	}

	public String[] representar() {

		String[] representacion = new String[tamano];
		for (int i = 0; !tamanoSuperado(i); i++) {

			representacion[i] = coleccionAulas[i].toString();

		}
		return representacion;
	}
}