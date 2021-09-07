import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.cssSelector;

class CardOrderTest {
    private WebDriver driver;

   @BeforeAll

    static void setUpAll() {
       WebDriverManager.chromedriver().setup();

   }

   @BeforeEach

    void setUp(){
       driver = new ChromeDriver();
       ChromeOptions options = new ChromeOptions();
       options.addArguments("start-maximized"); // open Browser in maximized mode
       options.addArguments("disable-infobars"); // disabling infobars
       options.addArguments("--disable-extensions"); // disabling extensions
       options.addArguments("--disable-gpu"); // applicable to windows os only
       options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
       options.addArguments("--no-sandbox");
       options.addArguments("--headless");
       options.setHeadless(true);
       driver = new ChromeDriver(options);
   }

   @AfterEach

    void tearDown(){
       driver.quit();
       driver = null;
   }

   @Test

   void shouldTestV1() {

       driver.get("http://localhost:9999");
       driver.findElement(cssSelector("[type='text']")).sendKeys("Иванов Петр");
       driver.findElement(cssSelector("[type='tel']")).sendKeys("+79205553421");
       driver.findElement(cssSelector(".checkbox__box")).click();
       driver.findElement(cssSelector("button")).click();
       String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
       String actual = driver.findElement(cssSelector(".paragraph")).getText().trim();
       assertEquals(expected, actual);

   }

}
