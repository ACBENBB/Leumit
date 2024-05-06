package Infra;

import Tests.BaseTest;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {


    static Properties prop = new Properties();

    static String projectPath = System.getProperty("user.dir");
    static String configPath = projectPath+"\\config.txt"; // path to config file

    public static void main(String[] args) {
    }

    // method that read properties and get data
    public static void readPropertiesFile(String taskNumber) {
        try {
            InputStream input = new FileInputStream(configPath);
            prop.load(input);
            BaseTest.driverPath = prop.getProperty("driverPath");
            BaseTest.driverName = prop.getProperty("driverName");
            BaseTest.url = getUrl(taskNumber);
            BaseTest.tableId = prop.getProperty("table");
            BaseTest.searchColumn = prop.getProperty("searchColumn");
            BaseTest.searchText = prop.getProperty("searchText");
            BaseTest.returnColumnText = prop.getProperty("returnColumnText");
            BaseTest.expectedText = prop.getProperty("expectedText");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUrl(String taskNumber) {
        try {
            InputStream input = new FileInputStream(configPath);
            prop.load(input);
            BaseTest.url = prop.getProperty(taskNumber+"-url");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BaseTest.url;
    }

}
