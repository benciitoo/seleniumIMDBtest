package keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpperSearchBar {

    public static void search(WebDriver driver, String searchKeyWord) throws Exception {
        Thread.sleep(1513);
        driver.findElement(By.id("navbar-query")).sendKeys(searchKeyWord);
        Thread.sleep(1513);
        driver.findElement(By.id("navbar-submit-button")).click();
    }


    public static void chooseFirstSearchResult(WebDriver driver, WebDriverWait wait) throws Exception {
        //Thread.sleep(1513);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main\"]/div/div[2]/table/tbody/tr[1]/td[2]/a"))).click();
    }


    public static void searchInSearchfieldAndAddFirstSuggestionToWatchList(WebDriver driver, WebDriverWait wait, String searchKeyWord) {
        driver.findElement(By.id("navbar-query")).sendKeys(searchKeyWord);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"navbar-suggestionsearch\"]/div[1]/a")));
        driver.findElement(By.xpath("//*[@id=\"navbar-suggestionsearch\"]/div[1]/a")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"title-overview-widget\"]/div[2]/div[2]/span/div")));
        driver.findElement(By.xpath("//*[@id=\"title-overview-widget\"]/div[2]/div[2]/span/div")).click();
    }


    public static void rateMovieThatWasSelectedInUpperSearchBar(WebDriver driver, WebDriverWait wait, String searchKeyWord, int star) throws Exception {
        if (star < 0 || star > 10 ) {
            System.out.println("Given rating is out of rating range.");
        } else {
            search(driver, searchKeyWord);
            chooseFirstSearchResult(driver, wait);
            driver.findElement(By.xpath("//*[@id=\"star-rating-widget\"]/div/button/span[1]")).click();
            if (star == 0) {
                wait.until(ExpectedConditions.elementToBeClickable(By
                        .xpath("//*[@id=\"star-rating-widget\"]/div/div/span[1]/a"))).click();
            } else {
                wait.until(ExpectedConditions.elementToBeClickable(By
                        .xpath(String.format("//*[@id=\"star-rating-widget\"]/div/div/span[1]/span/a[%s]", star)))).click();
            }
        }
    }


}