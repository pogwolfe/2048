package project3;


import org.junit.Assert;
import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Test2048 {

    @Test
    public void testBoard1() {//
    Board n = new Board();
    assertEquals(n, 4);
//need linked list

    }
    @Test
    public void testBoard2() {
//need linked list

    }


    @Test(expected = IllegalArgumentException.class)
    public void testBoard3() {
//need linked list

    }
    @Test
    public void testBoard4() {
//need linked list

    }
    @Test
    public void testTile1() {
        Tile t = new Tile();
        assertEquals(t.getValue(), 4);
    }
    @Test
    public void testTile2() {
        Tile t = new Tile(8);
        assertEquals(t.getValue(), 8);
    }
    @Test
    public void testTile3() {
        Tile t = new Tile(1024);
        assertEquals(t.getValue(), 1024);
    }

    @Test
    public void testHasEmpty1() {
//need linked list

    }
    @Test(expected = IllegalArgumentException.class)
    public void testHasEmpty2() {
//need linked list

    }

    @Test
    public void testgetTile1() {
//need linked list

    }

    @Test(expected = IllegalArgumentException.class)
    public void testgetTile2() {
//need linked list

    }

    @Test
    public void testsetTile1() {
//need linked list
    }

    @Test(expected = IllegalArgumentException.class)
    public void testsetTile2() {
//need linked list

    }

    @Test
    public void testgetValue1() {
        Tile t = new Tile(64);
        assertEquals(t.getValue(),64);
    }

    @Test
    public void testgetValue1v2() {
        Tile t = new Tile(128);
        assertEquals(t.getValue(),128);
    }
    @Test
    public void testPower2v1() {
        Tile t = new Tile(8);
        assertTrue(t.power2(t.getValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPower2v2() {
        Tile t = new Tile(45);
    }

    public void testPower2v3() {
        Tile t = new Tile(128);
        assertTrue(t.power2(t.getValue()));
    }

    public void testPower2v4() {
        Tile t = new Tile(1024);
        assertTrue(t.power2(t.getValue()));
    }
    @Test
    public void testToStringTile1() {
        Tile t = new Tile(2);
        Assert.assertEquals(t.toString(), 2);
    }
    @Test
    public void testToStringTile2() {
        Tile t = new Tile(64);
        Assert.assertEquals(t.toString(), 64);
    }
}

