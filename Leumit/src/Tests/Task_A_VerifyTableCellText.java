package Tests;

import PageObjects.w3schools;
import org.testng.annotations.Test;

public class Task_A_VerifyTableCellText extends BaseTest{

    @Override
    public String getTaskNumber() {
        return "A";
    }

    @Test(description = "verify table cell text")
    public void VerifyTableCellText_test() throws Exception {
        w3schools w3 = new w3schools(driver);
        w3.verifyTableCellText(table, Integer.parseInt(searchColumn), searchText, Integer.parseInt(returnColumnText), expectedText);
    }


}