package com.parasoft.findings.jenkins.pages;

import com.parasoft.findings.jenkins.common.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StatusPage extends PageObject {

    @FindBy(xpath = "//*[@id='parasoft-findings-details']/li[1]")
    private WebElement staticAnalysisFixedIssuesResultText;

    @FindBy(xpath = "//*[@id='parasoft-findings-details']/li[2]")
    private WebElement staticAnalysisNewIssuesResultText;

    @FindBy(xpath = "//*[@id='parasoft-findings-details']/li[4]")
    private WebElement staticAnalysisQualityGateResultText;

    @FindBy(xpath = "//*[@class='PROJECT-summary']/ul/li[1]")
    private WebElement overallProjectCoverageText;

    @FindBy(xpath = "//*[@class='PROJECT-summary']/ul/li[2]")
    private WebElement overallProjectLinesCoveredText;

    @FindBy(xpath = "//*[@id='coverage-summary-parasoft-coverage']/ul/li[2]")
    private WebElement codeCoverageQualityGateResultText;

    @FindBy(xpath = "//*[@id='coverage-summary-parasoft-coverage']/ul/table/tbody/tr[2]/td[4]")
    private WebElement modifiedCodeCoverageResultText;

    @FindBy(xpath = "//*[@id='main-panel']/table/tbody/tr[5]/td[2]")
    private WebElement testResultText;

    public StatusPage(WebDriver driver) {
        super(driver);
    }

    public String getStaticAnalysisQualityGateResultText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), staticAnalysisQualityGateResultText, staticAnalysisQualityGateResultText.getText());
    }

    public String getStaticAnalysisFixedIssuesResultText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), staticAnalysisFixedIssuesResultText, staticAnalysisFixedIssuesResultText.getText());
    }

    public String getStaticAnalysisNewIssuesResultText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), staticAnalysisNewIssuesResultText, staticAnalysisNewIssuesResultText.getText());
    }

    public String getOverallProjectCoverageText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), overallProjectCoverageText, overallProjectCoverageText.getText());
    }

    public String getOverallProjectLinesCoveredText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), overallProjectLinesCoveredText, overallProjectLinesCoveredText.getText());
    }

    public String getCodeCoverageQualityGateResultText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), codeCoverageQualityGateResultText, codeCoverageQualityGateResultText.getText());
    }

    public String getModifiedCodeCoverageResultText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), modifiedCodeCoverageResultText, modifiedCodeCoverageResultText.getText());
    }

    public String getTestResultText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), testResultText, testResultText.getText());
    }
}
