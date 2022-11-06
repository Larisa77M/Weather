import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Larisa77MTest {

    /* TC_1_1  - Тест кейс:
     * 1. Открыть страницу https://openweathermap.org/
     * 2. Набрать в строке поиска город Paris
     * 3. Нажать пункт меню Search
     * 4. Из выпадающего списка выбрать Paris, FR
     * 5. Подтвердить, что заголовок изменился на "Paris, FR"
     */

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);// с помощью этой команды сможем открыть драйвер и сможем пойти на какую-то url
        Thread.sleep(5000); // сделать задержку

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id= 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']")
        );
        searchButton.click();

        Thread.sleep(5000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );

        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );
        Thread.sleep(3000);

        String actualResult = h2CityCountryHeader.getText();

        //Assert

        Assert.assertEquals(actualResult, expectedResult);


        // Thread.sleep(5000); // задержка

        driver.quit();//закрывается сессия
    }
    // driver.close(); // закрывает окно


    //driver.quit(); //выйти из браузера

    /*
     * TC_11_01
     *  1.  Открыть базовую ссылку
     *  2.  Нажать на пункт меню Guide
     *  3.  Подтвердить, что вы перешли
     *  на страницу со ссылкой https://openweathermap.org/guide и что title этой
     * страницы OpenWeatherMap API guide - OpenWeatherMap
     */

    @Test
    public void test1_TransferToNewPage_AndPageTitle_WhenClickingGuideMenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String guideMenuTest = "Guide";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        Thread.sleep(5000);

        WebElement menuGuide = driver.findElement(By.linkText(guideMenuTest));
        menuGuide.click();

        String actualResult1 = driver.getCurrentUrl();
        String actualResult2 = driver.getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);

        driver.quit();
    }

    /*
     * TC_11_02
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     * 3.  Подтвердить, что температура для города показана в Фарингейтах
     * в двух вариантах
     */

    @Test
    public void test2_SpanHeadingText_WhenPressingOnImperialOption() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Arrange
        String url = "https://openweathermap.org/";
        String expectedResult = "°F";
        String fTempSimbol = "°F";

        //Act
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement temperatureUnitsButton = driver.findElement(
                By.xpath("//div[@class='switch-container']/div[@class='option']/following-sibling::div")
        );

        temperatureUnitsButton.click();
        Thread.sleep(2000);

        WebElement spanTemperatureHeading = driver.findElement(
                By.xpath("//div[@class='current-temp']/span"));

        // 1 вариант
        //Assert
        // 2 вариант

        String tempInF = spanTemperatureHeading.getText();

        String actualResult = tempInF.substring((tempInF.length() - 2));//дает кусочек желаемого текста -120°F  85°F

        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertTrue(spanTemperatureHeading.getText().contains(fTempSimbol));
        driver.quit();
    }


    /*
     * TC_11_03
     * 1. Открыть базовую ссылку
     * 2. Подтвердить, что внизу страницы есть панель с текстом
     * “We use cookies which are essential for the site to work.
     * We also use non-essential cookies to help us improve our services.
     * Any data collected is anonymised. You can allow all cookies or manage them individually.”
     * 3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
     */

    @Test
    public void test3_SpanHeadingText_WhenPressingOnImperialOption() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Arrange
        String url = "https://openweathermap.org/";
        String expectedResult1 = "We use cookies which are essential for the site to work. "
                + "We also use non-essential cookies to help us improve our services. Any data collected is anonymised. "
                + "You can allow all cookies or manage them individually.";
        String expectedResult2 = "Allow all";
        String expectedResult3 = "Manage cookies";

        //Act
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement stickFooterPanel_CookiesPopup = driver.findElement(
                By.xpath("//div/p[@class='stick-footer-panel__description']"));
        String actualResult1 = stickFooterPanel_CookiesPopup.getText();

        WebElement allowAll_Button = driver.findElement(
                By.xpath("//div/button[@type='button'][@class='stick-footer-panel__link']"));
        String actualResult2 = allowAll_Button.getText();

        WebElement manageCookies_Button = driver.findElement(
                By.xpath("//div/a[text()=' Manage cookies ']"));
        String actualResult3 = manageCookies_Button.getText();

        //Assert
        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);

        driver.quit();
    }

    /*
     * TC_11_04
     * 1.  Открыть базовую ссылку
     * 2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”
     * (см. картинку)
     */
    @Test
    public void test4_SpanHeadingText_WhenPressingOnImperialOption() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Arrange
        String url = "https://openweathermap.org/";
        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        //Act
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement supportDropdownMenu = driver.findElement(By.xpath("//ul//div[@id='support-dropdown']"));
        supportDropdownMenu.click();

        WebElement submenu_FAQ = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//li/a[@href='/faq'][text()='FAQ']"));
        String actualResult1 = submenu_FAQ.getText();

        WebElement submenu_HowToStart = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//li/a[@href='/appid'][text()='How to start']"));
        String actualResult2 = submenu_HowToStart.getText();

        WebElement submenu_AskAQuestion = driver.findElement(By.xpath
                ("//ul[@class='dropdown-menu dropdown-visible']//a[text()='Ask a question']"));
        String actualResult3 = submenu_AskAQuestion.getText();
        Thread.sleep(2000);

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);

        driver.quit();

    }

    /*
     * TC_11_05
     * 1. Открыть базовую ссылку
     * 2. Нажать пункт меню Support → Ask a question
     * 3. Заполнить поля Email, Subject, Message
     * 4. Не подтвердив CAPTCHA, нажать кнопку Submit
     * 5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
     * (см. картинку)
     */
    @Test
    public void test5_SpanHeadingText_WhenPressingOnImperialOption() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Arrange
        String url = "https://openweathermap.org/";

        //Act
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        driver.quit();

    }

    /*
     * TC_11_06
     * 1.  Открыть базовую ссылку
     * 2.  Нажать пункт меню Support → Ask a question
     * 3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
     * 4. Оставить пустым поле Email
     * 5. Заполнить поля  Subject, Message
     * 6. Подтвердить CAPTCHA
     * 7. Нажать кнопку Submit
     * 8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”
     * (см. картинку)
     */


}

//"//*[@id=\"weather-widget\"]//span[@class='heading'][text()='54°F']"