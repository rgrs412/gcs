package org.example.activity1;

import java.util.HashMap;
import java.util.Map;

/**
 * A generic class to store a collection of favorite {@link Object}.
 * @param <T>
 */
public class Favorite <T extends Nameable> {
    private Map<String, T> favorites;
    private int limit;
    public Favorite(int limit) {
        this.limit = limit;
        favorites = new HashMap<>();
    }

    public void addFavorite(T thing) {
        if(!isFavorited(thing.getName()) && favorites.size() < limit) {
            favorites.put(thing.getName(), thing);
        }
    }

    public boolean isFavorited(String name) {
        return favorites.containsKey(name);
    }

    public Map<String, T> getFavorites() {
        return favorites;
    }

    public int getLimit() {
        return limit;
    }

    public void display() {
        System.out.println("Favorite Cities");
        for(String cityName : favorites.keySet()) {
            System.out.printf("- %s%n", cityName);
        }
    }
}
