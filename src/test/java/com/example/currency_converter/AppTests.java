package com.example.currency_converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTests {
    final ManipulateAPIData API_DATA_TEST = new ManipulateAPIData();

    @Test
    void apiDataMapSizeTest() {
        assertEquals(191, API_DATA_TEST.apiDataMapSize());
    }

    @Test
    void getCurrentDateTest() {
        assertEquals(String.valueOf(java.time.LocalDate.now()), API_DATA_TEST.getCurrentDate());
    }
}