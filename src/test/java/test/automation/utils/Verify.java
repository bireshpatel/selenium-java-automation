package test.automation.utils;

import net.thucydides.core.annotations.Steps;


public class Verify {

    @Steps
    Report report;


    public void verifyEqual(String actualValue, String expectedValue){
        if(actualValue.trim().replace("\n"," ").equalsIgnoreCase(expectedValue.trim())){
            report.report("Expected Value : " + expectedValue + " is matching with Actual Value : " + actualValue);
            assert true;
        }else{
            report.report("Expected Value : " + expectedValue + " is NOT matching with Actual Value : " + actualValue);
            assert false;
        }
    }

    public void verifyContains(String actualValue, String expectedValue){
        if(actualValue.contains(expectedValue)){
            report.report("Expected Value : " + expectedValue + " is present in - " + actualValue);
            assert true;
        }else{
            report.report("Expected Value : " + expectedValue + " is NOT present in - " + actualValue);
            assert false;
        }
    }

    public void verifyNotContains(String actualValue, String expectedValue){
        if(!actualValue.contains(expectedValue)){
            report.report("Expected Value : " + expectedValue + " is not present in - " + actualValue);
            assert true;
        }else{
            report.report("Expected Value : " + expectedValue + " is present in - " + actualValue);
            assert false;
        }
    }


    public void verifyEqual(int actualValue, int expectedValue){
        if(actualValue==expectedValue){
            report.report("Expected Value : " + expectedValue + " is matching with Actual Value : " + actualValue);
            assert true;
        }else {
            report.report("Expected Value : " + expectedValue + " is NOT matching with Actual Value : " + actualValue);
            assert false;
        }
    }

    public void verifyStatus(String successMag, String failureMsg, boolean status){
        if (status){
            report.report(successMag);
        }else{
            report.report(failureMsg);
        }
        assert status;
    }

}