package DATA_visualization;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/**
 * 
 * @param China-COVID-19数据提取
 * 
 * @param ©️uiuing.top       2020
 *
 */
public class Extraction {
    // Store sql statement
    private static String sql;
    // Store China data
    public static String[] Chinadate;
    // v < MySQL 8.0
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/covid";
    // v > MySQL 8.0
    // private static final String JDBC_DRIVER =
    // "com.mysql.cj.jdbc.Driver";
    // private static final String DB_URL =
    // "jdbc:mysql://localhost:3306/covid?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    // NAME & PASSWORD
    private static final String USER = "covid";
    private static final String PASS = "nitR3aYP6N7bixY8";
    // CONNECTION & STATEMENT & ResultSet
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    // DATA China l:"yyyy/mm/dd" l^n:"xxxx"^n
    private static String[][] Data_China;
    // Tables Name
    private static String[] Table_name;
    // DATA TIME
    private static String[] Data_time;
    // RAM LIST
    private static List<String> RAM = new ArrayList<String>();
    // RAM String
    private static String RAM_string;
    // StringBuffer --> insert
    private static StringBuffer insert;

    // MAIN
    Extraction() {
        try {
            // URL
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // SQL
            stmt = conn.createStatement();
            // Table_management && Get Table
            Table_management();
            // Get TIME
            getTIME();
            // Get DATA
            getDATA();
            // SAVE  *./* --> visualization_transfer/*
            SaveData();
            // clean
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Table_management && Get Table
    private void Table_management() throws SQLException {
        try {
            RAM.clear();
            DatabaseMetaData dbMetaData = conn.getMetaData();
            ResultSet rs = dbMetaData.getTables(null, null, null,new String[] { "TABLE" });
            while (rs.next()) {
                RAM.add(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Table_name = RAM.toArray(new String[RAM.size()]);
        Data_China = new String[Table_name.length][7];
        Data_time = RAM.toArray(new String[RAM.size()]);
        try{
            if(Table_name.length>7){
                sql = "drop table if exists " + Table_name[0];
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get TIME
    private void getTIME(){
        for(int i=0; i<Data_time.length; i++){
            StringBuilder stbe = new StringBuilder(Data_time[i]);
            RAM_string = new String(stbe.replace(0, 9, ""));
            if(RAM_string.charAt(0) == '0') {
                RAM_string = new String(stbe.replace(0, 1, ""));
                if(RAM_string.charAt(1) == '0') RAM_string = new String(stbe.replace(1, 2, ""));
            }else if(RAM_string.charAt(2) == '0') RAM_string = new String(stbe.replace(2, 3, ""));
            char[] RAM_char=RAM_string.toCharArray();
            insert = new StringBuffer(RAM_string);
            if(RAM_char.length == 2){      
                RAM_string = new String(insert.insert(1,"月"));
                RAM_string = new String(insert.insert(3,"日"));
            }
            if(RAM_char.length == 4){      
                RAM_string = new String(insert.insert(2,"月"));
                RAM_string = new String(insert.insert(5,"日"));
            }
            if(RAM_char.length == 3){ 
                Calendar Cal = Calendar.getInstance();
                String pd = String.valueOf(RAM_string.charAt(0)) + String.valueOf(RAM_string.charAt(1));
                String RAM_strings = String.valueOf(RAM_string.charAt(2));
                if(pd.equals(String.valueOf(Cal.get(Calendar.MONTH) + 1)) && RAM_strings.equals(String.valueOf(Cal.get(Calendar.DATE)))){
                    RAM_string = new String(insert.insert(2,"月"));
                    RAM_string = new String(insert.insert(4,"日")); 
                }
                if(!pd.equals(String.valueOf(Cal.get(Calendar.MONTH) + 1)) && !RAM_strings.equals(String.valueOf(Cal.get(Calendar.DATE)))){
                    RAM_string = new String(insert.insert(1,"月"));
                    RAM_string = new String(insert.insert(4,"日"));
                }
            }
            Data_time[i] = RAM_string;
        }
    }
    
    // Get DATA
    private void getDATA() {
        for (int i = 0; i < Table_name.length; i++) {
            try{
                sql = "SELECT * FROM " + Table_name[i];
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    for(int j=1; j<8;j++)  Data_China[i][j-1] = rs.getString(j);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // SAVE  *./* --> visualization_transfer/*
    private void SaveData() {
        visualization_transfer.Data_time = Data_time;
        visualization_transfer.Data_China = Data_China;
    }

}
