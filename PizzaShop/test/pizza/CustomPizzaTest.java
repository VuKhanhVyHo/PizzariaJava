package pizza;

import exceptions.TooManyToppingsException;
import org.junit.Test;
import pizza.ingredients.Bases;
import pizza.ingredients.Cheeses;
import pizza.ingredients.Sauces;
import pizza.ingredients.Topping;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static pizza.ingredients.Bases.BaseSize.*;
import static pizza.ingredients.Sauces.Sauce.*;

public class CustomPizzaTest {

    @Deprecated
    public void testConstructor() {
        try {
            new CustomPizza(null, TOMATO, Cheeses.Cheese.NONE);
            fail("size cannot be null");
        } catch (IllegalArgumentException expected) {}
        try {
            new CustomPizza(SMALL, null, Cheeses.Cheese.VEGAN);
            fail("sauce cannot be null");
        } catch (IllegalArgumentException expected) {}
        try{
            new CustomPizza(LARGE, GARLIC, null);
            fail ("Cheese cannot be null");
        } catch (IllegalArgumentException expected) {}
        try{
            new CustomPizza(null, null, null);
            fail ("Those data cannot be null");
        } catch (IllegalArgumentException expected) {}
        try {
            CustomPizza test = new CustomPizza(LARGE, GARLIC, Cheeses.Cheese.VEGAN);
            assertEquals("Custom Pizza", test.getName());
            assertEquals(null, test.accessToppings());
            assertEquals(7.0, test.getTotalPrice(), 0.1);
            assertEquals("Custom Pizza: is a 'LARGE' sized base with " +
                    "'GARLIC' sauce and 'VEGAN' cheese $7.00", test.toString());
        } catch (Exception expected) {}
        try {
            CustomPizza test = new CustomPizza();
            assertEquals("Custom Pizza", test.getName());
            assertEquals(null, test.accessToppings());
            assertEquals(5.0, test.getTotalPrice(), 0.1);
            assertEquals("Custom Pizza: is a 'MEDIUM' sized base with " +
                    "'TOMATO' sauce and 'MOZARRELLA' cheese $5.00", test.toString());
        } catch (Exception expected) {}
        try {
            CustomPizza test = new CustomPizza(SMALL, GARLIC, Cheeses.Cheese.VEGAN);
            assertEquals("Custom Pizza", test.getName());
            assertEquals(null, test.accessToppings());
            assertEquals(3.0, test.getTotalPrice(), 0.1);
            assertEquals("Custom Pizza: is a 'SMALL' sized base with " +
                    "'GARLIC' sauce and 'VEGAN' cheese $3.00", test.toString());
        } catch (Exception expected) {}
    }

    @Test
    public void add() {
        Topping.resetToppings();
        Topping.createTopping("BACON", false);
        Topping.createTopping("MUSHROOM", true);
        Topping.createTopping("TOMATO", true);
        Topping.createTopping("PRAWN", false);
        Topping.createTopping("BEEF", false);
        Topping.createTopping("OLIVE", true);
        Topping.createTopping("PePPeRONI", false);
        Topping.createTopping("oNION", true);
        //add 5 toppings
        try {
            List<Topping> test = new ArrayList<>();
            List<Topping> test2 = new ArrayList<>();
            CustomPizza Success = new CustomPizza();
            CustomPizza Success2 = new CustomPizza();
            Success.add(Topping.valueOf("BACON"));
            Success.add(Topping.valueOf("BACON"));
            Success.add(Topping.valueOf("BACON"));
            Success.add(Topping.valueOf("BACON"));
            Success.add(Topping.valueOf("BACON"));
            for (int i = 0; i < 5; i++) {
                test.add(Topping.valueOf("BACON"));
                test2.add(Topping.valueOf("BACON"));
            }
            Success2.add(test2);
            assertEquals(test, Success.accessToppings());
            assertEquals(test2, Success2.accessToppings());
        } catch (TooManyToppingsException expected) {}
        try {
            List<Topping> test = new ArrayList<>();
            List<Topping> test2 = new ArrayList<>();
            CustomPizza Success = new CustomPizza();
            CustomPizza Success2 = new CustomPizza();
            Success.add(Topping.valueOf("BACON"));
            Success.add(Topping.valueOf("mushroom"));
            Success.add(Topping.valueOf("prawn"));
            test.add(Topping.valueOf("bacon"));
            test.add(Topping.valueOf("mushroom"));
            test.add(Topping.valueOf("prAWN"));
            test2.add(Topping.valueOf("prawn"));
            test2.add(Topping.valueOf("mushroom"));
            test2.add(Topping.valueOf("prAWN"));
            test2.add(Topping.valueOf("pepperoni"));
            test2.add(Topping.valueOf("Olive"));
            Success2.add(test2);
            assertEquals(test, Success.accessToppings());
            assertEquals(test2, Success2.accessToppings());
        } catch (TooManyToppingsException expected) {}
        try {
            CustomPizza pizza1 = new CustomPizza();
            pizza1.add(Topping.valueOf("BACON"));
            pizza1.add(Topping.valueOf("BACON"));
            pizza1.add(Topping.valueOf("BACON"));
            pizza1.add(Topping.valueOf("BACON"));
            pizza1.add(Topping.valueOf("BACON"));
            pizza1.add(Topping.valueOf("BACON"));
            fail("This exceed number of 5 toppings(same)");
        } catch (TooManyToppingsException expected) {}
        try {
            CustomPizza pizzaForThis = new CustomPizza();
            pizzaForThis.add(Topping.valueOf("BACON"));
            pizzaForThis.add(Topping.valueOf("MUSHROOM"));
            pizzaForThis.add(Topping.valueOf("TOMATO"));
            pizzaForThis.add(Topping.valueOf("PRAWN"));
            pizzaForThis.add(Topping.valueOf("BEEF"));
            pizzaForThis.add(Topping.valueOf("OLIVE"));
            fail("This exceed number of 5 toppings(different)");
        } catch (Exception expected) {
            if (!(expected instanceof TooManyToppingsException)) {
                fail("Wrong exception thrown for 6 toppings");
            }
        }
        //add list > 5
        try {
            ArrayList<Topping> testa1 = new ArrayList<>();
            for (int i = 0; i< 6; i++) {
                testa1.add(Topping.valueOf("BACON"));
            }
            CustomPizza testa = new CustomPizza();
            testa.add(testa1);
            fail("List is more than 5");
        } catch (TooManyToppingsException expected) {}
        try {
            CustomPizza pizza3 = new CustomPizza(LARGE, Sauces.Sauce.GARLIC, Cheeses.Cheese.VEGAN);
            ArrayList<Topping> testa1 = new ArrayList<Topping>();
            testa1.add(Topping.valueOf("BACON"));
            testa1.add(Topping.valueOf("MUSHROOM"));
            ArrayList<Topping> testa3 = new ArrayList<Topping>();
            testa3.add(Topping.valueOf("TOMATO"));
            testa3.add(Topping.valueOf("PEPPERONI"));
            testa3.add(Topping.valueOf("BACON"));
            testa3.add(Topping.valueOf("MUSHROOM"));
            testa3.add(Topping.valueOf("PRAWN"));
            testa3.add(Topping.valueOf("OLIVE"));
            pizza3.add(testa1);
            pizza3.add(testa3);
            fail("The number of Toppings in list cannot exceed 5");
        } catch (Exception e) {
            if (!(e instanceof TooManyToppingsException)) {
                fail("Wrong exception thrown for more than 6 toppings");
            }
        }
        try {
            CustomPizza pizza3 = new CustomPizza(LARGE, Sauces.Sauce.GARLIC, Cheeses.Cheese.VEGAN);
            ArrayList<Topping> testa3 = new ArrayList<Topping>();
            testa3.add(Topping.valueOf("TOMATO"));
            testa3.add(Topping.valueOf("PEPPERONI"));
            testa3.add(Topping.valueOf("BACON"));
            testa3.add(Topping.valueOf("MUSHROOM"));
            pizza3.add(testa3);
            pizza3.add((Topping)null);
            fail("Topping cannot be null");
        } catch (Exception e) {
            if (!(e instanceof IllegalArgumentException))
                fail("Must throw IllegalArg");
        }
        try {
            CustomPizza pizza3 = new CustomPizza(LARGE, Sauces.Sauce.GARLIC, Cheeses.Cheese.VEGAN);
            ArrayList<Topping> testa3 = new ArrayList<Topping>();
            testa3.add(Topping.valueOf("TOMATO"));
            testa3.add(Topping.valueOf("PEPPERONI"));
            testa3.add(Topping.valueOf("BACON"));
            testa3.add(Topping.valueOf("MUSHROOM"));
            testa3.add(Topping.valueOf("PRAWN"));
            testa3.add(Topping.valueOf("OLIVE"));
            pizza3.add(testa3);
            fail("The number of Toppings in list cannot exceed 5");
        } catch (Exception e) {}
        //add null list
        try {
            CustomPizza pizza4 = new CustomPizza(Bases.BaseSize.SMALL, Sauces.Sauce.BBQ, Cheeses.Cheese.NONE);
            pizza4.add((List<Topping>) null);
            fail("This is NULL");
        } catch (Exception e) {
            if (!(e instanceof IllegalArgumentException)) {
                fail("Wrong exception thrown for null");
            }
        }
        // add null topping
        try {
            CustomPizza pizza1 = new CustomPizza();
            pizza1.add((Topping) null);
            fail("Topping cannot be NULL");
        } catch (Exception e) {
            if (!(e instanceof IllegalArgumentException)) {
                fail("Wrong exception thrown for null");
            }
        }
        Topping.resetToppings();
    }

    @Test
    public void remove() {
        Topping.resetToppings();
        Topping.createTopping("chillies", true);
        Topping.createTopping("egg", false);
        Topping.createTopping("tomato", true);
        ArrayList<Topping> testa1 = new ArrayList<Topping>();
        ArrayList<Topping> testa3 = new ArrayList<Topping>();
        CustomPizza pizza1 = new CustomPizza();
        testa1.add(Topping.valueOf("chillies"));
        testa1.add(Topping.valueOf("egg"));
        testa3.add(Topping.valueOf("chillies"));
        try {
            pizza1.remove(Topping.valueOf("tomato"));
            assertEquals(new ArrayList<Topping>(), pizza1.accessToppings());
            pizza1.add(testa1);
            pizza1.remove(Topping.valueOf("tomato"));
            assertEquals(testa1, pizza1.accessToppings());
            pizza1.remove(Topping.valueOf("egg"));
            assertEquals(testa3, pizza1.accessToppings());
            pizza1.remove(Topping.valueOf("chillies"));
            assertEquals(new ArrayList<Topping>(), pizza1.accessToppings());
            pizza1.remove(Topping.valueOf("egg"));
            assertEquals(new ArrayList<Topping>(),pizza1.accessToppings());
        } catch (TooManyToppingsException expected) {}
        Topping.resetToppings();
    }
}