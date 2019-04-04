import keywords.User;

public class App {

    public static void main(String[] args) {
        User.getMainPage();
        User.signInToIMDBWithUserEmailAndPassword("bence.banszegi@gmail.com", "imdbpassword");

    }


}
