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


    //Upper searchbar methods
    public static void search(WebDriver driver, String searchKeyWord) {
        driver.findElement(By.id("navbar-query")).sendKeys(searchKeyWord);
        driver.findElement(By.id("navbar-submit-button")).click();
    }

    public static void searchInSearchfieldAndAddFirstSuggestionToWatchList(WebDriver driver, WebDriverWait wait, String searchKeyWord) {
        driver.findElement(By.id("navbar-query")).sendKeys(searchKeyWord);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"navbar-suggestionsearch\"]/div[1]/a")));
        driver.findElement(By.xpath("//*[@id=\"navbar-suggestionsearch\"]/div[1]/a")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"title-overview-widget\"]/div[2]/div[2]/span/div")));
        driver.findElement(By.xpath("//*[@id=\"title-overview-widget\"]/div[2]/div[2]/span/div")).click();
    }


    //Watchlist methods
    public static void goToWatchList(WebDriver driver, WebDriverWait wait, Actions activity) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Watchlist")));
        activity.moveToElement(driver.findElement(By.linkText("Watchlist"))).build().perform();
        driver.findElement(By.linkText("Watchlist")).click();
    }

    public static void clickEditOptionInWatchList(WebDriver driver) {
        driver.findElement(By.linkText("EDIT")).click();
    }

    public static void searchInWatchListEditMenuAndAddFirstSuggestionToWatchList(WebDriver driver, String searchKeyWord) {
        driver.findElement(By.id("add-to-list-search")).sendKeys(searchKeyWord);
        WebDriverWait waitForSuggestions = new WebDriverWait(driver, 5);
        waitForSuggestions.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"add-to-list-search-results\"]/a[1]")));
        driver.findElement(By.xpath("//*[@id=\"add-to-list-search-results\"]/a[1]")).click();
    }


    public static void rateMovieByGivenName (WebDriver driver, WebDriverWait wait, String movieTitleInMyWatchList) throws Exception{
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("center-1-react"))));
        /*System.out.println(driver.findElement(By.id("center-1-react"))
                .findElement(By.linkText(movieTitleInMyWatchList)).findElement(By.className("lister-item-year")).getText());*/
        //Thread.sleep(2000);
        List<WebElement> moviesInWatchlist = new ArrayList<>(driver.findElement(By.id("center-1-react"))
                .findElements(By.xpath("(//DIV[@class='lister-item-content'])")));
        System.out.println(moviesInWatchlist.size());

        WebElement theOne = null;
        //theOne = moviesInWatchlist.stream().filter(x -> x.findElement(By.tagName("h3")).getText().equals(movieTitleInMyWatchList)).findFirst();
        for (WebElement element : moviesInWatchlist) {
            if (element.findElement(By.tagName("h3")).getText().equals(movieTitleInMyWatchList)) {
                System.out.println(element.findElement(By.tagName("h3")).getText());
                theOne = element;
            }
        }

        if (theOne != null) {
            wait.until(ExpectedConditions.elementToBeClickable(theOne
                    .findElement(By.tagName("button")))).click();
                     // .findElement(By.xpath("//SPAN[@class='star-rating-text']")))).click();
        } else {
            System.out.println("nincs ilyen nevű film");
        }


    }


    //Sort Watchlist
    public static void sortWatchListByAlphabetical(WebDriver driver) {
        Select dropDown = new Select(driver.findElement(By.id("lister-sort-by-options")));
        dropDown.selectByValue("ALPHA");
    }

   /* public static void clickOnAscendDescendButton(){
        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[3]/div/div[1]/div/span/div/div/div[2]/div[1]/div[1]/div[1]/button/span")).click();
    }*/





    //for the JUnit tests
    public static String returnSignedInUsername(WebDriver driver) {
        return driver.findElement(By.id("nbusername")).getText();
    }

    public static String returnSingleLineLoginErrorMessage(WebDriver driver){
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