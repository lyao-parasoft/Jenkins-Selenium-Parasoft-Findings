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

public class CpptestStdProjectTests extends AbstractProjectTests {
    private static final String CPPTEST_STD_JOB_NAME = "cicd.findings.cpptest.std.FunctionalTest";
    private static final String CPPTEST_STD_PROJECT_GIT_URL = System.getProperty("cpptest.std.project.git.url");

    public CpptestStdProjectTests() {}

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
        createJob(CPPTEST_STD_JOB_NAME);
        JobConfigurePage jobConfigurePage = new JobConfigurePage(driver);

        if (StringUtils.isBlank(CPPTEST_STD_PROJECT_GIT_URL)) {
            throw new IllegalArgumentException("The system property 'cpptest.std.project.git.url' must not be blank");
        }
        configureGitRepository(jobConfigurePage, CPPTEST_STD_PROJECT_GIT_URL);
        setReplaceWorkspaceCommand(jobConfigurePage, SeleniumTestConstants.REPORT_PATH_UPDATER_COMMAND);
        configureStaticAnalysis(jobConfigurePage, SeleniumTestConstants.SETTINGS_FILE_PATH);
        configureCodeCoverageAnalysis(jobConfigurePage);

        jobConfigurePage.clickApplyButton();
        JobDetailPage jobDetailPage = buildJob(CPPTEST_STD_JOB_NAME, "#1");
        changeBuildBranch(jobDetailPage, jobConfigurePage, "*/luffy-Jenkins");
        configureUnitTest(jobConfigurePage, true);
        jobDetailPage = buildJob(CPPTEST_STD_JOB_NAME, "#2");
        jumpToParasoftWarningsPage(jobDetailPage);

        // Check test information in warnings page
        ParasoftWarningsPage parasoftWarningsPage = new ParasoftWarningsPage(driver);
        parasoftWarningsPage.clickModulesTab();
        assertTrue(parasoftWarningsPage.getModulesTableFooterText().contains("5 entries"));

        parasoftWarningsPage.clickFoldersTab();
        assertTrue(parasoftWarningsPage.getFoldersTableFooterText().contains("6 entries"));

        parasoftWarningsPage.clickCategoriesTab();
        assertTrue(parasoftWarningsPage.getCategoryTableFooterText().contains("125 entries"));

        parasoftWarningsPage.clickFilesTab();
        assertTrue(parasoftWarningsPage.getFilesTableFooterText().contains("8 entries"));

        parasoftWarningsPage.clickIssuesTab();
        assertTrue(parasoftWarningsPage.getIssuesTableFooterText().contains("1,269 entries"));

        parasoftWarningsPage.clickTypesTab();
        assertTrue(parasoftWarningsPage.getTypeTableFooterText().contains("334 entries"));

        parasoftWarningsPage.clickRuleTypeLink("APSC_DV-003110-a");
        assertEquals(parasoftWarningsPage.getRuleTitleText(), "Type APSC_DV-003110-a");

        parasoftWarningsPage.clickIssuesTab();
        parasoftWarningsPage.clickExpandIconButton();
        WebElement ruleDetailsText = driver.findElement(By.xpath("//*[@id='issues_wrapper']//tbody/tr[2]/td/strong[1]"));
        assertFalse(parasoftWarningsPage.getIssueDetailsText(ruleDetailsText).isEmpty());
        assertTrue(parasoftWarningsPage.getIssueDetailsText(ruleDetailsText).contains("APSC_DV-003110-a"));

        BuildStatusPage buildStatusPage = new BuildStatusPage(driver);
        buildStatusPage.clickParasoftCoverageLink();

        // Check test information in Parasoft coverage page
        ParasoftCoveragePage parasoftCoveragePage = new ParasoftCoveragePage(driver);
        parasoftCoveragePage.clickOverallCodeCoverageTab();
        assertEquals(SeleniumTestConstants.FILE_COVERAGE_TITLE_TEXT, parasoftCoveragePage.getOverallCodeCoverageTitleText());
        assertTrue(parasoftCoveragePage.getOverallCodeCoverageTableFooterText().contains("38 entries"));
        parasoftCoveragePage.clickModifiedCodeCoverageTab();
        assertEquals(SeleniumTestConstants.CHANGE_COVERAGE_TITLE_TEXT, parasoftCoveragePage.getModifiedCodeCoverageTitleText());
        assertTrue(parasoftCoveragePage.getModifiedCodeCoverageTableFooterText().contains("2 entries"));

        // Check test information in Test Result page
        buildStatusPage.clickTestResultLink();
        TestResultPage testResultPage = new TestResultPage(driver);
        assertEquals("No tests", testResultPage.getTestResultTitleText());

        // Check test results in status page
        buildStatusPage.clickStatusLink();
        StatusPage statusPage = new StatusPage(driver);
        assertTrue(statusPage.getTestResultText().contains("no tests"));

        // Check static analysis results in status page
        WebElementUtils.scrollToBottom(driver);
        assertEquals("Fixed issues: 35", statusPage.getStaticAnalysisFixedIssuesResultText());
        assertEquals("New issues: 78", statusPage.getStaticAnalysisNewIssuesResultText());
        assertEquals(SeleniumTestConstants.STATIC_ANALYSIS_QUALITY_GATE_RESULT_TEXT_ASSERTATION, statusPage.getStaticAnalysisQualityGateResultText());

        // Check code coverage results in status page
        assertEquals("Coverage: 23.58%", statusPage.getOverallProjectCoverageText());
        assertEquals("Lines covered: 778/3299", statusPage.getOverallProjectLinesCoveredText());
        assertTrue(statusPage.getCodeCoverageQualityGateResultText().contains(SeleniumTestConstants.CODE_COVERAGE_QUALITY_GATE_RESULT_TEXT_KEY));
        assertTrue(statusPage.getCodeCoverageQualityGateResultText().contains(SeleniumTestConstants.CODE_COVERAGE_QUALITY_GATE_RESULT_TEXT_VALUE));
        assertEquals("100.00%", statusPage.getModifiedCodeCoverageResultText());

        deleteJob(CPPTEST_STD_JOB_NAME);
    }
}
