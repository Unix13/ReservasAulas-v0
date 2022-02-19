package org.iesalandalus.programacion.reservasaulas.mvc.vista;


	import javax.naming.OperationNotSupportedException;
	import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
	import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
	import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
	import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
	import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;


	public class Vista {
		
		private Controlador controlador;
		
		public Vista() {
			
		}
		
		public void setControlador(Controlador controlador) {
			this.controlador = controlador;
		}
		
		public void comenzar()throws OperationNotSupportedException {
			Consola.mostrarCabecera("Gestión de las Reservas de Aulas del IES Al-Ándalus");
			int ordinalOpcion;
			do {
				Consola.mostrarMenu();
				ordinalOpcion = Consola.elegirOpcion();
				Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
				opcion.ejecutar();
			} while (ordinalOpcion != Opcion.SALIR.ordinal());
		}
		
		public void salir() {

			System.out.println("Se ha terminado el programa.");
			controlador.terminar();
		}
		
		public void insertarProfesor() {
			Consola.mostrarCabecera("Insertar Profesor");
			try {
				controlador.insertar(Consola.leerProfesor());
				System.out.println("Profesor insertado correctamente.");
			} catch (OperationNotSupportedException | IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		
		public void buscarProfesor() {
			Consola.mostrarCabecera("Buscar Profesor");
			Profesor profesor;
			try {
				profesor = controlador.buscar(Consola.leerProfesor());
				String mensaje = (profesor != null) ? profesor.toString() : "No existe dicho profesor.";
				System.out.println(mensaje);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		
		public void borrarProfesor() {
			Consola.mostrarCabecera("Borrar Profesor");
			try {
				controlador.borrar(Consola.leerProfesor());
				System.out.println("Profesor borrado satisfactoriamente.");
			}  catch (OperationNotSupportedException | IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		
		public void listarProfesores() {
			Consola.mostrarCabecera("Listado de Profesores");
			String[] representar = controlador.representarProfesores();

			for (String cadena : representar) {
				System.out.println(cadena);
			}
		}
			
		
		public void insertarAula() {
			Consola.mostrarCabecera("Insertar Aula");
			try {
				controlador.insertar(Consola.leerAula());
				System.out.println("Aula insertada correctamente.");
			} catch (OperationNotSupportedException | IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		
		public void buscarAula() {
			Consola.mostrarCabecera("Buscar Aula");
			Aula aula;
			try {
				aula = controlador.buscar(Consola.leerAula());
				String mensaje = (aula != null) ? aula.toString() : "No existe dicha aula.";
				System.out.println(mensaje);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		
		public void borrarAula() {
			Consola.mostrarCabecera("Borrar Aula");
			try {
				controlador.borrar(Consola.leerAula());
				System.out.println("Aula borrada satisfactoriamente.");
			}  catch (OperationNotSupportedException | IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		
		public void listarAulas() {
			
			Consola.mostrarCabecera("Listado de Aulas");
			String[] representar = controlador.representarAulas();

			for (String cadena : representar) {
				System.out.println(cadena);
			}

		}
		
		public void realizarReserva() throws OperationNotSupportedException {

			controlador.realizarReserva(leerReserva(Consola.leerProfesor()));
		}

		private Reserva leerReserva(Profesor profesor) {
			Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
			Reserva reserva = new Reserva(profesor, Consola.leerAula(), permanencia);
			return reserva;

		}

		public void anularReserva() throws OperationNotSupportedException {

			controlador.anularReserva(leerReserva(Consola.leerProfesor()));
		}

		public void listarReservas() {

			String[] representar = controlador.representarReservas();

			for (String cadena: representar) {
				System.out.println(cadena);
			}
		}

		public void listarReservasAula() {

			Reserva[] arrayReservas = controlador.getReservasAula(Consola.leerAula());

			for (Reserva reserva: arrayReservas) {
				System.out.println(reserva.toString());
			}

		}

		public void listarReservaProfesor() {
			
			Reserva[] arrayReservas = controlador.getReservasProfesor(Consola.leerProfesor());

			for (Reserva reserva: arrayReservas) {
				System.out.println(reserva.toString());
			}

		}

		public void listarReservaPermanencia() {

			Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
			
			Reserva[] arrayReservas = 	controlador.getReservasPermanencia(permanencia);
			
			for (Reserva reserva: arrayReservas) {
				System.out.println(reserva.toString());
			}

		}

		public void consultarDisponibilidad() {

			Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
			
			if(controlador.consultarDisponibilidad(Consola.leerAula(), permanencia)) {
				System.out.println("El aula está disponible.");
			} else {
				System.out.println("El aula ya está reservada.");
			}
			
		}

  }
