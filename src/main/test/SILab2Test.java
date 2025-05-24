import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class SILab2Test {

    @Test
    public void testEveryStatement() {
        SILab2 lab = new SILab2();

        try {
            lab.checkCart(null, "1234-5678-1234-5678");
            fail();
        } catch (RuntimeException ex) {
            assertEquals("allItems list can't be null!", ex.getMessage());
        }

        assertEquals(0, lab.checkCart(new ArrayList<>(), "1234-5678-1234-5678"));

        List<Item> items1 = new ArrayList<>();
        items1.add(new Item(null, "code1", 400, 0, 5));
        assertEquals(1970, lab.checkCart(items1, "1234-5678-1234-5678"));

        List<Item> items2 = new ArrayList<>();
        items2.add(new Item(null, "code2", 200, 10, 5));
        assertEquals(950, lab.checkCart(items2, "1234-5678-1234-5678"));

        List<Item> items3 = new ArrayList<>();
        items3.add(new Item(null, "code3", 100, 0, 11));
        assertEquals(1070, lab.checkCart(items3, "1234-5678-1234-5678"));

        try {
            List<Item> items4 = new ArrayList<>();
            items4.add(new Item(null, null, 100, 0, 5));
            lab.checkCart(items4, "1234-5678-1234-5678");
            fail();
        } catch (RuntimeException ex) {
            assertEquals("Invalid item!", ex.getMessage());
        }

        try {
            List<Item> items5 = new ArrayList<>();
            items5.add(new Item("item", "code", 100, 0, 5));
            lab.checkCart(items5, "1234-567X-1234-5678");
            fail();
        } catch (RuntimeException ex) {
            assertEquals("Invalid character in card number!", ex.getMessage());
        }
    }

    @Test
    public void testMultipleCondition() {
        SILab2 lab = new SILab2();

        List<Item> items;

        items = new ArrayList<>();
        items.add(new Item("item", "code", 100, 0, 1)); // A=false, B=false, C=false
        assertEquals(70, lab.checkCart(items, "1234-5678-1234-5678"));

        items = new ArrayList<>();
        items.add(new Item("item", "code", 100, 0, 11)); // A=false, B=false, C=true
        assertEquals(1070, lab.checkCart(items, "1234-5678-1234-5678"));

        items = new ArrayList<>();
        items.add(new Item("item", "code", 100, 10, 1)); // A=false, B=true, C=false
        assertEquals(90, lab.checkCart(items, "1234-5678-1234-5678"));

        items = new ArrayList<>();
        items.add(new Item("item", "code", 100, 10, 11)); // A=false, B=true, C=true
        assertEquals(990, lab.checkCart(items, "1234-5678-1234-5678"));

        items = new ArrayList<>();
        items.add(new Item("item", "code", 400, 0, 1)); // A=true, B=false, C=false
        assertEquals(370, lab.checkCart(items, "1234-5678-1234-5678"));

        items = new ArrayList<>();
        items.add(new Item("item", "code", 400, 0, 11)); // A=true, B=false, C=true
        assertEquals(4370, lab.checkCart(items, "1234-5678-1234-5678"));

        items = new ArrayList<>();
        items.add(new Item("item", "code", 400, 10, 1)); // A=true, B=true, C=false
        assertEquals(390, lab.checkCart(items, "1234-5678-1234-5678"));

        items = new ArrayList<>();
        items.add(new Item("item", "code", 400, 10, 11)); // A=true, B=true, C=true
        assertEquals(4290, lab.checkCart(items, "1234-5678-1234-5678"));
    }
}
