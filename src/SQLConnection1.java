import java.sql.*;
public class SQLConnection1 {
  Connection c = null; Statement stmt; String SQL;
  public Connection getConnection() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      String url ="jdbc:mysql://localhost/JAVA";
      c =DriverManager.getConnection(url,"root", "1234");
    }
    catch (Exception e) {
      System.out.println(e);
    }
    return c;
  }
  public boolean CreateDB(String fn) {
    SQL = "create database  "   + fn;
    try {
     stmt = c.createStatement();
     stmt.executeUpdate(SQL);
    }
    catch (Exception e) {
      return false;
    }
    return true;
  }
}