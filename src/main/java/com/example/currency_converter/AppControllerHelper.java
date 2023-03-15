/**
 * @apiNote This class has all the function control that are getting use
 * in "CurrencyExchangerController.java'
 */

package com.example.currency_converter;

import java.util.SortedSet;

public class AppControllerHelper {
    /**
     * @implNote Make all the functions static so that we can use it without making instant of the class
     */

    public static boolean checkValidCurrency(SortedSet<Object> currencies, String currency) {
        return currencies.contains(currency);
    }

    public static boolean checkValidAmount(int amount) {
        return amount < 1;
    }

    public static void displayInvalidNotification() {

    }
}


