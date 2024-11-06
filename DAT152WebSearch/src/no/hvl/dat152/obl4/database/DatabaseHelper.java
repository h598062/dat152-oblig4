package no.hvl.dat152.obl4.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseHelper {
  public static Connection getConnection() throws SQLException, NamingException {
    Context ctx = new InitialContext();
    DataSource ds = (DataSource) ctx.lookup(DatabaseSettings.source);
    return ds.getConnection();
  }

  public static void closeConnection(ResultSet r, Statement s, Connection c) {
    try {
      if (r!= null) r.close();
      if (s!= null) s.close();
      if (c!= null) c.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
