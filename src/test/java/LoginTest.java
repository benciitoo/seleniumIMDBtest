import keywords.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class LoginTest {

    @BeforeClass
    public static void loadMainPage() {
        User.getMainPage();
    }

    @AfterClass
    public static void quitSession(){
        User.quitSession();
    }

    @Test
    public void loginWithValidEmailAndPassword() throws Exception {
        User.signInToIMDBWithUserEmailAndPassword("bence.banszegi@gmail.com", "imdbpassword");
        assertEquals("testbenciitoo", User.returnSignedInUsername());
        User.logout();
    }

    @Test
    public void loginWithNoPassword() {
        User.signInToIMDBWithUserEmailAndPassword("bence.banszegi@gmail.com", "");
        assertEquals("Enter your password", User.returnErrorMessageIfNoPasswordProvided());
    }

}
