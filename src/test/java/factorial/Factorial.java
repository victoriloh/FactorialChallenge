package factorial;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.TestBase;
import util.TestUtils;

public class Factorial extends TestBase {

    @Parameters("testEnv")
    @Test
    public void factorialNumber(String testEnv) throws Exception {
        TestUtils.testTitle("Launch the Url");
        TestUtils.assertSearchText("XPATH","//h1","The greatest factorial calculator!");
        TestUtils.elementIsPresent("ID","getFactorial","Calculate Button");
        TestUtils.testTitle("Attempt to calculate factorial of an alphabet");
        getDriver().findElement(By.id("number")).sendKeys("TEST");
        getDriver().findElement(By.id("getFactorial")).click();
        Thread.sleep(500);
        TestUtils.assertSearchText("ID","resultDiv","Please enter an integer");
        TestUtils.testTitle("Attempt to calculate factorial of a large number");
        getDriver().findElement(By.id("number")).clear();
        getDriver().findElement(By.id("number")).sendKeys("500");
        getDriver().findElement(By.id("getFactorial")).click();
        Thread.sleep(500);
        String errNumber = getDriver().findElement(By.id("resultDiv")).getText();
        TestUtils.assertSearchText("ID","resultDiv",errNumber);
        TestUtils.testTitle("Attempt to calculate factorial of a Decimal Number");
        getDriver().findElement(By.id("number")).clear();
        getDriver().findElement(By.id("number")).sendKeys("0.1");
        getDriver().findElement(By.id("getFactorial")).click();
        Thread.sleep(500);
        TestUtils.assertSearchText("ID","resultDiv","Please enter an integer");
        TestUtils.testTitle("Calculate the factorial of 7");
        getDriver().findElement(By.id("number")).clear();
        getDriver().findElement(By.id("number")).sendKeys("7");
        getDriver().findElement(By.id("getFactorial")).click();
        Thread.sleep(500);
        String result = getDriver().findElement(By.id("resultDiv")).getText();
        TestUtils.assertSearchText("ID","resultDiv",result);
    }
}
