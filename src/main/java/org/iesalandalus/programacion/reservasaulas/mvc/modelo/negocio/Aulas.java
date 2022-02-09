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
		return copiaAulas;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	public Aulas(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		coleccionAulas = new Aula[capacidad];
		this.capacidad = capacidad;
		this.tamano = 0;

	}

	private boolean tamanoSuperado(int nuevoTamano) {
		boolean superado = false;
		if (nuevoTamano >= tamano) {
			superado = true;
		}
		return superado;
	}

	private boolean capacidadSuperada(int nuevoTamano) {
		boolean superado = false;
		if (nuevoTamano >= capacidad) {
			superado = true;
		}
		return superado;
	}

	private int buscarIndice(Aula aula) {
		int indice = tamano + 1;
		int i;
		for (i = 0; i < tamano; i++) {
			if (coleccionAulas[i].equals(aula)) {

				indice = i;
			}
		}
		return indice;
	}

	public void insertar(Aula aula) throws OperationNotSupportedException {
		
		
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}

		if (capacidadSuperada(tamano) == true) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más aulas.");
		}
			int indice = buscarIndice(aula);
			
			if (tamanoSuperado(indice)) {
				coleccionAulas[tamano]=new Aula(aula);
			}
			else{
				throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");
			
			}
			tamano++;
		}
	

	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un aula nula.");
		}
		int indice;
		indice = buscarIndice(aula);
		if (indice != tamano + 1) {

			desplazarUnaPosicionHaciaIzquierda(indice);
			System.out.println("Aula borrada correctamente.");
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}
	}

	public Aula buscar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}
		int indice;
		Aula aulaEncontrada = null;
		indice = buscarIndice(aula);
		if (indice != tamano + 1) {
			aulaEncontrada = new Aula(coleccionAulas[indice]);
		}

		return aulaEncontrada;
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		int i;
		for (i = 0; i < tamano; i++) {
			if (i > indice) {
				coleccionAulas[i - 1] = coleccionAulas[i];
			}
		}
		coleccionAulas[i] = null;
	}

	public String[] representar() {
		String[] representacion = new String[1];
		return representacion;
	}

}
