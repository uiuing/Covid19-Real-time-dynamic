package DATA_visualization;
/**
 * 
 * @param China-COVID-19数据可视化模块调度
 * 
 * @param ©️uiuing.top   2020
 *
 */
public class Run_visualization {
    // MAIN
    public Run_visualization(){
        new Extraction();
        /////////////////////////////////////////////////////////////////////////////////////
        // Set Generation Javascript Data  visualization_transfer --> Generation_Javascript
        SetGeneration_Javascript__Data();
        // Run Generation Javascript
        new Generation_Javascript();
        /////////////////////////////////////////////////////////////////////////////////////
        // Set Generation HTML   visualization_transfer -->  Generation_html
        SetGeneration_html__Data();
        // Run Generation HTML
        new Generation_html();
        /////////////////////////////////////////////////////////////////////////////////////
    }

    // Set Generation Javascript Data  visualization_transfer --> Generation_Javascript
    private void SetGeneration_Javascript__Data(){
        Generation_Javascript.Data_China_New = visualization_transfer.Data_China_New;
        Generation_Javascript.Data_China_today_ALL = visualization_transfer.Data_China_today_ALL;
        Generation_Javascript.Data_China_today = visualization_transfer.Data_China_today;
        Generation_Javascript.Data_China = visualization_transfer.Data_China;
        Generation_Javascript.Data_time = visualization_transfer.Data_time;
    }
    // Set Generation HTML Data   visualization_transfer -->  Generation_html
    private void SetGeneration_html__Data(){
       Generation_html.Javascript_todayChinaALL = visualization_transfer.Javascript_todayChinaALL;
       Generation_html.Javascript_todayChina = visualization_transfer.Javascript_todayChina;
       Generation_html.Javascript_todayChinaALL_name = visualization_transfer.Javascript_China_New_name;
       Generation_html.Javascript_todayChinaALL_value = visualization_transfer.Javascript_China_New_value;
       Generation_html.Javascript_todayChinaALL_name_T = visualization_transfer.Javascript_China_New_name_T;
       Generation_html.Javascript_todayChinaALL_value_T = visualization_transfer.Javascript_China_New_value_T;
       Generation_html.Data_China_today = visualization_transfer.Data_China_today;
       Generation_html.Javascript_China_ALL = visualization_transfer.Javascript_China_ALL;
       Generation_html.Javascript_Data_name = visualization_transfer.Javascript_Data_name;
       Generation_html.Javascript_Data_time = visualization_transfer.Javascript_Data_time;
    }
    
}
