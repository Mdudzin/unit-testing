package pl.dudzin.testing.cart;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import pl.dudzin.testing.order.Order;
import pl.dudzin.testing.order.OrderStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    @Test
    public void processCartShouldSendToPrepare() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        when(cartHandler.canHandleCart(cart)).thenReturn(true);
        given(cartHandler.canHandleCart(cart)).willReturn(true);
        //when
        Cart resultCart = cartService.processCart(cart);
        //then
        verify(cartHandler).sendToPrepare(cart);
        then(cartHandler).should().sendToPrepare(cart);

        verify(cartHandler, times(1)).sendToPrepare(cart);
        verify(cartHandler, atLeastOnce()).sendToPrepare(cart);

        InOrder inOrder = inOrder(cartHandler);
        inOrder.verify(cartHandler).canHandleCart(cart);
        inOrder.verify(cartHandler).sendToPrepare(cart);

        assertThat(resultCart.getOrders()).hasSize(1);
        assertThat(resultCart.getOrders().get(0).getOrderStatus()).isEqualTo(OrderStatus.PREPARING);
    }

    @Test
    public void processCartShouldNotSendToPrepare() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        when(cartHandler.canHandleCart(cart)).thenReturn(false);
        given(cartHandler.canHandleCart(cart)).willReturn(false);
        //when
        Cart resultCart = cartService.processCart(cart);
        //then
        verify(cartHandler, never()).sendToPrepare(cart);
        then(cartHandler).should(never()).sendToPrepare(cart);
        assertThat(resultCart.getOrders()).hasSize(1);
        assertThat(resultCart.getOrders().get(0).getOrderStatus()).isEqualTo(OrderStatus.REJECTED);
    }

    @Test
    public void processCartShouldNotSendToPrepareWithArgumentMatchers() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(any(Cart.class))).willReturn(false);
        //when
        Cart resultCart = cartService.processCart(cart);
        //then
        verify(cartHandler, never()).sendToPrepare(any(Cart.class));
        then(cartHandler).should(never()).sendToPrepare(any(Cart.class));
        assertThat(resultCart.getOrders()).hasSize(1);
        assertThat(resultCart.getOrders().get(0).getOrderStatus()).isEqualTo(OrderStatus.REJECTED);
    }
}