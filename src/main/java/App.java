import keywords.UpperSearchBar;
import keywords.User;
import keywords.WatchList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {

    static WebDriver driver = new FirefoxDriver();
    static WebDriverWait wait = new WebDriverWait(driver, 11);
    static Actions activity = new Actions(driver);

    public static void main(String[] args) throws Exception {
        User.getMainPage(driver);
        User.signInToIMDBWithUserEmailAndPassword(driver, wait, "bence.banszegi@gmail.com", "imdbpassword");
        WatchList.goToWatchList(driver, wait, activity);
        WatchList.rateMovieByGivenNameInWatchList(driver, wait, "The Matrix", 9);


    }
}