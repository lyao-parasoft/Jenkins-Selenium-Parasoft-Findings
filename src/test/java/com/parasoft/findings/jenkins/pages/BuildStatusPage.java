package com.parasoft.findings.jenkins.pages;

import com.parasoft.findings.jenkins.common.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BuildStatusPage extends PageObject {

    @FindBy(linkText = "Status")
    private WebElement statusLink;

    @FindBy(linkText = "Parasoft Coverage")
    private WebElement parasoftCoverageLink;

    @FindBy(linkText = "Test Result")
    private WebElement testResultLink;

    public BuildStatusPage(WebDriver driver) {
        super(driver);
    }

    public void clickStatusLink() {
        WebElementUtils.waitAndClick(getDriver(), statusLink);
    }

    public void clickParasoftCoverageLink() {
        WebElementUtils.waitAndClick(getDriver(), parasoftCoverageLink);
    }

    public void clickTestResultLink() {
        WebElementUtils.waitAndClick(getDriver(), testResultLink);
    }
}
