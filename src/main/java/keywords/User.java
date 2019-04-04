package keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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

    public static void sendUserPasswordToLoginForm(String password){
        driver.findElement(By.id("ap_password")).sendKeys(password);
    }

    public static void clickSignInButton(){
        driver.findElement(By.id("signInSubmit")).click();
    }

    public static void signInToIMDBWithUserEmailAndPassword (String userEmail, String password){
        navigateToSignInForm();
        sendUserEmailToLoginForm(userEmail);
        sendUserPasswordToLoginForm(password);
        clickSignInButton();
    }


}
