package menu;

import exceptions.TooManyToppingsException;
import gui.Display;
import pizza.CustomPizza;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * Customer Ordering class used for creating multiple types of pizzas,
 * and storing Orders in an order object, to be returned by a calling class.
 * It maintains the order number ID represented by a Universal Unique
 * Identifier (UUID) as described in the Order class. The name, UUID,
 * time and date are taken, and attached to each order. The customer name is required,
 * but not filtered. Such that any string value is accepted for a customer name.
 * But if a value is not provided through the constructor, a name is requested
 * through a GUI prompt in the requestName() method. This calls a simple JOptionPane.
 * see API for details on how to implement an GUI input dialogue. This class will not
 * be tested programmatically but will still be marked for style.
 */
public class CustomerOrder extends Object {

    /**
     * the name of the customer
     */
    private String customerName;
    /**
     * the order of this customer
     */
    private Order newOrder;

    /**
     * Default Constructor to create a pizza since no name supplied,
     * this constructor sends GUI prompt to the customer for their name
     * through by calling requestName(). and passes on the name to
     * the String signature constructor.
     */
    public CustomerOrder() {
        new Display();
        new CustomerOrder(requestName());
    }

    /**
     * Constructor taking customer name as the parameter. Creates a unique
     * order/customer ID, stores the customer name gets the current date and
     * time, and calls createOrder()
     * @param customerName - String donating the name of the customer
     */
    public CustomerOrder(String customerName) {
        this.customerName = customerName;
        try {
            this.newOrder = createOrder();
        } catch (TooManyToppingsException e) {
            System.out.println("Too many toppings");
        }
    }

    /**
     * Creates a test customer order to connect with a future GUI
     * This method is not going to be tested but rather is a proxy you can use instead of the GUI application to test your program.
     * @return Order containing the entire order of the customer, which includes the unique order/ cus
     * @throws TooManyToppingsException - when attempting to add topping to Pizza or any class extending Pizza
     */
    public Order createOrder() throws TooManyToppingsException {
        this.newOrder = new Order();
        this.newOrder.setName(customerName);
        return this.newOrder;
    }

    /**
     * Returns human-readable string representation of a Customer order.
     * This is:
     * Customer Order {`order`}
     * Where `order` is the Order.toString().
     * @return String representation of this Customer Order.
     */
    public String toString() {
        return "Customer Order {" + this.newOrder.toString() + "}";
    }

    /**
     * Prompts the user for their name, following an attempt to instantiate the order without one. The expectation is to use a JOptionPane to request the name through a GUI medium.
     * @return String from the input field donating the customers name.
     */
    protected String requestName() {
        String userInput = JOptionPane.showInputDialog(null, "Enter your name please.");
        return userInput;
    }
}