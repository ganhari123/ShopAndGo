package ghn.shopandgo;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    SuperMarketKart<Integer> superMarketKart;
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    public void createList() {
        superMarketKart = new SuperMarketKart<>();
        superMarketKart.addToKart(new Integer(5));
        superMarketKart.addToKart(new Integer(3));
        superMarketKart.addToKart(new Integer(9));
        superMarketKart.addToKart(new Integer(2));
        superMarketKart.addToKart(new Integer(7));
        superMarketKart.addToKart(new Integer(3));
        superMarketKart.addToKart(new Integer(9));
        superMarketKart.addToKart(new Integer(12));
    }

    @Test
    public void newKartIsEmpty() {
        superMarketKart = new SuperMarketKart<>();
        assertTrue(superMarketKart.isKartEmpty());
        assertTrue(superMarketKart.toArray().equals(Arrays.asList()));
    }

    @Test
    public void addManyElements() {
        createList();
        assertTrue(superMarketKart.toArray().equals(Arrays.asList(5, 3, 9, 2, 7, 3, 9, 12)));

    }

    @Test
    public void removeFirstElement() {
        createList();
        superMarketKart.removeFromKart(0);
        assertTrue(superMarketKart.toArray().equals(Arrays.asList(3, 9, 2, 7, 3, 9, 12)));
    }

    @Test
    public void removeRandomElement() {
        createList();
        superMarketKart.removeFromKart(2);
        assertTrue(superMarketKart.toArray().equals(Arrays.asList(5, 3, 2, 7, 3, 9, 12)));
        superMarketKart.removeFromKart(3);
        assertTrue(superMarketKart.toArray().equals(Arrays.asList(5, 3, 2, 3, 9, 12)));
        superMarketKart.removeFromKart(0);
        assertTrue(superMarketKart.toArray().equals(Arrays.asList(3, 2, 3, 9, 12)));
        //testing removing last element
        superMarketKart.removeFromKart(4);
        assertTrue(superMarketKart.toArray().equals(Arrays.asList(3, 2, 3, 9)));
        superMarketKart.removeFromKart(2);
        assertTrue(superMarketKart.toArray().equals(Arrays.asList(3, 2, 9)));
        superMarketKart.removeFromKart(1);
        assertTrue(superMarketKart.toArray().equals(Arrays.asList(3, 9)));
        superMarketKart.removeFromKart(0);
        assertTrue(superMarketKart.toArray().equals(Arrays.asList(9)));
        superMarketKart.removeFromKart(0);
        assertTrue(superMarketKart.toArray().equals(Arrays.asList()));
        assertTrue(superMarketKart.isKartEmpty() == true);

    }

    @Test

    public void getItemTest() {

    }
}