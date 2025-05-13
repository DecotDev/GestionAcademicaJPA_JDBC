package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import dao.AcademiaDAO;
import dao.AcademiaDAOImplJDBC;
import dao.DAOFactory;
import entidades.Alumno;
import entidades.Curso;
import entidades.Matricula;

public class InsertarHelper {

	private AcademiaDAO dao = null;

	// Constructor
	public InsertarHelper() {
		System.out.println("Creando el DAO...");
        //dao = new AcademiaDAOImplJDBC();
        dao = DAOFactory.getAcademiaDAO();
	}

	/*
	 * Alumnos
	 */
	private void insertarAlumno(int id, String nombre, String rutaFoto) {
		if (dao.getAlumno(id) != null) {
			System.out.println("\nEl alumno con id " + id + " ya existe, no se insertará.");
			return;
		}
		System.out.println("\nCreando un alumno...");
		Alumno alumno = new Alumno(id, nombre);
		
		// Leemos la foto del disco, la guardamos en el
		// objeto Alumno y posteriormente se graba en la BD
		File file = new File(rutaFoto);
		
		byte[] foto=null;
		try {
			foto = getBytesFromFile(file);
		} catch (IOException e) { e.printStackTrace(); }
		
		alumno.setFoto(foto);
		
		System.out.println("Grabando el nuevo alumno...");
		if (dao.grabarAlumno(alumno) == 1) {
			System.out.println("Se ha grabado el alumno");
		} else {
			System.out.println("Error al grabar el alumno");
		}
	}

	private void modificarAlumno(int id, String nombre, String rutaFoto) {
		// Recuperamos al alumno a partir de su id
		Alumno alumno = dao.getAlumno(id);
		System.out
				.println("\nModificando el nombre del alumno con id: " + id + " y nombre: " + alumno.getNombreAlumno());
		alumno.setNombreAlumno(nombre);
		
		// Si se ha pasado la ruta de la foto...
		if (rutaFoto!=null) {
			System.out.println("\nModificando la foto del alumno con id: "+id+" y nombre: "+alumno.getNombreAlumno());
			// Leemos la foto del disco, la guardamos en el
			// objeto Alumno y posteriormente se graba en la BD
			File file = new File(rutaFoto);
			
			byte[] foto=null;
			try {
				foto = getBytesFromFile(file);
			} catch (IOException e) { e.printStackTrace(); }
			alumno.setFoto(foto);
		}
		
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
		if (dao.getCurso(id) != null) {
			System.out.println("\nEl curso con id " + id + " ya existe, no se insertará.");
			return;
		}
		System.out.println("\nCreando un curso...");
		Curso curso = new Curso(id, nombre);
		System.out.println("Grabando el nuevo curso...");
		if (dao.grabarCurso(curso) == 1) {
			System.out.println("Se ha grabado el curso");
		} else {
			System.out.println("Error al grabar el curso");
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
		if (dao.getMatricula(idAlumno, idCurso) != null) {
			System.out.println(
					"\nLa matrícula del alumno " + idAlumno + " en el curso " + idCurso + " ya existe, no se insertará.");
			return;
		}
		System.out.println("\nCreando una matrícula...");
		Matricula matricula = new Matricula(idAlumno, idCurso);
		System.out.println("Grabando la nueva matrícula...");
		if (dao.grabarMatricula(matricula) == 1) {
			System.out.println("Se ha grabado la matrícula");
		} else {
			System.out.println("Error al grabar la matrícula");
		}
	}

	private void modificarMatricula(int idAlumno, int idCurso, java.util.Date fecha) {
		Matricula matricula = dao.getMatricula(idAlumno, idCurso);
		System.out.println("\nModificando el id de alumno i/o de curso de la matricula con id: "
				+ matricula.getIdmatricula() + " y los anteriores, id_alumno: " + matricula.getIdAlumno()
				+ " id_curso: " + matricula.getIdCurso());
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

	/*
	 * Devuelve el contenido del fichero (la foto)
	 * en un array de bytes
	 */
	
	private static byte[] getBytesFromFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);

	    // Obtener el tamaño del fichero
	    long length = file.length();

	    // No podemos crear un array usando un tipo long.
	    // Es necesario que sea un tipo int.
	    // Antes de convertirlo a int, comprobamos
	    // que el fichero no es mayor que Integer.MAX_VALUE
	    if (length > Integer.MAX_VALUE) {	        
	    	System.out.println("Fichero demasiado grande!");
	    	System.exit(1);
	    }

	    // Creamos el byte array que almacenará 
	    // temporalmente los datos leidos
	    byte[] bytes = new byte[(int)length];

	    // Leemos
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }

	    // Comprobacion de que todos los bytes se han leido
	    if (offset < bytes.length) {
	        throw new IOException("No se ha podido leer complemtamente el fichero "+file.getName());
	    }

	    // Cerrar el input stream y devolver los bytes
	    is.close();
	    return bytes;
	}
	
	
	public static void main(String[] args) {
		InsertarHelper programa = new InsertarHelper();
		/*
		 * Insertar alumnos
		 */
		programa.insertarAlumno(1000,"Daniel","imagenes/cara2.jpg");
		programa.insertarAlumno(1001, "Francisco","imagenes/cara4.jpg");
		// Cambiarle el nombre al primer alumno creado
		programa.modificarAlumno(1000, "Ezequiel", null);
		// Volverle a cambiar el nombre y ahora la foto
		programa.modificarAlumno(1000, "Agapito","imagenes/cara1.jpg");
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
		System.out.println("\nFin del programa.");
	}
}