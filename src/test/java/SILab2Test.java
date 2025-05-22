import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;

public class SILab2Test {

    //EveryStatement test cases

    @Test
    public void test_nullItemList_throwsException() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(null, "1234567812345678");
        });
        assertEquals("allItems list can't be null!", ex.getMessage());
    }


    @Test
    public void test_itemNoDiscountValidCard() {
        Item item = new Item("apple", 2, 100, 0.0);
        double result = SILab2.checkCart(List.of(item), "1234567812345678");
        assertEquals(200.0, result);
    }


    @Test
    public void test_itemWithDiscount() {
        Item item = new Item("banana", 3, 50, 0.1); // 50 * 0.9 * 3 = 135 - 30 = 105
        double result = SILab2.checkCart(List.of(item), "1234567812345678");
        assertEquals(105.0, result);
    }


    @Test
    public void test_penaltyForHighPrice() {
        Item item = new Item("laptop", 1, 400, 0.0); // -30 + 400 = 370
        double result = SILab2.checkCart(List.of(item), "1234567812345678");
        assertEquals(370.0, result);
    }


    @Test
    public void test_invalidCardCharacter() {
        Item item = new Item("book", 1, 100, 0.0);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(List.of(item), "1234abcd5678efgh");
        });
        assertEquals("Invalid character in card number!", ex.getMessage());
    }

    //MultipleConditions test cases

    @Test
    public void testOnlyPriceOver300() {
        Item item = new Item("item1", 1, 350, 0.0); // price > 300
        List<Item> items = List.of(item);
        double result = SILab2.checkCart(items, "1234567890123456");
        // (price > 300) -> -30 + 350*1 = 320
        assertEquals(320, result);
    }

    @Test
    public void testOnlyDiscountAbove0() {
        Item item = new Item("item2", 1, 100, 0.1); // discount > 0
        List<Item> items = List.of(item);
        double result = SILab2.checkCart(items, "1234567890123456");
        // -30 + 100*(1-0.1)*1 = -30 + 90 = 60
        assertEquals(60, result);
    }

    @Test
    public void testOnlyQuantityAbove10() {
        Item item = new Item("item3", 15, 100, 0.0); // quantity > 10
        List<Item> items = List.of(item);
        double result = SILab2.checkCart(items, "1234567890123456");
        // -30 + 100*15 = -30 + 1500 = 1470
        assertEquals(1470, result);
    }

    @Test
    public void testAllConditionsFalse() {
        Item item = new Item("item4", 1, 100, 0.0); // all false
        List<Item> items = List.of(item);
        double result = SILab2.checkCart(items, "1234567890123456");
        //  100*1 = 100
        assertEquals(100, result);
    }

}

