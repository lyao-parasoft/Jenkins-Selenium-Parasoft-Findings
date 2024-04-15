package com.parasoft.findings.jenkins.testcases;

import com.parasoft.findings.jenkins.common.SeleniumTestConstants;
import com.parasoft.findings.jenkins.common.WebDriverFactory;
import com.parasoft.findings.jenkins.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class CpptestProProjectTests extends AbstractProjectTests {
    private static final String CPPTEST_PRO_JOB_NAME = "cicd.findings.cpptest.pro.FunctionalTest";
    private static final String CPPTEST_PRO_PROJECT_GIT_URL = System.getProperty("cpptest.pro.project.git.url");

    public CpptestProProjectTests() {}

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
        createJob(CPPTEST_PRO_JOB_NAME);
        JobConfigurePage jobConfigurePage = new JobConfigurePage(driver);

        if (StringUtils.isBlank(CPPTEST_PRO_PROJECT_GIT_URL)) {
            throw new IllegalArgumentException("The system property 'cpptest.pro.project.git.url' must not be blank");
        }
        configureGitRepository(jobConfigurePage, CPPTEST_PRO_PROJECT_GIT_URL);
        setReplaceWorkspaceCommand(jobConfigurePage, SeleniumTestConstants.REPORT_PATH_UPDATER_COMMAND);
        configureStaticAnalysis(jobConfigurePage, SeleniumTestConstants.SETTINGS_FILE_PATH);

        configureUnitTest(jobConfigurePage, true);
        JobDetailPage jobDetailPage = buildJob(CPPTEST_PRO_JOB_NAME, "#1");
        jumpToParasoftWarningsPage(jobDetailPage);

        // Check test information in warnings page
        ParasoftWarningsPage parasoftWarningsPage = new ParasoftWarningsPage(driver);

        parasoftWarningsPage.clickFoldersTab();
        assertTrue(parasoftWarningsPage.getPackagesTableFooterText().contains("3 entries"));

        parasoftWarningsPage.clickCategoriesTab();
        assertTrue(parasoftWarningsPage.getCategoryTableFooterText().contains("194 entries"));

        parasoftWarningsPage.clickFilesTab();
        assertTrue(parasoftWarningsPage.getFilesTableFooterText().contains("6 entries"));

        parasoftWarningsPage.clickIssuesTab();
        assertTrue(parasoftWarningsPage.getIssuesTableFooterText().contains("3,216 entries"));

        parasoftWarningsPage.clickTypesTab();
        assertTrue(parasoftWarningsPage.getTypeTableFooterText().contains("508 entries"));

        parasoftWarningsPage.clickRuleTypeLink("APSC_DV-001290-b");
        assertEquals(parasoftWarningsPage.getRuleTitleText(), "Type APSC_DV-001290-b");

        parasoftWarningsPage.clickIssuesTab();
        parasoftWarningsPage.clickExpandIconButton();
        WebElement ruleDetailsText = driver.findElement(By.xpath("//*[@id='issues_wrapper']//tbody/tr[2]/td/strong[1]"));
        assertFalse(parasoftWarningsPage.getIssueDetailsText(ruleDetailsText).isEmpty());
        assertTrue(parasoftWarningsPage.getIssueDetailsText(ruleDetailsText).contains("APSC_DV-001290-b"));

        // Check test information in Test Result page
        BuildStatusPage buildPage = new BuildStatusPage(driver);
        buildPage.clickTestResultLink();
        TestResultPage testResultPage = new TestResultPage(driver);
        assertEquals("162 tests", testResultPage.getTestResultTotalTestsText());

        // Check test results in status page
        buildPage.clickStatusLink();
        StatusPage statusPage = new StatusPage(driver);
        assertNotEquals("24 failures", statusPage.getTestResultText());

        deleteJob(CPPTEST_PRO_JOB_NAME);
    }
}
