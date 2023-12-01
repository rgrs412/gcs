package org.example.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuPage {
    private String title;
    private List<MenuPageOption> menuPageOptions;

    public MenuPage(String title) {
        this.title = title;
        menuPageOptions = new ArrayList<>();
    }

    public List<MenuPageOption> getMenuPageOptions() {
        return menuPageOptions;
    }

    public void addMenuPageOption(MenuPageOption menuPageOption) {
        menuPageOptions.add(menuPageOption);
    }

    public void display() {
        int optionNumber = 1;

        System.out.println(title);
        for(MenuPageOption menuPageOption : menuPageOptions) {
            System.out.printf("%s) %s%n", optionNumber, menuPageOption.getName());
            optionNumber++;
        }
    }

    public void processOption(int option) {
        menuPageOptions.get(option-1).process();
    }
}
