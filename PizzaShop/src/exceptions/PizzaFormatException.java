
package exceptions;

/**
 * Exception thrown when an error is thrown while parsing the text file using MenuLoader.
 * Implementing class of {link}
 */
public class PizzaFormatException extends Exception {

    /**
     * Constructs a PizzaFormatException that contains a helpful detail message
     * explaining why the exception occurred.
     * <p>
     * <b>Important:</b> do not write JUnit tests that expect a valid implementation
     * of the assignment to have a certain
     * error message, as the official solution will use different messages to those
     * you are expecting, if any at all.
     *
     * @param message - message to be displayed
     * @param lineNum - int line number of error
     */
    public PizzaFormatException(String message, int lineNum) {
        super(message + " at " + lineNum);
    }

    /**
     * Constructs a PizzaFormatException that contains a helpful detail message
     * explaining why the exception occurred.
     *<p>
     * <b>Important:</b>  do not write JUnit tests that expect a valid
     * implementation of the assignment to have a
     * certain error message, as the official solution will use different
     * messages to those you are expecting,
     * if any at all.
     * @param message - message to be displayed
     * @param lineNum - int line num of error
     * @param cause - throwable that caused the exception
     */
    public PizzaFormatException(String message, int lineNum, Throwable cause) {
        super(message + " at " + lineNum, cause);
    }
}