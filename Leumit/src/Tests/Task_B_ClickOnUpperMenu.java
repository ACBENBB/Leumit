package Tests;

import PageObjects.DemoGuru99;
import org.testng.annotations.Test;

public class Task_B_ClickOnUpperMenu extends BaseTest{
    @Override
    public String getTaskNumber() {
        return "B";
    }

    @Test(description = "click on upper menu with one string")
    public void ClickOnUpperMenuWithString_test() {
        DemoGuru99 demoGuru99 = new DemoGuru99(driver);
        demoGuru99.clickUpperMenu("Selenium;Table Demo");
    }
}
