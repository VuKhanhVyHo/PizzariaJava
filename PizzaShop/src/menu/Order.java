package menu;

import pizza.Pizza;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Object model to manage an individual order.
 * An order will have an associated date, time and customer name.
 * To make it easy to identify order's they are also given a UUID.
 */
public class Order extends Object {

    /**
     * Name of the customer, by default is not given
     */
    private String name;

    /**
     * the number of this order
     */
    private UUID orderNumber;

    /**
     * the date that order is placed
     */
    private LocalDate date;

    /**
     * Time that order is placed
     */
    private LocalTime time;

    /**
     * Pizza that are ordered for this order
     */
    private List<Pizza> ordersOfPizza;

    /**
     * A constant lambda functional interface to apply a 25% discount
     */
    public static final MenuItem.Discount DISCOUNT_25 = a -> 0.75 * a;

    /**
     * A constant lambda functional interface to apply a 10% discount
     */
    public static final MenuItem.Discount DISCOUNT_10 = a -> 0.9 * a;

    /**
     * Creates an Order. The defaults for an order are given below:
     * Name: "Not Given"
     * Order Number: UUID.randomUUID();
     * Date: LocalDate.now();
     * Time: LocalTime.now();
     * Orders: No default items
     */
    public Order() {
        this.name = "Not Given";
        this.orderNumber = UUID.randomUUID();
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.ordersOfPizza = new ArrayList<Pizza>();
    }

    /**
     * Mutator method to modify the customer name
     * @param name String name of this customer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mutator method to modify the orders UUID
     * A UUID (universally unique identifier) is a 128-bit label used for
     * information purposes in computer systems. The Java default library
     * provides a UUID class, which enables a random UUID to be generated.
     * It allows a unique random number for each given order.
     * Please review the Java API for further information.
     *
     * Note that CheckStyle will complain about the use of the UUID class,
     * but this is a valid use case and no marks will be deducted.
     * @param uuid - UUID
     */
    public void setUUID(UUID uuid) {
        this.orderNumber = uuid;
    }

    /**
     * Mutator method to modify the order's creation date
     * LocalDate is an immutable date-time object that represents a date,
     * often viewed as year-month-day. See the Java API LocalDate for further information
     * @param date - localDate
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Mutator method to modify the order's creation time
     * LocalTime is an immutable date-time object that represents a time,
     * often viewed as hour-minute-second. LocalTime for further information
     * @param time - localTime
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Adds a completed pizza to the order list.
     * If there are 3 or more pizzas in the order then a 10% discount should be applied.
     *
     * If there are 6 or more pizzas in the order then a 25% discount should be applied.
     * This supersedes the previous 10% discount.
     * @param pizza - a pizza to add this order
     */
    public void add(Pizza pizza) {
        this.ordersOfPizza.add(pizza);
    }

    /**
     * Object.toString() providing a complete synopsis of the order
     * class including the current assigned date and time;
     * formatted as shown. The time is formatted to provide only the hour and minute.
     * Example 1:
     *  Date: 2022-10-12
     *  Time: 14:37
     *  Customer: John Smith
     *  Order number: e6ef5932-7f6a-46ff-a81e-856a6afabc3c
     *  Order:
     *  1 - Custom Pizza: is a 'MEDIUM' sized base with 'BBQ' sauce
     *  and 'MOZZARELLA' cheese- Toppings: [BACON, PEPPERONI, HAM] $11.00
     *  2 - [MenuPizza] Hawaiian: is a 'MEDIUM' sized base with 'TOMATO'
     *  sauce and 'MOZZARELLA' cheese- Toppings: [PINEAPPLE, HAM] $9.00
     *  3 - [MenuPizza] Spicy Italian: is a 'LARGE' sized base with 'GARLIC'
     *  sauce and 'MOZZARELLA' cheese- Toppings: [PEPPERONI, JALAPENO, PEPPERS] $13.00
     *  4 - Custom Pizza: is a 'SMALL' sized base with 'GARLIC' sauce and 'VEGAN' cheese $3.00
     *
     *  Multi item discount applied of $36.00 applied, new Total: $32.40
     *
     * Example 1:
     *  Date: 2022-10-12
     *  Time: 14:37
     *  Customer: John Smith
     *  Order number: e6ef5932-7f6a-46ff-a81e-856a6afabc3c
     *  Order:
     *  1 - Custom Pizza: is a 'SMALL' sized base with 'GARLIC' sauce and 'VEGAN' cheese $3.00
     *
     *  Total: $3.00
     *
     * @return String representing the instantiated class
     */
    public String toString() {
        double totalPrice = 0;
        String line1 = "Date: " + this.date + "\n";
        String line2 = "Time: " + this.time + "\n";
        String line3 = "Customer: " + this.name + "\n";
        String line4 = "Order number: " + this.orderNumber + "\n";
        String line5 = "Order:\n";
        String result = line1 + line2 + line3 + line4 + line5;
        for (int i = 0; i < this.ordersOfPizza.size(); i++) {
            int noOrd = i + 1;
            String line = noOrd + " - " + this.ordersOfPizza.get(i).toString() + "\n";
            totalPrice += ordersOfPizza.get(i).getTotalPrice();
            result += line;
        }
        result += "\n";
        String totalStr = String.format("%.2f", totalPrice);
        if (this.ordersOfPizza.size() >= 3) {
            double discountedPrice = this.ordersOfPizza.size() >= 6
                    ? DISCOUNT_25.applyDiscount(totalPrice)
                    : DISCOUNT_10.applyDiscount(totalPrice);
            result += "Multi item discount applied of $" + totalStr
                    + " applied, new Total: $" + String.format("%.2f", discountedPrice);
        } else {
            result += "Total: $" + totalStr;
        }
        result += "\n";
        return result;
    }
}
