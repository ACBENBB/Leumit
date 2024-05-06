package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.NoSuchElementException;

import static org.openqa.selenium.support.PageFactory.initElements;

// abstract page to contain general method for better and faster use
public abstract class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    public void click(WebElement el) {
        el.click();
    }

    public void clickJs(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void fillText(WebElement el, String text) {
        el.clear();
        el.sendKeys(text);
    }

    public void fillText(WebElement el, int text) {
        el.clear();
        el.sendKeys(String.valueOf(text));
    }

    public void fillText(WebElement el, double text) {
        el.clear();
        el.sendKeys(String.valueOf(text));
    }

    public void setSelectText(WebElement el, String value) {
        Select select = new Select(el);
        select.selectByVisibleText(value);
    }

    public void setSelectIndex(WebElement el, int index) {
        Select select = new Select(el);
        select.selectByIndex(index);
    }

    public int randomNumber(int min, int max) {
        int randomNum = (int) (Math.random() * (max - min + 1) + min);
        return randomNum;
    }

    public void mouseHold(WebElement el) {
        Actions actions = new Actions(driver);
        actions.moveToElement(el).build().perform();
    }

    public void ctrlA() {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys(String.valueOf('\u0061')).perform();
    }

    public void doubleClick(WebElement el) {
        Actions actions = new Actions(driver);
        actions.doubleClick(el).perform();
    }

    public void switchFrameByWebElement(WebElement frame) {
        driver.switchTo().frame(frame);
    }
    public void switchFrameById(String frameId) {
        WebElement frame = driver.findElement(By.id(frameId));
        driver.switchTo().frame(frame);
    }

    public void switchFrameByXpath(String frameXpath) {
        WebElement frame = driver.findElement(By.xpath(frameXpath));
        driver.switchTo().frame(frame);
    }
    public void quitFrame() {
        driver.switchTo().defaultContent();
    }

    public void accept() {
        driver.switchTo().alert().accept();
    }

    public void dismiss() {
        driver.switchTo().alert().dismiss();
    }

    public String getPopupText() {
        String message = driver.switchTo().alert().getText();
        return message;
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
        Date date = new Date();
        String date1 = dateFormat.format(date);
        return date1;
    }

    public String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String date1 = dateFormat.format(date);
        return date1;
    }

    public int stringInt(String s) {
        int i = Integer.parseInt(s);
        return i;
    }

    public String intString(int i) {
        String s = Integer.toString(i);
        return s;
    }

    public String doubleString(Double i) {
        String s = Double.toString(i);
        return s;
    }

    public double stringDouble(String str) {
        double d = Double.parseDouble(str);
        return d;
    }

    public List<String> convertListWebEtoString(List<WebElement> listWebE) {
        ArrayList<String> listString = new ArrayList<String>();
        for (int i = 0; i < listWebE.size(); i++) {
            String user = listWebE.get(i).getText();
            listString.add(user);
        }
        return listString;
    }

    public int getCurrentDay() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int day = calendar.get(Calendar.DATE);
        return day;
    }

    public String getExactTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyy");
        Date date = new Date();
        String date1 = dateFormat.format(date);
        return date1;
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void sleep(int milSeconds) throws InterruptedException {
        Thread.sleep(milSeconds);
    }

    public String getTitleChildWindow() {
        String titleChild = null;
        String parent = driver.getWindowHandle();
        Set<String> allHandles = driver.getWindowHandles();
        Iterator<String> allChilds = allHandles.iterator();
        while (allChilds.hasNext()) {
            String child_window = allChilds.next();
            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
                titleChild = driver.switchTo().window(child_window).getTitle();
                driver.close();
                driver.switchTo().window(parent);
                System.out.println(driver.getTitle());
            }
        }
        return titleChild;
    }

    public boolean isPopupDisplayed() {
        try {
            // Check if the popup is displayed by attempting to find its element
            //    List<WebElement> list = driver.findElement(By.xpath("//xpath-to-popup-element"));
            var popup = driver.findElement(By.xpath("//xpath-to-popup-element"));
            return popup.isDisplayed();
        } catch (NoSuchElementException e) {
            // Popup element not found, so it is not displayed
            return false;
        }
    }
}
