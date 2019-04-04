import keywords.User;

public class App {

    public static void main(String[] args) throws Exception {
        User.getMainPage();
        User.signInToIMDBWithUserEmailAndPassword("bence.banszegi@gmail.com", "imdbpassword");
        User.searchInSearchfieldAndAddFirstSuggestionToWatchList("Jurassic Park");


        //Thread.sleep(2000);
        //User.quitSession();

       /* User.goToWatchList();
        User.clickEditOptionInWatchList();
        User.addToWatchListInWatchListEditMenu("Snatch");*/





    }
}