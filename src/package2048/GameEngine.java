package package2048;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameEngine {

    public static final GameEngine GAME_ENGINE = new GameEngine();
    public static final int GOAL = 2048;
    private boolean isGameStopped;
    private int score;

    public GameEngine () {
        DrawGame.DRAW.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT   : moveLeft();
                        break;

                    case KeyEvent.VK_UP     : moveUp();
                        break;

                    case KeyEvent.VK_RIGHT  : moveRight();
                        break;

                    case KeyEvent.VK_DOWN   :  moveDown();
                        break;

                    case KeyEvent.VK_ESCAPE :  reset();
                        break;
                }
            }
        });
    }

    public void createGame() {
        isGameStopped = false;
        score = 0;

        createNewNumber();
        createNewNumber();
    }

    private void moveLeft() {
        if(isGameStopped == false) {
            if(canMove()) {

                boolean isMove = false;
                int[][] gameField = getGameField();

                for(int y = 0;y<DrawGame.SIDE;y++) {
                    if(compressRow(gameField[y]))
                        isMove = true;
                    if(mergeRow(gameField[y]))
                        isMove = true;
                    compressRow(gameField[y]);
                }

                DrawGame.DRAW.drawField(gameField);

                if(isMove)
                    createNewNumber();

            } else
                lose();

            if (getMaxNumber() == GOAL)
                win();
        }

    }

    private void moveUp() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }

    private void moveRight() {
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }

    private void moveDown() {
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }


    private void rotateClockwise() {
        if (isGameStopped == false) {
            int[][] tempField = new int[DrawGame.SIDE][DrawGame.SIDE];
            int[][] gameField = getGameField();

            for(int x = 0; x < DrawGame.SIDE; x++) {
                int i = 0;
                for(int y = DrawGame.SIDE-1; y >= 0; y--) {
                    tempField[x][i] = gameField[y][x];
                    i++;
                }
            }
            gameField = tempField;
            DrawGame.DRAW.drawField(gameField);
        }
    }

    private boolean mergeRow(int[] row) {
        boolean isMove = false;

        for(int i = 0;i<row.length-1;i++) {
            if(row[i] == row[i+1] && row[i] != 0) {
                row[i] = row[i] + row[i+1];
                score += row[i];
                row[i+1] = 0;
                isMove = true;
            }
        }

        return isMove;
    }

    private boolean compressRow(int[] row) {
        boolean isMove = false;

        for(int i = 0;i<row.length-1;i++) {
            for(int j = 0;j<row.length-1;j++) {
                if(row[j] == 0 && row[j+1] != 0 ) {
                    row[j] = row[j+1];
                    row[j+1] = 0;
                    isMove = true;
                }
            }
        }

        return isMove;
    }

    private void createNewNumber() {
        if(isEmptyTile()) {
            int[][] gameField = getGameField();
            int x = getRandomCoordinate();
            int y = getRandomCoordinate();
            int number = 2;

            while (gameField[x][y] != 0) {
                x = getRandomCoordinate();
                y = getRandomCoordinate();
            }

            if( (int)(Math.random() * 10) == 4)
                number = 4;

            gameField[x][y] = number;

            DrawGame.DRAW.drawField(gameField);

        }
    }

    private int getRandomCoordinate () {
        int coordinate = (int) ((Math.random()) * 4);

        return coordinate;
    }

    private int[][] getGameField() {
        int[][] field = new int[DrawGame.SIDE][DrawGame.SIDE];

        for(int x = 0; x < DrawGame.SIDE; x++) {
            for(int y = 0; y < DrawGame.SIDE; y++) {
                String s = DrawGame.DRAW.gameField[x][y].getText();
                field[x][y] = Integer.parseInt(s);
            }
        }

        return field;
    }

    private boolean isEmptyTile () {
        for(int x = 0; x < DrawGame.SIDE; x++) {
            for(int y = 0; y < DrawGame.SIDE; y++) {
                if(DrawGame.DRAW.gameField[x][y].getText().equals("0"))
                    return true;
            }
        }
        return false;
    }

    private void win () {
        isGameStopped = true;
        DrawGame.DRAW.getWinMessage(score);
    }

    private void lose() {
        isGameStopped = true;;
        DrawGame.DRAW.getLoseMessage(score);
    }

    private void reset() {
        int[][] gameField = new int[DrawGame.SIDE][DrawGame.SIDE];
        DrawGame.DRAW.drawField(gameField);
        createGame();
    }

    private boolean canMove() {

        int[][] gameField = getGameField();

        for(int x = 0; x < DrawGame.SIDE; x++) {
            for(int y = 0; y < DrawGame.SIDE; y++) {
                if(gameField[x][y] == 0)
                    return true;
            }
        }


        for(int x = 0; x < DrawGame.SIDE; x++) {
            for(int y = 0; y < DrawGame.SIDE; y++) {
                if(y+1 < DrawGame.SIDE && gameField[y][x] == gameField[y+1][x])
                    return true;
                if(x+1 < DrawGame.SIDE && gameField[y][x] == gameField[y][x+1])
                    return true;
                if(y-1 >= 0 && gameField[y][x] == gameField[y-1][x])
                    return true;
                if(x-1 >= 0 && gameField[y][x] == gameField[y][x-1])
                    return true;
            }
        }

        return false;
    }

    public int getMaxNumber () {
        int max = 0;
        int[][]gameField = getGameField();

        for(int x = 0; x < DrawGame.SIDE; x++) {
            for(int y = 0; y < DrawGame.SIDE; y++) {
                if(gameField[x][y] > max)
                    max = gameField[x][y];
            }
        }

        return max;
    }
}
