package test;

import dao.AcademiaDAO;
import dao.DAOFactory;
import dao.AcademiaDAOImplJDBC;
import entidades.Alumno;
import entidades.Curso;
import entidades.Matricula;
import java.util.Collection;

public class BorrarHelper {

    private AcademiaDAO dao;

    public BorrarHelper() {
        System.out.println("Creando el DAO...");
        //dao = new AcademiaDAOImplJDBC();
        dao = DAOFactory.getAcademiaDAO();
    }

    private void borrarMatriculas() {
        System.out.println("Borrando cualquier matricula previa...");
        Collection<Matricula> matriculas = dao.cargarMatriculas();
        for (Matricula m : matriculas) {
            if (dao.borrarMatricula(m.getIdmatricula()) == 1) {
                System.out.println("Se ha borrado la matricula");
            }
        }
    }

    private void borrarAlumnos() {
        System.out.println("Borrando cualquier alumno previo...");
        Collection<Alumno> alumnos = dao.cargarAlumnos();
        for (Alumno a : alumnos) {
            if (dao.borrarAlumno(a.getIdAlumno()) == 1) {
                System.out.println("Se ha borrado el alumno");
            }
        }
    }

    private void borrarCursos() {
        System.out.println("Borrando cualquier curso previo...");
        Collection<Curso> cursos = dao.cargarCursos();
        for (Curso c : cursos) {
            if (dao.borrarCurso(c.getIdCurso()) == 1) {
                System.out.println("Se ha borrado el curso");
            }
        }
    }

    public static void main(String[] args) {
        BorrarHelper programa = new BorrarHelper();
        programa.borrarMatriculas();
        programa.borrarAlumnos();
        programa.borrarCursos();
        System.out.println("\nFin del programa.");
    }
}
