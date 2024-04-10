package org.sazhnevdi;

public class Starter {

    static {
        Initializer.init();
    }

    public static void main(String[] args) {
        try {
            Initializer.getMainMenuHandler().start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
