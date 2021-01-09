package DATA_visualization;
/**
 * 
 * @param China-COVID-19生成Javascript关键代码
 * 
 * @param ©️uiuing.top       2020
 *
 */
public class Generation_Javascript {

    // Today ALL
    public static String[][] Data_China_today_ALL;
    // ALL China
    public static String[][] Data_China_ALL;
    // Today ALL NEW
    public static String[][] Data_China_New;
    // Today China
    public static String[] Data_China_today;
    // Data TIME
    public static String[] Data_time;
    // Data NAME
    public static String[] Data_name;
    // Data for China ALL
    public static String[][] Data_China;
    // Javascript For TodayChinaALL
    private static String Javascript_todayChinaALL = "";
    // Javascript For TodayChina
    private static String Javascript_todayChina = "";
    // Javascript TodayChinaALL NAME
    public static String Javascript_China_New_name = "";
    // Javascript TodayChinaALL VALUE
    public static String Javascript_China_New_value = "";
    // Javascript TodayChinaALL NAME Two
    public static String Javascript_China_New_name_T = "";
    // Javascript TodayChinaALL VALUE Two
    public static String Javascript_China_New_value_T = "";
    // Javascript Data China ALL
    public static String Javascript_China_ALL = "";
    // Javascript Data TIME
    public static String Javascript_Data_time = "";
    // Javascript Data Name
    public static String Javascript_Data_name = "";

    // MAIN
    Generation_Javascript(){
        // New Javascript for TodayChinaALL && NEW
        NewtodayChinaALL();
        // New Javascript for TodayChina
        NewtodayChina();
        // New Javascript Data TIME
        NewDatatime();
        // New Javascript Data China ALL
        NewChinaALL();
        //SAVE Data
        SavaData();
    }

    // New Javascript for TodayChinaALL && NEW
    private void NewtodayChinaALL(){
        for(int i=0; i<Data_China_today_ALL.length; i++){
            if(i<17){
                Javascript_China_New_name
                    +=
                    "'"+
                    Data_China_New[i][0] +
                    "'";
                Javascript_China_New_value
                    +=
                    Data_China_New[i][1];
                if(i!=16){
                    Javascript_China_New_name += ",";
                    Javascript_China_New_value += ",";
                }
            }
            if(i>=17){
                Javascript_China_New_name_T
                    +=
                    "'"+
                    Data_China_New[i][0] +
                    "'";
                Javascript_China_New_value_T
                    +=
                    Data_China_New[i][1];
                if(i!=33){
                    Javascript_China_New_name_T += ",";
                    Javascript_China_New_value_T += ",";
                }
            }
            Javascript_todayChinaALL 
                += 
                "{"+
                "name:'"+Data_China_today_ALL[i][0]+"',"+
                "value:" + Data_China_today_ALL[i][1]+
                "}";
            if(i!=Data_China_today_ALL.length-1) {
                Javascript_todayChinaALL += ",";
            }
        }
    }

    // New Javascript for TodayChina
    private void NewtodayChina() {
        for(int i=1; i<Data_China_today.length; i++){
            Javascript_todayChina += Data_China_today[i];
            if(i == Data_China_today.length-1) break;
            Javascript_todayChina += ","; 
        }  
    }

    // New Javascript Data China ALL
    private void NewChinaALL(){
        for(int i = 0; i <= Data_name.length; i++){
            String RAM = "";
            if(i!=0 && i!=Data_name.length){
                for(int j = 0; j < Data_China.length; j++){
                    RAM
                        +=
                        "\'"+
                        Data_China[j][i] +
                        "\'";
                    if(j!=Data_China.length-1) RAM += ",";
                }
                Javascript_China_ALL 
                    +=
                    "{" +
                    "name:\'" +
                    Data_name[i] +
                    "\'," +
                    "type: \'line\',"+
                    "stack: \'总量\',"+
                    "areaStyle: {},"+
                    "data: ["+
                    RAM +
                    "]},";
            }
            if(i==Data_name.length){
                for(int j = 0; j < Data_China.length; j++){
                    RAM
                        +=
                        "\'"+
                        Data_China[j][0] +
                        "\'";
                    if(j!=Data_China.length-1) RAM += ",";
                }
                Javascript_China_ALL 
                    +=
                    "        {"+
                    "          name: \'" +
                    Data_name[0] +
                    "\'," +
                    "          type: \'line\',"+
                    "          stack: \'总量\',"+
                    "          label: {"+
                    "            normal: {"+
                    "              show: true,"+
                    "              position: \'top\'"+
                    "            }"+
                    "          },"+
                    "          areaStyle: {},"+
                    "          data: ["+
                    RAM +
                    "]"+
                    "        }]"+
                    "      };";
            }
        }
    }

    // New Javascript Data TIME  && Name
    private void NewDatatime(){
        String[] Copy_name = {"累计确诊","现有确诊","疑似","治愈","死亡","境外输入","无症状"};
        Data_name = Copy_name;
        for(int i=0; i<Data_time.length; i++){
            Javascript_Data_time 
                +=
                "\'"+
                Data_time[i] +
                "\'";
            if(i!=Data_time.length-1) Javascript_Data_time += ",";
        }
        for(int i=0; i<Data_name.length; i++){
            Javascript_Data_name 
                +=
                "\'"+
                Data_name[i] +
                "\'";
            if(i!=Data_name.length-1) Javascript_Data_name += ",";
        }

    }

    //SAVE Data
    private void SavaData(){
        visualization_transfer.Javascript_todayChinaALL = Javascript_todayChinaALL;
        visualization_transfer.Javascript_todayChina = Javascript_todayChina;
        visualization_transfer.Javascript_China_New_name = Javascript_China_New_name;
        visualization_transfer.Javascript_China_New_value = Javascript_China_New_value;
        visualization_transfer.Javascript_China_New_name_T = Javascript_China_New_name_T; 
        visualization_transfer.Javascript_China_New_value_T = Javascript_China_New_value_T;
        visualization_transfer.Javascript_China_ALL = Javascript_China_ALL;
        visualization_transfer.Javascript_Data_time = Javascript_Data_time;
        visualization_transfer.Javascript_Data_name = Javascript_Data_name;
    }

}                         