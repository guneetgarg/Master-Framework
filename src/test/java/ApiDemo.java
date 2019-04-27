import com.utilities.ApiUtils;
import com.utilities.BaseTest;
import com.utilities.Environment;
import io.restassured.RestAssured;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ApiDemo extends BaseTest {

    static Logger logger = Logger.getLogger(ApiDemo.class);
    ApiUtils apiUtils = new ApiUtils();

    @Test
    public void getMethodDemo() {
        logger.info("========== Start of Test Case ==========");
        logger.info("Step 1 -> " + "Get Method");

        RestAssured.baseURI = "https://reqres.in/api";

        apiUtils.callApi("users/2", "get", Collections.emptyList(), null, null);
    }

    @Test
    public void postMethodDemo() {
        logger.info("========== Start of Test Case ==========");
        logger.info("Step 2 -> " + "Post Method");

        RestAssured.baseURI = "https://reqres.in/api";

        Map<Object, Object> jsonTemplateMap = new HashMap<>();
        jsonTemplateMap.put("name", "");
        jsonTemplateMap.put("job", "");

        Map<Object, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "morpheus");
        jsonAsMap.put("job", "leader");

        apiUtils.callApi("users", "post", Collections.emptyList(), jsonTemplateMap, jsonAsMap);
    }


    @Test
    public void putMethodDemo() {
        logger.info("========== Start of Test Case ==========");
        logger.info("Step 3 -> " + "Put Method");

        RestAssured.baseURI = "https://reqres.in/api";

        Map<Object, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.clear();
        jsonAsMap.put("smt", "morpheus");
        jsonAsMap.put("job", "zion resident");
        apiUtils.callApi("users/2", "put", Collections.emptyList(), jsonAsMap);
    }

    @Test
    public void StringAsCallingParamDemo() {
        logger.info("========== Start of Test Case ==========");
        logger.info("Step 4 -> " + "String as parameter");

        RestAssured.baseURI = "https://reqres.in/api";

        Map<Object, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.clear();
        jsonAsMap.put("smt", "morpheus");
        jsonAsMap.put("job", "zion resident");
        apiUtils.callApi("users/2", "put", Collections.emptyList(), jsonAsMap, jsonAsMap);
    }

    @Test
    public void classAsTemplateDemo() {
        logger.info("========== Start of Test Case ==========");
        logger.info("Step 5 -> " + "class As Template");

        RestAssured.baseURI = "https://reqres.in/api";

        ApiClassBody body = new ApiClassBody();
        body.setJob("leader");
        body.setName("morpheus");

        apiUtils.callApi("users", "post", Collections.emptyList(), body, "");

    }

}