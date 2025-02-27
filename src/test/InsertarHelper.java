package test;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import dao.AcademiaDAO;
import dao.AcademiaDAOImplJDBC;

import entidades.Alumno;
import entidades.Curso;
import entidades.Matricula;

public class InsertarHelper {

	private AcademiaDAO dao = null;

	// Constructor
	public InsertarHelper() {
		System.out.println("Creando el DAO...");
		dao = new AcademiaDAOImplJDBC();
	}

	/*
	 * Alumnos
	 */
	private void insertarAlumno(int id, String nombre) {
		System.out.println("\nCreando un alumno...");
		Alumno alumno = new Alumno(id, nombre);
		System.out.println("Grabando el nuevo alumno...");
		if (dao.grabarAlumno(alumno) == 1) {
			System.out.print("Se ha grabado el alumno");
		} else {
			System.out.print("Error al grabar el alumno");
		}
	}

	private void modificarAlumno(int id, String nombre) {
		// Recuperamos al alumno a partir de su id
		Alumno alumno = dao.getAlumno(id);
		System.out.println("\nModificando el nombre del alumno con id: " + id + " y nombre: " + alumno.getNombreAlumno());
		alumno.setNombreAlumno(nombre);
		if (dao.actualizarAlumno(alumno) == 1) {
			System.out.print("Se ha modificado el alumno con id: " + id);
		} else {
			System.out.print("Error al modificar el alumno con id: " + id);
		}
	}

	/*
	 * Cursos
	 */
	private void insertarCurso(int id, String nombre) {
		System.out.println("\nCreando un curso...");
		Curso curso = new Curso(id, nombre);
		System.out.println("Grabando el nuevo curso...");
		if (dao.grabarCurso(curso) == 1) {
			System.out.print("Se ha grabado el curso");
		} else {
			System.out.print("Error al grabar el curso");
		}
	}

	private void modificarCurso(int id, String nombre) {
		Curso curso = dao.getCurso(id);
		System.out.println("\nModificando el nombre del curso con id: " + id + " y nombre: " + curso.getNombreCurso());
		curso.setNombreCurso(nombre);
		if (dao.actualizarCurso(curso) == 1) {
			System.out.print("Se ha modificado el curso con id: " + id);
		} else {
			System.out.print("Error al modificar el curso con id: " + id);
		}
	}

	/*
	 * Matriculas
	 */
	private void insertarMatricula(int idAlumno, int idCurso) {
		System.out.println("\nCreando una matricula...");
		Matricula matricula = new Matricula(idAlumno, idCurso);
		System.out.println("Grabando la nueva matricula...");
		if (dao.grabarMatricula(matricula) == 1) {
			System.out.print("Se ha grabado la matricula");
		} else {
			System.out.print("Error al grabar la matricula");
		}
	}

	private void modificarMatricula(int idAlumno, int idCurso, java.util.Date fecha) {
		Matricula matricula = dao.getMatricula(idAlumno, idCurso);
		System.out.println("\nModificando el id de alumno i/o de curso de la matricula con id: " + matricula.getIdmatricula() + " y los anteriores, id_alumno: " + matricula.getIdAlumno() + " id_curso: " + matricula.getIdCurso());
		matricula.setIdAlumno(idAlumno);
		matricula.setIdCurso(idCurso);
		matricula.setFechaInicio(fecha);
		if (dao.actualizarMatricula(matricula) == 1) {
			System.out.print("Se ha modificado la matricula con id: " + matricula.getIdmatricula());
		} else {
			System.out.print("Error al modificar la matricula con id: " + matricula.getIdmatricula());
		}
	}

	private void showAllData() {
		showData(dao.cargarAlumnos(), "Alumnos");
		showData(dao.cargarCursos(), "Cursos");
		showData(dao.cargarMatriculas(), "Matriculas");
	}

	private void showData(Collection<?> coleccion, String entidad) {
		System.out.println("\nMostrando..." + entidad);
		for (Object obj : coleccion)
			System.out.println(obj);

	}

	public static void main(String[] args) {
		InsertarHelper programa = new InsertarHelper();
		/*
		 * Insertar alumnos
		 */
		programa.insertarAlumno(1000, "Daniel");
		programa.insertarAlumno(1001, "Francisco");
		// Cambiarle el nombre al primer alumno creado
		programa.modificarAlumno(1000, "Ezequiel");
		/*
		 * Insertar cursos
		 */
		programa.insertarCurso(500, "Java");
		programa.insertarCurso(501, ".NET");
		// Modificar el curso creado
		programa.modificarCurso(500, "Java avanzado");
		/*
		 * Insertar matriculas
		 */
		programa.insertarMatricula(1000, 500);
		programa.insertarMatricula(1000, 501);
		programa.insertarMatricula(1001, 500);
		/*
		 * Modificar fecha de la segunda matricula
		 */
		Calendar fecha = GregorianCalendar.getInstance();
		fecha.set(Calendar.MONTH, 11);
		programa.modificarMatricula(1001, 500, fecha.getTime());
		/*
		 * Mostrar lo que hemos grabado
		 *
		 * 
		 */
		programa.showAllData();
		System.out.println("\nfin del programa.");
	}
}