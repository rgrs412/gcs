package org.example;

import org.example.activity1.City;
import org.example.activity1.Favorite;
import org.example.activity1.WeatherService;
import org.example.activity2.Util;
import org.example.menu.Menu;
import org.example.menu.MenuPage;
import org.example.menu.MenuPageOption;

/**
 * The main method configures the menu that will be displayed on the command-line.
 *
 * Configuration Steps
 * 1) Create a {@link Menu}
 * 2) Create all the {@link MenuPage} for the Menu
 * 3) Create all the {@link MenuPageOption} for each {@link MenuPage}
 * 4) Assign each {@link MenuPageOption} a Java Functional Interface
 *    that will be executed when the user selects the option.
 * 5) Add each {@link MenuPageOption} to their respective {@link MenuPage}
 * 6) Start the {@link Menu}
 */
public class Main {
    public static void main(String[] args) {
        // 1) Create a Menu
        Menu menu = new Menu();

        // 2) Create all the MenuPage for the Menu
        MenuPage page1 = new MenuPage("Activities");
        MenuPage page2 = new MenuPage("Coding Activity 1 - Making API Calls");
        MenuPage page3 = new MenuPage("Coding Activity 2 - Pseudocode to code");

        // 3) Create all the MenuPageOption for each MenuPage
        MenuPageOption page1_Option1 = new MenuPageOption("Coding Activity 1 - Making API Calls");
        MenuPageOption page1_Option2 = new MenuPageOption("Coding Activity 2 - Pseudocode to code");
        MenuPageOption page1_Option3 = new MenuPageOption("Exit");

        // 4) Assign each MenuPageOption a Java Functional Interface
        // that will be executed when the user selects the option.
        page1_Option1.setRunnable(() -> menu.setCurrentPage(page2));
        page1_Option2.setRunnable(() -> menu.setCurrentPage(page3));
        page1_Option3.setRunnable(() -> System.exit(0));

        // 5) Add each MenuPageOption to their respective MenuPage
        page1.addMenuPageOption(page1_Option1);
        page1.addMenuPageOption(page1_Option2);
        page1.addMenuPageOption(page1_Option3);

        //Repeats Step 3 to 5 for Page 2
        MenuPageOption page2_Option1 = new MenuPageOption("Search for Weather Details of a City");
        MenuPageOption page2_Option2 = new MenuPageOption("Add a City to Favourites");
        MenuPageOption page2_Option3 = new MenuPageOption("List Favourite Cities");
        MenuPageOption page2_Option4 = new MenuPageOption("Update Favourite Cities");
        MenuPageOption page2_Option5 = new MenuPageOption("Return to Previous Page");

        WeatherService weatherService = new WeatherService();
        Favorite<City> favorite = new Favorite<>(3);
        page2_Option1.setRunnable(() -> weatherService.getAndDisplayCurrentWeatherDataByCity());
        page2_Option2.setRunnable(() -> weatherService.addCityToFavorite(favorite));
        page2_Option3.setRunnable(() -> weatherService.getAndDisplayCurrentWeatherDataByFavoriteCities(favorite));
        page2_Option4.setRunnable(() -> weatherService.updateFavoriteCities(favorite));
        page2_Option5.setRunnable(menu::goToPreviousPage);

        page2.addMenuPageOption(page2_Option1);
        page2.addMenuPageOption(page2_Option2);
        page2.addMenuPageOption(page2_Option3);
        page2.addMenuPageOption(page2_Option4);
        page2.addMenuPageOption(page2_Option5);

        //Repeats Step 3 to 5 for Page 3
        MenuPageOption page3_Option1 = new MenuPageOption("Sort and Find Medium");
        MenuPageOption page3_Option2 = new MenuPageOption("Generate Random Array By Size");
        MenuPageOption page3_Option3 = new MenuPageOption("Return to Previous Page");

        page3.addMenuPageOption(page3_Option1);
        page3.addMenuPageOption(page3_Option2);
        page3.addMenuPageOption(page3_Option3);

        page3_Option1.setRunnable(() -> Util.getUserInputAndSortAndFindMedium());
        page3_Option2.setRunnable(() -> Util.getUserInputAndGenerateRandomNumbersBySize());
        page3_Option3.setRunnable(menu::goToPreviousPage);

        // 6) Start the Menu
        menu.start(page1);
    }
}