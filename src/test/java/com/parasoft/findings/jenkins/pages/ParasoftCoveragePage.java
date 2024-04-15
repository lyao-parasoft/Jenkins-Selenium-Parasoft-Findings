package com.parasoft.findings.jenkins.pages;

import com.parasoft.findings.jenkins.common.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParasoftCoveragePage extends PageObject {

    @FindBy(id = "fileCoverage-tab")
    private WebElement overallCodeCoverageTab;

    @FindBy(xpath = "(//*[@id='fileCoverage']//child::span[@class='card-header-title'])[1]")
    private WebElement overallCodeCoverageTitleText;

    @FindBy(id = "absolute-coverage-table_info")
    private WebElement overallCodeCoverageTableFooterText;

    @FindBy(id = "changeCoverage-tab")
    private WebElement modifiedCodeCoverageTab;

    @FindBy(xpath = "(//*[@id='changeCoverage']//child::span[@class='card-header-title'])[1]")
    private WebElement modifiedCodeCoverageTitleText;

    @FindBy(id = "modified-lines-coverage-table_info")
    private WebElement modifiedCodeCoverageTableFooterText;

    public ParasoftCoveragePage(WebDriver driver) {
        super(driver);
    }

    public void clickOverallCodeCoverageTab() {
        WebElementUtils.waitAndClick(getDriver(), overallCodeCoverageTab);
    }

    public void clickModifiedCodeCoverageTab() {
        WebElementUtils.waitAndClick(getDriver(), modifiedCodeCoverageTab);
    }

    public String getOverallCodeCoverageTitleText() {
        WebElementUtils.scrollIntoView(getDriver(), overallCodeCoverageTab);
        WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), overallCodeCoverageTitleText, overallCodeCoverageTitleText.getText());
        return overallCodeCoverageTitleText.getText();
    }

    public String getOverallCodeCoverageTableFooterText() {
        WebElementUtils.scrollIntoView(getDriver(), overallCodeCoverageTableFooterText);
        WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), overallCodeCoverageTableFooterText, overallCodeCoverageTableFooterText.getText());
        return overallCodeCoverageTableFooterText.getText();
    }

    public String getModifiedCodeCoverageTitleText() {
        WebElementUtils.scrollIntoView(getDriver(), modifiedCodeCoverageTab);
        WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), modifiedCodeCoverageTitleText, modifiedCodeCoverageTitleText.getText());
        return modifiedCodeCoverageTitleText.getText();
    }

    public String getModifiedCodeCoverageTableFooterText() {
        WebElementUtils.scrollIntoView(getDriver(), modifiedCodeCoverageTableFooterText);
        WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), modifiedCodeCoverageTableFooterText, modifiedCodeCoverageTableFooterText.getText());
        return modifiedCodeCoverageTableFooterText.getText();
    }
}
