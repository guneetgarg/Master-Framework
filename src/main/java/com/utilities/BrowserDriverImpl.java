package com.utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class BrowserDriverImpl implements WebElement{

    static Logger logger = Logger.getLogger(BrowserDriverImpl.class);

    private WebElement element = null;
    private WebDriver driver = null;
    private int timeout = 30;


    /*
     * Class Constructor to initialize Webdriver
     */
    public BrowserDriverImpl(WebDriver driver) {
        logger.info("Initializing Webdriver");
        this.driver = driver;
    }

    private BrowserDriverImpl(WebElement element, WebDriver driver) {
        this.element = element;
        this.driver = driver;
    }

    /*
     * Navigate to Website
     */
    public void get(String url) {
        logger.info("Navigation to Site " + url);
        try {
            driver.get(url);
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException napee) {
            logger.info("No Alert found while loading page");
        }
        if (Environment.getBrowserType().equalsIgnoreCase("chrome"))
            waitForBrowserToLoadCompletely();
    }

    /*
     * return current url
     */
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        logger.info("Current Page url is : " + url);
        return url;
    }

    /*
     * return page title
     */
    public String getTitle() {
        String title = driver.getTitle();
        logger.info("Page Current title is : " + title);
        return title;
    }

    /*
     * return page source
     */
    public String getPageSource() {
        String PageSource = driver.getPageSource();
        logger.info("Page Source is : " + PageSource);
        return PageSource;
    }

    /*
     * Close Webdriver
     */
    public void close() {
        logger.info("Closing Webdriver");
        driver.close();
    }

    /*
     * Quit Webdriver
     */
    public void quit() {
        logger.info("Quiting Webdriver");
        driver.quit();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public WebDriver.TargetLocator switchTo() {
        return driver.switchTo();
    }

    public WebDriver.Navigation navigate() {
        return driver.navigate();
    }

    public WebDriver.Options manage() {
        return driver.manage();
    }

    public WebDriver getwrappedDriver() {
        return driver;
    }

    public Object executeScript(String script, Object... args) {
        return null;
    }

    public void switchtoFrame(String frameId) {

    }

    public void switchTo_iframe(String xpath) {

    }

    public void waitForBrowserToLoadCompletely() {
        String state = null;
        String oldstate = null;
        try {
            System.out.print("Waiting for browser loading to complete");
            int i = 0;
            while (i < 5) {
                Thread.sleep(1000);
                state = ((JavascriptExecutor) driver).executeScript("return document.readyState;").toString();
                System.out.print("." + Character.toUpperCase(state.charAt(0)) + ".");
                if (state.equals("interactive") || state.equals("loading"))
                    break;
                /*
                 * If browser in 'complete' state since last X seconds.
                 * Return.
                 */

                if (i == 1 && state.equals("complete")) {
                    System.out.println();
                    return;
                }
                i++;
            }
            i = 0;
            oldstate = null;
            Thread.sleep(2000);

            /*
             * Now wait for state to become complete
             */
            while (true) {
                state = ((JavascriptExecutor) driver).executeScript("return document.readyState;").toString();
                System.out.print("." + state.charAt(0) + ".");
                if (state.equals("complete"))
                    break;

                if (state.equals(oldstate))
                    i++;
                else
                    i = 0;
                /*
                 * If browser state is same (loading/interactive) since last 60 secs. Refresh the page.
                 */
                if (i == 15 && state.equals("loading")) {
                    System.out.println("\nBrowser in " + state + " state since last 60 secs. So refreshing browser.");
                    driver.navigate().refresh();
                    System.out.print("Waiting for browser loading to complete");
                    i = 0;
                } else if (i == 6 && state.equals("interactive")) {
                    System.out.println("\nBrowser in " + state + " state since last 30 secs. So starting with execution.");
                    return;
                }

                Thread.sleep(2000);
                oldstate = state;

            }
            System.out.println();

        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public int getCountOfElementsWithXPath(String xpath) {
        return 0;
    }

    public int getCountOfElementsWithCSSSelcector(String cssSelector) {
        return 0;
    }

    public void openNewTabAndSwitchToIt() {

    }

    public void openLinkInNewTabAndSwitchToIt() {

    }

    public void switchToTab(int tabIndex) {

    }

    public boolean switchToChildWindow(String mainWindow) {
        return false;
    }

    public WebElement waitForElementToBeClickable(By by, int timeUnit) {
        return null;
    }

    public WebElement waitForElementToBeVisible(By by, int timeUnit) {
        return null;
    }

    public void waitForElementToBeDisappear(By by, int timeUnit) {

    }

    public boolean isAlertPresent(int timeUnit) {
        return false;
    }

    public void click() {

        waitForVisible();
        try {
            element.click();
        } catch (WebDriverException e) {
            logger.info("Element was not clickable");
            logger.info("Tring again to click on Element: " + element);
        }

        moveToElementAndClick();
    }

    public void submit() {

    }

    public void sendKeys(CharSequence... keysToSend) {

    }

    public void clear() {

    }

    public String getTagName() {
        return null;
    }

    public String getAttribute(String name) {
        return null;
    }

    public boolean isSelected() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }

    public String getText() {
        return null;
    }

    public boolean isDisplayed() {
        return false;
    }

    public Point getLocation() {
        return null;
    }

    public Dimension getSize() {
        return null;
    }

    public String getCssValue(String propertyName) {
        return null;
    }

    public void scrollUpAndClick() {

    }

    public void scrollDownAndClick() {

    }

    public void scrollLeftAndClick() {

    }

    public void scrollRightAndClick() {

    }

    public void moveToElementAndClick() {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (WebDriverException wde) {
            logger.info(wde);
            wde.printStackTrace();
            return;
        }
    }

    public void rightClick() {

    }

    public void doubleClick() {

    }

    public WebElement getWrappedElement() {
        return null;
    }

    public void waitForVisible() {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf((org.openqa.selenium.WebElement) element));
    }

    public void waitTillNotVisible() {

    }

    public void waitTillNotPresent() {

    }

    public void waitTillNotPresent(int seconds) {

    }

    public String getTextForHiddenElement() {
        return null;
    }

    public void waitTillNotVisible(int seconds) {

    }

    public void removeFromDOM() {

    }

    public void forceClick() {

    }

    public void scrollDown(int px) {

    }

    public void mouseOver() {

    }

    public void selectDropdownElementUsingVisibleText(String textToBeSelect) {

    }

    public void selectDropdownElementUsingIndex(int Index) {

    }

    public void selectMultipleSegmnetsUsingVisibleText(List<String> textToBeSelect) {

    }

    public List<WebElement> getOptions() {
        return null;
    }

    //
    public String getFirstSelectedOption() {
        return null;
    }

    //
//    public List<WebElement> findElements(By by) {
//        return null;
//    }

    @Override
    public List<org.openqa.selenium.WebElement> findElements(By by) {
        return null;
    }

    //
    public org.openqa.selenium.WebElement findElement(By by) {
        element = (WebElement) driver.findElement(by);
        return (org.openqa.selenium.WebElement) element;
    }


}
