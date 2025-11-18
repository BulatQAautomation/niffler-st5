package guru.qa.niffler.config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserConfig {

    public static void configure() {
        Configuration.browser = "chrome";
        Configuration.browserCapabilities = getChromeOptions();
        Configuration.browserSize = "1920x1080";
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        
        // Критичные опции для MacOS
        options.addArguments("--remote-debugging-port=0");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        
        // Headless режим - более стабилен на MacOS
//        options.addArguments("--headless=new");
        
        // Дополнительные опции для стабильности
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");
        
        // Явно указываем путь к Chrome
        options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        
        return options;
    }
}

