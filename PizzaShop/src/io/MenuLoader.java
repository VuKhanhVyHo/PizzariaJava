package io;

import exceptions.PizzaFormatException;
import exceptions.TooManyToppingsException;
import menu.Menu;
import menu.MenuItem;
import pizza.MenuPizza;
import pizza.Pizza;
import pizza.ingredients.Bases;
import pizza.ingredients.Cheeses;
import pizza.ingredients.Sauces;
import pizza.ingredients.Topping;

import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Class to provide management for the loading, saving and parsing of text data retrieved from the PizzaMenu.txt file, stored in the assets package.
 * The loaded file should be called "PizzaMenu.txt"
 *
 * The save function may be revealed later by the GUI team, should this feature be required.
 */
public class MenuLoader extends Object {

    /**
     * provides folder location for text-file PizzaMenu.txt, this can be later improved to search for the file instead of hard coding. This path is: ` "./src/assets/"`
     */
    public static final String PATH = "./src/assets/";

    /**
     * Inherited default constructor, not used in this class
     */
    public MenuLoader() {
        super();
    }

    /**
     * Creates a BufferedReader, utilizing a FileReader to be parsed by the getMenu method.
     * The file to be loaded is stored within a package called assets under the filename PizzaMenu.txt. This is the supplied file name, as other filenames can be used depending on the 'menu-specials' that can be promoted from time to time.
     *
     * If the load method catches a FileNotFoundException, PizzaFormatException, TooManyToppingsException, IndexOutOfBoundsException or a IOException the application should exit with the values 1, 2, 4, 5 and 6 respectively.
     *
     * Given the importance of the menu, within the confines of the Pizza company, if the menu loading mechanism experiences a failure in loading or parsing, then the application is to exit, by providing an appropriate exit integer code stored in an inner class within MenuLoader, called Reason. This is not mandatory (and will not be tested) but provides a developer friendly mechanism for debugging.
     * Here is an example for the constant variable names:
     *
     *
     *          COULD_NOT_OPEN_FILE = 1
     *          FILE_FORMAT_ERROR = 2
     *          TOO_MANY_TOPPINGS = 4
     *          MISSING_NUMBER_OF_PIZZAS = 5
     *          CANNOT_READ_LINE = 6
     *
     * These values will assist you with the expected exit codes as defined above.
     * @param filename - String representing the file to be read
     * @return a parsed Menu type containing the list of Pizzas found in the menu text file.
     */
    public static Menu load(String filename) {
        try {
            BufferedReader menuReader = new BufferedReader(new FileReader(PATH + filename));
            return getMenu(menuReader);
        } catch (Exception ioe) {
            if (ioe instanceof FileNotFoundException) {
                System.exit(1);
            } else if (ioe instanceof PizzaFormatException) {
                System.exit(2);
            } else if (ioe instanceof TooManyToppingsException) {
                System.exit(4);
            } else if (ioe instanceof IndexOutOfBoundsException) {
                System.exit(5);
            } else if (ioe instanceof IOException) {
                System.exit(6);
            } else {
                System.exit(-1);
            }
        }
        return null;
    }

    /**
     * Used by the load method to manage the parsing of the loaded data.
     * The PizzaMenu.txt file content consists of the following formatted text:
     * The first-string token is a string with no spaces and must be the string "PizzaMenu".
     * On the same line separated by a space is a number (an integer value) representing the number of pizza
     * menu items that are available within the given file. This number reflects the number of lines the
     * file reader will read to obtain all the pizzas provided. There should
     * not be any extra whitespace after this number.
     *
     * Then, following the first line, is an empty line.
     * After this empty line will follow a comma separated list of non-vegan toppings.
     * On the line following non-vegan toppings will be another comma separated list of vegan toppings.
     * If any topping can not be parsed then an exception should be thrown.
     *
     * Following the list of vegan toppings will be an empty line.
     * Each subsequent pizza menu item is provided on its own line. A line is a string of text
     * that is separated by the carriage return or separated by an empty line. For each pizza menu item,
     * a line of text is formatted in the following style:
     * 'Pizza Name' which can include spaces and is written with a Title Format. A title format means
     * that each new word starts with a capital letter and is followed by lowercase letters.
     * This will not be tested for programmatically, but is expected for style points when marked.
     *
     * For example: Title Format String Of Words.
     * The menu item pizza name is followed by a space and an opening square bracket `[`. This
     * is then followed by a list of pizza toppings separated by commas, and finally a closing square bracket `]`.
     * The next menu pizza item is listed on the next line, and so on.
     * The list of toppings is provided in lower case. However, care must be taken
     * to read the text-file with any character-case type provided.
     * By default, all Menu Pizza's will be set as 'MEDIUM' with a 'TOMATO' base and 'MOZZARELLA' cheese.
     * The following is an example of a menu text file called PizzaMenu.txt that contains two menu items:
     *          PizzaMenu 2
     *
     *          bacon, chicken, beef, ham, pepperoni, sausage, prawn
     *          red onions, carolina reapers, peppers, olives, onions, chillies, jalapenos, pineapple
     *
     *          Flaming Hot [chillies, jalapenos, carolina reapers, red onions]
     *          Meat Feast [bacon, pepperoni, sausage, beef, chicken]
     * @param reader - BufferedReader used to read file
     * @return Menu item that has loaded all the pizza's from the file
     * @throws PizzaFormatException - if the given reader is `null` or empty, if the name
     * on the first line is not "PizzaMenu", if the space is missing after the name, if the
     * number of pizza's can not be parsed, if a blank line does not follow the first line,
     * if a topping line contains an invalid topping name, if a blank line does not follow
     * the vegan topping line, if a pizza line contains an invalid topping (such that, it
     * was not mentioned in any topping line).
     * @throws TooManyToppingsException - if the given reader is `null` or empty, if the name
     * on the first line is not "PizzaMenu", if the space is missing after the name, if the number
     * of pizza's can not be parsed, if a blank line does not follow the first line, if a topping
     * line contains an invalid topping name, if a blank line does not follow the vegan topping line,
     * if a pizza line contains an invalid topping (such that, it was not mentioned in any topping line).
     * @throws IOException - if an error occurs when trying to read a line.
     * @throws IndexOutOfBoundsException - if the number of pizza lines given in the first line does
     * not match the number of pizza lines present in the file.
     */
    public static Menu getMenu(BufferedReader reader)
            throws PizzaFormatException,
            TooManyToppingsException,
            IOException,
            IndexOutOfBoundsException {
        if (reader == null) {
            throw new PizzaFormatException("reader is null", -1);
        }
        int lineNum = 0;
        String line = reader.readLine();
        if (line == null) {
            throw new PizzaFormatException("First line is null line", lineNum);
        }
        String[] pair = line.split(" ");
        if (pair.length != 2) {
            throw new PizzaFormatException("Missing space after PizzaMenu "
                    + "or too many space", lineNum);
        }
        if (!pair[0].equals("PizzaMenu")) {
            throw new PizzaFormatException("File does not start with PizzaMenu", lineNum);
        }
        int numOfPizza;
        try {
            numOfPizza = Integer.parseInt(pair[1]);
        } catch (Exception e) {
            throw new PizzaFormatException("Not valid number of pizza", lineNum);
        }
        // empty
        ++lineNum;
        line = reader.readLine();
        if (!line.isEmpty()) {
            throw new PizzaFormatException("This line is not empty", lineNum);
        }
        // non vegan
        ++lineNum;
        line = reader.readLine();
        String[] nonVegans = line.split(",");
        for (int i = 0; i < nonVegans.length; i++) {
            Topping.createTopping(nonVegans[i].trim(), false);
        }
        // vegan
        ++lineNum;
        line = reader.readLine();
        String[] vegansToppings = line.split(",");
        for (int i = 0; i < vegansToppings.length; i++) {
            Topping.createTopping(vegansToppings[i].trim(), true);
        }
        // empty
        ++lineNum;
        line = reader.readLine();
        if (!line.isEmpty()) {
            throw new PizzaFormatException("This line is not empty", lineNum);
        }
        // pizza names
        int actualNumPizzas = 0;
        Menu menu = Menu.getInstance();
        for (;;) {
            ++lineNum;
            line = reader.readLine();
            if (line == null) {
                break;
            }
            // read this pizza name
            String[] title = line.split("\\[", 2);
            ArrayList<Topping> toppings = new ArrayList<Topping>();
            String[] toppingStrs = title[1].substring(0, title[1].length() - 1).split(",");
            if (toppingStrs.length > 5) {
                throw new TooManyToppingsException("More than 5 toppings");
            }
            for (int i = 0; i < toppingStrs.length; ++i) {
                try {
                    toppings.add(Topping.valueOf(toppingStrs[i].trim().toUpperCase()));
                } catch (Exception e) {
                    throw new PizzaFormatException("invalid topping", lineNum);
                }
            }
            MenuPizza addItem = new MenuPizza(Bases.BaseSize.MEDIUM, Sauces.Sauce.TOMATO,
                    Cheeses.Cheese.MOZZARELLA, toppings);
            addItem.setName(title[0].trim());
            menu.registerMenuItem(addItem);
            ++actualNumPizzas;
        }
        if (actualNumPizzas != numOfPizza) {
            throw new IndexOutOfBoundsException();
        }
        return menu;
    }
}