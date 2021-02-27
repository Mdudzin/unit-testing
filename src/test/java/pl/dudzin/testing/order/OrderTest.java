package pl.dudzin.testing.order;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.dudzin.testing.meal.Meal;
import pl.dudzin.testing.extensions.BeforeAfterExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(BeforeAfterExtension.class)
public class OrderTest {

    private Order order;

    @BeforeEach
    public void initializeOrder() {
        System.out.println("Before each");
        order = new Order();
    }

    @AfterEach
    public void cleanUp() {
        System.out.println("After each");
        order.cancel();
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOfOrder() {
        //when
        List<Meal> mealsList = order.getMeals();
        //then
        assertThat(mealsList).isEmpty();
    }

    @Test
    void addingMealToOrderShouldIncreaseOrderSize() {
        //given
        Meal meal1 = new Meal(15, "Burger");
        //when
        order.addMealToOrder(meal1);
        List<Meal> mealsList = order.getMeals();
        //then
        assertThat(mealsList).hasSize(1);
        assertThat(mealsList).contains(meal1);
    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize() {
        //given
        Meal meal = new Meal(15, "Burger");
        //when
        order.addMealToOrder(meal);
        order.removeMealFromOrder(meal);
        List<Meal> mealsList = order.getMeals();
        //then
        assertThat(mealsList).hasSize(0);
        assertThat(mealsList).doesNotContain(meal);
    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThemToOrder() {
        //given
        Meal meal1 = new Meal(15, "Burger");
        Meal meal2 = new Meal(5, "Sandwich");
        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);
        List<Meal> mealsList = order.getMeals();
        //then
        assertThat(mealsList).containsExactly(meal1, meal2);
    }

    @Test
    void testIfTwoMealListsAreTheSame() {
        //given
        Meal meal1 = new Meal(15, "Burger");
        Meal meal2 = new Meal(5, "Sandwich");

        List<Meal> meals1 = Arrays.asList(meal1, meal2);
        List<Meal> meals2 = Arrays.asList(meal1, meal2);
        //when
        //then
        assertThat(meals1, is(meals2));
    }

    @Test
    void testAssertArrayEquals() {
        //given
        int[] ints1 = {1,2,3};
        int[] ints2 = {1,2,3};
        //then
        assertArrayEquals(ints1, ints2);
    }

    @Test
    void orderTotalPriceShouldNotExceedMaxIntValue() {
        //given
        Meal meal1 = new Meal(Integer.MAX_VALUE, "Burger");
        Meal meal2 = new Meal(Integer.MAX_VALUE, "Sandwich");
        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);
        //then
        assertThrows(IllegalStateException.class, () -> order.totalPrice());
    }

    @Test
    void emptyOrderTotalPriceShouldEqualZero() {
        //given
        //Order is created in BeforeEach
        //then
        assertThat(order.totalPrice()).isEqualTo(0);
    }

    @Test
    void cancelingOrderShouldRemoveAllItemsFromMealsList() {
        //given
        Meal meal1 = new Meal(15, "Burger");
        Meal meal2 = new Meal(5, "Sandwich");
        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);
        order.cancel();
        //then
        assertThat(order.getMeals().size()).isEqualTo(0);
    }
}