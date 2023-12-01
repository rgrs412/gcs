package org.example.activity1;

import org.example.utils.Input;
import org.example.utils.Output;

public class WeatherService {
    private OpenWeatherRestApi openWeatherRestApi;

    public WeatherService() {
        openWeatherRestApi = new OpenWeatherRestApi();
    }

    /**
     * Get city name from user input and display weather data of the city
     */
    public void getAndDisplayCurrentWeatherDataByCity() {
        System.out.println("Enter the city name: ");
        String input = Input.getUserInput().toLowerCase();

        String weatherData = Output.prettyPrintJson(
                openWeatherRestApi.getCurrentWeatherDataByCity(input).getBody().toString()
        );
        System.out.println(weatherData + "\n");
        Input.promptReturnToMenu();
    }

    /**
     * Gets city name from user input and add the city to {@link Favorite}
     * if city is not already favorited and limit to # of favorite cities
     * not reached.
     *
     * @param favorite Object that stores all the favorite cities
     */
    public void addCityToFavorite(Favorite<City> favorite) {
        System.out.println("Enter the city name to add: ");
        String input = Input.getUserInput().toLowerCase();

        if(favorite.isFavorited(input)) {
            System.out.println(String.format("City with name [ %s ] is already favorited.", input));
            return;
        }

        if(favorite.getFavorites().size() == favorite.getLimit()) {
            System.out.println(String.format("Favorite cities limit (%d) reached.", favorite.getLimit()));
            return;
        }

        if(openWeatherRestApi.isValidCity(input)) {
            City city = new City(input);
            favorite.addFavorite(city);
        }
    }

    public void getAndDisplayCurrentWeatherDataByFavoriteCities(Favorite<City> favorite) {
        for(String key : favorite.getFavorites().keySet()) {
            String weatherData = Output.prettyPrintJson(
                    openWeatherRestApi.getCurrentWeatherDataByCity(key).getBody().toString()
            );
            System.out.println(key.toUpperCase() + "\n" + weatherData + "\n");
        }
        Input.promptReturnToMenu();
    }

    public void updateFavoriteCities(Favorite<City> favorite) {
        favorite.display();
        System.out.println("Enter the city name to update: ");
        String input = Input.getUserInput().toLowerCase();

        if(favorite.isFavorited(input)) {
            favorite.getFavorites().remove(input);
            addCityToFavorite(favorite);
            return;
        }

        System.out.println(String.format("[ %s ] is not a favorited city. Try again.%n", input));
    }
}
