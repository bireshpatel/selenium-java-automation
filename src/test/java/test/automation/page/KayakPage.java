package test.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import test.automation.page.common.BasePageSelenium;
import test.automation.steps.KayakSteps;
import test.automation.utils.Constants;
import test.automation.utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public class KayakPage extends BasePageSelenium {

    private static final By LNK_SIGNUP = By.xpath("//*[@id='headersigninlink']");
    private static final By TXT_ORIGIN = By.xpath("//*[@id='origin']");
    private static final By TXT_DESTINATION = By.xpath("//*[@id='destination']");
    private static final By DATE_DEPT = By.xpath("//*[@id='travel_dates-start-display']");
    private static final By DATE_RETURN = By.xpath("//*[@id='travel_dates-end-display']");
    private static final By CHKBOX_NRORIGIN = By.xpath("//*[@id='nearbyO-wrapper']/label");
    private static final By CHKBOX_NRDEST = By.xpath("//*[@id='nearbyD-wrapper']/label");
    private static final By BTN_SEARCH = By.xpath("//*[@id='fdimgbutton']/span");

//    private static final By BTN_DETAILS = By.xpath("//*[@id='content_div']/div[3]/div/div/div[2]/div[4]/div/div/div");

    //*[@id='content_div']/div[3]/div/div/div[2]/div[4]/div/div/div

    //*[@id="travel_dates-start-wrapper"]
    //*[@id="travel_dates-start-display"]
    //*[@id="travel_dates-start"]
    //*[@id="nearbyO-wrapper"]/label
    //*[@id="nearbyO"]
    //*[@id="fdimgbutton"]/span
    //*[@id="fdimgbutton"]

    private static final String url = Constants.mxiUrl;


    Utilities utilities;

    public KayakPage(WebDriver driver) {
        super(driver);
    }

    public boolean launchApplication(){

        getDriver().get(url);
        getDriver().manage().window().maximize();

        return isElementPresent(LNK_SIGNUP);
    }

    public void enterStationDetails(String origin, String destination){

        enterText(TXT_ORIGIN, origin);
        enterText(TXT_DESTINATION, destination);

    }

    public void selectDate(int dept, int rtn){

        if (dept < rtn){
            String deptDate = Utilities.generateDate(dept);
            String rtnDate = Utilities.generateDate(rtn);

            enterText(DATE_DEPT, deptDate);
            sendKeyEnter(DATE_DEPT);

            enterText(DATE_RETURN, rtnDate);
            sendKeyEnter(DATE_RETURN);
        }
    }

    public void selectNRAirport(boolean flag){
        if(flag){
            checkboxClick(CHKBOX_NRORIGIN, true);
            checkboxClick(CHKBOX_NRDEST, true);
        }
    }

    public void clickSearch(){

        buttonClick(BTN_SEARCH);
    }

    public void selectFlight(int rowNo){

//        buttonClick(By.xpath("//*[@id='content_div']/div["+rowNo+"]/div/div/div[2]/div[4]/div/div/div"));
        buttonClick(By.xpath("//*[@id='content_div']/div["+rowNo+"]/div/div/div[2]/div[4]/div[1]/div/div/a[1]"));
        waitForElement(By.xpath("//*[@id='content_div']/div["+rowNo+"]/div/div/div[2]/div[4]/div[1]/div/div/a[2]"));

    }

    public ArrayList<String> verifyFlightDetails(){

        int rowNo = KayakSteps.rowNoSelect;

        ArrayList<String> strings = new ArrayList<>();
        strings.add(getWebElementText(By.xpath("//*[@id='content_div']/div["+rowNo+"]/div/div/div[2]/div[2]/div[4]/div/div[3]")));
        strings.add(getWebElementText(By.xpath("//*[@id='content_div']/div["+rowNo+"]/div/div/div[2]/div[2]/div[4]/div/div[6]")));

        return strings;
    }

    public ArrayList<String> readFlightDetails(int rowNo){

//        rowNo = KayakSteps.rowNoSelect;

        ArrayList<String> strings = new ArrayList<>();
        strings.add(getWebElementText(By.xpath("//*[@id='content_div']/div["+rowNo+"]/div/div/div[2]/div[7]/div/div/div[2]/div/div/div/table/tbody/tr[1]/td[1]")));
        strings.add(getWebElementText(By.xpath("//*[@id='content_div']/div["+rowNo+"]/div/div/div[2]/div[7]/div/div/div[2]/div/div/div/table/tbody/tr[11]/td[1]")));
        strings.add(getWebElementText(By.xpath("//*[@id='content_div']/div["+rowNo+"]/div/div/div[1]/div[1]/div[1]/a")));

        return strings;
    }
}