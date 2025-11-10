package guru.qa.niffler.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;

public class SpendingTest {

    @Test
    void doLogin(){
        Selenide.open("http://127.0.0.1:3000/");
        $("a[href='/redirect']").click();
        $("input[name=username]").setValue("Bulat");
        $("input[name=password]").setValue("12345");
        $("input[name=submit]").submit();
    }

    @Test
    void spendingShouldBeDeletedAfter() {
    }
}
