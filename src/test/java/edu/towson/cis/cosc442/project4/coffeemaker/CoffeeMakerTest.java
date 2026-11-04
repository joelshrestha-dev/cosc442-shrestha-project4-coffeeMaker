package edu.towson.cis.cosc442.project4.coffeemaker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@Disabled("Temporarily disabled for mutation testing exercise")
public class CoffeeMakerTest {

    CoffeeMaker coffeeMaker;
    int initialCoffee;
    int initialMilk;
    int initialSugar;
    int initialChocolate;

    @BeforeEach // arrange
    void setUp() {
        coffeeMaker = new CoffeeMaker();
        for(Recipe recipe : coffeeMaker.getRecipes()) {
            coffeeMaker.deleteRecipe(recipe);
        }
        initialCoffee = coffeeMaker.checkInventory().getCoffee();
        initialMilk = coffeeMaker.checkInventory().getMilk();
        initialSugar = coffeeMaker.checkInventory().getSugar();
        initialChocolate = coffeeMaker.checkInventory().getChocolate();

    }

    @AfterEach
    void tearDown() {
        coffeeMaker = null;
        initialCoffee = 0;
        initialMilk = 0;
        initialSugar = 0;
        initialChocolate = 0;
    }

    @ParameterizedTest
    @CsvSource({
        "1,1,1,1",
        "5,5,5,5"
    })
    void testAddInventoryPasses(int coffee, int milk, int sugar, int chocolate) {
        // act
        boolean result = coffeeMaker.addInventory(coffee, milk, sugar, chocolate);
        // assert
        assertTrue(coffeeMaker.checkInventory().getCoffee() == initialCoffee + coffee);
        assertTrue(coffeeMaker.checkInventory().getMilk() == initialMilk + milk);
        assertTrue(coffeeMaker.checkInventory().getSugar() == initialSugar + sugar);
        assertTrue(coffeeMaker.checkInventory().getChocolate() == initialChocolate + chocolate);
        assertTrue(result);
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

    @ParameterizedTest
    @CsvSource({
        "0,0,0,0",
        "1,1,1,1"
    })
    void testAddInventoryZero(int coffee, int milk, int sugar, int chocolate) {
        // act
        boolean result = coffeeMaker.addInventory(coffee, milk, sugar, chocolate);
        // assert
        assertTrue(result);
        assertTrue(coffeeMaker.checkInventory().getCoffee() == initialCoffee + coffee);
        assertTrue(coffeeMaker.checkInventory().getMilk() == initialMilk + milk);
        assertTrue(coffeeMaker.checkInventory().getSugar() == initialSugar + sugar);
        assertTrue(coffeeMaker.checkInventory().getChocolate() == initialChocolate + chocolate);
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
    void testAddRecipeFull() {
        // act
        for (int i = 0; i < 4; i++) {
            Recipe recipe = new Recipe();
            coffeeMaker.addRecipe(recipe);
        }

        Recipe recipeFull = new Recipe();
        recipeFull.setName("testFull");
        recipeFull.setPrice(50);
        recipeFull.setAmtCoffee(1);
        recipeFull.setAmtMilk(1);
        recipeFull.setAmtSugar(1);
        recipeFull.setAmtChocolate(1);

        boolean result = coffeeMaker.addRecipe(recipeFull);

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
    void testDeleteRecipeNull() {
        // act
        boolean result = coffeeMaker.deleteRecipe(null);
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

        Recipe alteredRecipe = new Recipe();
        alteredRecipe.setName("test");
        alteredRecipe.setPrice(100);
        alteredRecipe.setAmtCoffee(2);
        alteredRecipe.setAmtMilk(2);
        alteredRecipe.setAmtSugar(2);
        alteredRecipe.setAmtChocolate(2);

        boolean result = coffeeMaker.editRecipe(recipe, alteredRecipe);
        if(result) System.out.println("Recipe edited successfully.");
        else System.out.println("Failed to edit recipe.");
        Recipe stored = coffeeMaker.getRecipeForName("test");
        // assert
        assertTrue(result);
        assertEquals(stored.getPrice(), 100);
        assertEquals(stored.getAmtCoffee(), 2);
        assertEquals(stored.getAmtMilk(), 2);
        assertEquals(stored.getAmtSugar(), 2);
        assertEquals(stored.getAmtChocolate(), 2);
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
        
        coffeeMaker.addRecipe(recipe);
        // assert
        boolean result = coffeeMaker.editRecipe(recipe, new Recipe());
        assertFalse(result);
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
    void testEditRecipeNull() {
        // act
        Recipe recipe = new Recipe();
        recipe.setName("test");
        recipe.setPrice(50);
        recipe.setAmtCoffee(1);
        recipe.setAmtMilk(1);
        recipe.setAmtSugar(1);
        recipe.setAmtChocolate(1);
        coffeeMaker.addRecipe(recipe);

        boolean result = coffeeMaker.editRecipe(null, recipe);

        // assert
        assertFalse(result);
    }

    @Test
    void testGetRecipeForName() {
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
        assertEquals("test", coffeeMaker.getRecipeForName("test").getName());
    }

    @Test
    void testGetRecipeForNameNotExists() {
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
        Recipe result = coffeeMaker.getRecipeForName("notExists");
        assertNotNull(result);
        assertNull(result.getName());
    }

    @Test
    void testGetRecipes() {
        // act
        Recipe recipe = new Recipe();
        recipe.setName("test");
        recipe.setPrice(50);
        recipe.setAmtCoffee(1);
        recipe.setAmtMilk(1);
        recipe.setAmtSugar(1);
        recipe.setAmtChocolate(1);
        coffeeMaker.addRecipe(recipe);

        Recipe[] recipes = coffeeMaker.getRecipes();
        
        // assert
        assertNotNull(recipes);
        assertEquals(4, recipes.length);
        assertEquals("test", recipes[0].getName());
    }

    @Test
    void testGetRecipesEmpty() {
        // act
        Recipe[] recipes = coffeeMaker.getRecipes();

        // assert
        assertNotNull(recipes);
        assertEquals(4, recipes.length);
        assertNull(recipes[0].getName());
    }

    @ParameterizedTest
    @ValueSource(ints = { 50, 100, 150 })
    void testMakeCoffee(int payment) {
        // act
        Recipe recipe = new Recipe();
        recipe.setName("test");
        recipe.setPrice(50);
        recipe.setAmtCoffee(1);
        recipe.setAmtMilk(1);
        recipe.setAmtSugar(1);
        recipe.setAmtChocolate(1);
        boolean result = coffeeMaker.addRecipe(recipe);

        int change = coffeeMaker.makeCoffee(recipe, payment);

        // assert
        assertEquals(payment - recipe.getPrice(), change);
        assertTrue(result);
        assertTrue(coffeeMaker.checkInventory().getCoffee() == initialCoffee - recipe.getAmtCoffee());
        assertTrue(coffeeMaker.checkInventory().getMilk() == initialMilk - recipe.getAmtMilk());
        assertTrue(coffeeMaker.checkInventory().getSugar() == initialSugar - recipe.getAmtSugar());
        assertTrue(coffeeMaker.checkInventory().getChocolate() == initialChocolate - recipe.getAmtChocolate());

    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 25, 49 })
    void testMakeCoffeeInsufficientPayment(int payment) {
        // act
        Recipe recipe = new Recipe();
        recipe.setName("test");
        recipe.setPrice(50);
        recipe.setAmtCoffee(1);
        recipe.setAmtMilk(1);
        recipe.setAmtSugar(1);
        recipe.setAmtChocolate(1);
        coffeeMaker.addRecipe(recipe);

        int change = coffeeMaker.makeCoffee(recipe, payment);

        // assert
        assertEquals(payment, change);
    }
    
    @Test
    void testMakeCoffeeInsufficientIngredients() {
        // act
        Recipe recipe = new Recipe();
        recipe.setName("test");
        recipe.setPrice(50);
        recipe.setAmtCoffee(100); // Set a high amount to ensure insufficient ingredients
        recipe.setAmtMilk(1);
        recipe.setAmtSugar(1);
        recipe.setAmtChocolate(1);
        coffeeMaker.addRecipe(recipe);

        int change = coffeeMaker.makeCoffee(recipe, 100);

        // assert
        assertEquals(100, change); // Should return the full payment since ingredients are insufficient
    }
}
