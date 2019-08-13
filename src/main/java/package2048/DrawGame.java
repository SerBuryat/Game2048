package main.java.package2048;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.io.BufferedReader;
import java.nio.ByteOrder;

class DrawGame extends JFrame {
    static final int SIDE = 4;
    static final DrawGame DRAW = new DrawGame();
    private JPanel complexityChooserPanel = new JPanel();
    private JPanel gamePanel = new JPanel();
    private JPanel infoPanel = new JPanel();
    private JLabel scoreLabel = new JLabel();
    JButton[][] gameField = new JButton[SIDE][SIDE];
    private Font font = new Font("Ariel",Font.BOLD,30);


    void initialize() {
        setTitle("2048 by SerBuryat");
        setSize(600,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setFocusable(true);

        setDifficultyPanel();

    }

    void drawField (int[][] intField) { // put intArray in JButtonArray (put each intToString to ButtonSetText) and draw it in app

        scoreLabel.setText("Goal : " + GameEngine.goal + " - Steps : " + String.valueOf(GameEngine.scoreSteps)); // changes score

        for(int x = 0; x < SIDE; x++) {
            for(int y = 0; y < SIDE; y++) {

                String s = String.valueOf(intField[x][y]);
                gameField[x][y].setText(s);

                gameField[x][y].setBackground(getBackgroundColor(intField[x][y]));

                if(intField[x][y] == 0)
                    gameField[x][y].setForeground(gameField[x][y].getBackground());
                else
                    gameField[x][y].setForeground(Color.BLACK);

            }
        }
    }

    private Color getBackgroundColor(int value) {
        switch (value) {
            case 2:    return new Color(0xeee4da);
            case 4:    return new Color(0xede0c8);
            case 8:    return new Color(0xf2b179);
            case 16:   return new Color(0xf59563);
            case 32:   return new Color(0xf67c5f);
            case 64:   return new Color(0xf65e3b);
            case 128:  return new Color(0xedcf72);
            case 256:  return new Color(0xedcc61);
            case 512:  return new Color(0xedc850);
            case 1024: return new Color(0xedc53f);
            case 2048: return new Color(0xedc22e);
        }
        return new Color(0xcdc1b4);
    }

    void getWinMessage () {
        JOptionPane.showMessageDialog(null, "Heyyyy, great!!! YOU WIN!" + "\n " + " Your score:  " + GameEngine.scoreSteps +"\n" + "Tap ESC to start new game :)");
    }

    void getLoseMessage () {
        JOptionPane.showMessageDialog(null, "Ohhhhh, sry buddy!!! YOU LOSE!" +"\n" +  " Your score:  " + GameEngine.scoreSteps +"\n" + "Tap ESC to start new game :)");
    }

    void startMessage () {
        JOptionPane.showMessageDialog(null, "Hello man! Some rules: Arrows(RIGHT,LEFT,UP,DOWN) is movement , ECS - reset. Goodluck :)");
    }

    private void setDifficultyPanel() {
        JLabel complexityLabel = new JLabel("Choose difficulty : ");
        JButton easyButton = new JButton("256");
        JButton mediumButton = new JButton("512");
        JButton hardButton = new JButton("1024");
        JButton nativeButton = new JButton("2048");

        getContentPane().add(complexityChooserPanel,BorderLayout.CENTER);
        complexityChooserPanel.add(complexityLabel);
        complexityChooserPanel.add(easyButton);
        complexityChooserPanel.add(mediumButton);
        complexityChooserPanel.add(hardButton);
        complexityChooserPanel.add(nativeButton);
        complexityChooserPanel.setLayout(new GridLayout(complexityChooserPanel.getComponentCount(),0));

        easyButton.addActionListener(e -> buttonAction(easyButton));
        mediumButton.addActionListener(e -> buttonAction(mediumButton));
        hardButton.addActionListener(e -> buttonAction(hardButton));
        nativeButton.addActionListener(e -> buttonAction(nativeButton));
    }

    private void setGamePanel() {
        getContentPane().add(infoPanel,BorderLayout.NORTH);
        infoPanel.add(scoreLabel);

        getContentPane().add(gamePanel, BorderLayout.CENTER);
        gamePanel.setLayout(new GridLayout(SIDE, SIDE));

        scoreLabel.setText("Goal : " + GameEngine.goal + " - Steps : " + String.valueOf(GameEngine.scoreSteps));

        for(int x = 0; x < SIDE; x++) {
            for(int y = 0; y < SIDE; y++) {
                gameField[x][y] = new JButton();
                gamePanel.add(gameField[x][y]);
                gameField[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gameField[x][y].setBackground(Color.YELLOW);
                gameField[x][y].setFont(font);
                gameField[x][y].setFocusable(false);
                gameField[x][y].setFocusPainted(false);
                gameField[x][y].setText("0");
            }
        }
    }

    private void buttonAction(JButton button) {
        GameEngine.setGOAL(Integer.parseInt(button.getText()));
        getContentPane().removeAll();
        setGamePanel();
        GameEngine.GAME_ENGINE.createGame();
    }

}


