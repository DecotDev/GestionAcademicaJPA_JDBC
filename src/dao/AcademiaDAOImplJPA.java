package dao;

import entidades.*;
import jakarta.persistence.*;
import java.util.*;

public class AcademiaDAOImplJPA implements AcademiaDAO {
  private final EntityManagerFactory emf =
      Persistence.createEntityManagerFactory("AcademiaPU");

  private EntityManager em() {
    return emf.createEntityManager();
  }

  @Override
  public Collection<Alumno> cargarAlumnos() {
    EntityManager em = em();
    try {
      return em.createQuery("SELECT a FROM Alumno a", Alumno.class)
               .getResultList();
    } finally {
      em.close();
    }
  }

  @Override
  public Alumno getAlumno(int id) {
    EntityManager em = em();
    try {
      return em.find(Alumno.class, id);
    } finally {
      em.close();
    }
  }

  @Override
  public int grabarAlumno(Alumno alumno) {
    EntityManager em = em();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.persist(alumno);
      tx.commit();
      return 1;
    } catch (RuntimeException e) {
      if (tx.isActive()) tx.rollback();
      e.printStackTrace();
      return -1;
    } finally {
      em.close();
    }
  }

@Override
public int actualizarAlumno(Alumno alumno) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int borrarAlumno(int idAlumno) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public Collection<Curso> cargarCursos() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Curso getCurso(int idCurso) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public int grabarCurso(Curso curso) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int actualizarCurso(Curso curso) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int borrarCurso(int idCurso) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public Collection<Matricula> cargarMatriculas() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public long getIdMatricula(int idAlumno, int idCurso) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public Matricula getMatricula(long idMatricula) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Matricula getMatricula(int idAlumno, int idCurso) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public int grabarMatricula(Matricula matricula) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int actualizarMatricula(Matricula matricula) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int borrarMatricula(long idMatricula) {
	// TODO Auto-generated method stub
	return 0;
}

  // implementar actualizarAlumno, borrarAlumno, y métodos para Curso y Matricula
}
