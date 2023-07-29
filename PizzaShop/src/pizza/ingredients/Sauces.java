package pizza.ingredients;

/**
 * Sauce interface for managing the types of sauces available
 */
public interface Sauces {

    /**
     * enum containing the cheeses available for the pizza sauce type
     */
    public static enum Sauce {

        /**
         * public static final Sauces.Sauce TOMATO
         * A rich tomato sauce
         */
        TOMATO,

        /**
         *public static final Sauces.Sauce BBQ
         * The classic BBQ sauce
         */
        BBQ,

        /**
         * public static final Sauces.Sauce GARLIC
         * A strong smelling garlic sauce
         */
        GARLIC,

        /**
         * public static final Sauces.Sauce NONE
         * A special option to represent no sauce
         */
        NONE
    }

    /**
     * Set (Sauce) utilises an enum with all the types of Sauce available for the Sauce Type. Sauces being (TOMATO, BBQ, GARLIC, NONE)
     * @param sauce - enum SauceType depicting the sauces of each Pizza sauce type
     */
    void set(Sauces.Sauce sauce);
}
