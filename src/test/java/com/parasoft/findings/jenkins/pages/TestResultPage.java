package com.parasoft.findings.jenkins.pages;

import com.parasoft.findings.jenkins.common.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestResultPage extends PageObject {

    @FindBy(xpath = "//*[@id='main-panel']/div[1]")
    private WebElement testResultTitleText;

    @FindBy(xpath = "//*[@id='main-panel']/div[1]/div[1]")
    private WebElement testResultFailuresText;

    @FindBy(xpath = "//*[@id='main-panel']/div[1]/div[3]")
    private WebElement testResultTotalTestsText;

    public TestResultPage(WebDriver driver) {
        super(driver);
    }

    public String getTestResultTitleText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), testResultTitleText, testResultTitleText.getText());
    }

    public String getTestResultFailuresText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), testResultFailuresText, testResultFailuresText.getText());
    }

    public String getTestResultTotalTestsText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), testResultTotalTestsText, testResultTotalTestsText.getText());
    }
}
