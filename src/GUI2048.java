/**
 * Authors: Connor Wolfe, Parker Kuchulan, Catherine Stacey
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GUI2048 extends JPanel implements KeyListener{ // has JFrame and GUI2048Panel which extends JPanel

    private GameController game;
    private boolean keyDown; // for use in keyPressed & keyReleased
    private ButtonListener buttonListener;
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
    private int gamesPlayedTracker = 1;
    private int gamesWonTracker = 0;
    private JLabel gamesPlayed;
    private JLabel gamesWon;
    /**
     * File menu in top left to quit or reset the board
     */
    private JMenu fileMenu;
    private JMenuItem quitItem;
    private JMenuItem resetItem;

    /**
     * constructor for the gui
     */
    public GUI2048(){
        int size = getSizeInput(); // collects size of board
        int winVal = getWinVal(); // collects winning #
        game = new GameController(size, winVal); // creates 2048 game
        initialize();
        update();
    }

    /**
     * @return the current gamecontroller
     */
    public GameController get_game(){
        return this.game;
    }

    /**
     * @return the size of the board inputed by the user, or the default size if nothing added
     */
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

    /**
     * @return the value to win the game inputed by the user or the defualt value if nothing was inputed
     */

    public int getWinVal(){
        int input_winValue = 0;

        // Attempt to get win value from user
        try {
            while (input_winValue <= 3 ||  !(power2(input_winValue))) { // HOW TO USE POWER2?
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

    /**
     * @param N the value inputed by the user
     * @return true if the value is a valid power of 2, false if not
     */

    private boolean power2(double N){
        if (N < 2){
            return false;
        }
        if(N == 2){
            return true;

        }

        return power2(N/2);
    }

    /**
     * @param status will display a message if the game is finished
     */
    private void showStatus(GameStatus status) {
        if (status == GameStatus.WON) {
            JOptionPane.showMessageDialog(null, "Nice Job!");
            gamesWonTracker++;
        } else if (status == GameStatus.LOST) { // if status == GameStatus.LOST
            JOptionPane.showMessageDialog(null, "Better luck next time!");
        }
        gui.dispose();
        gamesPlayedTracker++;
    }

    /**
     * intializes the board
     */
    public void initialize(){ // setup GUI stuff
        keyDown = false;
        buttonListener = new ButtonListener();
        gui = new JFrame();
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_panel = new JPanel();
        info_panel = new JPanel();
        gamesPlayed = new JLabel("Games Played: " + gamesPlayedTracker);
        gamesWon = new JLabel("Games Won: " + gamesWonTracker);

        // create fileMenu elements
        fileMenu = new JMenu("File:");
        quitItem = new JMenuItem("Quit!");
        resetItem = new JMenuItem("Reset");

        // add elements to fileMenu
        fileMenu.add(quitItem);
        fileMenu.add(resetItem);

        // adds individual ButtonListeners to each menu item
        quitItem.addActionListener(buttonListener::actionPerformed_quit);
        resetItem.addActionListener(buttonListener::actionPerformed_reset);

        // creates menu to hold fileMenu
        JMenuBar menus = new JMenuBar();
        menus.add(fileMenu);

        // creates game_panel to hold the 2048 buttons
        game_panel = new JPanel();
        game_panel.setLayout(new GridLayout(game.getBoard().getSize(), game.getBoard().getSize()));
        game_panel.setBounds(0, 20, 160 * game.getBoard().getSize(), 160 * game.getBoard().getSize());

        // creates the info_panel to hold our movement buttons and display games played/won
        info_panel = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        info_panel.add(gamesPlayed);
        info_panel.add(new JLabel("    |    "));
        info_panel.add(gamesWon);
        info_panel.setLayout(layout);
        info_panel.setBounds(((160 * game.getBoard().getSize()) / 2) - 155, 0, 300, 20);

        // creates the JButtonsBoard
        JButtonsBoard = new JButton[game.getBoard().getSize()][game.getBoard().getSize()];

        int count = 0;

        // creates buttons and sets borders
        for (int i = 0; i < JButtonsBoard.length; i++) {
            for (int j = 0; j < JButtonsBoard[0].length; j++) {
                JButtonsBoard[i][j] = new JButton("");
                JButtonsBoard[i][j].addActionListener(buttonListener);
                JButtonsBoard[i][j].setName(String.valueOf(count));
                game_panel.add(JButtonsBoard[i][j]);
                JButtonsBoard[i][j].setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, new Color(187,173,160)));
                JButtonsBoard[i][j].setBackground(new Color(205,193,180));
                JButtonsBoard[i][j].addKeyListener(this);
                count++;
            }
        }

        // TODO: format the info_panel elements
        gui.getRootPane().setDefaultButton(JButtonsBoard[0][0]);
        game_panel.requestFocus();
        //gui.setLayout(new GridLayout(2, 1));
        gui.setLayout(null);
        gui.getContentPane().add(info_panel);
        gui.getContentPane().add(game_panel);
        gui.setSize(new Dimension(160 * game.getBoard().getSize() + 15, 160 * game.getBoard().getSize() + 80));
        gui.setJMenuBar(menus);
        gui.setVisible(true);
    }

    /**
     * updates the board after very move
     */
    public void update(){

        // update button colors
        for(int i = 0; i < game.getBoard().getSize(); i++){
            for(int j = 0; j < game.getBoard().getSize(); j++){
                if(game.getBoard().getValue(i, j) == -1) { // if Tile is not blank, change displayed value
                    JButtonsBoard[i][j].setText("");
                } else{
                    JButtonsBoard[i][j].setText("" + game.getBoard().getValue(i, j));
                    JButtonsBoard[i][j].setFont(new Font("Text", Font.BOLD, 50));
                    if (game.getBoard().getValue(i,j) < 7) {
                        JButtonsBoard[i][j].setForeground(new Color(119, 110, 101));
                    }
                    else {
                        JButtonsBoard[i][j].setForeground(new Color(249,246,242));
                    }
                }
                JButtonsBoard[i][j].setBackground(getColor(JButtonsBoard[i][j].getText()));
            }
        }

        // check for wins and losses
        game.checkWin();
        if (!checkfinish()){
            game.checkLoss();
            checkfinish();
        }

    }

    /**
     * @return true if the game status has changed from IN_PROGRESS, false otherwise
     */
    private boolean checkfinish(){
        boolean hasfinished = false;
        if(game.getStatus() != GameStatus.IN_PROGRESS){ // if game is over
            showStatus(game.getStatus());
            game.reset();
            initialize();
            update();
            
            hasfinished = true;
        }
        return hasfinished;
    }

    /**
     *sets the color of the tiles
     */

     private Color getColor(String value) {
         return switch (value) {
             case "" -> new Color(205,193,180);
             case "2" -> new Color(238,228,218);
             case "4" -> new Color(238,225,201);
             case "8" -> new Color(243,178,122);
             case "16" -> new Color(246,150,100);
             case "32" -> new Color(247,125,95);
             case "64" -> new Color(246,94,59);
             case "128" -> new Color(237,208,115);
             case "256" -> new Color(237,204,97);
             case "512" -> new Color(237,200,80);
             case "1024" -> new Color(237,197,63);
             case "2048" -> new Color(237,194,46);
             default -> Color.BLACK; // for numbers beyond 2048
         };
     }

    @Override
    public void keyTyped(KeyEvent e) {  // for movement keys

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(!keyDown){ // ensures that key is not currently being held down
            if(e.getKeyCode() == 87) { // W key
                game.moveVertical(-1); // move up
                game.newTile();
                update();
            }
            if(e.getKeyCode() == 65) { // A key
                game.moveHorizontal(-1); // move left
                game.newTile();
                update();
            }
            if(e.getKeyCode() == 83) { // S key
                game.moveVertical(1); // move down
                game.newTile();
                update();
            }
            if(e.getKeyCode() == 68) { // D key
                game.moveHorizontal(1); // move right
                game.newTile();
                update();
            }
            keyDown = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyDown = false; // resets keyPressed

    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
        

        public void actionPerformed_quit(ActionEvent e){
            System.exit(0);
        }

        public void actionPerformed_reset(ActionEvent e){
            gui.dispose(); // closes last game
            game = new GUI2048().get_game(); // creates new game
        }

    }


    public static void main(String[] args){
        GUI2048 main_game = new GUI2048();
    }


}
