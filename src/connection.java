import java.sql.*;

class connection{
    private Connection conn = null;
    private static final String JDBC_Driver = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/FikirtepeDB";
    private static final String username = "root";
    private static final String password = "";
    private Connection connect(){
        try{
            Class.forName(JDBC_Driver);
            System.out.println("connection successful");
            conn = DriverManager.getConnection(DB_URL, username, password);
        }catch(Exception ex){
            ex.printStackTrace();
         }
        return conn;
    }
    private void printTable(){
        if(conn == null){
            System.out.println("i'm connecting..");
            connect();
        }
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from kayit_log;");
            System.out.println("tckn\t\tpassword\tbranch");
            while(rs.next()){
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private void user_ids(){
        ResultSet rs = null;
        if(conn == null){
            System.out.println("i'm connecting..");
            connect();
        }
        try{
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from kayit_log");
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private void printInfo(int columnIndex){
        if(conn == null){
            System.out.println("i'm connecting again.. ");
            connect();
        }
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from kayit_log");
            switch(columnIndex){
                case 1:
                    System.out.println("user ids  : "); break;
                case 2:
                    System.out.println("user passwords : "); break; // just print one column
                case 3 :
                    System.out.println("user branch : "); break;
            }
            while(rs.next()){
                System.out.println(rs.getString(columnIndex));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public static void main(String [] ærgs) throws SQLException {
        connection db = new connection();
        db.connect();
        //1 for id's 2 for passwords and 3 for branch
        db.printInfo(2);
        db.printTable();
    }
}