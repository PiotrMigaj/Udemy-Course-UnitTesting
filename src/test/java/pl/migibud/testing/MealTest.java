package pl.migibud.testing;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.*;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {

        //given
        Meal meal = new Meal(35);
        //when
        int discountedPrice = meal.getDiscountedPrice(7);

        //then
        assertEquals(28,discountedPrice);
        //assertThat(discountedPrice,equalTo(28));
        //assertThat(discountedPrice).isEqualTo(28);
    }

    @Test
    void referencesToTheSameObjectShouldBeEqual(){
        //given
        Meal meal1=new Meal(10);
        Meal meal2 = meal1;
        //then
        assertSame(meal1,meal2);
        //assertThat(meal1,sameInstance(meal2));
    }

    @Test
    void referencesToDifferentObjectsShouldNotBeEqual(){
        //given
        Meal meal1=new Meal(10);
        Meal meal2 = new Meal(20);

        assertNotSame(meal1,meal2);
        //assertThat(meal1,not(sameInstance(meal2)));
    }

    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame(){

        //given
        Meal meal1 = new Meal(10,"Pizza");
        Meal meal2 = new Meal(10,"Pizza");

        //then
        assertEquals(meal1,meal2);
    }

    @Test
    void exeptionShouldBeThrownIfDiscountIsHigherThanThePrice(){

        //given
        Meal meal = new Meal(8,"Soup");

        //then
        assertThrows(IllegalArgumentException.class,()->meal.getDiscountedPrice(40));
    }

    @ParameterizedTest
    @ValueSource(ints = {5,10,15,18})
    void mealPricesShouldBeLowerThan20(int price){

        assertThat(price,lessThan(20));


    }

    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void burgersShouldHaveCorrectNameAndPrice(String name, int price){
        assertThat(name,containsString("burger"));
        assertThat(price,greaterThanOrEqualTo(10));
    }

    private static Stream<Arguments> createMealsWithNameAndPrice(){
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheseburger", 10)
        );
    }

    @ParameterizedTest
    @MethodSource("createCakeName")
    void cakeNamesShouldEndWithCake(String name){
        assertThat(name,notNullValue());
        assertThat(name,endsWith("cake"));
    }

    private static Stream<String> createCakeName(){
        List<String> cakeNames = Arrays.asList("Cheesecake","Fruitcake","Cupcake");
        return cakeNames.stream();
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestCollection(){

        return Arrays.asList(
                DynamicTest.dynamicTest("Dynamic test 1",()->assertThat(5,lessThan(6))),
                DynamicTest.dynamicTest("Dynamic test 2",()->assertEquals(4,2*2))
        );
    }

    private int caclulatePrice(int price, int quantity){
        return price*quantity;
    }

    @Tag("fries")
    @TestFactory
    Collection<DynamicTest> calculateMealPrices(){

        Order order = new Order();
        order.addMealToOrder(new Meal(10,"Hamburger",2));
        order.addMealToOrder(new Meal(7,"Fries",7));
        order.addMealToOrder(new Meal(22,"Price",3));

        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        for (int i=0;i<order.getMeals().size();i++){
            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();

            Executable executable = () -> {assertThat(caclulatePrice(price,quantity),lessThan(67));
            };

            String name = "Test name: " + i;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name,executable);
            dynamicTests.add(dynamicTest);
        }

        return dynamicTests;
    }


}