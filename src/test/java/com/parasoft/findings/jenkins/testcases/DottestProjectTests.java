package com.parasoft.findings.jenkins.testcases;

import com.parasoft.findings.jenkins.common.*;
import com.parasoft.findings.jenkins.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class DottestProjectTests extends AbstractProjectTests {
    private static final String DOTTEST_JOB_NAME = "dottest.FunctionalTest";
    private static final String DOTTEST_PROJECT_GIT_URL = System.getProperty("dottest.project.git.url");

    public DottestProjectTests() {}

    @BeforeEach
    public void beforeTest() {
        driver = WebDriverFactory.getChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testParasoftFindingsPlugin() throws InterruptedException {
        createJob(DOTTEST_JOB_NAME);
        JobConfigurePage jobConfigurePage = new JobConfigurePage(driver);

        if (StringUtils.isBlank(DOTTEST_PROJECT_GIT_URL)) {
            throw new IllegalArgumentException("The system property 'dottest.project.git.url' must not be blank");
        }
        configureGitRepository(jobConfigurePage, DOTTEST_PROJECT_GIT_URL);
        setReplaceWorkspaceCommand(jobConfigurePage, SeleniumTestConstants.REPORT_PATH_UPDATER_COMMAND);
        configureStaticAnalysis(jobConfigurePage, SeleniumTestConstants.SETTINGS_FILE_PATH);
        configureCodeCoverageAnalysis(jobConfigurePage);

        JobDetailPage jobDetailPage = buildJob(DOTTEST_JOB_NAME, "#1");
        changeBuildBranch(jobDetailPage, jobConfigurePage, "*/selenic_test_modified_coverage");
        configureUnitTest(jobConfigurePage, true);
        jobDetailPage = buildJob(DOTTEST_JOB_NAME, "#2");
        jumpToParasoftWarningsPage(jobDetailPage);

        // Check test information in warnings page
        ParasoftWarningsPage parasoftWarningsPage = new ParasoftWarningsPage(driver);
        parasoftWarningsPage.clickNamespacesTab();
        assertTrue(parasoftWarningsPage.getNamespacesTableFooterText().contains("3 entries"));

        parasoftWarningsPage.clickFilesTab();
        assertTrue(parasoftWarningsPage.getFilesTableFooterText().contains("18 entries"));

        parasoftWarningsPage.clickCategoriesTab();
        assertTrue(parasoftWarningsPage.getCategoryTableFooterText().contains("35 entries"));

        parasoftWarningsPage.clickIssuesTab();
        assertTrue(parasoftWarningsPage.getIssuesTableFooterText().contains("1,536 entries"));

        parasoftWarningsPage.clickTypesTab();
        assertTrue(parasoftWarningsPage.getTypeTableFooterText().contains("100 entries"));

        parasoftWarningsPage.clickRuleTypeLink("BRM.CMT.MSC");
        assertEquals(parasoftWarningsPage.getRuleTitleText(), "Type BRM.CMT.MSC");

        parasoftWarningsPage.clickIssuesTab();
        parasoftWarningsPage.clickExpandIconButton();
        WebElement ruleDetailsText = driver.findElement(By.xpath("//*[@id='issues_wrapper']//tr[2]/td/div[1]/div[2]/h1"));
        assertFalse(parasoftWarningsPage.getIssueDetailsText(ruleDetailsText).isEmpty());
        assertTrue(parasoftWarningsPage.getIssueDetailsText(ruleDetailsText).contains("BRM.CMT.MSC"));

        BuildStatusPage buildStatusPage = new BuildStatusPage(driver);
        buildStatusPage.clickParasoftCoverageLink();

        // Check test information in Parasoft coverage page
        ParasoftCoveragePage parasoftCoveragePage = new ParasoftCoveragePage(driver);
        parasoftCoveragePage.clickOverallCodeCoverageTab();
        assertEquals(SeleniumTestConstants.FILE_COVERAGE_TITLE_TEXT, parasoftCoveragePage.getOverallCodeCoverageTitleText());
        assertTrue(parasoftCoveragePage.getOverallCodeCoverageTableFooterText().contains("27 entries"));
        parasoftCoveragePage.clickModifiedCodeCoverageTab();
        assertEquals(SeleniumTestConstants.CHANGE_COVERAGE_TITLE_TEXT, parasoftCoveragePage.getModifiedCodeCoverageTitleText());
        assertTrue(parasoftCoveragePage.getModifiedCodeCoverageTableFooterText().contains("4 entries"));

        // Check test information in Test Result page
        buildStatusPage.clickTestResultLink();
        TestResultPage testResultPage = new TestResultPage(driver);
        assertEquals("11 failures", testResultPage.getTestResultFailuresText());
        assertEquals("30 tests", testResultPage.getTestResultTotalTestsText());

        // Check test results in status page
        buildStatusPage.clickStatusLink();
        StatusPage statusPage = new StatusPage(driver);
        assertTrue(statusPage.getTestResultText().contains("11 failures"));

        // Check static analysis results in status page
        WebElementUtils.scrollToBottom(driver);
        assertEquals("Fixed issues: 111", statusPage.getStaticAnalysisFixedIssuesResultText());
        assertEquals("New issues: 95", statusPage.getStaticAnalysisNewIssuesResultText());
        assertEquals(SeleniumTestConstants.STATIC_ANALYSIS_QUALITY_GATE_RESULT_TEXT_ASSERTATION, statusPage.getStaticAnalysisQualityGateResultText());

        // Check code coverage results in status page
        assertEquals("Coverage: 36.14%", statusPage.getOverallProjectCoverageText());
        assertEquals("Lines covered: 232/642", statusPage.getOverallProjectLinesCoveredText());
        assertTrue(statusPage.getCodeCoverageQualityGateResultText().contains(SeleniumTestConstants.CODE_COVERAGE_QUALITY_GATE_RESULT_TEXT_KEY));
        assertTrue(statusPage.getCodeCoverageQualityGateResultText().contains(SeleniumTestConstants.CODE_COVERAGE_QUALITY_GATE_RESULT_TEXT_VALUE));
        assertEquals("55.56%", statusPage.getModifiedCodeCoverageResultText());

        deleteJob(DOTTEST_JOB_NAME);
    }
}
