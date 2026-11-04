package edu.towson.cis.cosc442.project4.coffeemaker;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecipeTest {
    private Recipe recipe;
    private static String name = "Coffee";
    private static int price = 50;
    private static int amtCoffee = 1;
    private static int amtMilk = 1;
    private static int amtSugar = 1;
    private static int amtChocolate = 1;

    @BeforeEach
    void setUp() {
        recipe = new Recipe();
        recipe.setName(name);
        recipe.setPrice(price);
        recipe.setAmtCoffee(amtCoffee);
        recipe.setAmtMilk(amtMilk);
        recipe.setAmtSugar(amtSugar);
        recipe.setAmtChocolate(amtChocolate);
    }

    @AfterEach
    void tearDown() {
        recipe = null;
    }

    @Test
    void testEquals() {
        // act
        Recipe equalRecipe = new Recipe();
        equalRecipe.setName(name);
        boolean result = equalRecipe.equals(recipe);
        // assert
        assertTrue(result);
    }

    @Test
    void testNotEquals() {
        // act
        Recipe notEqualRecipe = new Recipe();
        notEqualRecipe.setName("Tea");
        boolean result = notEqualRecipe.equals(recipe);
        // assert
        assertFalse(result);
    }

    @Test
    void testEqualsNull() {
        // act
        Recipe nullRecipe = new Recipe();
        nullRecipe.setName(null);
        boolean result = recipe.equals(nullRecipe);
        boolean result2 = nullRecipe.equals(recipe);
        // assert
        assertFalse(result);
    }

    @Test
    void testGetAmtChocolate() {
        // act
        int result = recipe.getAmtChocolate();
        // assert
        assertTrue(result == amtChocolate);
    }

    @Test
    void testGetAmtCoffee() {
        // act
        int result = recipe.getAmtCoffee();
        // assert
        assertTrue(result == amtCoffee);
    }


    @Test
    void testGetAmtMilk() {
        // act
        int result = recipe.getAmtMilk();
        // assert
        assertTrue(result == amtMilk);
    }

    @Test
    void testGetAmtSugar() {
        // act
        int result = recipe.getAmtSugar();
        // assert
        assertTrue(result == amtSugar);
    }

    @Test
    void testGetName() {
        // act
        String result = recipe.getName();
        // assert
        assertTrue(result == name);
    }

    @Test
    void testGetPrice() {
        // act
        int result = recipe.getPrice();
        // assert
        assertTrue(result == price);
    }

    @Test
    void testSetAmtChocolate() {
        // act
        recipe.setAmtChocolate(2);
        // assert
        assertTrue(recipe.getAmtChocolate() == 2);
    }

    @Test void testSetAmtChocolateNegative() {
        // act
        recipe.setAmtChocolate(-1);
        // assert
        assertTrue(recipe.getAmtChocolate() == 0);
    }

    @Test
    void setAmtChocolateZero() {
        // act
        recipe.setAmtChocolate(0);
        // assert
        assertTrue(recipe.getAmtChocolate() == 0);
    }

    @Test
    void testSetAmtCoffee() {
        // act
        recipe.setAmtCoffee(2);
        // assert
        assertTrue(recipe.getAmtCoffee() == 2);
    }

    @Test
    void testSetAmtCoffeeNegative() {
        // act
        recipe.setAmtCoffee(-1);
        // assert
        assertTrue(recipe.getAmtCoffee() == 0);
    }

    @Test 
void testSetAmtCoffeeZero() {
        // act
        recipe.setAmtCoffee(0);
        // assert
        assertTrue(recipe.getAmtCoffee() == 0);
    }

    @Test
    void testSetAmtMilk() {
        // act
        recipe.setAmtMilk(2);
        // assert
        assertTrue(recipe.getAmtMilk() == 2);
    }

    @Test
    void testSetAmtMilkNegative() {
        // act
        recipe.setAmtMilk(-1);
        // assert
        assertTrue(recipe.getAmtMilk() == 0);
    }

    @Test
    void testSetAmtMilkZero() {
        // act
        recipe.setAmtMilk(0);
        // assert
        assertTrue(recipe.getAmtMilk() == 0);
    }

    @Test
    void testSetAmtSugar() {
        // act
        recipe.setAmtSugar(2);
        // assert
        assertTrue(recipe.getAmtSugar() == 2);
    }

    @Test
    void testSetAmtSugarNegative() {
        // act
        recipe.setAmtSugar(-1);
        // assert
        assertTrue(recipe.getAmtSugar() == 0);
    }

    @Test
    void testSetAmtSugarZero() {
        // act
        recipe.setAmtSugar(0);
        // assert
        assertTrue(recipe.getAmtSugar() == 0);
    }

    @Test
    void testSetName() {
        // act
        recipe.setName("Latte");
        // assert
        assertTrue(recipe.getName() == "Latte");
    }

    @Test
    void testSetNameNull() {
        // act
        recipe.setName(null);
        // assert
        assertTrue(recipe.getName() == null);
    }

    @Test
    void testSetNameEmpty() {
        // act
        recipe.setName("");
        // assert
        assertTrue(recipe.getName() == "");
    }

    @Test
    void testSetPrice() {
        // act
        recipe.setPrice(5);
        // assert
        assertTrue(recipe.getPrice() == 5);
    }

    @Test
    void testSetPriceNegative() {
        // act
        recipe.setPrice(-1);
        // assert
        assertTrue(recipe.getPrice() == 0);
    }

    @Test
    void testToString() {
        // act
        String result = recipe.toString();
        // assert
        assertNotNull(result);
    }
}
