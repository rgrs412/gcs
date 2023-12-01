package org.example.activity1;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class OpenWeatherRestApi {
    //For testing purposes
    public static String API_KEY = "519ed720d8465cc1b1faee1f7e5bd6d5";
    public static String URL = "https://api.openweathermap.org/data/2.5/weather";

    /**
     * Utilizes the {@link Unirest} library to make an API call to get
     * current weather data for city
     * @param city
     * @return {@link HttpResponse}
     */
    public HttpResponse<JsonNode> getCurrentWeatherDataByCity(String city) {
        HttpResponse<JsonNode> response;

        response = Unirest.get(URL)
                .header("accept", "application/json")
                .queryString("q", city)
                .queryString("appid", API_KEY)
                .asJson();

        return response;
    }

    public boolean isValidCity(String city) {
        boolean isValidCity = true;
        HttpResponse<JsonNode> response = getCurrentWeatherDataByCity(city);

        if(!response.isSuccess()) {
            String failureMessage = response.getBody().getObject().getString("message");
            System.out.println(failureMessage + "\n");
            isValidCity = false;
        }

        return isValidCity;
    }
}
