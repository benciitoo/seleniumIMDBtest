import keywords.User;

public class App {

    public static void main(String[] args) {
        User.getMainPage();
        User.loginToIMDBWithUserEmailAndPassword("bence.banszegi@gmail.com", "imdbpassword");

    }


}
