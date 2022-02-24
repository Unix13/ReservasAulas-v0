package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Permanencia {

	private Tramo tramo;
	private LocalDate dia;
	private final static DateTimeFormatter FORMATO_DIA=DateTimeFormatter.ofPattern("dd/MM/yyyy");
	

	public LocalDate getDia() {
		return dia;
	}
	public Tramo getTramo() {
		return tramo;
	}
	
	private void setDia(LocalDate dia) {
		if (dia==null) {
			throw new NullPointerException("ERROR: El día de una permanencia no puede ser nulo.");
		}
		this.dia = dia;
	}
	private void setTramo(Tramo tramo) {
		if (tramo==null) {
			throw new NullPointerException("ERROR: El tramo de una permanencia no puede ser nulo.");
		}
		this.tramo = tramo;
	}
	
//	Constructor con parámetros
	public Permanencia (LocalDate dia, Tramo tramo) {
		setDia(dia);
		setTramo(tramo);
	}
	
//	Constructor copia
	public Permanencia (Permanencia p) {
		if (p==null) {
			throw new NullPointerException("ERROR: No se puede copiar una permanencia nula.");
		}
		setDia(p.getDia());
		setTramo(p.getTramo());
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(dia, tramo);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permanencia other = (Permanencia) obj;
		return Objects.equals(dia, other.dia) && tramo == other.tramo;
	}
	


	@Override
	public String toString() {
		
		return "dia=" + dia.format(FORMATO_DIA) + ", tramo="+tramo;
	}
}