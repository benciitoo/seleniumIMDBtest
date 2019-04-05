import keywords.User;
import org.junit.*;

import static org.junit.Assert.*;


public class LoginTest {

    @BeforeClass
    public static void maximizeBrowser() {
        User.maximizeBrowser();
    }

    @Before
    public void loadMainPage() {
        User.getMainPage();
    }

    @After
    public void deleteAllCookies() {
        User.deleteAllCookies();
    }

    @AfterClass
    public static void quitSession() {
        User.quitSession();
    }


    @Test
    public void loginWithValidEmailAndValidPassword() throws Exception {
        User.signInToIMDBWithUserEmailAndPassword("bence.banszegi@gmail.com", "imdbpassword");
        assertEquals("testbenciitoo", User.returnSignedInUsername());
        User.logout();
    }

    @Test
    public void loginAttemptWithNoPasswordProvided() {
        User.signInToIMDBWithUserEmailAndPassword("bence.banszegi@gmail.com", "");
        assertEquals("Enter your password", User.returnSingleLineLoginErrorMessage());
    }

    @Test
    public void loginAttemptWithNoEmailProvided() {
        User.signInToIMDBWithUserEmailAndPassword("", "testpassword");
        assertEquals("Enter your email", User.returnSingleLineLoginErrorMessage());
    }

    @Test
    public void loginAttemptWithNoEmailAndNoPasswordProvided(){
        User.signInToIMDBWithUserEmailAndPassword("","");
        assertEquals("Enter your email", User.returnErrorMessageIfNoEmailAndNoPasswordProvided().get(0));
        assertEquals("Enter your password", User.returnErrorMessageIfNoEmailAndNoPasswordProvided().get(1));
    }

    @Test
    public void loginAttemptWithValidEmailButInvalidPasswordProvided(){
        User.signInToIMDBWithUserEmailAndPassword("bence.banszegi@gmail.com", "notMyPassword");
        assertEquals("Your password is incorrect", User.returnSingleLineLoginErrorMessage());
    }

    @Test
    public void loginAttemptWithInvalidEmailAndInvalidPasswordProvided(){
        User.signInToIMDBWithUserEmailAndPassword("thereIsNoSuchEmailLikeThis12345@nomail.com", "randomPass");
        assertEquals("We cannot find an account with that email address", User.returnSingleLineLoginErrorMessage());
    }
}