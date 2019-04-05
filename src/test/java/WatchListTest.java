import keywords.User;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;


public class WatchListTest {

    static WebDriver driver = new FirefoxDriver();
    static WebDriverWait wait = new WebDriverWait(driver, 5);
    static Actions activity = new Actions(driver);


    @BeforeClass
    public static void maximizeBrowser(){
        User.maximizeBrowser(driver);
        User.getMainPage(driver);
        User.signInToIMDBWithUserEmailAndPassword(driver, wait,"bence.banszegi@gmail.com", "imdbpassword");
    }

    /*@Before
    public void goToWatchListPage(){
        User.goToWatchList(driver, wait, activity);
    }*/

    @AfterClass
    public static void quitSession(){
        User.quitSession(driver);
    }

    @After
    public void deleteAllCookies(){
        User.deleteAllCookies(driver);
    }


    @Test
    public void checkIfBrowserIsOnWatchListPage(){
        User.goToWatchList(driver, wait, activity);

        assertTrue(User.returnWatchListPageText(driver, wait).equals("Your Watchlist"));
    }


}