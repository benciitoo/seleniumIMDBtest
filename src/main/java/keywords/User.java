package keywords;

import javafx.scene.control.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.MouseAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class User {

    //static WebDriver driver = new FirefoxDriver();
    //static WebDriverWait wait = new WebDriverWait(driver, 5);
    //static Actions activity = new Actions(driver);


    public static void maximizeBrowser(WebDriver driver) {
        driver.manage().window().maximize();
    }

    public static void getMainPage(WebDriver driver) {
        driver.get("https://imdb.com");
    }

    public static void deleteAllCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }

    public static void quitSession(WebDriver driver) {
        driver.quit();
    }

    public static void closeBrowser(WebDriver driver) {
        driver.close();
    }

    public static void cheatMoveMouse(WebDriver driver, Actions activity) throws Exception {
        activity.moveToElement(driver.findElement(By.id("home_img"))).build().perform();
        Thread.sleep(1000);
    }

    //login methods
    public static void navigateToSignInForm(WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("imdb-signin-link"))));
        driver.findElement(By.id("imdb-signin-link")).click();
        driver.findElement(By.xpath("//*[@id=\"signin-options\"]/div/div[1]/a[1]")).click();
    }

    public static void sendUserEmailToLoginForm(WebDriver driver, String userEmail) {
        driver.findElement(By.id("ap_email")).sendKeys(userEmail);
    }

    public static void sendUserPasswordToLoginForm(WebDriver driver, String password) {
        driver.findElement(By.id("ap_password")).sendKeys(password);
    }

    public static void clickSignInButton(WebDriver driver) {
        driver.findElement(By.id("signInSubmit")).click();
    }

    public static void signInToIMDBWithUserEmailAndPassword(WebDriver driver, WebDriverWait wait, String userEmail, String password) {
        navigateToSignInForm(driver, wait);
        sendUserEmailToLoginForm(driver, userEmail);
        sendUserPasswordToLoginForm(driver, password);
        clickSignInButton(driver);
    }


    //user dropdown menu methods
    public static void logout(WebDriver driver, WebDriverWait wait, Actions activity) throws Exception {
        /*activity.moveToElement(driver.findElement(By.id("home_img"))).build().perform();
        Thread.sleep(1000);*/
        activity.moveToElement(driver.findElement(By.id("navUserMenu"))).build().perform();
        //((JavascriptExecutor) driver).executeScript("document.querySelector('#navUserMenu > div').style.display=\"block\"");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nblogout")));
        driver.findElement(By.id("nblogout")).click();
    }


    public static void goToActivityPage(WebDriver driver, WebDriverWait wait, Actions activity) {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbusername")));
        activity.moveToElement(driver.findElement(By.id("navUserMenu"))).build().perform();
        //((JavascriptExecutor) driver).executeScript("document.querySelector('#navUserMenu > div').style.display=\"block\"");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Your Activity")));
        driver.findElement(By.linkText("Your Activity")).click();
    }


    public static void goToYourRatingsPage(WebDriver driver, WebDriverWait wait, Actions activity) throws Exception {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbusername")));
        activity.moveToElement(driver.findElement(By.id("home_img"))).build().perform();
        Thread.sleep(1000);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navUserMenu")));
        activity.moveToElement(driver.findElement(By.id("navUserMenu"))).build().perform();
        //((JavascriptExecutor) driver).executeScript("document.querySelector('#navUserMenu > div').style.display=\"block\"");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Your Ratings")));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Your Ratings")));
        driver.findElement(By.linkText("Your Ratings")).click();
    }



    //for the JUnit tests
    public static String returnSignedInUsername(WebDriver driver) {
        return driver.findElement(By.id("nbusername")).getText();
    }

    public static String returnSingleLineLoginErrorMessage(WebDriver driver){
        return driver.findElement(By.id("auth-error-message-box")).findElement(By.className("a-list-item")).getText();
    }


    public static List<String> returnErrorMessageIfNoEmailAndNoPasswordProvided(WebDriver driver){
        List<String> errorList = driver.findElement(By.id("auth-error-message-box")).findElements(By.className("a-list-item"))
                .stream()
                .map(x -> x.getText())
                .collect(Collectors.toList());
        return errorList;
    }

    public static String returnWatchListPageText(WebDriver driver, WebDriverWait wait){
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//*[@id=\"center-1-react\"]/div/div[1]/div/div[2]/h1")))
                .getText();
        //return driver.findElement(By.xpath("//*[@id=\"center-1-react\"]/div/div[1]/div/div[2]/h1")).getText();
    }

}