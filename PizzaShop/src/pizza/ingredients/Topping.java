package pizza.ingredients;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * The Topping class represents possible toppings that can be placed on a Pizza.
 * Topping's are created using the createTopping(String, boolean) method which in turn
 * calls a private constructor. A Topping's name should be made uppercase such
 * that: createTopping("baCOn",false) would save the topping with a name of "BACON".
 *
 * The Topping class is meant to be a dynamic enum such that given a name the correct
 * Topping is returned by the valueOf(String) method. Similar to createTopping(String, boolean)
 * this method will allow any case of string to match. That is, an input of
 * "bacon", "BaCON", "BACON" would all return the topping with name "BACON".
 *
 * The Topping's that are available in the values() method will be any previously created toppings.
 *
 * <b>NOTE: </b> Unlike a normal enum class you <b>DO</b> need to implement
 * the values() and valueOf(String) methods as part of the assignment.
 */
public class Topping extends Object {

    /**
     *The price of the topping is 2.0.
     */
    public static final double PRICE = 2.0;

    /**
     * The name of the toppings that are created
     */
    private String toppingName;

    /**
     * To know if topping is vegan or not
     */
    private boolean thisIsVegan;

    /**
     * the arraylist to store the toppings that are created
     */
    private static ArrayList<Topping> listOfTopping = new ArrayList<Topping>();

    /**
     * Create a new topping with the specified name and began boolean state.
     * @param name - name of the topping
     * @param isVegan - if the topping is vegan or not
     */
    private Topping(String name, boolean isVegan) {
        this.toppingName = name.toUpperCase();
        this.thisIsVegan = isVegan;
    }

    /**
     * Creates a new topping with the specified name and vegan boolean state.
     * This new topping is not returned but rather should be accessed by valueOf(String)
     * @param name - name of the topping
     * @param isVegan - if the topping is vegan or not
     * @throws IllegalArgumentException - if name == `null` or topping with
     * that name has already been created or name.isBlank() is true
     */
    public static void createTopping(String name, boolean isVegan) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException();
        }
        name = name.toUpperCase();
        for (int i = 0; i < listOfTopping.size(); i++) {
            if (listOfTopping.get(i).toppingName.equals(name)) {
                throw new IllegalArgumentException();
            }
        }
        listOfTopping.add(new Topping(name, isVegan));
    }

    /**
     * Returns a Topping that has previously been defined by this class with the specified name.
     * The string must match exactly an identifier used to match
     * a Topping's name. (Extraneous whitespace characters are not permitted.)
     * @param name - name of the topping
     * @return the topping with the specified name
     * @throws IllegalArgumentException - if this class has no topping with the specified name
     * @throws NullPointerException - if the argument is null
     */
    public static Topping valueOf(String name) throws
            IllegalArgumentException, NullPointerException {
        if (name == null) {
            throw new NullPointerException();
        }
        name = name.toUpperCase();
        for (int i = 0; i < listOfTopping.size(); i++) {
            if (listOfTopping.get(i).toppingName.equals(name)) {
                return listOfTopping.get(i);
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Returns an array containing the Topping's that have been
     * defined by this class, in the order they are declared.
     * @return an array containing the topping's that have been
     * defined by this class, in the order they are declared.
     */
    public static Topping[] values() {
        Topping[] ret = new Topping[listOfTopping.size()];
        listOfTopping.toArray(ret);
        return ret;
    }

    /**
     * Resets Topping such that values() returns an empty Topping[].
     */
    public static void resetToppings() {
        listOfTopping.clear();
    }

    /**
     * Returns the topping's name
     * @return topping name
     */
    public String toString() {
        return toppingName;
    }

    /**
     * Returns the vegan boolean property value The isSpicy boolean
     * property value was flat out abandoned because there were fierce
     * arguments about what constitutes a spicy topping. Dr Java considered
     * sweetcorn to be spicy, but his team disagreed. The debate was
     * so fierce that Dr Java was forced to concede and abandon the isSpicy property.
     * @return boolean for vegan friendly item
     */
    public boolean isVegan() {
        return thisIsVegan;
    }
}
