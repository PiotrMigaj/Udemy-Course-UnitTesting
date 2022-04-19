package pl.migibud.testing;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    void testAssertArrayEquals(){

        //given
        int[] ints1 = {1,2,3};
        int[] ints2 = {1,2,3};

        //then
        assertArrayEquals(ints1,ints2);

    }

    @Test

    void mealListShouldBeEmptyAfterCreationOfOrder(){

        //given
        Order order = new Order();

        //then
        assertThat(order.getMeals(),empty());
        assertThat(order.getMeals().size(),equalTo(0));
        assertThat(order.getMeals(),hasSize(0));
        assertThat(order.getMeals(),emptyCollectionOf(Meal.class));
    }

    @Test

    void addingMealToOrderShouldIncreaseOrderSize(){

        //given
        Meal meal = new Meal(15,"Burger");
        Meal meal2 = new Meal(5,"Sandwich");
        Order order = new Order();
        order.addMealToOrder(meal);

        assertThat(order.getMeals(),hasSize(1));
        assertThat(order.getMeals(),contains(meal));
        //assertEquals(order.getMeals(),hasItem(meal));

        assertThat(order.getMeals().get(0).getPrice(),equalTo(15));
    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize(){

        //given
        Meal meal = new Meal(15,"Burger");
        Order order = new Order();
        order.addMealToOrder(meal);
        order.removeMealFromOrder(meal);

        //then
        assertThat(order.getMeals(),hasSize(0));
        assertThat(order.getMeals(),not(contains(meal)));

    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThem(){
        //given
        Meal meal1 = new Meal(15,"Burger");
        Meal meal2 = new Meal(5,"Sandwich");
        Order order = new Order();

        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);

        //then
        //assertThat(order.getMeals(),contains(meal,meal2));
        assertThat(order.getMeals().get(0),is(meal1));
        assertThat(order.getMeals().get(1),is(meal2));
    }

    @Test
    void testIfTwoMealListsAreTheSame(){
        //given
        Meal meal = new Meal(15,"Burger");
        Meal meal2 = new Meal(5,"Sandwich");
        Meal meal3 = new Meal(11,"Kebab");

        List<Meal> meals1 = Arrays.asList(meal,meal2);
        List<Meal> meals2 = Arrays.asList(meal,meal2);

        //then
        assertThat(meals1,is(meals2));
    }

    @Test
    void orderTotalPriceShouldNotExceedMaxIntValue(){
        //given
        Meal meal1 = new Meal(Integer.MAX_VALUE,"Burger");
        Meal meal2 = new Meal(Integer.MAX_VALUE,"Sandwich");
        Order order = new Order();

        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);

        //then
        int sum = meal1.getPrice()+meal2.getPrice();
        System.out.println(sum);

    }

//    @Test
//    void emptyOrderTotalPriceShouldEqual0(){
//        //given
//        Order order = new Order();
//
//
//    }

    @Test
    void cancelingOrderShouldRemoveAllItemsFromMealsList(){
        //given
        Meal meal1 = new Meal(Integer.MAX_VALUE,"Burger");
        Meal meal2 = new Meal(Integer.MAX_VALUE,"Sandwich");
        Order order = new Order();

        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);
        order.cancel();

        //then
        assertThat(order.getMeals().size(),is(0));

    }

}
