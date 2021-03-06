import keywords.User;
import keywords.WatchList;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;


public class WatchListTest {

    private static WebDriver driver = new FirefoxDriver();
    private static WebDriverWait wait = new WebDriverWait(driver, 5);
    private static Actions activity = new Actions(driver);


    @BeforeClass
    public static void maximizeBrowser(){
        User.maximizeBrowser(driver);
        User.getMainPage(driver);
        User.signInToIMDBWithUserEmailAndPassword(driver, wait,"bence.banszegi@gmail.com", "imdbpassword");
    }

    @Before
    public void goToWatchListPage(){
        WatchList.goToWatchList(driver, wait, activity);
    }

    @AfterClass
    public static void quitSession(){
        //User.deleteAllCookies(driver);
        User.quitSession(driver);
    }


    @Test
    public void checkIfBrowserIsOnWatchListPage(){
        WatchList.goToWatchList(driver, wait, activity);
        assertEquals("Your Watchlist", WatchList.returnWatchListPageText(driver, wait));
    }
}