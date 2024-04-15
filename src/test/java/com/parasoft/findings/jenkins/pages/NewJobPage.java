package com.parasoft.findings.jenkins.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import com.parasoft.findings.jenkins.common.WebElementUtils;

public class NewJobPage extends PageObject {
    @FindBy(name = "name")
    private WebElement nameInput;

    @FindBy(className = "hudson_model_FreeStyleProject")
    private WebElement freestyleProjectText;

    @FindBy(xpath = "//*[@id='ok-button']")
    private WebElement oKButton;

    public NewJobPage(WebDriver driver) {
        super(driver);
    }

    public void setItemNameInput(String text) {
        WebElementUtils.waitAndType(getDriver(), nameInput, text);
    }

    public void clickFreestyleProjectText() {
        WebElementUtils.waitAndClick(getDriver(), freestyleProjectText);
    }

    public void clickOKButton() {
        WebElementUtils.waitAndClick(getDriver(), oKButton);
    }
}