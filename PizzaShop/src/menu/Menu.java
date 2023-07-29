package menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class which defines a menu that contains items that can be ordered from.
 * All classes that implement MenuItem must be registered with this Menu, which will allow them to be ordered in the simulation.
 *
 * A Menu starts as empty but will grow over the programs run time.
 */
public class Menu extends Object {

    /**
     * the arraylist to store the item of the menu
     */
    private List<MenuItem> items = new ArrayList<MenuItem>();

    /**
     * the menu
     */
    private static Menu menu = new Menu();

    /**
     * default constructor
     */
    private Menu() {}

    /**
     * Returns the singleton instance of the menu.
     * @return singleton instance
     */
    public static Menu getInstance() {
        return menu;
    }

    /**
     * Returns the items that have been registered with the Menu.
     * Adding or removing elements from the returned list should NOTaffect the original list.
     * @return the items of the menu
     */
    public List<MenuItem> getItems() {
        List<MenuItem> tempItems = items;
        return tempItems;
    }

    /**
     * Registers a menu item with this menu.
     * The menu will only register item's that it has not seen before.
     *
     * The menu has not seen an item if the Menu does not contain
     * any item (existingItem) such that Objects.equals(item,existingItem) == true.
     * @param item - a menu item to be registered to the menu
     */
    public void registerMenuItem(MenuItem item) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.get(i).equals(item)) {
                return;
            }
        }
        this.items.add(item);
    }

    /**
     * Returns a menu item from the list of loaded menu items
     * @param index - the index of the item in the list
     * @return The item that has been found.
     * @throws IndexOutOfBoundsException - if index does not exist or array is null
     */
    public MenuItem get(int index) throws IndexOutOfBoundsException {
        return items.get(index);
    }

    /**
     * Removes all loaded items from the Menu such that getItems() will return a list of size 0.
     */
    public void clear() {
        items.clear();
    }
}
