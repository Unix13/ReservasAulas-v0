package org.iesalandalus.programacion.reservasaulas.mvc.vista;


import javax.naming.OperationNotSupportedException;

public enum Opcion {
	
	INSERTAR_AULA("Insertar aula") {
		
		public void ejecutar() throws OperationNotSupportedException {
			vista.insertarAula();
		}
	},
	BUSCAR_AULA("Buscar aula") {
		
		public void ejecutar() {
			vista.buscarAula();
		}
	},
	BORRAR_AULA("Borrar aula") {
		
		public void ejecutar() throws OperationNotSupportedException {
			vista.borrarAula();
		}
	},
	LISTAR_AULAS("Listar aulas") {
		
		public void ejecutar() {
			vista.listarAulas();
		}
	},
	INSERTAR_PROFESOR("Insertar profesor") {
		
		public void ejecutar() throws OperationNotSupportedException {
			vista.insertarProfesor();
		}
	},
	BUSCAR_PROFESOR("Buscar profesor") {
		
		public void ejecutar() {
			vista.buscarProfesor();
		}
	},
	BORRAR_PROFESOR("Borrar profesor") {
		
		public void ejecutar() throws OperationNotSupportedException {
			vista.borrarProfesor();
		}
	},
	LISTAR_PROFESORES("Listar profesores") {
		
		public void ejecutar() {
			vista.listarProfesores();
		}
	},
	INSERTAR_RESERVA("Inserta reserva") {
		
		public void ejecutar() throws OperationNotSupportedException {
			vista.realizarReserva();
		}
	},
	BORRAR_RESERVA("Borrar reserva") {
		
		public void ejecutar() throws OperationNotSupportedException {
			vista.anularReserva();
		}
	},
	LISTAR_RESERVAS("Listar reservas") {
		
		public void ejecutar() {
			vista.listarReservas();
		}
	},
	LISTAR_RESERVAS_AULA("Listar reservas aula") {
		
		public void ejecutar() {
			vista.listarReservasAula();
		}
	},
	LISTAR_RESERVAS_PROFESOR("Listar reservas profesor") {
		
		public void ejecutar() {
			vista.listarReservaProfesor();
		}
	},
	LISTAR_RESERVAS_PERMANENCIA("Listar reservas permanencia") {
		
		public void ejecutar() {
			vista.listarReservaPermanencia();
		}
	},
	
	CONSULTAR_DISPONIBILIDAD("Consultar disponibilidad") {
		
		public void ejecutar() {
			vista.consultarDisponibilidad();
		}
	},
	SALIR("Salir") {
		
		public void ejecutar() {
			vista.salir();
		}
	};
	
	private String mensajeAMostrar;
	private static Vista vista;
	
	private Opcion(String mensajeAMostrar) {
		this.mensajeAMostrar = mensajeAMostrar;
	}
	
	public abstract void ejecutar() throws OperationNotSupportedException;
	
	public static void setVista(Vista vista) {
		if (vista == null) {
			throw new NullPointerException("ERROR: La vista no pueda ser nula.");
		}
		Opcion.vista = vista;
	}
	
	public static Opcion getOpcionSegunOrdinal(int ordinal) {
		if (!esOrdinalValido(ordinal)) {
			throw new IllegalArgumentException("Ordinal de la opción no válido");
		}
		return values()[ordinal];
	}
	
	public static boolean esOrdinalValido(int ordinal) {
		return (ordinal >= 0 && ordinal <= values().length - 1);
	}
	
	public String getMensaje() {
		
		return mensajeAMostrar;
	}
		
	@Override
	public String toString() {
		return String.format("%d.- %s", ordinal(), mensajeAMostrar);
	}

}