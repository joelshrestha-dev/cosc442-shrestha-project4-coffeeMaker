package edu.towson.cis.cosc442.project4.coffeemaker;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InventoryTest {

    private Inventory inventory;
    private static int initialCoffee = 15;
    private static int initialMilk = 15;
    private static int initialSugar = 15;
    private static int initialChocolate = 15;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    @AfterEach
    void tearDown() {
        inventory = null;
    }

    @Test
    void testEnoughIngredients() {
        // act
        Recipe enoughRecipe = new Recipe();
        enoughRecipe.setAmtCoffee(1);
        enoughRecipe.setAmtMilk(1);
        enoughRecipe.setAmtSugar(1);
        enoughRecipe.setAmtChocolate(1);
        boolean result = inventory.enoughIngredients(enoughRecipe);
        // assert
        assertTrue(result);
    }

    @Test
    void testNotEnoughIngredients() {
        // act
        Recipe notEnoughRecipe = new Recipe();
        notEnoughRecipe.setAmtCoffee(20);
        notEnoughRecipe.setAmtMilk(20);
        notEnoughRecipe.setAmtSugar(20);
        notEnoughRecipe.setAmtChocolate(20);
        boolean result = inventory.enoughIngredients(notEnoughRecipe);
        // assert
        assertTrue(!result);
    }

    @Test
    void ExactlyEnoughIngredients() {
        // act
        Recipe exactlyEnoughRecipe = new Recipe();
        exactlyEnoughRecipe.setAmtCoffee(initialCoffee);
        exactlyEnoughRecipe.setAmtMilk(initialMilk);
        exactlyEnoughRecipe.setAmtSugar(initialSugar);
        exactlyEnoughRecipe.setAmtChocolate(initialChocolate);
        boolean result = inventory.enoughIngredients(exactlyEnoughRecipe);
        // assert
        assertTrue(result);
    }

    @Test
    void testGetChocolate() {
        // act
        int chocolate = inventory.getChocolate();
        // assert
        assertEquals(initialChocolate, chocolate);
    }

    @Test
    void testGetCoffee() {
        // act
        int coffee = inventory.getCoffee();
        // assert
        assertEquals(initialCoffee, coffee);
    }

    @Test
    void testGetMilk() {
        // act
        int milk = inventory.getMilk();
        // assert
        assertEquals(initialMilk, milk);
    }

    @Test
    void testGetSugar() {
        // act
        int sugar = inventory.getSugar();
        // assert
        assertEquals(initialSugar, sugar);
    }

    @Test
    void testSetChocolate() {
        // act
        inventory.setChocolate(20);
        // assert
        assertEquals(20, inventory.getChocolate());
    }

    @Test void testSetChocolateNegative() {
        // act
        inventory.setChocolate(-5);
        // assert
        assertEquals(0, inventory.getChocolate());
    }

    @Test
    void testSetChocolateZero() {
        // act
        inventory.setChocolate(0);
        // assert
        assertEquals(0, inventory.getChocolate());
    }

    @Test
    void testSetCoffee() {
        // act
        inventory.setCoffee(20);
        // assert
        assertEquals(20, inventory.getCoffee());
    }

    @Test
    void testSetCoffeeNegative() {
        // act
        inventory.setCoffee(-5);
        // assert
        assertEquals(0, inventory.getCoffee());
    }

    @Test
    void testSetCoffeeZero() {
        // act
        inventory.setCoffee(0);
        // assert
        assertEquals(0, inventory.getCoffee());
    }

    @Test
    void testSetMilk() {
        // act
        inventory.setMilk(20);
        // assert
        assertEquals(20, inventory.getMilk());
    }

    @Test
    void testSetMilkNegative() {
        // act
        inventory.setMilk(-5);
        // assert
        assertEquals(0, inventory.getMilk());
    }

    @Test
    void testSetMilkZero() {
        // act
        inventory.setMilk(0);
        // assert
        assertEquals(0, inventory.getMilk());
    }

    @Test
    void testSetSugar() {
        // act
        inventory.setSugar(20);
        // assert
        assertEquals(20, inventory.getSugar());
    }

    @Test
    void testSetSugarZero() {
        // act
        inventory.setSugar(0);
        // assert
        assertEquals(0, inventory.getSugar());
    }

    @Test
    void testSetSugarNegative() {
        // act
        inventory.setSugar(-5);
        // assert
        assertEquals(0, inventory.getSugar());
    }

    @Test
    void testToString() {
        // act
        String result = inventory.toString();
        // assert
        assertNotNull(result);
        assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n", result);
    }
    
}
