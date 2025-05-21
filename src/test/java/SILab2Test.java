import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    public void testEveryStatement() {
        List<Item> items = Arrays.asList(
                new Item("Item1", 1, 100, 0),
                new Item("Item2", 5, 50, 0.1),
                new Item("Item3", 11, 400, 0)
        );
        String validCard = "1234567890123456";
        double result = SILab2.checkCart(items, validCard);
        assertEquals(4665, result, 0.001);
    }

    @Test
    public void testMultipleCondition() {
        List<Item> case1 = List.of(new Item("Item1", 1, 4000, 0));
        List<Item> case2 = List.of(new Item("Item1", 1, 100, 0.2));
        List<Item> case3 = List.of(new Item("Item1", 11, 100, 0));
        List<Item> case4 = List.of(new Item("Item1", 1, 100, 0));

        String validCard = "1234567890123456";

        Assertions.assertDoesNotThrow(() -> SILab2.checkCart(case1, validCard));
        Assertions.assertDoesNotThrow(() -> SILab2.checkCart(case2, validCard));
        Assertions.assertDoesNotThrow(() -> SILab2.checkCart(case3, validCard));
        Assertions.assertDoesNotThrow(() -> SILab2.checkCart(case4, validCard));
    }
}
