import javazoom.jl.decoder.JavaLayerException;
import keywords.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;

public class App {

    private static WebDriver driver = new FirefoxDriver();
    private static WebDriverWait wait = new WebDriverWait(driver, 11);
    private static Actions activity = new Actions(driver);
    private static MusicInBackground music = new MusicInBackground();


    public static void main(String[] args) throws Exception {
        music.playMusic();
        //Thread.sleep(10000);

        User.getMainPage(driver);
        User.signInToIMDBWithUserEmailAndPassword(driver, wait, "bence.banszegi@gmail.com", "imdbpassword");
        WatchList.goToWatchList(driver, wait, activity);
        WatchList.rateMovieByGivenNameInWatchList(driver, wait, "The Matrix", 10);
        Thread.sleep(4000);
        User.quitSession(driver);
        music.stopMusic();
    }
}