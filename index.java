import Data_mining.Run_mining;
import Data_mining.mining_transfer;
import DATA_visualization.visualization_transfer;
import DATA_visualization.Run_visualization;
/**
 * 
 * @param China-COVID-19架构总调度
 * 
 * @param ©️uiuing.top   2020
 * 
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//                  佛祖保佑       永不宕机     永无BUG              //
 *
 */
public class index {
    public static void main(String[] args) {
        ////////////////////////////////////////////////////////
        // Run Data mining
        new Run_mining();
        ////////////////////////////////////////////////////////
        // Data transfer   DATA_mining --> DATA_visualization
        setvisualization_Data();
        // Run Data visualization
        new Run_visualization();
        ////////////////////////////////////////////////////////
    }

    // Data transfer   DATA_mining --> DATA_visualization
    private static void setvisualization_Data() {
        visualization_transfer.Data_China_New = mining_transfer.Data_China_New;
        visualization_transfer.Data_China_today_ALL = mining_transfer.Data_China_today_ALL;
        visualization_transfer.Data_China_today = mining_transfer.Data_China_today; 
    }
}