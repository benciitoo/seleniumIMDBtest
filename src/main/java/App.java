import keywords.User;

public class App {

    public static void main(String[] args) throws Exception {
        User.getMainPage();
        User.signInToIMDBWithUserEmailAndPassword("bence.banszegi@gmail.com", "imdbpassword");
        User.goToWatchList();
        User.sortWatchListByAlphabetical();
        User.goToActivityPage();
        User.goToYourRatingsPage();
        User.logout();

        //Thread.sleep(1000);
        //User.clickOnAscendDescendButton();


        Thread.sleep(2000);
        User.quitSession();

        //User.logout();


    }
}