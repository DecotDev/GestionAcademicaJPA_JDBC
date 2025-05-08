package dao;

import java.io.*;
import java.util.*;

public class DAOFactory {
  private static String mode;
  private static String jdbcUrl;

  static {
    try (InputStream in = DAOFactory.class.getResourceAsStream("/config.properties")) {
      Properties p = new Properties();
      p.load(in);
      mode = p.getProperty("persistence.mode", "JDBC");
      jdbcUrl = p.getProperty("jdbc.url");
    } catch (IOException e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  public static AcademiaDAO getAcademiaDAO() {
    if ("JPA".equalsIgnoreCase(mode)) {
      return new AcademiaDAOImplJPA();
    } else {
      return new AcademiaDAOImplJDBC(jdbcUrl);
    }
  }
}
