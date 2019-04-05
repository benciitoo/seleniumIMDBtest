package keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class User {

    static WebDriver driver = new FirefoxDriver();
    static WebDriverWait wait = new WebDriverWait(driver, 5);
    static Actions activity = new Actions(driver);


    public static void maximizeBrowser() {
        driver.manage().window().maximize();
    }

    public static void getMainPage() {
        driver.get("https://imdb.com");
    }

    public static void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    public static void quitSession() {
        driver.quit();
    }

    public static void closeBrowser() {
        driver.close();
    }


    //login methods
    public static void navigateToSignInForm() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("imdb-signin-link"))));
        driver.findElement(By.id("imdb-signin-link")).click();
        driver.findElement(By.xpath("//*[@id=\"signin-options\"]/div/div[1]/a[1]")).click();
    }

    public static void sendUserEmailToLoginForm(String userEmail) {
        driver.findElement(By.id("ap_email")).sendKeys(userEmail);
    }

    public static void sendUserPasswordToLoginForm(String password) {
        driver.findElement(By.id("ap_password")).sendKeys(password);
    }

    public static void clickSignInButton() {
        driver.findElement(By.id("signInSubmit")).click();
    }

    public static void signInToIMDBWithUserEmailAndPassword(String userEmail, String password) {
        navigateToSignInForm();
        sendUserEmailToLoginForm(userEmail);
        sendUserPasswordToLoginForm(password);
        clickSignInButton();
    }


    //user dropdown menu methods
    public static void logout() throws Exception {
        /*activity.moveToElement(driver.findElement(By.id("home_img"))).build().perform();
        Thread.sleep(1000);*/
        Actions logout = new Actions(driver);
        logout.moveToElement(driver.findElement(By.id("navUserMenu"))).build().perform();
        //((JavascriptExecutor) driver).executeScript("document.querySelector('#navUserMenu > div').style.display=\"block\"");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nblogout")));
        driver.findElement(By.id("nblogout")).click();
    }


    public static void goToActivityPage() {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbusername")));
        activity.moveToElement(driver.findElement(By.id("navUserMenu"))).build().perform();
        //((JavascriptExecutor) driver).executeScript("document.querySelector('#navUserMenu > div').style.display=\"block\"");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Your Activity")));
        driver.findElement(By.linkText("Your Activity")).click();
    }


    public static void goToYourRatingsPage() throws Exception {
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


    //Upper searchbar methods
    public static void search(String searchKeyWord) {
        driver.findElement(By.id("navbar-query")).sendKeys(searchKeyWord);
        driver.findElement(By.id("navbar-submit-button")).click();
    }

    public static void searchInSearchfieldAndAddFirstSuggestionToWatchList(String searchKeyWord) {
        driver.findElement(By.id("navbar-query")).sendKeys(searchKeyWord);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"navbar-suggestionsearch\"]/div[1]/a")));
        driver.findElement(By.xpath("//*[@id=\"navbar-suggestionsearch\"]/div[1]/a")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"title-overview-widget\"]/div[2]/div[2]/span/div")));
        driver.findElement(By.xpath("//*[@id=\"title-overview-widget\"]/div[2]/div[2]/span/div")).click();
    }


    //Watchlist methods
    public static void goToWatchList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Watchlist")));
        activity.moveToElement(driver.findElement(By.linkText("Watchlist"))).build().perform();
        driver.findElement(By.linkText("Watchlist")).click();
    }

    public static void clickEditOptionInWatchList() {
        driver.findElement(By.linkText("EDIT")).click();
    }

    public static void searchInWatchListEditMenuAndAddFirstSuggestionToWatchList(String searchKeyWord) {
        driver.findElement(By.id("add-to-list-search")).sendKeys(searchKeyWord);
        WebDriverWait waitForSuggestions = new WebDriverWait(driver, 5);
        waitForSuggestions.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"add-to-list-search-results\"]/a[1]")));
        driver.findElement(By.xpath("//*[@id=\"add-to-list-search-results\"]/a[1]")).click();
    }


    //Sort Watchlist
    public static void sortWatchListByAlphabetical() {
        Select dropDown = new Select(driver.findElement(By.id("lister-sort-by-options")));
        dropDown.selectByValue("ALPHA");
    }

   /* public static void clickOnAscendDescendButton(){
        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[3]/div/div[1]/div/span/div/div/div[2]/div[1]/div[1]/div[1]/button/span")).click();
    }*/


    public static void cheatMoveMouse() throws Exception {
        activity.moveToElement(driver.findElement(By.id("home_img"))).build().perform();
        Thread.sleep(1000);
    }


    //for the JUnit tests
    public static String returnSignedInUsername() {
        return driver.findElement(By.id("nbusername")).getText();
    }

    public static String returnSingleLineLoginErrorMessage(){
        return driver.findElement(By.id("auth-error-message-box")).findElement(By.className("a-list-item")).getText();
    }

    /*public static String returnErrorMessageIfNoPasswordProvided() {
        return driver.findElement(By.id("auth-error-message-box")).findElement(By.className("a-list-item")).getText();
    }

    public static String returnErrorMessageIfNoEmailProvided() {
        return driver.findElement(By.id("auth-error-message-box")).findElement(By.className("a-list-item")).getText();
    }

    public static String returnErrorMessageIfValidEmailButInvalidPasswordProvided(){
        return driver.findElement(By.id("auth-error-message-box")).findElement(By.className("a-list-item")).getText();
    }

    public static String returnErrorMessageIfInvalidEmailAndInvalidPasswordProvided(){
        return driver.findElement(By.id("auth-error-message-box")).findElement(By.className("a-list-item")).getText();
    }*/

    public static List<String> returnErrorMessageIfNoEmailAndNoPasswordProvided(){
        List<String> errorList = new ArrayList<String>();
        errorList.add(driver.findElement(By.xpath("//*[@id=\"auth-error-message-box\"]/div/div/dl/li[1]/span")).getText());
        errorList.add(driver.findElement(By.xpath("//*[@id=\"auth-error-message-box\"]/div/div/dl/li[2]/span")).getText());
        return errorList;
    }


}