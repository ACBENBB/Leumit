package Infra;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.TestListenerAdapter;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.testng.ITestResult;

import java.io.File;
import java.util.Base64;

public class AllureTestListener extends TestListenerAdapter implements ITestListener {

    // Generate the Allure report in the test-output folder
    @Override
    public void onFinish(ITestContext iTestContext) {
        File allureResultsDirectory = new File("test-output/allure-results"); // Create a File object pointing to the 'allure-results' directory inside the 'test-output' folder
        File allureReportDirectory = new File("test-output/allure-report"); // Create a File object pointing to the 'allure-report' directory inside the 'test-output' folder
        if (!allureResultsDirectory.exists()) { // Check if the 'allure-results' directory does not exist
            allureResultsDirectory.mkdirs();  // If it doesn't exist, create the directory and any necessary but nonexistent parent directories
        }
        if (!allureReportDirectory.exists()) { // Check if the 'allure-report' directory does not exist
            allureReportDirectory.mkdirs(); // If it doesn't exist, create the directory and any necessary but nonexistent parent directories
        }
    }

    // This method is called when a test method is failed
    @Override
    public void onTestFailure(ITestResult result) {
        byte[] screenshot = (byte[]) result.getAttribute("screenshot");  // Get the screenshot byte array from the test result
        String screenshotBase64 = Base64.getEncoder().encodeToString(screenshot); // Encode the screenshot byte array to a Base64-encoded String
        Allure.addAttachment("Screenshot", "image/png", screenshotBase64); // Add the Base64-encoded screenshot as an attachment to the Allure report
        Allure.step("Test failed", Status.FAILED); // Add a step to the Allure report indicating that the test failed
    }

    // This method is called when a test method is skipped
    @Override
    public void onTestSkipped(ITestResult result) {
        Allure.step("Test skipped", Status.SKIPPED);
    }

    // This method is called when a test method passes
    @Override
    public void onTestSuccess(ITestResult result) {
        Allure.step("Test passed", Status.PASSED);
    }


}
