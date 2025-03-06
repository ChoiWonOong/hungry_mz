package com.webCrawler.service;

import com.menu.service.MenuService;
import com.restaurant.service.RestaurantService;
import com.webCrawler.AbstractWebCrawler;
import data.dto.MenuDto;
import data.dto.RestaurantDto;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class RestaurantCrawler extends AbstractWebCrawler {
    private static WebDriver driver;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RestaurantService restaurantService;
    private String url = "https://map.naver.com/p/search/%EC%8B%9D%EB%8B%B9?searchType=place&c=14.29,0,0,0,dh";
    public static boolean isNumeric(String string) {
        int intValue;

        System.out.println(String.format("Parsing string: \"%s\"", string));

        if(string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }
    // 생성자에서 WebDriver 초기화
//    public RestaurantCrawler(String url) {
//        super(url);
//    }

    @Override
    public void connectToWebsite() {
        System.out.println("Connecting to website: " + url);
        System.out.println("driver setup");
        WebDriverManager.chromedriver().setup(); // WebDriver 자동 다운로드
        ChromeDriverService service = new ChromeDriverService.Builder()
                //.usingDriverExecutable(new File("C:\\Users\\dnd53\\OneDrive\\univ\\4-2\\Java\\Java24-2-HW8-32194659-최원웅\\WebCrawling\\lib\\chromedriver-win64\\chromedriver.exe")) // 드라이버 경로 지정
                //.usingDriverExecutable(new File("C:\\Users\\최원웅\\OneDrive\\univ\\4-2\\Java\\Java24-2-HW8-32194659-최원웅\\WebCrawling\\lib\\chromedriver-win64\\chromedriver.exe"))
                .usingDriverExecutable(new File("C:\\Users\\최원웅\\Desktop\\중요 문서\\NaverCloudCamp\\HungryMz\\hungry_mz\\hungry_mz\\lib\\chromedriver-win64\\chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        //driver = new ChromeDriver(); // WebDriver 초기화
        System.out.println("driver connect");
        driver = new ChromeDriver(service,new ChromeOptions()); // WebDriver 초기화
        System.out.println(((RemoteWebDriver) driver).getCapabilities().getBrowserVersion());
    }

    @Override
    public void fetchPage() {
        try {
            driver.get(url); // URL 로드
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            // 특정 요소가 나타날 때까지 대기
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CSS_SELECTOR)));
            Thread.sleep(10000);
            System.out.println("Page loaded successfully!");
        } catch (Exception e) {
            System.out.println("Failed to load the page: " + e.getMessage());
        }
    }

    @Override
    public void parsePage() {
        process(); // 크롤링 후 처리
    }
    // 식당 목록 : searchIframe
    // 식당 상세 : entryIframe
    @Override
    public void process() {
        System.out.println("Processing page: " + url);

        // 요소를 찾고 출력
        try {
            WebElement searchIframe = driver.findElement(By.id("searchIframe"));
            // Enter 식당 Iframe
            driver.switchTo().frame(searchIframe);
            // 식당 찾기
            List<WebElement> restaurantList = driver.findElements(By.cssSelector("li.rTjJo"));
            System.out.println("size : "+restaurantList.size());
            if (restaurantList.isEmpty()) {
                System.out.println("No elements found!");
            } else {
                for (WebElement restaurant : restaurantList) {
                    // 식당
                    WebElement title = restaurant.findElement(By.cssSelector("a.tzwk0"));
                    String restaurantImage="";
                    try{
                        restaurantImage  = restaurant.findElement(By.tagName("img")).getDomAttribute("src");
                    }catch (NoSuchElementException e){}
                    String titleText = restaurant.findElement(By.cssSelector("span.TYaxT")).getText();
                    if(restaurantService.isExistByTitle(titleText)){
                        continue;
                    }
                    RestaurantDto restaurantDto = new RestaurantDto(titleText, restaurantImage);

                    restaurantService.insertRestaurant(restaurantDto);
                    restaurantDto = restaurantService.getRestaurantByTitle(titleText);
                    System.out.println(restaurantDto);
                    // 클릭
                    title.click();

                    Thread.sleep(3000);
                    // Exit iframe
                    driver.switchTo().defaultContent();
                    // Find Sub Panel
                    WebElement subPanel = driver.findElement(By.cssSelector("div.jARwwU"));
                    // Find 식당 detail Iframe
                    WebElement entryIframe = subPanel.findElement(By.tagName("iframe"));
                    // Enter detail Iframe
                    driver.switchTo().frame(entryIframe);
                    // Click Menu Tab and Wait
                    List<WebElement> menuTabs = driver.findElements(By.cssSelector("a._tab-menu"));
                    WebElement menuTab;
                    for(WebElement menuTabCandi: menuTabs){
                        String menuTabText = menuTabCandi.findElement(By.tagName("span")).getText();
                        if(menuTabText.equals("메뉴")){
                            menuTab = menuTabCandi;
                            menuTab.click();
                            break;
                        }
                        System.out.println("not menu tab");
                    }
                    Thread.sleep(2000);
                    // Search Menu List
                    List<WebElement> liMenus;
                    try{
                        liMenus = driver.findElements(By.cssSelector("li.E2jtL"));
                        System.out.println("liMenus : " + liMenus.size());
                        //List<MenuDto> menus = new ArrayList<>();
                        int cnt = 0;
                        if(liMenus.size()!=0){
                            for(WebElement liMenu : liMenus){
                                System.out.println("menu" + cnt);cnt++;
                                String priceStr;
                                String description="";
                                String img ="";
                                try{
                                    priceStr = liMenu.findElement(By.cssSelector(".GXS1X")).findElement(By.tagName("em")).getText();
                                }catch (NoSuchElementException e){
                                    continue;
                                }
                                try{
                                    img = liMenu.findElement(By.tagName("img")).getAttribute("src");
                                    description = liMenu.findElement(By.cssSelector(".kPogF")).getText();
                                }catch (NoSuchElementException e){
                                }
                                String price= "";
                                String[] arr = priceStr.split(",");
                                for(String str : arr){
                                    price+=str;
                                }
                                System.out.println("price : "+price);
                                String menuName = liMenu.findElement(By.cssSelector(".yQlqY")).findElement(By.tagName("span")).getText();


                                if(isNumeric(price)){
                                    MenuDto menu = new MenuDto(
                                            // titleText,
                                            1,
                                            menuName,
                                            Integer.parseInt(price),
                                            img,
                                            description
                                    );
                                    menu.setRestaurantId(restaurantDto.getRestaurantId());
                                    System.out.println(menu);
                                    menuService.insertMenu(menu);
                                }
                            }
                        }else{
                            liMenus = driver.findElements(By.cssSelector("li.order_list_item"));
                            System.out.println("delivery restaurant");
                            System.out.println("liMenus size : " + liMenus.size());
                            for(WebElement liMenu : liMenus){
                                String menuName = liMenu.findElement(By.cssSelector("div.tit")).getText();
                                String priceStr="";
                                String description="";
                                String img="";
                                try{
                                    priceStr = liMenu.findElement(By.cssSelector(".price")).findElement(By.tagName("strong")).getText();
                                    description = liMenu.findElement(By.cssSelector(".detail_txt")).getText();
                                    img = liMenu.findElement(By.tagName("img")).getDomAttribute("src");
                                }catch (NoSuchElementException e1){
                                    continue;
                                }
                                String price= "";
                                String[] arr = priceStr.split(",");
                                for(String str : arr){
                                    price+=str;
                                }
                                if(isNumeric(price)){
                                    MenuDto menu = new MenuDto(
                                            //titleText,
                                            1,
                                            menuName,
                                            Integer.parseInt(price),
                                            img,
                                            description
                                    );
                                    menu.setRestaurantId(restaurantDto.getRestaurantId());
                                    System.out.println(menu);
                                    menuService.insertMenu(menu);
                                }
                            }
                        }
                    }catch (NoSuchElementException e){

                    }
                    driver.switchTo().defaultContent();
                    driver.switchTo().frame(searchIframe);
                }
            }
        } catch (Exception e) {
            System.out.println("Error during processing: " + e.getMessage());
        } finally {
            // 크롤링 완료 후 드라이버 종료
            closeDriver();
        }
    }

    // 드라이버 종료 메서드
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
            System.out.println("Driver closed successfully.");
        }
    }
}
