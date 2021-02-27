package pl.dudzin.testing.cart;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.dudzin.testing.order.Order;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTimeout;

@DisplayName("Test cases for Cart")
public class CartTest {

    @Test
    @DisplayName("Cart is able to process 1000 orders in 100 ms")
    public void simulateLargeOrder() {
        //given
        Cart cart = new Cart();
        //when
        //then
        assertTimeout(Duration.ofMillis(100), cart::simulateLargeOrder);
    }

    @Test
    @DisplayName("Cart is able to process 1000 orders in 100 ms")
    public void cartShouldNotBeEmptyAfterAddingOrderToCart() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        //when
        cart.addOrderToCart(order);
        //then
        assertAll(
                () -> assertThat(cart.getOrders()).isNotNull(),
                () -> assertThat(cart.getOrders()).hasSize(1),
                () -> assertThat(cart.getOrders()).isNotEmpty(),
                () -> assertThat(cart.getOrders().get(0).getMeals()).isEmpty());
    }
}