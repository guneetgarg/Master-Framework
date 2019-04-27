package com.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    static Logger logger = Logger.getLogger(ApiUtils.class);

    public void print() {
        System.out.println("HELLOOO");
    }

    //--------------------CALLING API---------------------------------------------

    /*
     * apiTemplateParam and apiCallingParams = MAP
     * apiTemplateParam = String and apiCallingParams = MAP
     * apiTemplateParam = class and apiCallingParams = blank
     */

    public Response callApi(String endpoints, String method, List<Header> headerlist, Object apiTemplateParam, Object... apiCallingParams) {

        logger.info("Endpoint is " + endpoints);
        Headers headers = new Headers(headerlist);
        Response response = null;

        if (apiTemplateParam != null && apiCallingParams.length > 0) {
            if (apiTemplateParam instanceof Map && apiCallingParams[0] instanceof Map)
                ((HashMap<String, String>) apiTemplateParam).putAll((Map<? extends String, ? extends String>) apiCallingParams[0]);
            else if (apiTemplateParam instanceof String && apiCallingParams[0] instanceof Map) {
                StrSubstitutor sub = new StrSubstitutor((Map) apiCallingParams[0]);
                apiTemplateParam = sub.replace(apiTemplateParam);
            }
        }

        switch (method) {
            case "get":
                response = given()
                        .headers(headers)
                        .get(endpoints);
                break;
            case "post":
                response = given()
                        .contentType(ContentType.JSON)
                        .headers(headers)
                        .body(apiTemplateParam).post(endpoints);
                break;
            case "put":
                response = given()
                        .contentType(ContentType.JSON)
                        .headers(headers)
                        .body(apiTemplateParam).when().put(endpoints);
                break;
            default:
                logger.info("Invalid Method Requested" + method);
                return null;
        }
        logger.info("Response is " + response.asString());
        return response;
    }

    /**
     * return value from response header based on key
     *
     * @param response
     * @param key
     * @return String
     */
    public String responseHeader(Response response, String key) {
        logger.info("Response Header " + response.headers());
        String value = response.headers().get(key).getValue();

        return value;
    }

    /**
     * return response
     *
     * @param response
     * @return int
     */
    public int responseCode(Response response) {
        logger.info("Response Code " + response.getStatusCode());
        return response.getStatusCode();
    }


    //--------------------Extracting data from JSON---------------------------------------------

    /**
     * This method parses a Response return as JsonNode
     */
    public JsonNode convertResponseToJsonNode(Object jsonData) {
        JsonNode jsonNode = null;
        try {
            if (jsonData instanceof String)
                jsonNode = new ObjectMapper().readTree(jsonData.toString());
            else if (jsonData instanceof Response)
                jsonNode = new ObjectMapper().readTree(((Response) jsonData).asString());
            return jsonNode;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }


    public Object getValueFromJson(Object node, String key) {
        JsonNode current = null;
        if (node instanceof JsonNode)
            current = (JsonNode) node;
        else if (node instanceof Response)
            current = convertResponseToJsonNode(node);
        Object value = current.findValue(key);

        System.out.println(value);
        return value;
    }

    public List<JsonNode> getArrayFromJson(Object node, String key) {
        JsonNode current = null;
        if (node instanceof JsonNode)
            current = (JsonNode) node;
        else if (node instanceof Response)
            current = convertResponseToJsonNode(node);
        return current.findValues(key);
    }

}