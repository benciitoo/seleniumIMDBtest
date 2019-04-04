package keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class User {

    static WebDriver driver = new FirefoxDriver();

    public static void getMainPage() {
        driver.get("https://imdb.com");
    }

    public static void navigateToSignInForm() {
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

    public static void logout() {
        ((JavascriptExecutor) driver).executeScript("document.querySelector('#navUserMenu > div').style.display=\"block\"");
        driver.findElement(By.id("nblogout")).click();
    }


    //Upper searchbar
    public static void search(String searchKeyWord) {
        driver.findElement(By.id("navbar-query")).sendKeys(searchKeyWord);
        driver.findElement(By.id("navbar-submit-button")).click();
    }

    public static void searchAndAddToWatchListFirstSuggestion(String searchKeyWord){
        search(searchKeyWord);

        WebDriverWait waitForSuggestions = new WebDriverWait(driver, 10);
        waitForSuggestions.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"navbar-suggestionsearch\"]/div[1]/a")));
        driver.findElement(By.xpath("//*[@id=\"navbar-suggestionsearch\"]/div[1]/a")).click();

    }



    //Watchlist methods
    public static void goToWatchList(){
        driver.findElement(By.id("navWatchlistMenu")).click();
    }

    public static void clickEditOptionInWatchList(){
        driver.findElement(By.linkText("EDIT")).click();
    }

    public static void addFirstSuggestionToWatchListInWatchListEditMenu (String searchKeyWord) {
        driver.findElement(By.id("add-to-list-search")).sendKeys(searchKeyWord);
        WebDriverWait waitForSuggestions = new WebDriverWait(driver, 10);
        waitForSuggestions.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"add-to-list-search-results\"]/a[1]")));
        driver.findElement(By.xpath("//*[@id=\"add-to-list-search-results\"]/a[1]")).click();
    }



}