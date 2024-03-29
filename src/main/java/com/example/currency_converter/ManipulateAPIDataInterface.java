package com.example.currency_converter;

import java.util.SortedSet;

// This class the interface for the function that will be useful for
// retrieving the information from the api.
public interface ManipulateAPIDataInterface {
    // This will give use the most recent date for the person
    // using the app, the api date is bit off, thus we will
    // get date base on the persons' location.
    String getCurrentDate();

    // It will return all the currencies names are in the api
    SortedSet<Object> getAllCurrenciesNames();

    // This will return the rate of the given country currency
    // NOTE: This will only the rate on the conversions. i.e. 3.3%
    double getRateForGivenCurrency(String currency);

    // This will return 'all' the currencies with their rates
    String getCurrenciesNamesWithRates();

    // This function will return the conversion of the currencies
    double calculateRates(String amount, String currency1, String currency2);
}
