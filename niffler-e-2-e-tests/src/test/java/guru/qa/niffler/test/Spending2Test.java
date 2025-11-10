package guru.qa.niffler.test;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.BrowserConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Spending2Test {

    @BeforeAll
    static void setUp() {
        BrowserConfig.configure();
    }

    @Test
    void smth() {
        Selenide.open("http://127.0.0.1:3000/");
    }
}
