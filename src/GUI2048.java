import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GUI2048 extends JPanel implements ActionListener, KeyListener{ // has JFrame and GUI2048Panel which extends JPanel

    private GameController game;
    private boolean keyPressed; // for use in keyPressed & keyReleased
    private JFrame gui;
    /**
     * JPanel that contains the JLabels that represent the 2048 board
     */
    private JPanel game_panel;
    /**
     * JPanel that contains the # of games played, # of games won, and JButtons UP, DOWN, LEFT, RIGHT
     */
    private JPanel info_panel;
    private JButton[][] JButtonsBoard;
    private JLabel gamesPlayed;
    private JLabel gamesWon;
    /**
     * JButtons that allow movement
     */
    private JButton UP;
    private JButton DOWN;
    private JButton LEFT;
    private JButton RIGHT;
    /**
     * File menu in top left to quit or reset the board
     */
    private JMenu fileMenu;
    private JMenuItem quitItem;
    private JMenuItem resetItem;

    public GUI2048(){
        int size = getSizeInput(); // collects size of board
        int winVal = getWinVal(); // collects winning #
        game = new GameController(size, winVal); // creates 2048 game
        initialize();
    }

    public int getSizeInput(){
        int input_size = 0;

        // Attempt to get size of board from user
        try {
            while (input_size <= 3 || input_size >= 11) { // board sizes of 4 - 10
                String s = JOptionPane.showInputDialog(null,
                        "Enter desired size of 2048:");
                if (s == null) {
                    System.exit(0);
                }
                input_size = Integer.parseInt(s);
            }
        }catch (Exception e) { // Default to 4 if user inputs invalid size or closes window
            input_size = 4;
        }
        return input_size;
    }

    public int getWinVal(){
        int input_winValue = 0;

        // Attempt to get win value from user
        try {
            while (input_winValue <= 3 /**||!power2(input_winValue)**/) { // HOW TO USE POWER2?
                String s = JOptionPane.showInputDialog(null,
                        "Enter desired win value for 2048:");
                if (s == null) {
                    System.exit(0);
                }
                input_winValue = Integer.parseInt(s);
            }
        }catch (Exception e) { // Default to 2048 if user inputs invalid size or closes window
            input_winValue = 2048;
        }
        return input_winValue;
    }

    private void showStatus(GameStatus status) {
        if (status == GameStatus.WON) {
            JOptionPane.showMessageDialog(null, "Nice Job!");
        }
            // if status == GameStatus.LOST
        JOptionPane.showMessageDialog(null, "Better luck next time!");
    }

    /**
    private Color getColor(String value){
        switch(value){
            case(){

            }
        }
    }
    **/

    public void initialize(){ // setup GUI stuff
        keyPressed = false;
        gui = new JFrame();
        game_panel = new JPanel();
        info_panel = new JPanel();
        gamesPlayed = new JLabel("1");
        gamesWon = new JLabel("0");

        for(int i = 0; i < game.getBoard().getSize(); i++){
            for(int j = 0; j < game.getBoard().getSize(); j++){
                JButtonsBoard[i][j] = new JButton(); // implement border design from TicTacToe?
            }
        }

        /**TODO: add location-specific borders to buttons, set layout, add items to gui
         *
         */

    }

    public void update(){

    }

    @Override
    public void actionPerformed(ActionEvent e) { // for side buttons

    }

    @Override
    public void keyTyped(KeyEvent e) {  // for movement keys

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    public static void main(String[] args){
        GUI2048 main_game = new GUI2048();
    }


}
