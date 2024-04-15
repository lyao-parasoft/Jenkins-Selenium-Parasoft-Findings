package com.parasoft.findings.jenkins.common;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WebElementUtils {
    public static final int DEFAULT_WAIT_FOR_ELEMENT = 20;

    public static void waitAndClick(WebDriver driver, WebElement element) {
        waitUntilClickable(driver, element);
        clickElementUseJs(driver, element);
    }

    public static void waitAndType(WebDriver driver, WebElement element, String text) {
        waitUntilClickable(driver, element);
        element.clear();
        element.sendKeys(text);
    }

    public static void scrollIntoView(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT);
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(e -> ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(false); arguments[0].scrollIntoView(true); return true;", element));
    }

    public static void waitUntilClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(elementToBeClickable(element));
    }

    public static void waitUntilVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT);
        wait.until(visibilityOf(element));
    }

    public static void clickElementUseJs(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();",element);
    }

    public static void scrollToBottom(WebDriver driver) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static String waitUntilTextToBePresentInElement(WebDriver driver, WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT);
        wait.until(visibilityOf(element));
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        if(text.isBlank()) {
            // The text may be empty when using getText() function
            text = element.getAttribute("textContent");
        }
        return text;
    }

    public static void waitUntilAttributeToNotContains(WebDriver driver, By locator, String attribute, String value, long timeOutInSeconds) {
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, timeOutInSeconds)
                .pollingEvery(Duration.ofMillis(500));
        boolean timedOut = wait.until((WebDriver d) -> {
            try {
                WebElement element = d.findElement(locator);
                return element.isDisplayed() && !element.getAttribute(attribute).contains(value);
            } catch (StaleElementReferenceException ignored) {
                return false;
            }
        });
        if (!timedOut) {
            throw new TimeoutException("Timed out waiting for \"" + attribute + "\" attribute to not contain \"" + value + "\"");
        }
    }

}
