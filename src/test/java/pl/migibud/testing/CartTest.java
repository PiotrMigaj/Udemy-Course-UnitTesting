package pl.migibud.testing;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Disabled
    @Test
    void simulateLargeOrder() {

        //Given
        Cart cart = new Cart();

        //when
        //then
        assertTimeout(Duration.ofMillis(100), cart::simulateLargeOrder);
    }

    @Test
    void carShouldNotBeEmptyAfterAddingOrderToCart()
    {
        //given
        Order order = new Order();
        Cart cart = new Cart();

        //when
        cart.addOrderToCart(order);

        //then
//        assertThat(cart.getOrders(),anyOf(
//                notNullValue(),
//                hasSize(1),
//                is(not(empty())),
//                is(not(emptyCollectionOf(Order.class)))
//        ));

//        assertThat(cart.getOrders(),allOf(
//                notNullValue(),
//                hasSize(1),
//                is(not(empty())),
//                is(not(emptyCollectionOf(Order.class)))
//        ));

        assertAll(
                ()->assertThat(cart.getOrders(),notNullValue()),
                ()->assertThat(cart.getOrders(),hasSize(1)),
                ()->assertThat(cart.getOrders(),is(not(empty()))),
                ()->assertThat(cart.getOrders(),is(not(emptyCollectionOf(Order.class)))),
                ()->assertThat(cart.getOrders().get(0).getMeals(),empty())
        );


    }
}