package main;

import gui.Display;
import io.MenuLoader;
import menu.CustomerOrder;
import pizza.CustomPizza;
/**
 * The entry point for launching the application.
 * The application is a simulation of a Pizza Shop. It forms part of a large scale project,
 * that will later be expected interface with a GUI to support the running of a world leading pizza shop.
 */

public class Launcher extends Object {
    /**
     * A place you can use to call the CustomerOrder class.
     * A simple entry point initiating and launching anonymously
     * @param args - provides no usable function
     */
    public static void main(String[] args) {
        new CustomerOrder();
    }
}
