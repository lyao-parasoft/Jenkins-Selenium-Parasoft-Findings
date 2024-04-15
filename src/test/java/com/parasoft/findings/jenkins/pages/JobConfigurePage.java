package com.parasoft.findings.jenkins.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import com.parasoft.findings.jenkins.common.WebElementUtils;

public class JobConfigurePage extends PageObject {
    @FindBy(css = "button[data-section-id='source-code-management']")
    private WebElement sourceCodeManagementAnchor;

    @FindBy(xpath = "//label[text()='Git']")
    private WebElement gitRadioLabel;

    @FindBy(name = "_.url")
    private WebElement repositoryUrlInput;

    @FindBy(xpath = "//div[text()='Branches to build']")
    private WebElement BranchesToBuildLabel;

    @FindBy(xpath = "//input[@name='_.name'][@value='*/master']")
    private WebElement buildBranchInput;

    @FindBy(css = "button[data-section-id='build-steps']")
    private WebElement buildStepsAnchor;

    @FindBy(xpath = "//button[text()='Add build step']")
    private WebElement addBuildStepDropdown;

    @FindBy(xpath = "//*[@id='main-panel']//child::a[text()='Execute Windows batch command']")
    private WebElement executeWindowsBatchCommandOption;

    @FindBy(xpath = "//*[@name='builder'][1]//textarea[@name='command']")
    private WebElement firstCommandTextarea;

    @FindBy(xpath = "//*[@name='builder'][2]//textarea[@name='command']")
    private WebElement secondCommandTextarea;

    @FindBy(id = "post-build-actions")
    private WebElement postBuildActionsLabel;

    @FindBy(css = "button[data-section-id='post-build-actions']")
    private WebElement postBuildActionsAnchor;

    @FindBy(xpath = "//button[text()='Add post-build action']")
    private WebElement addPostBuildActionDropdown;

    @FindBy(xpath = "//div[text()='Quality gates']")
    private WebElement addQualityGateLabel;

    @FindBy(xpath = "//button[text()='Add Quality Gate']")
    private WebElement addQualityGateButton;

    @FindBy(css = "#qualityGates .last [name='_.type']")
    private WebElement coverageQualityGateTypeDropdown;

    @FindBy(linkText = "Record compiler warnings and static analysis results")
    private WebElement recordCompilerWarningsAndStaticAnalysisResultsOption;

    @FindBy(xpath = "(//*[@id='tools']//child::input[@name='_.pattern'])[2]")
    private WebElement staticAnalysisPatternInput;

    @FindBy(name = "_.localSettingsPath")
    private WebElement staticAnalysisLocalSettingsPathInput;

    @FindBy(xpath = "//div[@descriptorid='io.jenkins.plugins.analysis.core.steps.IssuesRecorder']//button[@class='jenkins-button advanced-button advancedButton']")
    private WebElement staticAnalysisAdvancedButton;

    @FindBy(xpath = "//div[@name='qualityGates']//input[@name='_.threshold']")
    private WebElement staticAnalysisQualityGateThresholdInput;

    @FindBy(linkText = "Record Parasoft code coverage results")
    private WebElement recordParasoftCodeCoverageResultsOption;

    @FindBy(xpath = "//div[@descriptorid='com.parasoft.findings.jenkins.coverage.ParasoftCoverageRecorder']//input[@name='_.pattern']")
    private WebElement coveragePatternInput;

    @FindBy(xpath = "//div[@name='coverageQualityGates']//input[@name='_.threshold']")
    private WebElement coverageQualityGateOverallThresholdInput;

    @FindBy(css = "#qualityGates .last [name='_.threshold']")
    private WebElement coverageQualityGateModifiedCodeLinesThresholdInput;

    @FindBy(css = "#tools .jenkins-select__input")
    private WebElement toolDropdown;

    @FindBy(linkText = "Publish xUnit test result report")
    private WebElement publishXUnitTestResultReportOption;

    @FindBy(xpath = "//*[@descriptorid='org.jenkinsci.plugins.xunit.XUnitPublisher']//div[4]//button[text()='Add']")
    private WebElement reportTypeAddDropdown;

    @FindBy(xpath = "//div[@descriptorid='org.jenkinsci.plugins.xunit.XUnitPublisher']/div/div[4]//input")
    private WebElement reportTypeFilterInput;

    @FindBy(linkText = "ParasoftAnalyzers-10.x")
    private WebElement parasoftAnalyzers10Option;

    @FindBy(xpath = "//*[@descriptorid='org.jenkinsci.plugins.xunit.XUnitPublisher']//input[@name='_.pattern']")
    private WebElement parasoftAnalyzerPatternInput;

    @FindBy(xpath = "//*[@descriptorid='org.jenkinsci.plugins.xunit.XUnitPublisher']//div[5]//button[text()='Add']")
    private WebElement unitTestThresholdsAddButton;

    @FindBy(linkText = "Failed Tests")
    private WebElement failedTestsOption;

    @FindBy(xpath = "//*[@descriptorid='org.jenkinsci.plugins.xunit.threshold.FailedThreshold']//input[@name='_.unstableThreshold']")
    private WebElement totalFailedTestsUnstableThresholdsInput;

    @FindBy(xpath = "//*[@descriptorid='org.jenkinsci.plugins.xunit.threshold.FailedThreshold']//input[@name='_.unstableNewThreshold']")
    private WebElement newFailedTestsUnstableThresholdsInput;

    @FindBy(xpath = "//*[@descriptorid='org.jenkinsci.plugins.xunit.XUnitPublisher']//a[text()='Passed Tests']")
    private WebElement passedTestsOption;

    @FindBy(xpath = "//*[@descriptorid='org.jenkinsci.plugins.xunit.threshold.PassedThreshold']//input[@name='_.unstableThreshold']")
    private WebElement totalPassedUnstableThresholdsInput;

    @FindBy(xpath = "//*[@descriptorid='org.jenkinsci.plugins.xunit.threshold.PassedThreshold']//input[@name='_.unstableNewThreshold']")
    private WebElement newPassedTestsUnstableThresholdsInput;

    @FindBy(xpath = "//*[@descriptorid='org.jenkinsci.plugins.xunit.XUnitPublisher']//a[text()='Skipped Tests']")
    private WebElement skippedTestsOption;

    @FindBy(xpath = "//*[@descriptorid='org.jenkinsci.plugins.xunit.threshold.SkippedThreshold']//input[@name='_.unstableThreshold']")
    private WebElement totalSkippedTestsUnstableThresholdsInput;

    @FindBy(xpath = "//*[@descriptorid='org.jenkinsci.plugins.xunit.threshold.SkippedThreshold']//input[@name='_.unstableNewThreshold']")
    private WebElement newSkippedTestsUnstableThresholdsInput;

    @FindBy(xpath = "//button[text()='Apply']")
    private WebElement applyButton;

    @FindBy(id = "notification-bar")
    private WebElement savedNotificationBar;

    public JobConfigurePage(WebDriver driver) {
        super(driver);
    }

    public void clickSourceCodeManagementAnchor() {
        WebElementUtils.waitAndClick(getDriver(), sourceCodeManagementAnchor);
    }

    public void clickBuildStepsAnchor() {
        WebElementUtils.waitAndClick(getDriver(), buildStepsAnchor);
    }

    public void clickPostBuildActionsAnchor() {
        WebElementUtils.waitAndClick(getDriver(), postBuildActionsAnchor);
    }

    public void clickGitRadio() {
        WebElementUtils.waitAndClick(getDriver(), gitRadioLabel);
    }

    public void setRepositoryUrlInput(String text) {
        WebElementUtils.waitAndType(getDriver(), repositoryUrlInput, text);
    }

    public void setBuildBranchInput(String text) {
        WebElementUtils.waitUntilClickable(getDriver(), buildBranchInput);
        buildBranchInput.clear();
        WebElementUtils.scrollIntoView(getDriver(), BranchesToBuildLabel);
        buildBranchInput.sendKeys(text);
    }

    public void clickAddBuildStepDropdown() {
        WebElementUtils.waitUntilClickable(getDriver(), addBuildStepDropdown);
        addBuildStepDropdown.click();
    }

    public void clickExecuteWindowsBatchCommandOption() {
        WebElementUtils.waitAndClick(getDriver(), executeWindowsBatchCommandOption);
    }

    public void clickAddPostBuildActionDropdown() {
        WebElementUtils.waitUntilClickable(getDriver(), addPostBuildActionDropdown);
        WebElementUtils.scrollIntoView(getDriver(), addPostBuildActionDropdown);
        addPostBuildActionDropdown.click();
    }

    public void clickRecordCompilerWarningsAndStaticAnalysisResultsOption() {
        WebElementUtils.waitAndClick(getDriver(), recordCompilerWarningsAndStaticAnalysisResultsOption);
    }

    public void selectToolDropdown(String text) {
        WebElementUtils.waitUntilClickable(getDriver(), toolDropdown);
        clickPostBuildActionsAnchor();
        Select dropdown = new Select(toolDropdown);
        dropdown.selectByVisibleText(text);
    }

    public void clickApplyButton() {
        WebElementUtils.waitUntilClickable(getDriver(), applyButton);
        WebElementUtils.waitUntilClickable(getDriver(), applyButton);
        WebElementUtils.clickElementUseJs(getDriver(), applyButton);
        WebElementUtils.waitUntilVisible(getDriver(), savedNotificationBar);
    }

    public void clickStaticAnalysisAdvancedButton() {
        WebElementUtils.waitUntilClickable(getDriver(), staticAnalysisAdvancedButton);
        WebElementUtils.scrollIntoView(getDriver(), staticAnalysisAdvancedButton);
        WebElementUtils.clickElementUseJs(getDriver(), staticAnalysisAdvancedButton);
    }

    public void setStaticAnalysisPatternInput(String text) {
        WebElementUtils.waitAndType(getDriver(), staticAnalysisPatternInput, text);
    }

    public void setStaticAnalysisLocalSettingsPathInput(String text) {
        WebElementUtils.waitAndType(getDriver(), staticAnalysisLocalSettingsPathInput, text);
    }

    public void clickAddQualityGateButton() {
        WebElementUtils.waitUntilClickable(getDriver(), addQualityGateLabel);
        WebElementUtils.scrollIntoView(getDriver(), addQualityGateLabel);
        addQualityGateButton.click();
    }

    public void setStaticAnalysisQualityGateThresholdInput(String text) {
        WebElementUtils.waitAndType(getDriver(), staticAnalysisQualityGateThresholdInput, text);
    }

    public void setFirstCommandInput(String text) {
        WebElementUtils.waitAndType(getDriver(), firstCommandTextarea, text);
    }

    public void setSecondCommandInput(String text) {
        WebElementUtils.waitAndType(getDriver(), secondCommandTextarea, text);
    }

    public void clickRecordParasoftCodeCoverageResultsOption() {
        WebElementUtils.waitAndClick(getDriver(), recordParasoftCodeCoverageResultsOption);
    }

    public void setCoveragePatternInput(String text) {
        WebElementUtils.waitAndType(getDriver(), coveragePatternInput, text);
    }

    public void selectCoverageQualityGateTypeDropdown(String text) {
        WebElementUtils.waitUntilClickable(getDriver(), coverageQualityGateTypeDropdown);
        WebElementUtils.scrollIntoView(getDriver(), coverageQualityGateTypeDropdown);
        Select dropdown = new Select(coverageQualityGateTypeDropdown);
        dropdown.selectByVisibleText(text);
    }

    public void setCoverageQualityGateOverallThresholdInput(String text) {
        WebElementUtils.waitAndType(getDriver(), coverageQualityGateOverallThresholdInput, text);
    }

    public void setCoverageQualityGateModifiedCodeLinesThresholdInput(String text) {
        WebElementUtils.waitAndType(getDriver(), coverageQualityGateModifiedCodeLinesThresholdInput, text);
    }

    public void clickPublishXUnitTestResultReportOption() {
        WebElementUtils.waitAndClick(getDriver(), publishXUnitTestResultReportOption);
    }

    public void clickReportTypeAddDropdown() {
        WebElementUtils.waitUntilClickable(getDriver(), reportTypeAddDropdown);
        WebElementUtils.scrollIntoView(getDriver(), postBuildActionsLabel);
        reportTypeAddDropdown.click();
    }

    public void setReportTypeFilterInput() {
        WebElementUtils.waitAndType(getDriver(), reportTypeFilterInput, "Parasoft");
    }

    public void clickParasoftAnalyzers10Option() {
        WebElementUtils.waitUntilClickable(getDriver(), parasoftAnalyzers10Option);
        WebElementUtils.clickElementUseJs(getDriver(), parasoftAnalyzers10Option);
    }

    public void setParasoftAnalyzerPatternInput(String text) {
        WebElementUtils.waitAndType(getDriver(), parasoftAnalyzerPatternInput, text);
    }

    public void clickUnitTestThresholdsAddButton() {
        WebElementUtils.scrollIntoView(getDriver(), reportTypeAddDropdown);
        WebElementUtils.waitUntilClickable(getDriver(), unitTestThresholdsAddButton);
        unitTestThresholdsAddButton.click();
    }

    public void clickUnitTestThresholdsAddButtonTwice() {
        // For the first click action, the button element is only selected, and no click action is made
        unitTestThresholdsAddButton.click();
        unitTestThresholdsAddButton.click();
    }

    public void clickFailedTestsOption() {
        WebElementUtils.waitAndClick(getDriver(), failedTestsOption);
    }

    public void setUnstableThresholdsInputForTotalFailedTests(String text) {
        WebElementUtils.waitAndType(getDriver(), totalFailedTestsUnstableThresholdsInput, text);
    }

    public void setUnstableThresholdsInputForNewFailedTests(String text) {
        WebElementUtils.waitAndType(getDriver(), newFailedTestsUnstableThresholdsInput, text);
    }

    public void clickPassedTestsOption() {
        WebElementUtils.waitAndClick(getDriver(), passedTestsOption);
    }

    public void setUnstableThresholdInputForTotalPassedTests(String text) {
        WebElementUtils.waitAndType(getDriver(), totalPassedUnstableThresholdsInput, text);
    }

    public void setUnstableThresholdInputForNewPassedTests(String text) {
        WebElementUtils.waitAndType(getDriver(), newPassedTestsUnstableThresholdsInput, text);
    }

    public void clickSkippedTestOption() {
        WebElementUtils.waitAndClick(getDriver(), skippedTestsOption);
    }

    public void setUnstableThresholdInputForTotalSkippedTests(String text) {
        WebElementUtils.waitAndType(getDriver(), totalSkippedTestsUnstableThresholdsInput, text);
    }

    public void setUnstableThresholdInputForNewSkippedTests(String text) {
        WebElementUtils.waitAndType(getDriver(), newSkippedTestsUnstableThresholdsInput, text);
    }
}