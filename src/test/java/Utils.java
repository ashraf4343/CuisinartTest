import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public  class Utils {


    // Method to scroll a specific distance
    public static void scroll(WebDriver driver, int scrollAmount) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll by the specified amount (positive to scroll down, negative to scroll up)
        js.executeScript("window.scrollBy(0," + scrollAmount + ")");
    }

    public static void waitForElementToBeClickable(WebDriver driver, By xpath) {
    }
}
