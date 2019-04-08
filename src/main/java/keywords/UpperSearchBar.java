package keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpperSearchBar {

    public static void search(WebDriver driver, String searchKeyWord) {
        driver.findElement(By.id("navbar-query")).sendKeys(searchKeyWord);
        driver.findElement(By.id("navbar-submit-button")).click();
    }

    public static void searchInSearchfieldAndAddFirstSuggestionToWatchList(WebDriver driver, WebDriverWait wait, String searchKeyWord) {
        driver.findElement(By.id("navbar-query")).sendKeys(searchKeyWord);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"navbar-suggestionsearch\"]/div[1]/a")));
        driver.findElement(By.xpath("//*[@id=\"navbar-suggestionsearch\"]/div[1]/a")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"title-overview-widget\"]/div[2]/div[2]/span/div")));
        driver.findElement(By.xpath("//*[@id=\"title-overview-widget\"]/div[2]/div[2]/span/div")).click();
    }
}