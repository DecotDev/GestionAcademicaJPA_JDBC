package dao;

import entidades.*;
import jakarta.persistence.*;
import java.util.*;

public class AcademiaDAOImplJPA implements AcademiaDAO {
	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("AcademiaPersistanceUnit");

	private EntityManager em() {
		return emf.createEntityManager();
	}

	@Override
	public Collection<Alumno> cargarAlumnos() {
		EntityManager em = em();
		try {
			return em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();
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
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
			return -1;
		} finally {
			em.close();
		}
	}

	@Override
	public int actualizarAlumno(Alumno alumno) {
		EntityManager em = em();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(alumno); // merge = actualizar
			tx.commit();
			return 1;
		} catch (RuntimeException e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
			return -1;
		} finally {
			em.close();
		}
	}

	@Override
	public int borrarAlumno(int idAlumno) {
		EntityManager em = em();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Alumno alumno = em.find(Alumno.class, idAlumno);
			if (alumno != null) {
				em.remove(alumno);
				tx.commit();
				return 1;
			} else {
				tx.rollback();
				return 0;
			}
		} catch (RuntimeException e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
			return -1;
		} finally {
			em.close();
		}
	}

	@Override
	public Collection<Curso> cargarCursos() {
		EntityManager em = em();
		try {
			return em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public Curso getCurso(int idCurso) {
		EntityManager em = em();
		try {
			return em.find(Curso.class, idCurso);
		} finally {
			em.close();
		}
	}

	@Override
	public int grabarCurso(Curso curso) {
		EntityManager em = em();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(curso);
			tx.commit();
			return 1;
		} catch (RuntimeException e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
			return -1;
		} finally {
			em.close();
		}
	}

	@Override
	public int actualizarCurso(Curso curso) {
		EntityManager em = em();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(curso); // merge = actualizar
			tx.commit();
			return 1;
		} catch (RuntimeException e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
			return -1;
		} finally {
			em.close();
		}
	}

	@Override
	public int borrarCurso(int idCurso) {
		EntityManager em = em();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Curso curso = em.find(Curso.class, idCurso);
			if (curso != null) {
				em.remove(curso);
				tx.commit();
				return 1;
			} else {
				tx.rollback();
				return 0;
			}
		} catch (RuntimeException e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
			return -1;
		} finally {
			em.close();
		}
	}

	@Override
	public Collection<Matricula> cargarMatriculas() {
		EntityManager em = em();
		try {
			return em.createQuery("SELECT m FROM Matricula m", Matricula.class).getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public long getIdMatricula(int idAlumno, int idCurso) {
		EntityManager em = em();
		try {
			TypedQuery<Long> query = em.createQuery(
				"SELECT m.idMatricula FROM Matricula m WHERE m.idAlumno = :idAlumno AND m.idCurso = :idCurso",
				Long.class);
			query.setParameter("idAlumno", idAlumno);
			query.setParameter("idCurso", idCurso);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return 0;
		} finally {
			em.close();
		}
	}

	@Override
	public Matricula getMatricula(long idMatricula) {
		EntityManager em = em();
		try {
			return em.find(Matricula.class, idMatricula);
		} finally {
			em.close();
		}
	}

	@Override
	public Matricula getMatricula(int idAlumno, int idCurso) {
		EntityManager em = em();
		try {
			TypedQuery<Matricula> query = em.createQuery(
				"SELECT m FROM Matricula m WHERE m.idAlumno = :idAlumno AND m.idCurso = :idCurso",
				Matricula.class);
			query.setParameter("idAlumno", idAlumno);
			query.setParameter("idCurso", idCurso);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public int grabarMatricula(Matricula matricula) {
		EntityManager em = em();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(matricula);
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
	public int actualizarMatricula(Matricula matricula) {
		EntityManager em = em();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(matricula);
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
	public int borrarMatricula(long idMatricula) {
		EntityManager em = em();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Matricula matricula = em.find(Matricula.class, idMatricula);
			if (matricula != null) {
				em.remove(matricula);
				tx.commit();
				return 1;
			} else {
				tx.rollback();
				return 0;
			}
		} catch (RuntimeException e) {
			if (tx.isActive()) tx.rollback();
			e.printStackTrace();
			return -1;
		} finally {
			em.close();
		}
	}

	// implementar actualizarAlumno, borrarAlumno, y métodos para Curso y Matricula
}
