package keywords;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

import java.io.File;

public class MusicInBackground {

    private BasicPlayer player = new BasicPlayer();
    private File myFile = new File("C:/greenfoxOtherRepos/SeleniumIMDBtest/seleniumIMDBtest/src/main/resources/static/sound/darkness2.mp3");


    public void playMusic() {

        try {
            player.open(myFile);
            player.play();
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }
    }


    public void stopMusic() {
        try {
            player.stop();
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }
    }
}
