package Data_mining;
import java.sql.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 *  @param China-COVID-19数据存储
 * 
 *  @param ©️uiuing.top            2020
 * 
 */
public class Submission {
    
    //Store sql statement
    private static String sql;
    //Store China data
    public static String[]Chinadate;
    // v < MySQL 8.0
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/databases";
    // v > MySQL 8.0
    //private static final String JDBC_DRIVER = 
    //    "com.mysql.cj.jdbc.Driver";
    //private static final String DB_URL = 
    //    "jdbc:mysql://localhost:3306/databases?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    // NAME & PASSWORD
    private static final String USER = "username";
    private static final String PASS = "Password"; 
    // CONNECTION & STATEMENT
    private static Connection conn = null;
    private static Statement stmt = null;
    // DATE & TABALE NAME
    private static String DATE;
    
    //MAIN
    Submission() {
        try {
            // Get TIME 
            gettime();
            // URL
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //SQL
            stmt = conn.createStatement();
            // Table_management
            Table_management();
            //Storing data
            Insert_table();
            //clean
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get TIME
    private void gettime(){
        Date day = new Date();    
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
        List<String> RAM = new ArrayList<String>();
        Matcher matcher = Pattern.compile("[0-9]").matcher(df.format(day));
        while (matcher.find()) RAM.add(matcher.group(0));
        DATE = String.join("",RAM);
    }

    // Table_management
    private void Table_management() throws SQLException {
        sql = " drop table if exists China" + DATE;
        stmt.executeUpdate(sql);
        sql =" CREATE TABLE IF NOT EXISTS China" + DATE +
             " (confirm INT(10), " +
             " existing INT(10)," + 
             " suspect INT(10), " + 
             " heal INT(10), " + 
             " dead INT(10), " + 
             " input INT(10), " +
             " noca INT(10), " +
             " newnoca INT(10))" +
             " ENGINE=InnoDB DEFAULT CHARSET=utf8 ";
        stmt.executeUpdate(sql);
    }

    //Storing data
    private void Insert_table() throws SQLException{
        try {
            sql = "";
            // NEW SQL
            for(int i=1;i<Chinadate.length;i++){
                sql += Chinadate[i];
                if(Chinadate.length-1!=i) sql += ",";
            }
            sql = 
                    "insert into China"+DATE
                    +" (confirm,existing,suspect,"
                    +"heal,dead,input,noca,newnoca) values("
                    +sql
                    +")";
            stmt.executeUpdate(sql);
        }catch(SQLException se){
           se.printStackTrace();
        }catch (Exception es){
            es.printStackTrace();
        }
    }
}