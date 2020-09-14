import java.sql.Connection;
import java.sql.DriverManager;
class db{
    private static final String JDBC_Driver = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/fikirtepedb";
    private static final String username = "root";
    private static final String password = "";
    private void connect(){
        try{
            Class.forName(JDBC_Driver);
            System.out.println("connection successful");
            Connection conn = DriverManager.getConnection(DB_URL, username, password);
        }catch(Exception ex){
            ex.printStackTrace();
         }
    }
    public static void main(String [] ærgs){
        db db = new db();
        db.connect();
    }
}