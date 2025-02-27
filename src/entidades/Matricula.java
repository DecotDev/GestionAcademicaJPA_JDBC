package entidades;

import java.io.Serializable;
import java.util.Date;

public class Matricula implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long idmatricula;
	private int idAlumno;
	private int idCurso;
	private Date fechaInicio;
	
	public Matricula(long idmatricula, int idAlumno, int idCurso) {
		this.idmatricula = idmatricula;
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
		this.idmatricula = idmatricula;
		this.idAlumno = idAlumno;
		this.idCurso = idCurso;
		this.fechaInicio = fecha_inicio;
	}

	public long getIdmatricula() {
		return idmatricula;
	}

	public void setIdmatricula(long idmatricula) {
		this.idmatricula = idmatricula;
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
	
}
