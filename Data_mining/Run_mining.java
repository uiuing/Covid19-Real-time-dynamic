package Data_mining;
/**
 * 
 * @param China-COVID-19数据挖掘模块调度
 * 
 * @param ©️uiuing.top   2020
 *
 */
public class Run_mining {
    // MAIN
    public Run_mining() {
        ////////////////////////////////////////////////////////
        // Run Scraping
        new Scraping();
        ////////////////////////////////////////////////////////
        // Data transfer   Cleaning --> mining_transfer
        setCleaning_Data();
        // Run Cleaning
        new Cleaning();
        ////////////////////////////////////////////////////////
        // Data transfer   Submission --> mining_transfer
        setSubmission_Data();
        // Run Submission
        new Submission();
        ////////////////////////////////////////////////////////
    }

    // Data transfer   Cleaning --> mining_transfer
    private void setCleaning_Data(){
        Cleaning.JSON_Data = mining_transfer.JSON_Data;
    }
    // Data transfer   Submission --> mining_transfer
    private void setSubmission_Data(){
        Submission.Chinadate = mining_transfer.Data_China_today;
    }

}