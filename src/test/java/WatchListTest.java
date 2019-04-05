import keywords.User;
import org.junit.*;

import static org.junit.Assert.*;


public class WatchListTest {

    @BeforeClass
    public static void maximizeBrowser(){
        User.maximizeBrowser();
        User.getMainPage();
        User.signInToIMDBWithUserEmailAndPassword("bence.banszegi@gmail.com", "imdbpassword");
        User.goToWatchList();
    }

    @AfterClass
    public static void quitSession(){
        User.quitSession();
    }

    @After
    public void deleteAllCookies(){
        User.deleteAllCookies();
    }


    @Test
    public void akarmi(){
        assertEquals("hello", "hello");
    }




}