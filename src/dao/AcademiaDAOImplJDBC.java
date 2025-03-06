package dao;

import java.lang.reflect.Executable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import entidades.Alumno;
import entidades.Curso;
import entidades.Matricula;

public class AcademiaDAOImplJDBC implements AcademiaDAO {
	// Cadena de conexión predeterminada
	private String URLConexion = new String("jdbc:mysql://127.0.0.1:3306/dbformacion?user=dam2&password=secreto");
	/*
	 * SQL
	 */
	private static final String FIND_ALL_ALUMNOS_SQL = "select id_alumno, nombre_alumno from alumnos";
	private static final String FIND_ALL_CURSOS_SQL = "select id_curso, nombre_curso from cursos";
	private static final String FIND_ALL_MATRICULAS_SQL = "select id_matricula, id_alumno, id_curso, fecha_inicio from matriculas";


	/*
	 * CONSTRUCTORES
	 */
	public AcademiaDAOImplJDBC() {
	}

	// Sobrecargamos el método por si queremos
	// machacar la cadena de conexión
	public AcademiaDAOImplJDBC(String URLConexion) {
		this.URLConexion = URLConexion;
	}

	/*
	 * OPERACIONES GENERALES
	 */
	// Obtener la conexión
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URLConexion);
	}

	// Liberar la conexión
	private void releaseConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				for (Throwable t : e) {
					System.err.println("Error: " + t);
				}
			}
		}
	}

	/*
	 * OPERACIONES ALUMNO
	 */
	@Override
	public Collection<Alumno> cargarAlumnos() {
		Collection<Alumno> alumnos = new ArrayList<Alumno>();
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_ALL_ALUMNOS_SQL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String nombre = (rs.getString(2) != null ? rs.getString(2) : "sin nombre");
				alumnos.add(new Alumno(id, nombre));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			for (Throwable t : e) {
				System.err.println("Error: " + t);
			}
		} finally {
			releaseConnection(con);
		}
		return alumnos;
	}

	@Override
	public Alumno getAlumno(int idAlumno) {
		try {
			Connection con = getConnection();
			String query = "SELECT id_alumno, nombre_alumno FROM alumnos WHERE id_alumno= ?;";
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setInt(1, idAlumno);
			ResultSet res = pStmt.executeQuery();
	
			if (res.next()) {
			    return new Alumno(res.getInt("id_alumno"), res.getString("nombre_alumno"));
			} else {
			    return null;  // Handle case where student is not found
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
		/*try {
			Connection con = getConnection();
			String query = "Select nombre_alumno FROM alumnos WHERE id_alumno = " + idAlumno + ";";
			Statement a = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet res = a.executeQuery(query);
			return new Alumno(res.getInt("id_alumno"), res.getString("nombre_alumno"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}*/

	@Override
	public int grabarAlumno(Alumno alumno) {
		try {
			Connection con = getConnection();
			String query = "INSERT INTO alumnos VALUES(?,?);";
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setInt(1, alumno.getIdAlumno());
			pStmt.setString(2, alumno.getNombreAlumno());
			return pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int actualizarAlumno(Alumno alumno) {
		try {
			Connection con = getConnection();
			String query = "UPDATE alumnos SET nombre_alumno = ? WHERE id_alumno = ?;";
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setString(1, alumno.getNombreAlumno());
			pStmt.setInt(2, alumno.getIdAlumno());
			return pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int borrarAlumno(int idAlumno) {
		try {
			Connection con = getConnection();
			String query = "DELETE FROM alumnos WHERE id_alumno = ?;";
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setInt(1, idAlumno);
			return pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Collection<Curso> cargarCursos() {
		Collection<Curso> cursos = new ArrayList<Curso>();
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_ALL_CURSOS_SQL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String nombre = (rs.getString(2) != null ? rs.getString(2) : "sin nombre");
				cursos.add(new Curso(id, nombre));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			for (Throwable t : e) {
				System.err.println("Error: " + t);
			}
		} finally {
			releaseConnection(con);
		}
		return cursos;
	}

	@Override
	public Curso getCurso(int idCurso) {
		try {
			Connection con = getConnection();
			String query = "SELECT id_curso, nombre_curso FROM cursos WHERE id_curso = ?;";
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setInt(1, idCurso);
			ResultSet res = pStmt.executeQuery();
			
			if (res.next()) {
				return new Curso(res.getInt("id_curso"), res.getString("nombre_curso"));
			} else {
			    return null; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	@Override
	public int grabarCurso(Curso curso) {
		try {
			Connection con = getConnection();
			String query = "INSERT INTO cursos VALUES(?,?);";
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setInt(1, curso.getIdCurso());
			pStmt.setString(2, curso.getNombreCurso());
			return pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int actualizarCurso(Curso curso) {
		try {
			Connection con = getConnection();
			String query = "UPDATE cursos SET nombre_curso = ? WHERE id_curso = ?;";
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setString(1, curso.getNombreCurso());
			pStmt.setInt(2, curso.getIdCurso());
			return pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int borrarCurso(int idCurso) {
		try {
			Connection con = getConnection();
			String query = "DELETE FROM cursos WHERE id_curso = ?;";
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setInt(1, idCurso);
			return pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Collection<Matricula> cargarMatriculas() {
		Collection<Matricula> matriculas = new ArrayList<Matricula>();
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_ALL_MATRICULAS_SQL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				long id = rs.getLong(1);
				int idAlumno = rs.getInt(2);
				int idCurso = rs.getInt(3);
				Date fechaInicio = rs.getDate(4);
				matriculas.add(new Matricula(id, idAlumno, idCurso, fechaInicio));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			for (Throwable t : e) {
				System.err.println("Error: " + t);
			}
		} finally {
			releaseConnection(con);
		}
		return matriculas;
	}

	@Override
	public long getIdMatricula(int idAlumno, int idCurso) {
		try {
			Connection con = getConnection();
			String query = "Select nombre_alumno FROM alumnos WHERE id_alumno = " + idAlumno + " AND id_curso = " + idCurso + ";";
			Statement a = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet res = a.executeQuery(query);
			long idMatricula = res.getLong(1);
			return idMatricula;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Matricula getMatricula(long idMatricula) {
		try {
			Connection con = getConnection();
			String query = "Select * FROM matriculas WHERE id_matricula = ?;";
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setLong(1, idMatricula);
			ResultSet res = pStmt.executeQuery();
			
			if (res.next()) {
				return new Matricula(res.getLong("id_matricula"),res.getInt("id_alumno"), res.getInt("id_curso"), res.getDate("fecha_inicio"));
			} else {
			    return null; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public Matricula getMatricula(int idAlumno, int idCurso) {
		try {
			Connection con = getConnection();
			String query = "Select * FROM matriculas WHERE id_alumno = ? AND id_curso = ?;";
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setInt(1, idAlumno);
			pStmt.setInt(2, idCurso);
			ResultSet res = pStmt.executeQuery();
			if (res.next()) {
				return new Matricula(res.getLong("id_matricula"),res.getInt("id_alumno"), res.getInt("id_curso"), res.getDate("fecha_inicio"));
			} else {
			    return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int grabarMatricula(Matricula matricula) {
		try {
			Connection con = getConnection();
			String query = "INSERT INTO matriculas (id_alumno, id_curso, fecha_inicio) VALUES(?,?,?);";
			PreparedStatement pStmt = con.prepareStatement(query);
			//pStmt.setLong(1, matricula.getIdmatricula());
			pStmt.setInt(1, matricula.getIdAlumno());
			pStmt.setInt(2, matricula.getIdCurso());
			pStmt.setDate(3, new java.sql.Date(matricula.getFechaInicio().getTime()));
			return pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int actualizarMatricula(Matricula matricula) {
		try {
			Connection con = getConnection();
			String query = "UPDATE matriculas SET id_alumno = ?, id_curso = ? WHERE id_matricula = ?;";
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setInt(1, matricula.getIdAlumno());
			pStmt.setInt(2, matricula.getIdCurso());
			pStmt.setLong(3, matricula.getIdmatricula());
			return pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int borrarMatricula(long idMatricula) {
		try {
			Connection con = getConnection();
			String query = "DELETE FROM matriculas WHERE id_matricula = ?;";
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setLong(1, idMatricula);
			return pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
}