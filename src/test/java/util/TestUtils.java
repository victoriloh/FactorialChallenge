package util;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import enums.TargetTypeEnum;
import org.apache.commons.codec.binary.Base64;
import org.codehaus.plexus.util.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

// import verificationRequest.ForgotPassword;

// import static verificationRequest.DeferredPayment.alumniPaymentForDefferedRequest;
// import static verificationRequest.ProfileCreation.verifyProfileCreation;

/**
 * @author victor@fasterpay.com
 */
public class TestUtils extends TestBase {

    public static void assertSearchText(String type, String element, String value) {

        StringBuffer verificationErrors = new StringBuffer();
        TargetTypeEnum targetTypeEnum = TargetTypeEnum.valueOf(type);
        String ttype = null;

        switch (targetTypeEnum) {
            case ID:
                ttype = getDriver().findElement(By.id(element)).getText();
                break;
            case NAME:
                ttype = getDriver().findElement(By.name(element)).getText();
                break;
            case CSSSELECTOR:
                ttype = getDriver().findElement(By.cssSelector(element)).getText();
                break;

            case XPATH:
                ttype = getDriver().findElement(By.xpath(element)).getText();
                break;

            default:
                ttype = getDriver().findElement(By.id(element)).getText();
                break;
        }

        try {
            Assert.assertEquals(ttype, value);
            testInfo.get().log(Status.INFO, value + " found");
        } catch (Error e) {
            verificationErrors.append(e.toString());
            String verificationErrorString = verificationErrors.toString();
            testInfo.get().error(value + " not found");
            testInfo.get().error(verificationErrorString);
        }
    }


    public static String addScreenshot() {

        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File scrFile = ts.getScreenshotAs(OutputType.FILE);

        String encodedBase64 = null;
        FileInputStream fileInputStreamReader = null;
        try {
            fileInputStreamReader = new FileInputStream(scrFile);
            byte[] bytes = new byte[(int) scrFile.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "data:image/png;base64," + encodedBase64;
    }

    public static boolean isAlertPresents() {
        try {
            getDriver().switchTo().alert();
            return true;
        } // try
        catch (Exception e) {
            return false;
        } // catch
    }


    public static String CheckBrowser() {
        //Get Browser name and version.
        Capabilities caps = ((RemoteWebDriver) getDriver()).getCapabilities();
        String browserName = caps.getBrowserName();
        String browserVersion = caps.getVersion();

        //return browser name and version.
        String os = browserName + " " + browserVersion;

        return os;
    }


    public static ExtentTest elementIsPresent(String elementType, String locator, String message) {

        WebElement elementPresent = null;

        TargetTypeEnum targetTypeEnum = TargetTypeEnum.valueOf(elementType);
        switch (targetTypeEnum) {
            case ID:
                try {
                    elementPresent = getDriver().findElement(By.id(locator));
                } catch (Exception e) {
                }
                break;
            case NAME:
                try {
                    elementPresent = getDriver().findElement(By.name(locator));
                } catch (Exception e) {
                }
                break;
            case CSSSELECTOR:
                try {
                    elementPresent = getDriver().findElement(By.cssSelector(locator));
                } catch (Exception e) {
                }
                break;
            case XPATH:
                try {
                    elementPresent = getDriver().findElement(By.xpath(locator));
                } catch (Exception e) {
                }
                break;
            default:
                try {
                    elementPresent = getDriver().findElement(By.id(locator));
                } catch (Exception e) {
                }
        }
        if (elementPresent != null) {
            return testInfo.get().log(Status.INFO, message + " is present");

        } else {
            return testInfo.get().fail(message + " is not present");
        }
    }



    public static void testTitle(String phrase) {
        String word = "<b>" + phrase + "</b>";
        Markup w = MarkupHelper.createLabel(word, ExtentColor.BLUE);
        testInfo.get().info(w);
    }





}
