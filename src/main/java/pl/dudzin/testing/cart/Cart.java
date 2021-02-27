package pl.dudzin.testing.cart;

import pl.dudzin.testing.meal.Meal;
import pl.dudzin.testing.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrderToCart(Order order) {
        orders.add(order);
    }

    public void clearCart() {
        orders.clear();
    }

    public void simulateLargeOrder() {
        for (int i = 0; i < 1000; i++) {
            Meal meal = new Meal(i%10, "Hamburger no " + i);
            Order order = new Order();
            order.addMealToOrder(meal);
            addOrderToCart(order);
        }
        System.out.println("Cart size: " + orders.size());
        clearCart();
    }
}