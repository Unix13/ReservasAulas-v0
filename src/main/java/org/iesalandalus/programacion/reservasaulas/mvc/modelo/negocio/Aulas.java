package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {

	private int capacidad;
	private int tamano;
	private Aula[] coleccionAulas;

	public Aula[] get() {

		return copiaProfundaAulas();
	}

	private Aula[] copiaProfundaAulas() {
		Aula[] copiaAulas = new Aula[capacidad];
		for (int i = 0; i < tamano; i++) {
			copiaAulas[i] = new Aula(coleccionAulas[i]);
		}
		return copiaAulas;
	}

	public Aulas(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		coleccionAulas = new Aula[capacidad];
		this.capacidad = capacidad;
		this.tamano = 0;

	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	public void insertar(Aula insertarAula) throws OperationNotSupportedException {
		if (insertarAula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}
		int indice = buscarIndice(insertarAula);

		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más aulas.");
		}

		if (tamanoSuperado(indice)) {
			coleccionAulas[indice] = new Aula(insertarAula);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");
		}

	}

	private int buscarIndice(Aula buscarAula) {
		
		int indice = 0;
		boolean aulaEncontrada = false;
		while (!tamanoSuperado(indice) && !aulaEncontrada) {
			if (coleccionAulas[indice].equals(buscarAula)) {
				aulaEncontrada = true;
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

	public Aula buscar(Aula buscarAula) {
		
		if (buscarAula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}
		
		int indice = buscarIndice(buscarAula);
		if (tamanoSuperado(indice)) {
			
			return null;
		} else {
			
			return new Aula(buscarAula);
		}
	}

	public void borrar(Aula borrarAula) throws OperationNotSupportedException {
		
		if (borrarAula == null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}
		
		int indice = buscarIndice(borrarAula);
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		} else {
			
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}

	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; !tamanoSuperado(i); i++) {
			coleccionAulas[i] = coleccionAulas[i + 1];
		}
		tamano--;
	}

	public String[] representar() {
		String[] representacion = new String[tamano];
		for (int i = 0; i < tamano; i++) {
			representacion[i] = coleccionAulas[i].toString();
		}
		return representacion;
	}
}