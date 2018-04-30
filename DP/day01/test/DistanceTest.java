import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceTest {

    @Test
    public void testMinEditDist1() {
        assertEquals(3, EditDistance.minEditDist("sunday", "saturday"));
    }

    @Test
    public void testMinEditDist2() {
        assertEquals(2, EditDistance.minEditDist("nick", "nek"));
    }

    @Test
    public void testMinEditDist3() {
        assertEquals(1, EditDistance.minEditDist("ben", "bean"));
    }

    @Test
    public void testMinEditDist4() {
        assertEquals(6 ,EditDistance.minEditDist("jingyi", "david"));
    }

    @Test
    public void testMinEditDist5() {
        assertEquals(11,EditDistance.minEditDist("", "bombdiggity"));
    }

    @Test
    public void testMinEditDist6() {
        assertEquals(1,EditDistance.minEditDist("aabbcdef", "abbcdef"));
    }

    @Test
    public void testMinEditDist7() {
        assertEquals(1,EditDistance.minEditDist("aabbcdef", "aaabbcdef"));
    }

    @Test
    public void testMinEditDist8() {
        assertEquals(3,EditDistance.minEditDist("aaabcde", "abcfe"));
    }

}
