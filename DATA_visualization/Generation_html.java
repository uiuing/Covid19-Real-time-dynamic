package DATA_visualization;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @param China-COVID-19生成HTML代码
 * 
 * @param ©️uiuing.top           2020
 *
 */
public class Generation_html {
    // Javascript TodayChinaALL
    public static String Javascript_todayChinaALL;
    // Javascript For TodayChina
    public static String Javascript_todayChina;
    // Javascript TodayChinaALL NAME
    public static String Javascript_todayChinaALL_name;
    // Javascript TodayChinaALL VALUE
    public static String Javascript_todayChinaALL_value;
    // Javascript TodayChinaALL NAME TWO
    public static String Javascript_todayChinaALL_name_T;
    // Javascript TodayChinaALL VALUE TWO
    public static String Javascript_todayChinaALL_value_T;
    // Javascript Data China ALL
    public static String Javascript_China_ALL = "";
    // Javascript Data TIME
    public static String Javascript_Data_time = "";
    // Javascript Data Name
    public static String Javascript_Data_name = "";
    // Generation TodayChinaALL html
    private String TodayChinaALL_html;
    // Today China
    public static String[] Data_China_today;
    // UPDATE TMIE
    private static String UpdateTime;

    // MAIN
    Generation_html(){
        // Get TIME
        gettime();
        // Generation ALL HTML CODE
        html_code();
        // Storage ALL HTML CODE
        Storage_html();
    }

    // Get TIME
    private void gettime(){
        Date day = new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		UpdateTime =  df.format(day);  
    }

    // Storage ALL HTML CODE
    private void Storage_html(){
        try {
            File file = new File("index.html");
            file.delete();
            file.createNewFile();
            OutputStreamWriter outs = new OutputStreamWriter(new FileOutputStream("index.html", true), "utf-8");
            outs.write(TodayChinaALL_html);
            outs.close();
        } catch (IOException e) {
            e.printStackTrace(); System.out.println("Error at save html...");
        }
    }

    // Generation ALL HTML CODE
    private void html_code(){
        TodayChinaALL_html 
                            = 
                            "<!DOCTYPE html>"+
                            "<html>"+
                            "  "+
                            "  <head>"+
                            "    <meta charset=utf-8>"+
                            "    <title>新冠肺炎疫情最新动态</title>"+
                            "    <script src=ReferenceDrawing.js></script>"+
                            " <script src =\"https://cdn.jsdelivr.net/npm/echarts@4.1.0/dist/echarts.js\"></script> "+
                            "    <script src=https://echarts.apache.org/examples/vendors/echarts/map/js/china.js></script>"+
                            "    <script src=Form_theme.js></script>"+
                            "  </head>"+
                            "  "+
                            "  <body>"+
                            "    <style>.al{ float: left; } .bl{ float: right; } body{ background-size:cover; background-attachment:fixed; background-color: rgba(255,255,255); }</style>"+
                            "    <div class=\"al\" style=\"width:55%;height:800px;\">"+
                            "      <p>"+
                            "        <font color=#0066CC size=\"3\">&nbsp;&nbsp;数据来源：国家及各地卫健委每日信息发布&nbsp;&nbsp;&nbsp; 截至：" + 
                            UpdateTime +
                            "      </p>"+
                            "      <div class=\"al\" id=Pie_chart style=\"width:45%;height:300px;\"></div> "+
                            "      <div class=\"al\" id=line_chart style=\"width:55%;height:300px;\"></div> "+
                            "      <div class=\"al\" id=bar_chart style=\"width:50%;height:500px;\"></div> " +
                            "      <div class=\"al\" id=bar_chart_t style=\"width:50%;height:500px;\"></div> "+
                            "</div> "+
                            "    <div class=\"al\" style=\"width:45%;height:800px;\">"+
                            "      <div class=\"bl\" id=Map style=\"width:100%;height:800px;\"></div>" +
                            "联系我: &nbsp;Blog&nbsp;"+
                            "          <a href=\"http://uiuing.top\" target=\"_blank\">"+
                            "            <font color=#FF9900 size=\"4\">GO!</font></a>&nbsp;&nbsp; GitHub&nbsp;"+
                            "          <a href=\"https://github.com/uiuing/\" target=\"_blank\">"+
                            "            <font color=#FF9900 size=\"4\">GO!</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;© ️uiuing.top 2020</font>"+
                            "</div>"+
                            "    <script type=text/javascript>var myChart = echarts.init(document.getElementById(\'Pie_chart\'), \'Form_theme\');"+
                            "option = {"+
                            "    title: [{"+
                            "        text: \'中国新冠肺炎疫情数据  相关比例\'"+
                            "    }, {"+
                            "        subtext: \'治愈率\',"+
                            "        left: \'16.67%\',"+
                            "        top: \'75%\',"+
                            "        textAlign: \'center\'"+
                            "    }, {"+
                            "        subtext: \'传播来源\',"+
                            "        left: \'50%\',"+
                            "        top: \'75%\',"+
                            "        textAlign: \'center\'"+
                            "    }, {"+
                            "        subtext: \'症状\',"+
                            "        left: \'83.33%\',"+
                            "        top: \'75%\',"+
                            "        textAlign: \'center\'"+
                            "    }],"+
                            "    series: [{"+
                            "        type: \'pie\',"+
                            "        radius: \'10%\',"+
                            "        center: [\'20%\', \'50%\'],"+
                            "        data: [{"+
                            "                name: \'死亡\',"+
                            "                value: "+
                            Data_China_today[5]+ 
                            "            }, {"+
                            "                name: \'治愈\',"+
                            "                value: "+
                            Data_China_today[4] +
                            "            }],"+
                            "        animation: false,"+
                            "        label: {"+
                            "            position: \'outer\',"+
                            "            alignTo: \'none\',"+
                            "            bleedMargin: 5"+
                            "        },"+
                            "        left: 0,"+
                            "        right: \'66.6667%\',"+
                            "        top: 0,"+
                            "        bottom: 0"+
                            "    }, {"+
                            "        type: \'pie\',"+
                            "        radius: \'10%\',"+
                            "        center: [\'50%\', \'50%\'],"+
                            "        data: [{"+
                            "                name: \'境外输入\',"+
                            "                value: " +
                            Data_China_today[6] +
                            "            }, {"+
                            "                name: \'本地\',"+
                            "                value: " +
                            String.valueOf(
                                Integer.valueOf(Data_China_today[1]) - 
                                Integer.valueOf(Data_China_today[5])
                             ) +
                            "            }],"+
                            "        animation: false,"+
                            "        label: {"+
                            "            position: \'outer\',"+
                            "            alignTo: \'labelLine\',"+
                            "            bleedMargin: 5"+
                            "        },"+
                            "        left: \'33.3333%\',"+
                            "        right: \'33.3333%\',"+
                            "        top: 0,"+
                            "        bottom: 0"+
                            "    }, {"+
                            "        type: \'pie\',"+
                            "        radius: \'10%\',"+
                            "        center: [\'75%\', \'50%\'],"+
                            "        data: [{"+
                            "                name: \'无症状\',"+
                            "                value: " +
                            Data_China_today[7] +
                            "            }, {"+
                            "                name: \'症状明显\',"+
                            "                value: " +
                            String.valueOf(
                                Integer.valueOf(Data_China_today[1]) - 
                                Integer.valueOf(Data_China_today[7])
                             ) +
                            "            }],"+
                            "        animation: false,"+
                            "        label: {"+
                            "            position: \'outer\',"+
                            "            alignTo: \'edge\',"+
                            "            margin: 20"+
                            "        },"+
                            "        left: \'66.6667%\',"+
                            "        right: 0,"+
                            "        top: 0,"+
                            "        bottom: 0"+
                            "    }]"+
                            "};"+
                            "      myChart.setOption(option);</script>"+
                            "    <script type=text/javascript>var myChart = echarts.init(document.getElementById(\'bar_chart_t\'), \'Form_theme\');"+
                            "      option = {"+
                            "        tooltip: {"+
                            "          trigger: \'axis\',"+
                            "          axisPointer: {"+
                            "            type: \'shadow\'"+
                            "          }"+
                            "        },"+
                            "        legend: {"+
                            "          data: [\'\']"+
                            "        },"+
                            "        grid: {"+
                            "          left: \'3%\',"+
                            "          right: \'4%\',"+
                            "          bottom: \'3%\',"+
                            "          containLabel: true"+
                            "        },"+
                            "        xAxis: {"+
                            "          type: \'value\',"+
                            "          boundaryGap: [0, 0.01]"+
                            "        },"+
                            "        yAxis: {"+
                            "          type: \'category\',"+
                            "          data: ["+
Javascript_todayChinaALL_name +
                            "]"+
                            "        },"+
                            "        series: [{"+
                            "          name: \'\',"+
                            "          type: \'bar\',"+
                            "          data: [" +
Javascript_todayChinaALL_value +
                            "]"+
                            "        }]"+
                            "      };"+
                            "      myChart.setOption(option);</script>"+
                            "    <script type=text/javascript>var myChart = echarts.init(document.getElementById(\'bar_chart\'), \'Form_theme\');"+
                            "      option = {"+
                            "      title: {"+
                            "          text: \'较昨日\',"+
                            "          left: \'left\'"+
                            "        },"+
                            "        tooltip: {"+
                            "          trigger: \'axis\',"+
                            "          axisPointer: {"+
                            "            type: \'shadow\'"+
                            "          }"+
                            "        },"+
                            "        legend: {"+
                            "          data: ['新增人数']"+
                            "        },"+
                            "        grid: {"+
                            "          left: \'3%\',"+
                            "          right: \'4%\',"+
                            "          bottom: \'3%\',"+
                            "          containLabel: true"+
                            "        },"+
                            "        xAxis: {"+
                            "          type: \'value\',"+
                            "          boundaryGap: [0, 0.01]"+
                            "        },"+
                            "        yAxis: {"+
                            "          type: \'category\',"+
                            "          data: ["+
Javascript_todayChinaALL_name_T +
                            "]"+
                            "        },"+
                            "        series: [{"+
                            "          name: \'新增人数\',"+
                            "          type: \'bar\',"+
                            "          data: [" +
Javascript_todayChinaALL_value_T +
                            "]"+
                            "        }]"+
                            "      };"+
                            "      myChart.setOption(option);</script>"+
                            "    <script type=text/javascript>var myChart = echarts.init(document.getElementById(\'line_chart\'), \'Form_theme\');"+
                            "      option = {"+
                            "        title: {"+
                            "          text: \'\'"+
                            "        },"+
                            "        tooltip: {"+
                            "          trigger: \'axis\',"+
                            "          axisPointer: {"+
                            "            type: \'cross\',"+
                            "            label: {"+
                            "              backgroundColor: \'#6a7985\'"+
                            "            }"+
                            "          }"+
                            "        },"+
                            "        legend: {"+
                            "          data: ["+
Javascript_Data_name +
                            "]"+
                            "        },"+
                            "        toolbox: {"+
                            "          feature: {"+
                            "            saveAsImage: {}"+
                            "          }"+
                            "        },"+
                            "        grid: {"+
                            "          left: \'3%\',"+
                            "          right: \'4%\',"+
                            "          bottom: \'3%\',"+
                            "          containLabel: true"+
                            "        },"+
                            "        xAxis: [{"+
                            "          type: \'category\',"+
                            "          boundaryGap: false,"+
                            "          data: ["+
Javascript_Data_time +
                            "]"+
                            "        }],"+
                            "        yAxis: [{"+
                            "          type: \'value\'"+
                            "        }],"+
                            "        series: ["+
Javascript_China_ALL +                         
                            "      myChart.setOption(option);</script>"+
                            "    <script type=text/javascript>var myChart = echarts.init(document.getElementById(\'Map\'), \'Form_theme\');"+
                            "      var option = {"+
                            "        title: {"+
                            "          text: \'现有确诊地区分布图\',"+
                            "          left: \'center\'"+
                            "        },"+
                            "        tooltip: {"+
                            "          trigger: \'item\'"+
                            "        },"+
                            "        legend: {"+
                            "          orient: \'vertical\',"+
                            "          left: \'left\',"+
                            "          data: [\'中国疫情图\']"+
                            "        },"+
                            "        visualMap: {"+
                            "          type: \'piecewise\',"+
                            "          pieces: [{"+
                            "            min: 1000,"+
                            "            max: 1000000,"+
                            "            label: \'1000人及以上\',"+
                            "            color: \'#ff2736\'"+
                            "          },"+
                            "          {"+
                            "            min: 500,"+
                            "            max: 999,"+
                            "            label: \'500-999人\',"+
                            "            color: \'#ff6341\'"+
                            "          },"+
                            "          {"+
                            "            min: 100,"+
                            "            max: 499,"+
                            "            label: \'100-499人\',"+
                            "            color: \'#ffa577\'"+
                            "          },"+
                            "          {"+
                            "            min: 10,"+
                            "            max: 99,"+
                            "            label: \'10-99人\',"+
                            "            color: \'#ffcea0\'"+
                            "          },"+
                            "          {"+
                            "            min: 1,"+
                            "            max: 9,"+
                            "            label: \'1-9人\',"+
                            "            color: \'#ffe7b2\'"+
                            "          },"+
                            "          {"+
                            "            min: 0,"+
                            "            max: 0,"+
                            "            label: \'0人\',"+
                            "            color: \'#ffffff\'"+
                            "          },"+
                            "          ],"+
                            "          color: [\'#FF99CC\', \'#FF99CC\', \'#FF99CC\']"+
                            "        },"+
                            "        toolbox: {"+
                            "          show: false,"+
                            "          orient: \'vertical\',"+
                            "          left: \'right\',"+
                            "          top: \'center\',"+
                            "          feature: {"+
                            "            mark: {"+
                            "              show: true"+
                            "            },"+
                            "            dataView: {"+
                            "              show: true,"+
                            "              readOnly: false"+
                            "            },"+
                            "            restore: {"+
                            "              show: true"+
                            "            },"+
                            "            saveAsImage: {"+
                            "              show: true"+
                            "            }"+
                            "          }"+
                            "        },"+
                            "        roamController: {"+
                            "          show: true,"+
                            "          left: \'right\',"+
                            "          mapTypeControl: {"+
                            "            \'china\': true"+
                            "          }"+
                            "        },"+
                            "        series: [{"+
                            "          name: \'确诊数\',"+
                            "          type: \'map\',"+
                            "          mapType: \'china\',"+
                            "          roam: false,"+
                            "          label: {"+
                            "            show: true,"+
                            "            color: \'#1C1C1C\'"+
                            "          },"+
                           "          data: ["+
Javascript_todayChinaALL +   // COVID 
                            "          ]"+
                            "        }]"+
                            "      };"+
                            "      myChart.setOption(option);</script>"+
                            "  </body>"+
                            "</html>";
    }

}
