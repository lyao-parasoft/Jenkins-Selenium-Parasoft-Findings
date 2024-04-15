package com.parasoft.findings.jenkins.common;

import org.junit.platform.commons.util.StringUtils;

public class SeleniumTestConstants {
    public static final String BASE_URL = StringUtils.isBlank(System.getProperty("base.url")) ?
            "http://localhost:8080/jenkins/" : System.getProperty("base.url");
    public static final String SETTINGS_FILE_PATH = System.getProperty("settings.path");

    // Commands to replace ${jenkins_workspace} with the value of %WORKSPACE% to mock reports generated in current workspace
    public static final String REPORT_PATH_UPDATER_COMMAND = "@echo off\n"
            + "SETLOCAL EnableDelayedExpansion\n"
            + "powershell -Command \"(Get-Content 'reports/static/report.xml') -replace '\\$\\{jenkins_workspace\\}', ('file:/' + $Env:WORKSPACE.replace('\\', '/')) | Set-Content 'reports/static/report.xml' -Encoding UTF8\"\n"
            + "powershell -Command \"(Get-Content 'reports/unit/coverage.xml') -replace '\\$\\{jenkins_workspace\\}', ('file:/' + $Env:WORKSPACE.replace('\\', '/')) | Set-Content 'reports/unit/coverage.xml' -Encoding UTF8\"\n"
            + "powershell -Command \"(Get-Content 'reports/unit/report.xml') -replace '\\$\\{jenkins_workspace\\}', ('file:/' + $Env:WORKSPACE.replace('\', '/')) | Set-Content 'reports/unit/report.xml' -Encoding UTF8\"\n"
            + "ENDLOCAL";

    // Static Analysis
    public static final String STATIC_ANALYSIS_REPORT_PATH = "reports/static/report.xml";
    public static final String STATIC_ANALYSIS_QUALITY_GATE_RESULT_TEXT_ASSERTATION = "Quality gate: Success";

    // Tests
    public static final String UNIT_TEST_REPORT_PATH = "reports/unit/report.xml";

    // Coverage
    public static final String COVERAGE_REPORT_PATH = "reports/unit/coverage.xml";
    public static final String FILE_COVERAGE_TITLE_TEXT = "Coverage of all files";
    public static final String CHANGE_COVERAGE_TITLE_TEXT = "Coverage of modified lines";
    public static final String CODE_COVERAGE_QUALITY_GATE_RESULT_TEXT_KEY = "Quality gate(s)";
    public static final String CODE_COVERAGE_QUALITY_GATE_RESULT_TEXT_VALUE = "Success";

}
