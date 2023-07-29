package pizza.ingredients;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class ToppingTest {

    @Test
    public void createTopping() {
        Topping.resetToppings();
        try {
            Topping.createTopping(null, true);
            fail ("name cannot be Null");
        } catch (IllegalArgumentException expected) {}
        Topping.resetToppings();
        try {
            Topping.createTopping(" ", true);
            fail ("name cannot be blank");
        } catch (IllegalArgumentException expected) {}
        Topping.resetToppings();
        try {
            Topping.createTopping("bacon", true);
            Topping.createTopping("BAcon", false);
            fail ("This cannot be the same");
        } catch (IllegalArgumentException expected) {}
        Topping.resetToppings();
        try {
            Topping.createTopping("\n", true);
            fail("name cannot be null");
        } catch (IllegalArgumentException expected) {}
        try {
            Topping.createTopping("", true);
            fail("name cannot be null");
        } catch (IllegalArgumentException expected) {}
        try {
            Topping.createTopping("bacon", false);
            Topping.createTopping("pepper", true);
            Topping.createTopping("chillies", true);
            String[] nameTopping = {"BACON", "PEPPER", "CHILLIES"};
            Boolean[] status = {false, true, true};
            Topping[] forTest = Topping.values();
            for (int i = 0; i< nameTopping.length; i++) {
                assertEquals(nameTopping[i], forTest[i].toString());
                assertEquals(status[i], forTest[i].isVegan());
                assertEquals(2.0, Topping.PRICE, 0.1);
            }
        } catch (Exception e) {
            fail("successful add should not throw exception");
        }
        Topping.resetToppings();
    }

    @Test
    public void valueOf() {
        Topping.resetToppings();
        String[] names = { "BACON", "PEPPER", "CHILLIES" };
        boolean[] vegans = { false, true, true };
        for (int i = 0; i < names.length; ++i) {
            Topping.createTopping(names[i], vegans[i]);
        }
        try {
            Topping r = Topping.valueOf(null);
            fail("name cannot be null");
        } catch (NullPointerException e) {}
        try {
            Topping r = Topping.valueOf("BACON ");
            fail("This name didn't appear");
        } catch (IllegalArgumentException expected) {}
        try {
            Topping r = Topping.valueOf("MUSHROOM");
            fail("Cannot return name that does not exist in the list");
        } catch (IllegalArgumentException expected) {}
        try {
            for (int i = 0; i < names.length; ++i) {
                Topping r = Topping.valueOf(names[i]);
                assertEquals(names[i], r.toString());
                assertEquals(vegans[i], r.isVegan());
                assertEquals(2.0, Topping.PRICE, 0.1);
            }
        } catch (IllegalArgumentException exception) {
            fail("Can't throw since the items exist");
        }
        Topping.resetToppings();
        for (int i = 0; i < names.length; ++i) {
            try {
                Topping.valueOf(names[i]);
                fail("Removed, can't valueOf");
            } catch (IllegalArgumentException expected) {}
        }
    }

    @Test
    public void values() {
        Topping.resetToppings();
        String[] names = { "BACON", "PEPPER", "CHILLIES" };
        boolean[] vegans = { false, true, true };
        for (int i = 0; i < names.length; ++i) {
            Topping.createTopping(names[i], vegans[i]);
        }
        Topping[] toppings = Topping.values();
        assertEquals(names.length, toppings.length);
        for (int i = 0; i < toppings.length; ++i) {
            assertEquals(vegans[i], toppings[i].isVegan());
            assertEquals(names[i], toppings[i].toString());
        }
        Topping.resetToppings();
    }

    @Test
    public void resetToppings() {
        Topping.resetToppings();
        assertEquals(0, Topping.values().length);
        String[] names = { "BACON", "PEPPER", "CHILLIES", "olive", "prAWN" };
        boolean[] vegans = { false, true, true, true, false };
        for (int i = 0; i < names.length; ++i) {
            Topping.createTopping(names[i], vegans[i]);
        }
        assertEquals(5, Topping.values().length);
        Topping.resetToppings();
        assertEquals(0, Topping.values().length);
    }

    @Test
    public void testToString() {
        Topping.resetToppings();
        String[] names1 = {"BACON", "PEPPER", "BACON", "BeEf"};
        boolean[] vegans1 = {false, true, true, false};
        for (int i = 0; i < names1.length; ++i) {
            try {
                Topping.createTopping(names1[i], vegans1[i]);
            } catch (IllegalArgumentException expected) {}
        }
        Topping.resetToppings();
        String[] names = {"BACON", "PEPPER", "CHILLIES", "BeEf"};
        boolean[] vegans = {false, true, true, false};
        for (int i = 0; i < names.length; ++i) {
            Topping.createTopping(names[i], vegans[i]);
        }
        Topping[] toppings = Topping.values();
        for (int i = 0; i < names.length; ++i) {
            assertEquals(names[i].toUpperCase(), toppings[i].toString());
            assertEquals(vegans[i], toppings[i].isVegan());
        }
        Topping.resetToppings();
    }

    @Test
    public void isVegan() {
        Topping.resetToppings();
        String[] names = { "BACON", "PEPPER", "CHILLIES" };
        boolean[] vegans = { false, true, true };
        for (int i = 0; i < names.length; ++i) {
            Topping.createTopping(names[i], vegans[i]);
        }
        Topping[] toppings = Topping.values();
        for (int i = 0; i < names.length; ++i) {
            assertEquals(names[i], toppings[i].toString());
            assertEquals(vegans[i], toppings[i].isVegan());
        }
        Topping.resetToppings();
    }
}