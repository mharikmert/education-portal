import java.math.BigInteger;
import java.io.Serializable;
public class loginBeans implements Serializable{
    private BigInteger id;
    private String password;
    private String branch;

    public BigInteger getID(){
        return this.id;
    }
    public void setID(BigInteger id){
        this.id = id;
    }
    public String getPassword(){return this.password; }
    public void setPassword(String password){
        this.password = password;
    }
    public String getBranch(){
        return this.branch;
    }
    public void setBranch(String branch){
        this.branch = branch;
    }
    public static void main(String []args) {
    }
}
