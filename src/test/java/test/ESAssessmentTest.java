package test;

import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import source.Driver;

public class ESAssessmentTest {
    private WebDriver driver;
    private static final By addElementButton = By.xpath("//button[contains(text(),'Add Element')]");
    private static final By deleteButton = By.xpath("//button[contains(text(),'Delete')]");
    private static final String url = "https://the-internet.herokuapp.com/add_remove_elements/";
    private static final int expectedNumberOfElements = 5;

    @BeforeMethod
    public void driverInitializer() {
        driver = Driver.getDriver(true, false);
    }

    @Feature("SDET Assessment")
    @Test
    public void theScenario() {
        int actualNumberOfElements;

        driver.get(url);
        for (int i = 0; i < expectedNumberOfElements; i++) {
            driver.findElement(addElementButton).click();
        }
        actualNumberOfElements = driver.findElements(deleteButton).size();
        Assert.assertEquals(expectedNumberOfElements, actualNumberOfElements,
                "The actual number of \"Delete button\" elements on the page is not equal" +
                " to expected number of elements (" + expectedNumberOfElements + ")");
    }

    @AfterMethod
    public void driverQuit() {
        Driver.tearDown();
    }
}
