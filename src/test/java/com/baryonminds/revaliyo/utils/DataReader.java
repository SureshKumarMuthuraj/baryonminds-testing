package com.baryonminds.revaliyo.utils;

import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

    private static JsonNode data;

    private static void loadData(String dataFileName) {

        try (InputStream input = DataReader.class
                .getClassLoader()
                .getResourceAsStream("testdata/"+dataFileName+"")) {

            if (input == null) {
                throw new RuntimeException(
                        "Configuration file not found: testdata/"+dataFileName+"");
            }

            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readTree(input);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to load "+dataFileName+"", e);
        }
    }
    
    public static JsonNode getTestData(String dataFileName) {
        loadData(dataFileName);
		return data;
    }

    public static String get(String key) {

        JsonNode value = data.get(key);

        if (value == null) {
            throw new RuntimeException(
                    "Configuration key not found: " + key);
        }

        return value.asText();
    }

    public static boolean getBoolean(String key) {

        JsonNode value = data.get(key);

        if (value == null) {
            throw new RuntimeException(
                    "Configuration key not found: " + key);
        }

        return value.asBoolean();
    }
}