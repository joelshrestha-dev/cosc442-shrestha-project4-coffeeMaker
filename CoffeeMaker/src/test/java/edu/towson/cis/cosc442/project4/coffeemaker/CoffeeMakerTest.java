package edu.towson.cis.cosc442.project4.coffeemaker;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CoffeeMakerTest {

    CoffeeMaker coffeeMaker;

    @BeforeEach // arrange
    void setUp() {
        coffeeMaker = new CoffeeMaker();
        for(Recipe recipe : coffeeMaker.getRecipes()) {
            coffeeMaker.deleteRecipe(recipe);
        }
    }

    @AfterEach
    void tearDown() {
        coffeeMaker = null;
    }

    @ParameterizedTest
    @CsvSource({
        "1,1,1,1",
        "5,5,5,5"
    })
    void testAddInventoryPasses(int coffee, int milk, int sugar, int chocolate) {
        // act
        coffeeMaker.addInventory(coffee, milk, sugar, chocolate);
        // assert
        assertTrue(coffeeMaker.checkInventory().getCoffee() >= coffee);
        assertTrue(coffeeMaker.checkInventory().getMilk() >= milk);
        assertTrue(coffeeMaker.checkInventory().getSugar() >= sugar);
        assertTrue(coffeeMaker.checkInventory().getChocolate() >= chocolate);
    }

    @ParameterizedTest
    @CsvSource({
        "-1,1,1,1",
        "1,-1,1,1",
        "1,1,-1,1",
        "1,1,1,-1"
    })
    void testAddInventoryFails(int coffee, int milk, int sugar, int chocolate) {
        // act
        boolean result = coffeeMaker.addInventory(coffee, milk, sugar, chocolate);
        // assert
        assertFalse(result);
    }

    @Test
    void testAddRecipe() {
        // act
        Recipe addRecipe = new Recipe();
        addRecipe.setName("test");
        addRecipe.setPrice(50);
        addRecipe.setAmtCoffee(1);
        addRecipe.setAmtMilk(1);
        addRecipe.setAmtSugar(1);
        addRecipe.setAmtChocolate(1);
        boolean result = coffeeMaker.addRecipe(addRecipe);

        // assert
        assertTrue(result);
        assertEquals("test", coffeeMaker.getRecipeForName("test").getName());

    }

    @Test
    void testAddRecipeDuplicate() {
        // act
        Recipe recipe = new Recipe();
        recipe.setName("test");
        recipe.setPrice(50);
        recipe.setAmtCoffee(1);
        recipe.setAmtMilk(1);
        recipe.setAmtSugar(1);
        recipe.setAmtChocolate(1);
        coffeeMaker.addRecipe(recipe);

        Recipe recipeDuplicate = new Recipe();
        recipeDuplicate.setName("test");
        recipeDuplicate.setPrice(50);
        recipeDuplicate.setAmtCoffee(1);
        recipeDuplicate.setAmtMilk(1);
        recipeDuplicate.setAmtSugar(1);
        recipeDuplicate.setAmtChocolate(1);

        boolean result = coffeeMaker.addRecipe(recipeDuplicate);

        // assert
        assertFalse(result);
    }

    @Test
    void testCheckInventory() {
        // act
        Recipe recipe = new Recipe();
        recipe.setName("test");
        recipe.setPrice(50);
        recipe.setAmtCoffee(1);
        recipe.setAmtMilk(1);
        recipe.setAmtSugar(1);
        recipe.setAmtChocolate(1);
        coffeeMaker.addRecipe(recipe);
        // assert
        assertTrue(coffeeMaker.checkInventory().getCoffee() >= recipe.getAmtCoffee());
        assertTrue(coffeeMaker.checkInventory().getMilk() >= recipe.getAmtMilk());
        assertTrue(coffeeMaker.checkInventory().getSugar() >= recipe.getAmtSugar());
        assertTrue(coffeeMaker.checkInventory().getChocolate() >= recipe.getAmtChocolate());
        
    }

    @Test
    void testDeleteRecipeExists() {
        // act
        Recipe recipe = new Recipe();
        recipe.setName("test");
        recipe.setPrice(50);
        recipe.setAmtCoffee(1);
        recipe.setAmtMilk(1);
        recipe.setAmtSugar(1);
        recipe.setAmtChocolate(1);
        coffeeMaker.addRecipe(recipe);
        boolean result = coffeeMaker.deleteRecipe(recipe);
        // assert
        assertTrue(result);
    }

    @Test
    void testDeleteRecipeNotExists() {
        // act
        Recipe recipe = new Recipe();
        recipe.setName("test");
        recipe.setPrice(50);
        recipe.setAmtCoffee(1);
        recipe.setAmtMilk(1);
        recipe.setAmtSugar(1);
        recipe.setAmtChocolate(1);
        boolean result = coffeeMaker.deleteRecipe(recipe);
        // assert
        assertFalse(result);
    }

    @Test
    void testEditRecipeExists() {
        // act
        Recipe recipe = new Recipe();
        recipe.setName("test");
        recipe.setPrice(50);
        recipe.setAmtCoffee(1);
        recipe.setAmtMilk(1);
        recipe.setAmtSugar(1);
        recipe.setAmtChocolate(1);
        coffeeMaker.addRecipe(recipe);
        int initialPrice = recipe.getPrice();
        recipe.setPrice(100);
        // assert
        assertTrue(recipe.getPrice() != initialPrice);
    }

    @Test
    void testEditRecipeNotExists() {
        // act
        Recipe recipe = new Recipe();
        recipe.setName("test");
        recipe.setPrice(50);
        recipe.setAmtCoffee(1);
        recipe.setAmtMilk(1);
        recipe.setAmtSugar(1);
        recipe.setAmtChocolate(1);
        int initialPrice = recipe.getPrice();
        recipe.setPrice(100);
        // assert
        assertTrue(recipe.getPrice() != initialPrice);
    }

    @Test
    void testEditRecipeDuplicate() {
        // act
        Recipe recipe = new Recipe();
        recipe.setName("test");
        recipe.setPrice(50);
        recipe.setAmtCoffee(1);
        recipe.setAmtMilk(1);
        recipe.setAmtSugar(1);
        recipe.setAmtChocolate(1);
        coffeeMaker.addRecipe(recipe);

        Recipe recipe2 = new Recipe();
        recipe2.setName("test2");
        recipe2.setPrice(100);
        recipe2.setAmtCoffee(2);
        recipe2.setAmtMilk(2);
        recipe2.setAmtSugar(2);
        recipe2.setAmtChocolate(2);
        coffeeMaker.addRecipe(recipe2);

        Recipe recipeDuplicate = new Recipe();
        recipeDuplicate.setName("testDupeEdit");
        recipeDuplicate.setPrice(50);
        recipeDuplicate.setAmtCoffee(1);
        recipeDuplicate.setAmtMilk(1);
        recipeDuplicate.setAmtSugar(1);
        recipeDuplicate.setAmtChocolate(1);

        boolean result = coffeeMaker.editRecipe(recipe2, recipeDuplicate);

        // assert
        assertFalse(result);
    }

    @Test
    void testGetRecipeForName() {
        // act
        // assert
    }

    @Test
    void testGetRecipes() {
        // act
        // assert
    }

    @Test
    void testMakeCoffee() {
        // act
        // assert
    }
}
