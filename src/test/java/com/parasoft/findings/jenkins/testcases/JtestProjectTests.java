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

public class JtestProjectTests extends AbstractProjectTests {
    private static final String JTEST_JOB_NAME = "cicd.findings.jtest.FunctionalTest";
    private static final String JTEST_PROJECT_GIT_URL = System.getProperty("jtest.project.git.url");

    public JtestProjectTests() {}

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
        createJob(JTEST_JOB_NAME);
        JobConfigurePage jobConfigurePage = new JobConfigurePage(driver);

        if (StringUtils.isBlank(JTEST_PROJECT_GIT_URL)) {
            throw new IllegalArgumentException("The system property 'jtest.project.git.url' must not be blank");
        }
        configureGitRepository(jobConfigurePage, JTEST_PROJECT_GIT_URL);
        setReplaceWorkspaceCommand(jobConfigurePage, SeleniumTestConstants.REPORT_PATH_UPDATER_COMMAND);
        configureStaticAnalysis(jobConfigurePage, SeleniumTestConstants.SETTINGS_FILE_PATH);
        configureCodeCoverageAnalysis(jobConfigurePage);

        jobConfigurePage.clickApplyButton();
        JobDetailPage jobDetailPage = buildJob(JTEST_JOB_NAME, "#1");
        changeBuildBranch(jobDetailPage, jobConfigurePage, "*/Luffy-test");
        configureUnitTest(jobConfigurePage, true);
        jobDetailPage = buildJob(JTEST_JOB_NAME, "#2");
        jumpToParasoftWarningsPage(jobDetailPage);

        // Check test information in Parasoft warnings page
        ParasoftWarningsPage parasoftWarningsPage = new ParasoftWarningsPage(driver);
        parasoftWarningsPage.clickPackagesTab();
        assertTrue(parasoftWarningsPage.getPackagesTableFooterText().contains("7 entries"));

        parasoftWarningsPage.clickFilesTab();
        assertTrue(parasoftWarningsPage.getFilesTableFooterText().contains("7 entries"));

        parasoftWarningsPage.clickCategoriesTab();
        assertTrue(parasoftWarningsPage.getCategoryTableFooterText().contains("41 entries"));

        parasoftWarningsPage.clickIssuesTab();
        assertTrue(parasoftWarningsPage.getIssuesTableFooterText().contains("454 entries"));

        parasoftWarningsPage.clickTypesTab();
        assertTrue(parasoftWarningsPage.getTypeTableFooterText().contains("83 entries"));

        parasoftWarningsPage.clickRuleTypeLink("APSC_DV.001460.SIO");
        assertEquals(parasoftWarningsPage.getRuleTitleText(), "Type APSC_DV.001460.SIO");

        parasoftWarningsPage.clickIssuesTab();
        parasoftWarningsPage.clickExpandIconButton();
        WebElement ruleDetailsText = driver.findElement(By.xpath("//*[@id='issues_wrapper']//tbody/tr[2]/td/strong[1]"));
        assertFalse(parasoftWarningsPage.getIssueDetailsText(ruleDetailsText).isEmpty());
        assertTrue(parasoftWarningsPage.getIssueDetailsText(ruleDetailsText).contains("APSC_DV.001460.SIO"));

        BuildStatusPage buildStatusPage = new BuildStatusPage(driver);
        buildStatusPage.clickParasoftCoverageLink();

        // Check test information in Parasoft coverage page
        ParasoftCoveragePage parasoftCoveragePage = new ParasoftCoveragePage(driver);
        parasoftCoveragePage.clickOverallCodeCoverageTab();
        assertEquals(SeleniumTestConstants.FILE_COVERAGE_TITLE_TEXT, parasoftCoveragePage.getOverallCodeCoverageTitleText());
        assertTrue(parasoftCoveragePage.getOverallCodeCoverageTableFooterText().contains("6 entries"));
        parasoftCoveragePage.clickModifiedCodeCoverageTab();
        assertEquals(SeleniumTestConstants.CHANGE_COVERAGE_TITLE_TEXT, parasoftCoveragePage.getModifiedCodeCoverageTitleText());
        assertTrue(parasoftCoveragePage.getModifiedCodeCoverageTableFooterText().contains("1 entries"));

        // Check test information in Test Result page
        buildStatusPage.clickTestResultLink();
        TestResultPage testResultPage = new TestResultPage(driver);
        assertEquals("0 failures", testResultPage.getTestResultFailuresText());
        assertEquals("8 tests", testResultPage.getTestResultTotalTestsText());

        // Check test results in status page
        buildStatusPage.clickStatusLink();
        StatusPage statusPage = new StatusPage(driver);
        assertTrue(statusPage.getTestResultText().contains("no failures"));

        // Check static analysis results in status page
        WebElementUtils.scrollToBottom(driver);
        assertEquals("Fixed issues: 6", statusPage.getStaticAnalysisFixedIssuesResultText());
        assertEquals("New issues: 31", statusPage.getStaticAnalysisNewIssuesResultText());
        assertEquals(SeleniumTestConstants.STATIC_ANALYSIS_QUALITY_GATE_RESULT_TEXT_ASSERTATION, statusPage.getStaticAnalysisQualityGateResultText());

        // Check code coverage results in status page
        assertEquals("Coverage: 64.44%", statusPage.getOverallProjectCoverageText());
        assertEquals("Lines covered: 29/45", statusPage.getOverallProjectLinesCoveredText());
        assertTrue(statusPage.getCodeCoverageQualityGateResultText().contains(SeleniumTestConstants.CODE_COVERAGE_QUALITY_GATE_RESULT_TEXT_KEY));
        assertTrue(statusPage.getCodeCoverageQualityGateResultText().contains(SeleniumTestConstants.CODE_COVERAGE_QUALITY_GATE_RESULT_TEXT_VALUE));
        assertEquals("100.00%", statusPage.getModifiedCodeCoverageResultText());

        deleteJob(JTEST_JOB_NAME);
    }

}
