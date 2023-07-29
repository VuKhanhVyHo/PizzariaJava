package pizza;

import exceptions.TooManyToppingsException;
import menu.MenuItem;
import pizza.ingredients.Bases;
import pizza.ingredients.Cheeses;
import pizza.ingredients.Sauces;
import pizza.ingredients.Topping;

import java.util.ArrayList;
import java.util.List;

/**
 * Pizza combines the required basic elements of a pizza, being the base, sauce and cheese, and 5 additional toppings.
 * This is the Pizza template to be extended for more rewarding pizza toppings
 */
public abstract class Pizza extends Object implements Bases, Sauces, Cheeses, MenuItem {
    /**
     * The maximum number of toppings that can be placed on a pizza. (5)
     */
    public static final int MAX_TOPPINGS = 5;

    /**
     * The size of the pizza
     */
    private Bases.BaseSize size;

    /**
     * Sauce of the pizza
     */
    private Sauces.Sauce sauce;

    /**
     * Cheese of the pizza
     */
    private Cheeses.Cheese cheese;

    /**
     * name of this pizza
     */
    private String name;

    /**
     * the list of topping of this pizza
     */
    private List<Topping> originalListToppings = new ArrayList<Topping>();

    /**
     * Default constructor which creates a medium cheese pizza with tomato sauce.
     * A cheese pizza has a tomato sauce base, mozzarella cheese and no additional toppings.
     * This pizza should be called "Dr Java's Pizza" unless/until another name is set.
     */
    public Pizza() {
        this.size = Bases.BaseSize.MEDIUM;
        this.sauce = Sauces.Sauce.TOMATO;
        this.cheese = Cheeses.Cheese.MOZZARELLA;
        this.name = "Dr Java's Pizza";
    }

    /**
     * Creating the base requirements of the pizza with no toppings.
     * This pizza should be called "Dr Java's Pizza" unless/until another name is set.
     * @param size - The size of the pizza base as defined by Bases
     * @param sauce - The sauce on the pizza as defined by Sauces
     * @param cheese - The cheese on the pizza as defined by Cheeses
     * @throws IllegalArgumentException - if size, sauce or cheese is null.
     */
    public Pizza(Bases.BaseSize size, Sauces.Sauce sauce,
                 Cheeses.Cheese cheese) throws IllegalArgumentException {
        if (size == null || sauce == null || cheese == null) {
            throw new IllegalArgumentException();
        }
        this.size = size;
        this.sauce = sauce;
        this.cheese = cheese;
        this.name = "Dr Java's Pizza";
    }

    /**
     * Creating a Pizza with a set base size, sauce, cheese and list of toppings.
     * This pizza should be called "Dr Java's Pizza" unless/ until another name is set.
     * @param size - The size of the pizza base as defined by Bases
     * @param sauce - The sauce on the pizza as defined by Sauces
     * @param cheese - The cheese on the pizza as defined by Cheeses
     * @param toppings - The list of toppings on the Pizza, List of Toppings
     * @throws TooManyToppingsException - if toppings.size() > 5
     * @throws IllegalArgumentException - if size, sauce, cheese or toppings is null.
     */
    public Pizza(Bases.BaseSize size, Sauces.Sauce sauce,
                 Cheeses.Cheese cheese, List<Topping> toppings)
            throws TooManyToppingsException, IllegalArgumentException {
        if (toppings == null || size == null || sauce == null || cheese == null) {
            throw new IllegalArgumentException();
        }
        if (toppings.size() > 5) {
            throw new TooManyToppingsException("Too many topping");
        }
        this.size = size;
        this.sauce = sauce;
        this.cheese = cheese;
        this.name = "Dr Java's Pizza";
        this.originalListToppings = toppings;
    }

    /**
     * Returns the list of toppings that are on this pizza.
     * Adding or removing elements from the returned list should NOT affect the
     * original list.
     * @return the list of toppings on this pizza.
     */
    public List<Topping> getToppings() {
        return new ArrayList<Topping>(this.originalListToppings);
    }

    /**
     * Returns the price of the pizza base size, defined in the BaseSize enum, and adds the price
     * of each topping on the pizza.
     * @return the price of the Pizza.
     */
    public double getTotalPrice() {
        return this.size.getPrice() + this.originalListToppings.size() * Topping.PRICE;
    }

    /**
     * Returns the name of this pizza.
     * @return a String name of this pizza.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name for this pizza.
     * @param name - String providing a replacement name of the pizza
     * @throws IllegalArgumentException - if name is null or name.isBlank();
     */
    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    /**
     * Set the size of this pizza base.
     * @param size - the size of this pizza
     */
    public void set(Bases.BaseSize size) {
        this.size = size;
    }

    /**
     * Set the sauce on this pizza.
     * @param sauce - the sauce on this pizza.
     */
    public void set(Sauces.Sauce sauce) {
        this.sauce = sauce;
    }

    /**
     * Set the cheese on this pizza.
     * @param cheese - the cheese on this pizza.
     */
    public void set(Cheeses.Cheese cheese) {
        this.cheese = cheese;
    }

    /**
     * Returns the hash code of this pizza.
     * Two pizza's that are equal according to the equals(Object) method should have the same hash code.
     * @return hash code of this pizza.
     */
    public int hashCode() {
        int code = 1;
        int prime = 3;
        code = code * prime + ((this.size == null) ? 0 : this.size.hashCode());
        code = code * prime + ((this.cheese == null) ? 0 : this.cheese.hashCode());
        code = code * prime + ((this.sauce == null) ? 0 : this.sauce.hashCode());
        code = code * prime + ((this.originalListToppings == null) ? 0 :
                this.originalListToppings.hashCode());
        return code;
    }

    /**
     * Returns true if and only if this pizza is equal to the other given object.
     * For two pizzas' to be equal, they must at least have the same base size, sauce,
     * cheese and collection of toppings ( in any order ).
     * @param other - the reference object with which to compare.
     * @return true if this pizza is the same as the other argument; false otherwise
     */
    public boolean equals(Object other) {
        if (!(other instanceof Pizza)) {
            return false;
        }
        Pizza mine = (Pizza) other;
        if (!this.size.equals(mine.size)) {
            return false;
        }
        if (!this.sauce.equals(mine.sauce)) {
            return false;
        }
        if (!this.cheese.equals(mine.cheese)) {
            return false;
        }
        if (this.originalListToppings.size() != mine.originalListToppings.size()) {
            return false;
        }
        for (int i = 0; i < mine.originalListToppings.size(); ++i) {
            boolean have = false;
            for (int j = 0; j < this.originalListToppings.size(); ++j) {
                if (this.originalListToppings.get(j).equals(mine.originalListToppings.get(i))) {
                    have = true;
                    break;
                }
            }
            if (!have) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the human - readable string representation of this Pizza
     * The format of the string to return is:
     * $Name: is a '`BaseSize`' sized base with '`Sauce`' sauce and '`Cheese`' cheese`Toppings` $`Price`
     * Where:
     * `Name` is the name of the pizza
     * `BaseSize` is the base size of the pizza
     * `Sauce` is the Sauce on the pizza
     * `Cheese` is the Cheese on the pizza
     * `Toppings` is:
     * The empty string if there are no toppings
     * " - Toppings: `Tops`" if there are toppings.
     * Where `Tops` is the string representation of the list of toppings
     * `Price` is the price of the pizza formatted to two decimal places
     * For example:
     *     Dr Java's Pizza: is a 'MEDIUM' sized base with 'BBQ' sauce and 'MOZZARELLA' cheese $5.00
     * OR, on a pizza with toppings
     *     Dr Java's Pizza: is a 'MEDIUM' sized base with 'BBQ' sauce and
     *     'MOZZARELLA' cheese - Toppings: [BACON, PEPPERONI, HAM] $11.00
     * @return string representation of this Pizza.
     */
    public String toString() {
        double totalPrice = this.getTotalPrice();
        String totalStr = String.format("%.2f", totalPrice);
        if (this.originalListToppings.size() == 0) {
            return this.name + ": is a '" + this.size + "' sized base with '" + this.sauce
                    + "' sauce and '" + this.cheese + "' cheese $" + totalStr;
        } else {
            return this.name + ": is a '" + this.size + "' sized base with '" + this.sauce
                    + "' sauce and '" + this.cheese + "' cheese - Toppings: "
                    + this.originalListToppings + " $" + totalStr;
        }
    }

    /**
     * Returns the list of toppings that are on this pizza.
     * This method is used by subclasses to interface with the toppings on this pizza.
     * Adding or removing elements from the returned list should affect the original list.
     * @return the list of toppings on this pizza.
     */
    protected List<Topping> accessToppings() {
        return this.originalListToppings;
    }
}