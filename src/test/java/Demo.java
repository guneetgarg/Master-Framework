import com.utilities.ApiUtils;
import com.utilities.BaseTest;
import com.utilities.Browser;
import com.utilities.BrowserDriverImpl;
import io.restassured.RestAssured;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Demo extends BaseTest {

    static Logger logger = Logger.getLogger(Demo.class);

    @Test
    public void ddd3() {
//        BrowserDriverImpl driver = Browser.getBrowserInstance();
//        driver.get("http://www.google.com");
// //       driver.getTitle();
//
//        driver.findElement(By.xpath("//*[@id='gbw']/div/div/div[1]/div[1]/a")).click();

        RestAssured.baseURI = "https://reqres.in/api";

        ApiClassBody body = new ApiClassBody();
        body.setJob("leader");
        body.setName("morpheus");

        new ApiUtils().callApi("users", "post", Collections.emptyList(), body, "");


    }

}


