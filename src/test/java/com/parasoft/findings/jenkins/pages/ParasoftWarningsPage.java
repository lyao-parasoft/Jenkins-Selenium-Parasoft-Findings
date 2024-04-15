package com.parasoft.findings.jenkins.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import com.parasoft.findings.jenkins.common.WebElementUtils;

public class ParasoftWarningsPage extends PageObject {
    @FindBy(id = "moduleName_info")
    private WebElement modulesTableFooterText;

    @FindBy(id = "folder_info")
    private WebElement foldersTableFooterText;

    @FindBy(id = "packageName_info")
    private WebElement packagesTableFooterText;

    @FindBy(id = "fileName_info")
    private WebElement filesTableFooterText;

    @FindBy(id = "category_info")
    private WebElement categoryTableFooterText;

    @FindBy(id = "type_info")
    private WebElement typeTableFooterText;

    @FindBy(id = "issues_info")
    private WebElement issuesTableFooterText;

    @FindBy(linkText = "Modules")
    private WebElement modulesTab;

    @FindBy(linkText = "Namespaces")
    private WebElement namespacesTab;

    @FindBy(linkText = "Folders")
    private WebElement foldersTab;

    @FindBy(linkText = "Packages")
    private WebElement packagesTab;

    @FindBy(linkText = "Files")
    private WebElement filesTab;

    @FindBy(linkText = "Categories")
    private WebElement categoriesTab;

    @FindBy(linkText = "Types")
    private WebElement typesTab;

    @FindBy(linkText = "Issues")
    private WebElement issuesTab;

    @FindBy(id = "issues_paginate")
    private WebElement issuesPaginate;

    @FindBy(xpath = "//*[@id='issues']//tr[1]/td[1]/div")
    private WebElement expandIconButton;

    @FindBy(css = ".container-fluid h1")
    private WebElement ruleTitleText;

    public ParasoftWarningsPage(WebDriver driver) {
        super(driver);
    }

    public void clickModulesTab() {
        WebElementUtils.scrollIntoView(getDriver(), modulesTab);
        WebElementUtils.waitAndClick(getDriver(), modulesTab);
    }

    public String getModulesTableFooterText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), modulesTableFooterText, modulesTableFooterText.getText());
    }

    public void clickNamespacesTab() {
        WebElementUtils.scrollIntoView(getDriver(), namespacesTab);
        WebElementUtils.waitAndClick(getDriver(), namespacesTab);
    }

    public String getNamespacesTableFooterText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), packagesTableFooterText, packagesTableFooterText.getText());
    }

    public void clickFoldersTab() {
        WebElementUtils.scrollIntoView(getDriver(), foldersTab);
        WebElementUtils.waitAndClick(getDriver(), foldersTab);
    }

    public String getFoldersTableFooterText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), foldersTableFooterText, foldersTableFooterText.getText());
    }

    public void clickPackagesTab() {
        WebElementUtils.scrollIntoView(getDriver(), packagesTab);
        WebElementUtils.waitAndClick(getDriver(), packagesTab);
    }

    public String getPackagesTableFooterText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), packagesTableFooterText, packagesTableFooterText.getText());
    }

    public void clickFilesTab() {
        WebElementUtils.waitAndClick(getDriver(), filesTab);
    }

    public String getFilesTableFooterText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), filesTableFooterText, filesTableFooterText.getText());
    }

    public void clickCategoriesTab() {
        WebElementUtils.waitAndClick(getDriver(), categoriesTab);
    }

    public String getCategoryTableFooterText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), categoryTableFooterText, categoryTableFooterText.getText());
    }

    public void clickTypesTab() {
        WebElementUtils.waitAndClick(getDriver(), typesTab);
    }

    public String getTypeTableFooterText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), typeTableFooterText, typeTableFooterText.getText());
    }

    public void clickIssuesTab() {
        WebElementUtils.scrollIntoView(getDriver(), issuesTab);
        WebElementUtils.waitAndClick(getDriver(), issuesTab);
        WebElementUtils.waitUntilClickable(getDriver(), issuesPaginate);
    }

    public String getIssuesTableFooterText() {
        return WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), issuesTableFooterText, issuesTableFooterText.getText());
    }

    public void clickRuleTypeLink(String ruleType) {
        WebElement ruleTypeLink = getDriver().findElement(By.xpath("//*[@id='type']//a[text()='" + ruleType + "']"));
        WebElementUtils.scrollIntoView(getDriver(), ruleTypeLink);
        WebElementUtils.waitAndClick(getDriver(), ruleTypeLink);
    }

    public String getRuleTitleText() {
        WebElementUtils.waitUntilClickable(getDriver(), ruleTitleText);
        WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), ruleTitleText, ruleTitleText.getText());
        return ruleTitleText.getText();
    }

    public void clickExpandIconButton() {
        WebElementUtils.scrollIntoView(getDriver(), expandIconButton);
        WebElementUtils.waitAndClick(getDriver(), expandIconButton);
    }

    public String getIssueDetailsText(WebElement issueDetailsText) {
        WebElementUtils.waitUntilClickable(getDriver(), issueDetailsText);
        WebElementUtils.waitUntilTextToBePresentInElement(getDriver(), issueDetailsText, issueDetailsText.getText());
        return issueDetailsText.getText();
    }
}