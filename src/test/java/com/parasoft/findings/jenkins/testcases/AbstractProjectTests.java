package com.parasoft.findings.jenkins.testcases;

import com.parasoft.findings.jenkins.common.WebElementUtils;
import com.parasoft.findings.jenkins.common.SeleniumTestConstants;
import com.parasoft.findings.jenkins.pages.JobConfigurePage;
import com.parasoft.findings.jenkins.pages.DashboardPage;
import com.parasoft.findings.jenkins.pages.JobDetailPage;
import com.parasoft.findings.jenkins.pages.NewJobPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

public abstract class AbstractProjectTests {
    private static final String PARASOFT_FINDINGS_PLUGIN_DROPDOWN_OPTION = "Parasoft Findings";
    protected WebDriver driver;

    public AbstractProjectTests() {}

    public void createJob(String jobName) {
        driver.get(SeleniumTestConstants.BASE_URL);

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickNewItemLink();

        NewJobPage newJobPage = new NewJobPage(driver);
        newJobPage.setItemNameInput(jobName);
        newJobPage.clickFreestyleProjectText();
        newJobPage.clickOKButton();
    }

    public void configureGitRepository(JobConfigurePage configPage, String projectGitUrl) {
        configPage.clickSourceCodeManagementAnchor();
        configPage.clickGitRadio();
        configPage.setRepositoryUrlInput(projectGitUrl);
        configPage.clickApplyButton();
    }

    public JobDetailPage buildJob(String jobName, String expectedBuildNumber) {
        driver.get(SeleniumTestConstants.BASE_URL + "/job/" + jobName + "/");
        JobDetailPage jobDetailPage = new JobDetailPage(driver);
        jobDetailPage.clickBuildNowLink();
        jobDetailPage.waitBuildFinished(driver, expectedBuildNumber);
        return jobDetailPage;
    }

    public void changeBuildBranch(JobDetailPage jobDetailPage, JobConfigurePage configPage, String branchName) {
        jobDetailPage.clickConfigureLink();
        configPage.clickSourceCodeManagementAnchor();
        configPage.setBuildBranchInput(branchName);
        configPage.clickApplyButton();
    }

    public void configureStaticAnalysis(JobConfigurePage configPage, String localSettingsText) throws InterruptedException {
        // Configure Parasoft static analysis
        configPage.clickPostBuildActionsAnchor();
        configPage.clickAddPostBuildActionDropdown();
        configPage.clickRecordCompilerWarningsAndStaticAnalysisResultsOption();
        Thread.sleep(5000);
        configPage.selectToolDropdown(PARASOFT_FINDINGS_PLUGIN_DROPDOWN_OPTION);
        configPage.setStaticAnalysisPatternInput(SeleniumTestConstants.STATIC_ANALYSIS_REPORT_PATH);
        configPage.setStaticAnalysisLocalSettingsPathInput(localSettingsText);
        // Configure static analysis quality gate
        configPage.clickStaticAnalysisAdvancedButton();
        configPage.clickAddQualityGateButton();
        configPage.setStaticAnalysisQualityGateThresholdInput("4000");
        configPage.clickApplyButton();
    }

    public void configureCodeCoverageAnalysis(JobConfigurePage configPage) {
        // Configure code coverage analysis
        configPage.clickAddPostBuildActionDropdown();
        configPage.clickRecordParasoftCodeCoverageResultsOption();
        configPage.setCoveragePatternInput(SeleniumTestConstants.COVERAGE_REPORT_PATH);
        configPage.clickPostBuildActionsAnchor();
        // Configure code coverage quality gate
        configPage.clickAddQualityGateButton();
        configPage.setCoverageQualityGateOverallThresholdInput("20");
        configPage.clickAddQualityGateButton();
        configPage.selectCoverageQualityGateTypeDropdown("Modified code lines");
        configPage.setCoverageQualityGateModifiedCodeLinesThresholdInput("10");
        configPage.clickApplyButton();
    }

    public void configureUnitTest(JobConfigurePage configPage, boolean isSetQualityGate) {
        configPage.clickAddPostBuildActionDropdown();
        configPage.clickPublishXUnitTestResultReportOption();
        configPage.clickPostBuildActionsAnchor();
        // Configure report type
        configPage.clickReportTypeAddDropdown();
        configPage.setReportTypeFilterInput();
        configPage.clickParasoftAnalyzers10Option();
        configPage.setParasoftAnalyzerPatternInput(SeleniumTestConstants.UNIT_TEST_REPORT_PATH);
        // Configure quality gate
        if (isSetQualityGate) {
            configPage.clickUnitTestThresholdsAddButton();
            configPage.clickFailedTestsOption();
            configPage.setUnstableThresholdsInputForTotalFailedTests("40");
            configPage.setUnstableThresholdsInputForNewFailedTests("40");
            configPage.clickUnitTestThresholdsAddButtonTwice();
            configPage.clickPassedTestsOption();
            configPage.setUnstableThresholdInputForTotalPassedTests("0");
            configPage.setUnstableThresholdInputForNewPassedTests("0");
            configPage.clickUnitTestThresholdsAddButtonTwice();
            configPage.clickSkippedTestOption();
            configPage.setUnstableThresholdInputForTotalSkippedTests("10");
            configPage.setUnstableThresholdInputForNewSkippedTests("10");
        }
        configPage.clickApplyButton();
    }

    public void jumpToParasoftWarningsPage(JobDetailPage jobDetailPage) {
        jobDetailPage.clickStatusLink();
        jobDetailPage.clickParasoftWarningsLink();
    }





    public void deleteJob(String jobName) {
        WebDriverWait wait = new WebDriverWait(driver, WebElementUtils.DEFAULT_WAIT_FOR_ELEMENT);
        String currentUrl = driver.getCurrentUrl();
        driver.get(SeleniumTestConstants.BASE_URL + "/job/" + jobName + "/");

        // If failed in config page, there is an alert when return to other page
        if (currentUrl.contains("/configure")) {
            Alert configurationAlert = driver.switchTo().alert();
            wait.until(alertIsPresent());
            configurationAlert.accept();
        }

        new JobDetailPage(driver).clickDeleteProjectLink();
        wait.until(alertIsPresent());
        Alert projectDeleteAlert = driver.switchTo().alert();
        assertTrue(projectDeleteAlert.getText().contains(jobName));
        projectDeleteAlert.accept();

        // Make sure the project is deleted
        assertEquals(driver.getCurrentUrl(), SeleniumTestConstants.BASE_URL);
        Exception exception = null;
        try {
            driver.findElement(By.linkText(jobName));
        } catch (Exception e){
            exception = e;
        } finally {
            assertNotNull(exception);
        }
    }

    public void setReplaceWorkspaceCommand(JobConfigurePage configPage, String generateUnitTestCommand) {
        configPage.clickBuildStepsAnchor();
        configPage.clickAddBuildStepDropdown();
        configPage.clickExecuteWindowsBatchCommandOption();
        configPage.setFirstCommandInput(generateUnitTestCommand);
        configPage.clickApplyButton();
    }
}
