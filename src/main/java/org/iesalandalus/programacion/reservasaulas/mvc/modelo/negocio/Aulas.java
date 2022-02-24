package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {

	private int capacidad;
	private int tamano;
	Aula[] coleccionAulas;

	


	public Aulas(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		coleccionAulas = new Aula[capacidad];
		this.capacidad = capacidad;
		this.tamano = 0;
	}


	private Aula[] copiaProfundaAulas() {
		
		int indice = 0;
		Aula[] copiaProfunda = new Aula[tamano];
		for (int i = indice; i <= tamano - 1; ++i) {
			copiaProfunda[indice] = new Aula(coleccionAulas[i]);
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


	public Aula[] get() {
		
		return copiaProfundaAulas();
	}


	private int buscarIndice(Aula aula) {
		
		boolean aulaEncontrada = false;
		int resultado = 0;
		for (int i = 0; i <= tamano - 1; i++) {
			if (aula.equals(coleccionAulas[i])) {
				aulaEncontrada = true;
				resultado = i;
			}
		}
		if (aulaEncontrada) {
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


	public void insertar(Aula aula) throws OperationNotSupportedException {
		
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}
		
		int espacioPosible = buscarIndice(aula);
		if (capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más aulas.");
		} else if (tamanoSuperado(espacioPosible)) {
			coleccionAulas[tamano] = new Aula(aula);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");
		}
	}


	public Aula buscar(Aula aula) {
		
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}
		int indice = buscarIndice(aula);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Aula(coleccionAulas[indice]);
		}
	}


	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		
		for (int i = indice; i < tamano - 1; ++i) {
			coleccionAulas[i] = new Aula(coleccionAulas[i + 1]);
		}
	}


	public void borrar(Aula aula) throws OperationNotSupportedException {
		
		int indice = 0;
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}
		indice = buscarIndice(aula);
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			coleccionAulas[tamano - 1] = null;
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}
	}


	public String[] representar() {
		
		String[] representacion = new String[tamano];
		int indice = 0;
		for (int i = indice; i <= tamano - 1; ++i) {
			representacion[indice] = coleccionAulas[i].toString();
			indice++;
		}
		return representacion;
	}

}