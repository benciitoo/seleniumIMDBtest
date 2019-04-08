package keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class WatchList {

    public static void goToWatchList(WebDriver driver, WebDriverWait wait, Actions activity) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Watchlist")));
        activity.moveToElement(driver.findElement(By.linkText("Watchlist"))).build().perform();
        driver.findElement(By.linkText("Watchlist")).click();
    }

    public static void clickEditOptionInWatchList(WebDriver driver) {
        driver.findElement(By.linkText("EDIT")).click();
    }

    public static void searchInWatchListEditMenuAndAddFirstSuggestionToWatchList(WebDriver driver, String searchKeyWord) {
        driver.findElement(By.id("add-to-list-search")).sendKeys(searchKeyWord);
        WebDriverWait waitForSuggestions = new WebDriverWait(driver, 5);
        waitForSuggestions.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"add-to-list-search-results\"]/a[1]")));
        driver.findElement(By.xpath("//*[@id=\"add-to-list-search-results\"]/a[1]")).click();
    }


    public static void rateMovieByGivenNameInWatchList (WebDriver driver, WebDriverWait wait, String movieTitleInMyWatchList, int starRating) throws Exception{
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("center-1-react"))));
        List<WebElement> moviesInWatchlist = new ArrayList<>(driver.findElement(By.id("center-1-react"))
                .findElements(By.className("lister-item-content")));
        // .findElements(By.xpath("(//DIV[@class='lister-item-content'])")));  EZ IS MŰKÖDIK

        WebElement theOne = null;
        for (WebElement element : moviesInWatchlist) {
            if (element.findElement(By.tagName("h3")).getText().equals(movieTitleInMyWatchList)) {
                theOne = element;
            }
        }

        if (theOne != null) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", theOne);
            wait.until(ExpectedConditions.elementToBeClickable(theOne
                    .findElement(By.tagName("button")))).click();
            //theOne.findElement(By.xpath(String.format("//*[@id=\"star-rating-widget\"]/div/div/span[1]/span/a[%s]", star))).click();
            //theOne.findElement(By.xpath(String.format("//DIV[@class='star-rating']/span[1]/span[1]/a[%s]", star))).click();

            theOne.findElement(By.className("star-rating-button"))
                    .findElement(By.className("star-rating-stars"))
                    .findElements(By.tagName("a"))
                    .get(starRating - 1)
                    .click();
        } else {
            System.out.println("nincs ilyen nevű film");
        }
    }


    public static void deleteMovieFromWatchlist(WebDriver driver, String movieTitleInMyWatchList){
        List<WebElement> moviesInWatchlist = driver.findElements(By.className("lister-item"));
       /* ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//*[@id=\"1489765909\"]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id=\"1489765909\"]")));
        driver.findElement(By.id("delete_items")).click();
        driver.findElement(By.xpath("//*[@id=\"delete_items_form\"]/div/input")).click();*/

        int index = -1;
        for (int i = 0; i < moviesInWatchlist.size(); i++) {
            if (moviesInWatchlist.get(i).findElement(By.tagName("a")).getText().equals(movieTitleInMyWatchList)) {
                System.out.println(moviesInWatchlist.get(i).findElement(By.tagName("a")).getText());
                index = i;
                System.out.println(index);
            }
        }

        if (index > -1) {
            WebElement movieToDelete = driver.findElements(By.className("lister-item"))
                    .get(index)
                    .findElement(By.tagName("input"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", movieToDelete);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", movieToDelete);
            driver.findElement(By.id("delete_items")).click();
            driver.findElement(By.xpath("//*[@id=\"delete_items_form\"]/div/input")).click();
        } else {
            System.out.println("nincs ilyen nevű film");
        }
    }


    //Sort Watchlist
    public static void sortWatchListByAlphabetical(WebDriver driver) {
        Select dropDown = new Select(driver.findElement(By.id("lister-sort-by-options")));
        dropDown.selectByValue("ALPHA");
    }

   /* public static void clickOnAscendDescendButton(){
        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[3]/div/div[1]/div/span/div/div/div[2]/div[1]/div[1]/div[1]/button/span")).click();
    }*/
}