package org.sazhnevdi;

public class Starter {

    static {
        Initializer.init();
    }

    public static void main(String[] args) {
        Initializer.getMainMenuHandler().start();
    }
}
