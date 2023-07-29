package pizza.ingredients;

/**
 * Cheeses interface for managing the types of Cheese available
 */
public interface Cheeses {

    /**
     * enum containing the cheeses available for the pizza cheese type
     */
    public static enum Cheese {

        /**
         * public static final Cheeses.Cheese MOZZARELLA
         * Classic shredded Mozzarella cheese
         */
        MOZZARELLA,

        /**
         * public static final Cheeses.Cheese NONE
         * A special option to represent no cheese
         */
        NONE,

        /**
         * public static final Cheeses.Cheese VEGAN
         * Vegan friendly cheese
         */
        VEGAN
    }

    /**
     * Set (Cheese) utilises an enum with all the types of Cheese available for the Cheese Type.
     * Cheese being (MOZZARELLA, VEGAN, NONE)
     * @param cheese - enum Type depicting the cheese for each Pizza
     */
    void set(Cheeses.Cheese cheese);
}
