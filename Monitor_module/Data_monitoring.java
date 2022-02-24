package Monitor_module;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @param China-COVID-19数据监听
 * 
 * @param ©️uiuing.top       2020
 *
 */
public class Data_monitoring {
    public static void main(String[] args) {
        new MyThread("https://c.m.163.com/ug/api/wuhan/app/data/list-total").start();
    }
}

// RUN
class MyThread extends Thread {
    // URL
    private URL url;
    // Crawl storage
    private InputStream htm_in;
    // Encoding format
    private String charset = "utf-8";
    // Delay
    private final int sec_cont = 10000;
    // Simulate browser
    private final String browser[] = { "User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)" };
    // Aims
    private String url_str;
    // address
    private String[] JSON_address = new String[2];

    MyThread(String url_str) {
        super();
        this.url_str = url_str;
    }

    @Override
    public void run() {
        int i = 0;
        for (; i < 3;) {
            try {
                url = new URL(url_str);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            if (url == null)
                System.out.println("Error url is null !");
            try {
                // Start crawling
                URLConnection url_con = url.openConnection();
                url_con.setDoOutput(true);
                url_con.setReadTimeout(sec_cont);
                url_con.setRequestProperty(browser[0], browser[1]);
                htm_in = url_con.getInputStream();
                // Formatting
                JSON_address[i] = InputStream2String(htm_in, charset);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (htm_in == null)
                System.out.println("Error htm_in is null !");
            try {
                Thread.sleep(8000);
            } catch (Exception e) {
            }
            if (i == 1 && !JSON_address[0].equals(JSON_address[1]))
                System.exit(0);
            i++;
            if (i == 2)
                i = 0;
        }
    }

    private static String InputStream2String(InputStream in_st, String charset) throws IOException {
        BufferedReader buff = new BufferedReader(new InputStreamReader(in_st, charset));
        StringBuffer res = new StringBuffer();
        String line = "";
        while ((line = buff.readLine()) != null)
            res.append(line);
        String regEx = "[\"`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher m = Pattern.compile(regEx).matcher(res.toString());
        ArrayList<String> RAM_ArrayList = new ArrayList<String>();
        Matcher matcher;
        matcher = Pattern
                .compile("talconfirm+[0-9]+suspect+[0-9]+heal+[0-9]+dead+[0-9]+severe+[0-9]"
                        + "+input+[\\S]+extDatanoSymptom+[0-9]+incrNoSymptom+[0-9]+name中国")
                .matcher(m.replaceAll("").trim());
        while (matcher.find())
            RAM_ArrayList.add(matcher.group(0));
        return String.join("", RAM_ArrayList);
    }
}