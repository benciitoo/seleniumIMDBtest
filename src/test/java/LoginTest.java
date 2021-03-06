import keywords.MusicInBackground;
import keywords.User;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;


public class LoginTest {

    private static WebDriver driver = new FirefoxDriver();
    private static WebDriverWait wait = new WebDriverWait(driver, 5);
    private static Actions activity = new Actions(driver);
    private static MusicInBackground music = new MusicInBackground();


    @BeforeClass
    public static void maximizeBrowser() {
        music.playMusic();
        User.maximizeBrowser(driver);
    }

    @Before
    public void loadMainPage() {
        User.deleteAllCookies(driver);
        User.getMainPage(driver);
    }

    /*@After
    public void deleteAllCookies() {
        User.deleteAllCookies(driver);
    }*/

    @AfterClass
    public static void quitSession() {
        User.quitSession(driver);
        music.stopMusic();
    }


    @Test
    public void loginWithValidEmailAndValidPassword() throws Exception {
        User.signInToIMDBWithUserEmailAndPassword(driver,wait,"bence.banszegi@gmail.com", "imdbpassword");
        assertEquals("testbenciitoo", User.returnSignedInUsername(driver));
        User.logout(driver, wait, activity);
    }

    @Test
    public void loginAttemptWithNoPasswordProvided() {
        User.signInToIMDBWithUserEmailAndPassword(driver, wait,"bence.banszegi@gmail.com", "");
        assertEquals("Enter your password", User.returnSingleLineLoginErrorMessage(driver));
    }

    @Test
    public void loginAttemptWithNoEmailProvided() {
        User.signInToIMDBWithUserEmailAndPassword(driver, wait,"", "testpassword");
        assertEquals("Enter your email", User.returnSingleLineLoginErrorMessage(driver));
    }

    @Test
    public void loginAttemptWithNoEmailAndNoPasswordProvided(){
        User.signInToIMDBWithUserEmailAndPassword(driver, wait,"","");
        assertEquals("Enter your email", User.returnErrorMessageIfNoEmailAndNoPasswordProvided(driver).get(0));
        assertEquals("Enter your password", User.returnErrorMessageIfNoEmailAndNoPasswordProvided(driver).get(1));
    }

    @Test
    public void loginAttemptWithValidEmailButInvalidPasswordProvided(){
        User.signInToIMDBWithUserEmailAndPassword(driver, wait,"bence.banszegi@gmail.com", "notMyPassword");
        assertEquals("Your password is incorrect", User.returnSingleLineLoginErrorMessage(driver));
    }

    @Test
    public void loginAttemptWithInvalidEmailAndInvalidPasswordProvided(){
        User.signInToIMDBWithUserEmailAndPassword(driver, wait,"thereIsNoSuchEmailLikeThis1234567@nomail.com", "randomPass");
        assertEquals("We cannot find an account with that email address", User.returnSingleLineLoginErrorMessage(driver));
    }
}