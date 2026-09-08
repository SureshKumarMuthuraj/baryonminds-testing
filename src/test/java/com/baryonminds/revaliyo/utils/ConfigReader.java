package com.baryonminds.revaliyo.utils;

import java.io.InputStream;

import io.cucumber.messages.ndjson.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.messages.ndjson.internal.com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigReader {

    private static JsonNode config;

    static {
        loadConfig();
    }

    private static void loadConfig() {

        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config/androidEmulator.json")) {

            if (input == null) {
                throw new RuntimeException(
                        "Configuration file not found: config/android.json");
            }

            ObjectMapper mapper = new ObjectMapper();
            config = mapper.readTree(input);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to load android.json", e);
        }
    }

    public static String get(String key) {

        JsonNode value = config.get(key);

        if (value == null) {
            throw new RuntimeException(
                    "Configuration key not found: " + key);
        }

        return value.asText();
    }

    public static boolean getBoolean(String key) {

        JsonNode value = config.get(key);

        if (value == null) {
            throw new RuntimeException(
                    "Configuration key not found: " + key);
        }

        return value.asBoolean();
    }
}