package com.remitly.exercise;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JSONResourceVerifierTest {
    private final String EXAMPLE_PATH = "src/main/resources/examples/";

    @Test
    void emptyResource() throws IOException {
//        given
        String path = EXAMPLE_PATH + "emptyResource.json";
//        when
        boolean result = JSONResourceVerifier.verify(path);
//        then
        assertFalse(result);
    }

    @Test
    void withResources() throws IOException {
//        given
        String path = EXAMPLE_PATH + "withResources.json";
//        when
        boolean result = JSONResourceVerifier.verify(path);
//        then
        assertTrue(result);
    }

    @Test
    void oneOfResourcesIsEmpty() throws IOException {
//        given
        String path = EXAMPLE_PATH + "oneOfResourcesIsEmpty.json";
//        when
        boolean result = JSONResourceVerifier.verify(path);
//        then
        assertFalse(result);
    }

    @Test
    void invalidRolePolicy() throws IOException {
//        given
        String path = EXAMPLE_PATH + "invalidRolePolicy.json";
//        when
        JSONException exception = assertThrows(JSONException.class, () -> JSONResourceVerifier.verify(path));
//        then
        String expectedMessage = "not found";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void fileNotFound() {
//        given
        String path = EXAMPLE_PATH + "notExist.json";
//        when
        IOException exception = assertThrows(IOException.class, () -> JSONResourceVerifier.verify(path));
//        then
        assertTrue(exception.getMessage().contains("notExist.json"));
    }
}