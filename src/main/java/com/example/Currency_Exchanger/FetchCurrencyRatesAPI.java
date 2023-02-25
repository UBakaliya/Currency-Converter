/**
 * @author Uvaish Bakaliya
 * @apiNote This class the online getting hte data from the api.
 * @implNote If the data is not retrieve successfully then it will give error.
 * API Used: "https://cdn.moneyconvert.net/api/latest.json"
 */

package com.example.Currency_Exchanger;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class FetchCurrencyRatesAPI {

    // JSONParser parse = new JSONParser;
    // Given the api it will return the format the api is in:
    public String fetchAPI(String API_URL) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(API_URL).openConnection();
            conn.setRequestMethod("GET"); // since we are getting the data

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            // get all the data from the api
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // if the api data is successful retrieve then:
            return response.toString();
        } catch (Exception e) {
            System.out.println("ERROR. Loading API;");
        }
        return "200";
    }
}
