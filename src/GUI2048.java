import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GUI2048 extends JPanel implements KeyListener{ // has JFrame and GUI2048Panel which extends JPanel

    private GameController game;
    private boolean keyPressed; // for use in keyPressed & keyReleased
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
        update();
    }

    public GameController get_game(){
        return this.game;
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

    public void initialize(){ // setup GUI stuff
        keyPressed = false;
        buttonListener = new ButtonListener();
        gui = new JFrame();
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_panel = new JPanel();
        info_panel = new JPanel();
        gamesPlayed = new JLabel("1");
        gamesWon = new JLabel("0");
        UP = new JButton("UP");
        DOWN = new JButton("DOWN");
        LEFT = new JButton("LEFT");
        RIGHT = new JButton("RIGHT");

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

        // adds individual actionListeners to the movement buttons displayed in buttonPanel
        UP.addActionListener(buttonListener::actionPerformed_UP);
        DOWN.addActionListener(buttonListener::actionPerformed_DOWN);
        LEFT.addActionListener(buttonListener::actionPerformed_LEFT);
        RIGHT.addActionListener(buttonListener::actionPerformed_RIGHT);

        // creates menu to hold fileMenu
        JMenuBar menus = new JMenuBar();
        menus.add(fileMenu);

        // creates game_panel to hold the 2048 buttons
        game_panel = new JPanel();
        game_panel.setLayout(new GridLayout(game.getBoard().getSize(), game.getBoard().getSize()));
        game_panel.setSize(new Dimension(500, 500));

        // creates the info_panel to hold our movement buttons and display games played/won
        info_panel = new JPanel();
        info_panel.setLayout(new GridLayout(6, 1)); // reformat movement buttons?
        info_panel.add(gamesPlayed);
        info_panel.add(gamesWon);
        info_panel.add(UP);
        info_panel.add(DOWN);
        info_panel.add(LEFT);
        info_panel.add(RIGHT);

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
                JButtonsBoard[i][j].setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, Color.getHSBColor(44, 67, 100)));
                JButtonsBoard[i][j].setBackground(Color.darkGray);
                count++;
            }
        }

        // TODO: format the info_panel elements
        gui.getContentPane().add(info_panel);
        gui.getContentPane().add(game_panel);
        gui.setSize(610, 560);
        gui.setJMenuBar(menus);
        gui.setVisible(true);
    }

    public void update(){

        // update button colors
        for(int i = 0; i < game.getBoard().getSize(); i++){
            for(int j = 0; j < game.getBoard().getSize(); j++){
                if(game.getBoard().getValue(i, j) != -1) { // if Tile is not blank
                    JButtonsBoard[i][j].setText("" + game.getBoard().getValue(i, j));
                    JButtonsBoard[i][j].setBackground(getColor(JButtonsBoard[i][j].getText()));
                }
            }
        }

        // check for wins and losses
        game.checkWin();
        game.checkLoss();
    }

     private Color getColor(String value) {
         return switch (value) {
             case "2" -> Color.PINK;
             case "4" -> Color.MAGENTA;
             case "8" -> Color.BLUE;
             case "16" -> Color.CYAN;
             case "32" -> Color.GREEN;
             case "64" -> Color.ORANGE;
             case "128" -> Color.RED;
             case "256" -> Color.GRAY;
             case "512" -> Color.LIGHT_GRAY;
             case "1024" -> Color.getHSBColor(44, 67, 100); // I have zero clue what color this is
             case "2048" -> Color.getHSBColor(99, 20, 100); // just making them up at this point
             // and so on
             default -> Color.BLACK; // for numbers beyond 2048
         };
     }

    @Override
    public void keyTyped(KeyEvent e) {  // for movement keys

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(!keyPressed){ // ensures that key is not currently being held down
            if(e.getKeyCode() == 87) { // W key
                game.moveVertical(-1); // move up
            }
            if(e.getKeyCode() == 65) { // A key
                game.moveHorizontal(-1); // move left
            }
            if(e.getKeyCode() == 83) { // S key
                game.moveVertical(1); // move down
            }
            if(e.getKeyCode() == 68) { // D key
                game.moveVertical(1); // move right
            }
        }
        keyPressed = true;
        update();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyPressed = false; // resets keyPressed

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

        public void actionPerformed_UP(ActionEvent e) {
            game.moveVertical(-1);
            update();
        }

        public void actionPerformed_DOWN(ActionEvent e) {
            game.moveVertical(1);
            update();
        }

        public void actionPerformed_LEFT(ActionEvent e) {
            game.moveHorizontal(-1);
            update();
        }

        public void actionPerformed_RIGHT(ActionEvent e) {
            game.moveHorizontal(1);
            update();
        }
    }


    public static void main(String[] args){
        GUI2048 main_game = new GUI2048();
    }


}
