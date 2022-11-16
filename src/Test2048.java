
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Test2048 {

    @Test
    public void testBoard1() {//
    Board n = new Board();
       LinkedList game_board = new LinkedList<LinkedList>(4);
    assertEquals(n, game_board);
//need linked list

    }
    @Test
    public void testBoard2() {
        Board n = new Board(5);
        LinkedList game_board = new LinkedList<LinkedList>(5);
        assertEquals(n, game_board);
//need linked list

    }


    @Test(expected = IllegalArgumentException.class)
    public void testBoard3() {
        Board n = new Board(11);
//need linked list

    }

    @Test
    public void testTile1() {
        Tile t = new Tile();
        assertEquals(t.getTileValue(), 4);
    }
    @Test
    public void testTile2() {
        Tile t = new Tile(8);
        assertEquals(t.getTileValue(), 8);
    }
    @Test
    public void testTile3() {
        Tile t = new Tile(1024);
        assertEquals(t.getTileValue(), 1024);
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
        assertEquals(t.getTileValue(),64);
    }

    @Test
    public void testgetValue1v2() {
        Tile t = new Tile(128);
        assertEquals(t.getTileValue(),128);
    }
    @Test
    public void testPower2v1() {
        Tile t = new Tile(8);
        assertTrue(t.power2(t.getTileValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPower2v2() {
        Tile t = new Tile(45);
    }

    public void testPower2v3() {
        Tile t = new Tile(128);
        assertTrue(t.power2(t.getTileValue()));
    }

    public void testPower2v4() {
        Tile t = new Tile(1024);
        assertTrue(t.power2(t.getTileValue()));
    }
    @Test
    public void testToStringTile1() {
        Tile t = new Tile(2);
        assertEquals(t.toString(), 2);
    }
    @Test
    public void testToStringTile2() {
        Tile t = new Tile(64);
        assertEquals(t.toString(), 64);
    }
}

