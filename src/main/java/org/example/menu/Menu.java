package org.example.menu;

import org.example.utils.Input;

import java.util.ArrayList;
import java.util.List;

/**
 * Menu class that functions like a mini web browser.
 */
public class Menu {
    private List<MenuPage> pageVisitHistory;
    private MenuPage currentPage;

    public Menu() {
        pageVisitHistory = new ArrayList<>();
    }

    public void start(MenuPage rootPage) {
        if(currentPage == null) {
            setCurrentPage(rootPage);

            while(true) {
                displayCurrentPage();

                System.out.println("\nEnter the option #: ");
                int input = Input.readIntInRange(1, currentPage.getMenuPageOptions().size());

                processOption(input);
            }
        }
    }

    public void processOption(int option) {
        currentPage.processOption(option);
    }

    private void displayCurrentPage() {
        currentPage.display();
    }

    public void setCurrentPage(MenuPage menuPage) {
        pageVisitHistory.add(currentPage);
        currentPage = menuPage;
    }

    public void goToPreviousPage() {
        currentPage = pageVisitHistory.remove(pageVisitHistory.size() - 1);
    }
}
