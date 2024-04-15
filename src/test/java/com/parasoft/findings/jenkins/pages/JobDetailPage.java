package com.parasoft.findings.jenkins.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.parasoft.findings.jenkins.common.WebElementUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JobDetailPage extends PageObject {

    private static final String BUILD_STATUS_TOOLTIP_ATTRIBUTE = "tooltip";
    private static final String BUILD_STATUS_TOOLTIP_ATTRIBUTE_VALUE_SUCCESS = "Success";
    private static final String BUILD_STATUS_TOOLTIP_ATTRIBUTE_VALUE_IN_PROGRESS = "In progress";
    private static final int BUILD_FINISHED_TIMEOUT_IN_SECONDS = 20 * 60 * 1000; // seconds

    @FindBy(linkText = "Build Now")
    private WebElement buildNowLink;

    @FindBy(linkText = "Configure")
    private WebElement configureLink;

    @FindBy(xpath = "//*[@id='tasks']//a/span[text()='Parasoft Warnings']")
    private WebElement parasoftWarningsLink;

    @FindBy(linkText = "Delete Project")
    private WebElement deleteProjectLink;

    @FindBy(linkText = "Status")
    private WebElement projectStatusLink;

    @FindBy(xpath = "//div[@class='build-icon'][1]/a")
    private WebElement buildIconLink;

    @FindBy(xpath = "//*[@id='buildHistory']//tr[2]/td//div[1]//a[@update-parent-class]")
    private WebElement buildNumberLink;

    private final By buildIconLinkLocator = By.xpath("//div[@class='build-icon'][1]/a");

    public JobDetailPage(WebDriver driver) {
        super(driver);
    }

    public void clickStatusLink() {
        WebElementUtils.waitAndClick(getDriver(), projectStatusLink);
    }

    public void clickBuildNowLink() {
        WebElementUtils.waitAndClick(getDriver(), buildNowLink);
    }

    public void clickConfigureLink() {
        WebElementUtils.waitAndClick(getDriver(), configureLink);
    }

    public void clickParasoftWarningsLink () {
        WebElementUtils.waitAndClick(getDriver(), parasoftWarningsLink);
    }

    public void clickDeleteProjectLink() {
        WebElementUtils.waitAndClick(getDriver(), deleteProjectLink);
    }

    public void waitBuildFinished(WebDriver driver, String buildNumberText) {
        WebElementUtils.waitUntilClickable(driver, buildIconLink);
        WebElementUtils.waitUntilTextToBePresentInElement(driver, buildNumberLink, buildNumberText);
        WebElementUtils.waitUntilAttributeToNotContains(driver, buildIconLinkLocator, "tooltip", "In progress", 20 * 60 * 1000);
        assertTrue(driver.findElement(buildIconLinkLocator).getAttribute("tooltip").contains("Success"));
    }
}