package Data_mining;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 *  @param China-COVID-19数据获取
 * 
 *  @param ©️uiuing.top   2020
 * 
 */

class Scraping {

    // URL
    private URL url;
    // JSON DATA
    private String JSON_Data;
    // Log storage location
    private String filepath;
    //Crawl storage
    private InputStream htm_in; 
    // Encoding format
    private String charset = "utf-8";
    // Delay
    private final int sec_cont = 10000;
    //Simulate browser
    private final String browser[] = {
                                        "User-Agent", 
                                        "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"
                                    };
    // Aims
    private final String url_str = "https://c.m.163.com/ug/api/wuhan/app/data/list-total";
    // DATE
    private final String time = 
                                String.valueOf(Calendar.getInstance().get(Calendar.DATE))+"-"
                                + String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))+"-"
                                + String.valueOf(Calendar.getInstance().get(Calendar.MINUTE))+"-"
                                + String.valueOf(Calendar.getInstance().get(Calendar.SECOND));
    //Main
    Scraping(){
        try {
            url = new URL(url_str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } if (url == null) System.out.println("Error url is null !");
        try {
            // Start crawling
            URLConnection url_con = url.openConnection();
            url_con.setDoOutput(true);
            url_con.setReadTimeout(sec_cont);
            url_con.setRequestProperty(browser[0],browser[1]);
            htm_in = url_con.getInputStream();
            // Formatting
            JSON_Data = InputStream2String(htm_in, charset);
            // Store log
            saveHtml("Initial_data");
            // Save Data  *.JSON_Data --> mining_transfer/*
            SaveData();
        } catch (IOException e) {
            e.printStackTrace();
        } if(htm_in == null) System.out.println("Error htm_in is null !");
    }

    // Blank processing
    private static String InputStream2String(InputStream in_st, String charset) throws IOException {      
        BufferedReader buff = new BufferedReader(new InputStreamReader(in_st, charset));
        StringBuffer res = new StringBuffer();
        String line = "";
        while ((line = buff.readLine()) != null) 
        res.append(line);
        String regEx="[\"`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher m = Pattern.compile(regEx).matcher(res.toString());
        return m.replaceAll("").trim();
    }

    // Store log
    private void saveHtml(String NameId) {
        filepath = time + "_" + NameId + ".txt";
        try {
            OutputStreamWriter outs = new OutputStreamWriter(new FileOutputStream(filepath, true), charset);
            outs.write(JSON_Data);
            outs.close();
        } catch (IOException e) {
          System.out.println("Error at save html...");  e.printStackTrace();
        }
    }

    // Save Data  *.JSON_Data --> mining_transfer/*
    private void SaveData(){
        mining_transfer.JSON_Data = JSON_Data;
    }

}