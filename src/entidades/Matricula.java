package entidades;

import java.io.Serializable;
import java.util.Date;

public class Matricula implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long idMatricula;
	private int idAlumno;
	private int idCurso;
	private Date fechaInicio;
	
	public Matricula(long idmatricula, int idAlumno, int idCurso) {
		this.idMatricula = idmatricula;
		this.idAlumno = idAlumno;
		this.idCurso = idCurso;
		this.fechaInicio = new Date(System.currentTimeMillis());
	}
	
	public Matricula(int idAlumno, int idCurso) {
		this.idAlumno = idAlumno;
		this.idCurso = idCurso;
		this.fechaInicio = new Date(System.currentTimeMillis());
	}
	
	public Matricula(long idmatricula, int idAlumno, int idCurso, Date fecha_inicio) {
		this.idMatricula = idmatricula;
		this.idAlumno = idAlumno;
		this.idCurso = idCurso;
		this.fechaInicio = fecha_inicio;
	}

	public long getIdmatricula() {
		return idMatricula;
	}

	public void setIdmatricula(long idmatricula) {
		this.idMatricula = idmatricula;
	}

	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String toString() {
		return this.idMatricula + " - " + this.idAlumno + " - " + this.idCurso + " - " + this.fechaInicio;
	}
	
}
