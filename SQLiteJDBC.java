import java.sql.*;

public class SQLiteJDBC
{
  public static void main( String args[] )
  {
    Connection c = null;
    Statement sql_statement = null;
    try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:test.db");
        System.out.println("database opened successfully");

        sql_statement = c.createStatement();
        try{
        String sql = "CREATE TABLE EMPLOYEES " +
                     "(ID INT PRIMARY KEY     NOT NULL," +
                     " NAME           TEXT    NOT NULL, " + 
                     " AGE            INT     NOT NULL, " + 
                     " ADDRESS        CHAR(50), " + 
                     " SALARY         REAL)"; 
        sql_statement.executeUpdate(sql);

          System.out.println("Table is created successfully");

      }


      catch(Exception except){
          System.out.println("Table already exists");
      }

      try{

          String sql = "INSERT INTO EMPLOYEES (ID,NAME,AGE,ADDRESS,SALARY) " +
                       "VALUES (1, 'Abbas Ali', 20, 'California', 20000.00 );"; 
          sql_statement.executeUpdate(sql);
          sql =         "INSERT INTO EMPLOYEES (ID,NAME,AGE,ADDRESS,SALARY) " +
                        "VALUES (2, 'Muneer Ahmed', 20, 'California', 20000.00 );"; 
          sql_statement.executeUpdate(sql);
          System.out.println("Row has beeen inserted in table");
      }

      catch(Exception except){  
          System.out.println("This Row is already present in table");
      }

      ResultSet rs = sql_statement.executeQuery( "SELECT * FROM EMPLOYEES;" );
      while ( rs.next() ) {
           int id = rs.getInt("id");
           String  name = rs.getString("name");
           int age  = rs.getInt("age");
           String  address = rs.getString("address");
           float salary = rs.getFloat("salary");
           System.out.println( "ID = " + id );
           System.out.println( "NAME = " + name );
           System.out.println( "AGE = " + age );
           System.out.println( "ADDRESS = " + address );
           System.out.println( "SALARY = " + salary );
           System.out.println();
        }
        sql_statement.close();
        c.close();

    } 

    catch ( Exception except ) 
    {
      System.err.println( except.getClass().getName() + ": " + except.getMessage() );
      System.exit(0);
    }
  
  }
}