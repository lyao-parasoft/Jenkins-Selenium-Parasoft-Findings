package com.parasoft.findings.jenkins.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import com.parasoft.findings.jenkins.common.WebElementUtils;

public class DashboardPage extends PageObject {
    @FindBy(xpath = "//*[@id='tasks']//a")
    private WebElement newItemLink;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void clickNewItemLink() {
        WebElementUtils.waitAndClick(getDriver(), newItemLink);
    }
}