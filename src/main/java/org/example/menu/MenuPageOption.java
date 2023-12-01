package org.example.menu;


public class MenuPageOption {
    private String name;
    private Runnable runnable;

    public MenuPageOption(String name) {
        this.name = name;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public void process() {
        runnable.run();
    }

    public String getName() {
        return name;
    }
}
