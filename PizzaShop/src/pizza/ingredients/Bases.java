package pizza.ingredients;

/**
 * Base interface for managing the types of base sizes available
 */
public interface Bases {
    /**
     * enum containing the sizes available for the pizza Bases type
     */
    public static enum BaseSize {

        /**
         * public static final Bases.BaseSize SMALL
         * SMALL base size, $3
         */
        SMALL(3.0),

        /**
         * public static final Bases.BaseSize MEDIUM
         * MEDIUM base size, $5
         */
        MEDIUM(5.0),

        /**
         *public static final Bases.BaseSize LARGE
         * LARGE base size, $7
         */
        LARGE(7.0);

        /**
         * price of the different base sizes
         */
        public final double price;

        /**
         * Constructor for the enum
         */
        BaseSize(double price) {
            this.price = price;
        }

        /**
         * Returns the price of this base
         * @return the price of this base
         */
        public double getPrice() {
            return this.price;
        }
    }

    /**
     * Set (Bases) utilises an enum with all the types of Bases available for the Bases Type.
     * BaseSize being (SMALL, MEDIUM, LARGE)
     * @param size - enum Type depicting the sizes of each Pizza base type
     */
    void set(Bases.BaseSize size);
}
