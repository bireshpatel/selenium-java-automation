package test.automation.steps;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import test.automation.page.KayakPage;
import test.automation.utils.Report;
import test.automation.utils.Verify;

import java.util.ArrayList;


public class KayakSteps extends ScenarioSteps{

    @Steps
    Report report;
    @Steps
    public Verify verify;

    private KayakPage kayakPages;
    public static int rowNoSelect;
    public String Origin;
    public String Destination;

    @Given("I visit to Kayak site to search the flight")
    public void visitSite(){

        boolean status = kayakPages.launchApplication();
        verify.verifyStatus("Successfully Landed in Home Page", "Failed to browse", status);
    }

    @Given("I enter <Origin> and <Destination> Cities")
    public void enterStation(@Named("Origin") String origin, @Named("Destination") String destination){

//        Origin = origin;
//        Destination = destination;
        kayakPages.enterStationDetails(origin, destination);
    }

    @Given("I select Departure date as <dept> days and Return date as <rtn> days from today")
    public void selectDate(@Named("dept") int dept, @Named("rtn") int rtn){

        kayakPages.selectDate(dept, rtn);
    }

    @Given("I select Near by airports as <near> for both origin and destination")
    public void selectNRAirport(@Named("near") boolean near){

        kayakPages.selectNRAirport(near);
    }

    @When("I click Search")
    public void selectSearch(){

        kayakPages.clickSearch();
    }

    @When("I select the search result <N> row")
    public void selectFlight(@Named("N") int rowNo){
        rowNoSelect = rowNo+1;
        kayakPages.selectFlight(rowNoSelect);

    }

    @Then("I found Origin <Origin> and Destination <Destination> Details are same as the one displaying in the main screen")
    public void verifyFlightDetails(@Named("Origin") String origin, @Named("Destination") String destination){

        ArrayList<String> flightInfo = kayakPages.verifyFlightDetails();
        Origin = flightInfo.get(0);
        Destination = flightInfo.get(1);
        verify.verifyEqual(Origin, origin);
        verify.verifyEqual(Destination, destination);

    }

    @Then("I report flight details - Total Fare, Time of Travel")
    public void printDetails(){

        ArrayList<String> flightDetails = kayakPages.readFlightDetails(rowNoSelect);

        report.report("Origin Station: " + Origin + "\n" + "Destination Station: " + Destination + "\n" + "Onward Journey Date: " + flightDetails.get(0) + "\n" + "Return Journey Date: " + flightDetails.get(1) + "\n" + "Total Fare: " + flightDetails.get(2));
        System.out.println("biresh");
    }


}
