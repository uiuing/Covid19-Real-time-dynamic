package Data_mining;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 *  @param China-COVID-19数据清洗
 * 
 *  @param ©️uiuing.top   2020
 *
 */

public class Cleaning {
    // Today ALL
    private String[][] Data_China_today_ALL;
    // Today China
    private String[] Data_China_today;
    // Today ALL NEW
    private String[][] Data_China_New;
    // JSON DATA
    public static String JSON_Data;
    // new Matcher
    Matcher matcher;
    // Log storage location
    private String filepath;
    // Encoding format
    private String charset = "utf-8";
    // DATE
    private final String time = 
                                String.valueOf(Calendar.getInstance().get(Calendar.DATE))+"-"
                                + String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))+"-"
                                + String.valueOf(Calendar.getInstance().get(Calendar.MINUTE))+"-"
                                + String.valueOf(Calendar.getInstance().get(Calendar.SECOND));
    private int ii = 0;
    
    // MAIN
    Cleaning() {
        // Data Analysis for Today
        Today();
        // Data Analysis for Today_NEW
        Today_New();
        // SAVE Data_China_ALL & Data_China_New & Data_China_today_ALL   *./* --> mining_transfer/*
        SaveData();
    }

    // Data Analysis for Data_China_today_ALL
    private void Today(){
    // Data_China_today_ALL
        // SAVE Calculation Rules
        String[] Calculation_Rules_ONES = {  
                                            "totalconfirm+[0-9]+suspect+[0-9]+heal+[0-9]+dead+[0-9]+"+
                                            "severe+[0-9]+input+[\\S]+extDataname+[\u4e00-\u9fa5]+id+[0-9]+0000+",
                                            "total+[\\s\\S]+540000+"
                                          }; 
        String RAM_String = String.join("",Analysis(Calculation_Rules_ONES,JSON_Data)); 
        String[][] Calculation_Rules = {  
                                            {
                                                "[\u4e00-\u9fa5]+"
                                            },
                                            {
                                                "confirm+[0-9]+",
                                                "[0-9]+"
                                            },
                                            {
                                                "heal+[0-9]+",
                                                "[0-9]+"
                                            },
                                            {
                                                "dead+[0-9]+",
                                                "[0-9]+"
                                            }
                                        };
        String[] name = Analysis(Calculation_Rules[0],RAM_String);
        String[] confirm = Analysis(Calculation_Rules[1],RAM_String);
        String[] heal = Analysis(Calculation_Rules[2],RAM_String);
        String[] dead = Analysis(Calculation_Rules[3],RAM_String);
        String[][] copy = new String[name.length][2];
        Data_China_today_ALL = copy;
        for(int i=0; i<name.length; i++){
            Data_China_today_ALL[i][0] = name[i];
            Data_China_today_ALL[i][1] = String.valueOf
                                        (
                                            Integer.valueOf(confirm[i])
                                            -Integer.valueOf(heal[i])
                                            -Integer.valueOf(dead[i])
                                         );
        }
    // Today China
        String[] Calculation_Rules_TOWS = {
                                            "talconfirm+[0-9]+suspect+[0-9]+heal+[0-9]+dead+[0-9]+severe+[0-9]"+
                                            "+input+[\\S]+extDatanoSymptom+[0-9]+incrNoSymptom+[0-9]+name中国"
                                          };
        RAM_String = String.join("",Analysis(Calculation_Rules_TOWS,JSON_Data));
        String[][] Calculation_Rules_T = {  
                                            {
                                                "[\u4e00-\u9fa5]+"
                                            },
                                            {
                                                  "[0-9]+"
                                            },
                                       };
        String[] name_T = Analysis(Calculation_Rules_T[0],RAM_String);
        String[] data = Analysis(Calculation_Rules_T[1],RAM_String);
        String[] Copy = new String[name_T.length+data.length];
        Data_China_today = Copy;
        Data_China_today[0] = name_T[0];
        for(int i = 1; i < Data_China_today.length; i++){
            //  data: ['累计确诊','现有确诊', '疑似', '治愈', '死亡', '境外输入', '无症状','新增无症状']
            if(i==1) Data_China_today[i] = data[i-1];
            if(i==2)
                Data_China_today[i] = String.valueOf
                                    (
                                        Integer.valueOf(data[0])
                                        -Integer.valueOf(data[2])
                                        -Integer.valueOf(data[3])
                                    ); 
            if(i>2&&i<6) Data_China_today[i] = data[i-2];
            if(i>=6) Data_China_today[i] = data[i-1];
        }
    }

    // NEW
    private void Today_New(){
        String[] Calculation_Rules = {
                                        "塞尔维亚+[\\s\\S]+拉萨+",
                                        "todayconfirm+[0-9a-z]+suspect+[0-9a-z]+heal+[0-9a-z]+"+
                                        "dead+[0-9a-z]+severe+[0-9a-z]+storeConfirm+[0-9a-z-]+"+
                                        "totalconfirm+[0-9a-z]+suspect+[0-9a-z]+heal+[0-9a-z]+dead+"+
                                        "[0-9a-z]+severe+[0-9a-z]+input+[\\S]+extDataname+"+
                                        "[\u4e00-\u9fa5]+id+[0-9]+0000+"
                                     };
        String RAM_String = String.join("",Analysis(Calculation_Rules,JSON_Data));
        String[][] Calculation_Rules_T = {  
                                            {
                                                "[\u4e00-\u9fa5]+"
                                            },
                                            {
                                                 "todayconfirm+[0-9a-z]+suspect+[0-9a-z]+"+
                                                 "heal+[0-9a-z]+dead+[0-9a-z]+severe+"+
                                                 "[0-9a-z]+storeConfirm+[0-9a-z-]",
                                                 "confirm+[0-9]+",
                                                 "[0-9]+"
                                            },
                                         };
        String[] name = Analysis(Calculation_Rules_T[0],RAM_String);
        String[] confirm = Analysis(Calculation_Rules_T[1],RAM_String);
        String[][] Copy = new String[name.length][2];
        Data_China_New = Copy;
        for(int i = 0; i<name.length; i++){
                Data_China_New[i][0] = name[i];
                Data_China_New[i][1] = confirm[i]; 
        }
    }


    // Regular Analysis
    private String[] Analysis(String[] regEx,String RAM_string){
        // RAM
        ArrayList<String> RAM_ArrayList = new ArrayList<String>();
        String RAM_String = RAM_string;
        // Deal with
        for(int i=0;i<regEx.length;i++){
            RAM_ArrayList.clear();
            matcher = Pattern.compile(regEx[i]).matcher(RAM_String);
            while (matcher.find()) RAM_ArrayList.add(matcher.group(0));
            RAM_String = String.join("",RAM_ArrayList);
        }   
        // Store log
        saveReAn(String.join("",RAM_ArrayList));
        // RETURN
        return RAM_ArrayList.toArray(new String[RAM_ArrayList.size()]);
    }

    // SAVE Data_China_ALL & Data_China_New & Data_China_today_ALL   *./* --> mining_transfer/*
    private void SaveData(){
        mining_transfer.Data_China_today = Data_China_today;
        mining_transfer.Data_China_New = Data_China_New;
        mining_transfer.Data_China_today_ALL = Data_China_today_ALL;
    }
    
    // Store log
    private void saveReAn(String data) {
        filepath = "Regular_Analysis_"+time+"__"+(ii++)+".txt";
        try {
            OutputStreamWriter outs = new OutputStreamWriter(new FileOutputStream(filepath, true), charset);
            outs.write(data);
            outs.close();
        } catch (IOException e) {
          System.out.println("Error at save Regular Analysis...");  e.printStackTrace();
        }
    }

}
