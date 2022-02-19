package org.iesalandalus.programacion.reservasaulas.mvc.controlador;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.Vista;


public class Controlador {

	private Vista vista;
	private Modelo modelo;

	public Controlador(Modelo modelo, Vista vista) {
		if (modelo == null) {
			throw new IllegalArgumentException("ERROR: El modelo no puede ser nulo.");
		}
		if (vista == null) {
			throw new IllegalArgumentException("ERROR: La vista no puede ser nula.");
		}
		this.modelo = modelo;
		this.vista = vista;
		this.vista.setControlador(this);
	}

	public void comenzar() {
		vista.comenzar();
	}

	public void terminar() {
		System.out.println("Adiós");
	}
	
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		modelo.insertarProfesor(profesor);
	}

	public void insertar(Aula aula) throws OperationNotSupportedException {
		modelo.insertarAula(aula);
	}
		

	public Profesor buscar(Profesor profesor) {
		return modelo.buscarProfesor(profesor);
	}

	public Aula buscar(Aula aula) {
		return modelo.buscarAula(aula);
	}

	

	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		modelo.borrarProfesor(profesor);
	}

	public void borrar(Aula aula) throws OperationNotSupportedException {
		modelo.borrarAula(aula);
	}

public String[] representarAulas() {
		
		return modelo.representarAulas();

	}
	
	public String[] representarProfesores() {
		
		return modelo.representarProfesores();
		
	}
	
	public String[] representarReservas() {
		
		return modelo.respresentarReservas();
		
	}
	
	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {
		
		modelo.realizarReserva(reserva);
		
	}
	
	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {
		
		modelo.anularReserva(reserva);
		
	}
	
	public Reserva[] getReservasAula(Aula aula) {
		
		return modelo.getReservasAula(aula);
		
	}
	
	public Reserva[] getReservasProfesor(Profesor profesor) {
		
		return modelo.getReservasProfesor(profesor);
	}
	
	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		
		return modelo.getReservasPermanencia(permanencia);
	}
	
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		
		return modelo.consulrarDisponibilidad(aula, permanencia);
		
	}
}

