package dao;

import java.io.*;
import java.util.*;

public class DAOFactory {
  private static String mode;
  private static String jdbcUrl;

  static {
    try (InputStream in = DAOFactory.class.getResourceAsStream("/config.properties")) {
      Properties properties = new Properties();
      properties.load(in);
      mode = properties.getProperty("persistence.mode", "JDBC");
      jdbcUrl = properties.getProperty("jdbc.url");
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
