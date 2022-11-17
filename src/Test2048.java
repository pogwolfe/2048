import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

import static org.junit.Assert.*;


public class Test2048 {

    @Test
    public void testBoard1() {//
    Board n = new Board();

    assertEquals(4, n.getSize());

//need linked list

    }
    @Test
    public void testBoard2() {
        Board n = new Board(5);
        assertEquals(5,n.getSize());
//need linked list

    }

    @Test(expected = IllegalArgumentException.class)
    public void testBoard3() {
        Board n = new Board(20);

    }

    @Test
    public void testTile1() {
        Tile t = new Tile();
        int result = t.getTileValue();
        if (result == 2 || result ==4){
            assertTrue(true);
        }
        else {
            assertTrue(false);
        }

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
        Board n = new Board();
        Tile T = new Tile();
        boolean result;

        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++){
                n.setTile(i,j, T );
            }
        }

        result = n.hasEmpty();

        assertEquals(false,result);

    }

    @Test
    public void testHasEmpty2() {
        Board n = new Board();

        assertTrue(n.hasEmpty());

    }

    @Test
    public void testgetTile1() {
    Tile T = new Tile();
    int value = T.getTileValue();
    if (value == 2 || value == 4){
        assertTrue(true);
        //need linked list
    }
    else {
        assertTrue(false);
    }

    }


    @Test
    public void testsetTile1() {
        Tile T = new Tile();
        T.setTileValue(2);

        assertEquals(2, T.getTileValue() );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testsetTile2() {
        Board n = new Board();
        Tile t = new Tile();
        n.setTile(11,2,t);

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

    @Test(expected = IllegalArgumentException.class)
    public void testgetTileBoard() {
        Board n = new Board();
        n.getTile(11,3);
    }
    @Test
    public void testgetTileBoard2() {
        Board n = new Board();
        Tile t = new Tile(2);
        n.setTile(1,2,t);

        assertEquals(t,n.getTile(1,2));

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
        String T = "2";
        assertEquals(t.toString(), T);
    }
    @Test
    public void testToStringTile2() {
        Tile t = new Tile(64);
        String T = "64";
        assertEquals(t.toString(), T);
    }





    @Test
    public void testgetsize(){

        Board n = new Board();
        assertEquals(4,n.getSize());

    }
    @Test(expected = IllegalArgumentException.class)
    public void testgetvalue(){
        Board n = new Board();
        n.getValue(11,2);

    }

    @Test
    public void testPrintBoard(){
        Board n = new Board();
        n.printBoard();
    }
}

