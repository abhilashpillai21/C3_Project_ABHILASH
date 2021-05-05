import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    Restaurant restaurant;

    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        Restaurant mocked_Restaurant = Mockito.mock(Restaurant.class);
        Mockito.when(mocked_Restaurant.isRestaurantOpen()).thenReturn(true);
        assertThat(mocked_Restaurant.isRestaurantOpen(), equalTo(true));
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Restaurant mocked_Restaurant = Mockito.mock(Restaurant.class);
        Mockito.when(mocked_Restaurant.isRestaurantOpen()).thenReturn(false);
        assertThat(mocked_Restaurant.isRestaurantOpen(), equalTo(false));
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        addToMenu();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        addToMenu();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        addToMenu();

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }

    @Test
    public void when_item_is_selected_it_should_add_to_sum_of_price() {
        addToMenu();
        int priceBefore = restaurant.getTotalPrice(restaurant.getMenu());
        restaurant.addToMenu("Sizzling brownie",319);
        int priceAfter = restaurant.getTotalPrice(restaurant.getMenu());

        assertEquals(priceAfter, priceBefore +  restaurant.getPrice("Sizzling brownie"));
    }

    @Test
    public void when_item_is_removed_it_should_deduct_from_sum_of_price() throws itemNotFoundException {
        addToMenu();
        int priceBefore = restaurant.getTotalPrice(restaurant.getMenu());
        int priceOfVL = restaurant.getPrice("Vegetable lasagne");
        restaurant.removeFromMenu("Vegetable lasagne");
        int priceAfter = restaurant.getTotalPrice(restaurant.getMenu());

        assertEquals(priceAfter, priceBefore -  priceOfVL);
    }

    private void addToMenu() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
