package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoGuru99 extends BasePage {

    String headerPattern = "//div[@id='navbar-brand-centered']//a[contains(text(),'%s')]";

    public DemoGuru99(WebDriver driver) {
        super(driver);
    }

    public void clickUpperMenu(String menuPath) {
        String[] menus = menuPath.split(";");
        clickUpperMenu(menus[0], menus[1]);
    }

    public void clickUpperMenu(String mainManu, String subMenu) {
        String mainMenuXpath = String.format(headerPattern, mainManu);
        click(driver.findElement(By.xpath(mainMenuXpath)));
        try{
        if (subMenu != null){
                String menuXpath = String.format(headerPattern, subMenu);
                click(driver.findElement(By.xpath(menuXpath)));
        }}catch (NullPointerException e){
            System.out.println("Null point exception: "+ e);
        }
    }
}
