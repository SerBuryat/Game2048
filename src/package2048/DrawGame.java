package package2048;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;


public class DrawGame extends JFrame {

    public static final int SIDE = 4;
    public static final DrawGame DRAW = new DrawGame();
    public JButton[][] gameField = new JButton[SIDE][SIDE];
    public static final Font font = new Font("Ariel",Font.BOLD,30);

    public void initialize() {
        setTitle("2048");
        setSize(600,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(SIDE, SIDE));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setFocusable(true);

        for(int x = 0; x < SIDE; x++) {
            for(int y = 0; y < SIDE; y++) {
                gameField[x][y] = new JButton();
                getContentPane().add(gameField[x][y]);
                gameField[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gameField[x][y].setBackground(Color.YELLOW);
                gameField[x][y].setFont(font);
                gameField[x][y].setFocusable(false);
                gameField[x][y].setFocusPainted(false);
                gameField[x][y].setText("0");
            }
        }
    }

    public void drawField (int[][] intField) { // put intArray in JButtonArray (put each intToString to ButtonSetText) and draw it in app

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

    public Color getBackgroundColor(int value) {
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

    public void getWinMessage (int score) {
        JOptionPane.showMessageDialog(null, "Heyyyy, great!!! YOU WIN!" + "\n " + " Your score:  " + score +"\n" + "Tap ESC to start new game :)");
    }

    public void getLoseMessage (int score) {
        JOptionPane.showMessageDialog(null, "Ohhhhh, sry buddy!!! YOU LOSE!" + "\n" + "  Your score:  " + score +"\n" + "Tap ESC to start new game :)");
    }

    public void startMessage () {
        JOptionPane.showMessageDialog(null, "Hello man! Some rules: ↑ ↓ → ← is movement , ECS - reset. Goodluck :)");
    }
}


