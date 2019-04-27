package com.utilities;

import org.testng.ITestContext;

import java.util.Map;

public class Environment {
    private static Map<String, String> propertyValuePair = null;

    public static void loadProperties(ITestContext context) {
        propertyValuePair = context.getCurrentXmlTest().getAllParameters();
    }

    public static String getParam(final String key) {
        String valueCandidate = System.getProperty(key);
        if (valueCandidate != null) {
            return valueCandidate;
        } else if ((valueCandidate = System.getenv("TESTNG_" + key)) != null) {
            return valueCandidate;
        } else {
            try {
                return propertyValuePair.get(key).toString();
            } catch (NullPointerException npe) {
                System.out
                        .println("No browser instance present in map for Thread id - "
                                + Thread.currentThread().getId());

                return "invalidValue";
            }
        }
    }

    public static String getBrowserType(){
        return "";
    }
}
