import keywords.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {

    static WebDriver driver = new FirefoxDriver();
    static WebDriverWait wait = new WebDriverWait(driver, 5);
    static Actions activity = new Actions(driver);

    public static void main(String[] args) throws Exception {
        User.getMainPage(driver);
        User.signInToIMDBWithUserEmailAndPassword(driver, wait,"bence.banszegi@gmail.com", "imdbpassword");
        User.goToWatchList(driver,wait,activity);
        User.rateMovieByGivenName(driver, wait,"Snatch");

        //Thread.sleep(2000);
        //User.logout(driver, wait, activity);
        //User.quitSession(driver);
        //User.sortWatchListByAlphabetical(driver);


        //Thread.sleep(1000);
        //User.clickOnAscendDescendButton();

        /*Thread.sleep(2000);
        User.quitSession();*/


    }
}