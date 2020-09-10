import java.sql*;
class connection{
    public static void main(String[]args){
        try{
            Class.forName("com.Mysql.jdbc.Driver"){
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/3306/FikirtepeDB","root","");
                Statement stmt = conn.createStatement();
                )
            }
            conn.open();
            System.out.println("connection successfully");
            conn.close();
        }catch(Exception ex){
            System.out.println("connection failed");
        }
    }
}