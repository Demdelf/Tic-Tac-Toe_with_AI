package tictactoe;

import java.util.ArrayList;
import java.util.List;

public class GameField {
    private char[][] field;
    boolean X = true;

    public GameField(String s) {
        this.field = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = s.charAt(3*i + j);
            }
        }
    }

    public GameField() {

    }

    public char[][] getField() {
        return field;
    }

    public void setField(char[][] field) {
        this.field = field;
    }

    public void printField(){
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }

        System.out.println("---------");
    }
    public boolean isFinished(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!Character.isLetter(field[i][j] )) return false;
            }
        }
        return true;
    }

    public boolean isPossible(){
        if (have3InRow('X') && have3InRow('O')) return false;
        int xQ = 0;
        int oQ = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 'X') xQ++;
                if (field[i][j] == 'O') oQ++;
            }
        }
        return Math.abs(xQ - oQ) < 2;
    }

    public boolean have3InRow(char c){
        StringBuilder win = new StringBuilder();
        win.append(c).append(c).append(c);
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                sb.append(field[i][j]);
            }
            list.add(sb.toString());
        }

        for (int j = 0; j < 3; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                sb.append(field[i][j]);
            }
            list.add(sb.toString());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(field[i][i]);
        }
        list.add(sb.toString());
        sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(field[i][2 - i]);
        }
        list.add(sb.toString());
        return list.contains(win.toString());
    }
    public boolean isCellOccupied(int x, int y){
        if (field[3 - x][y - 1] == 'X' || field[3 - x][y - 1] == 'O') return true;
        return false;
    }

    public void setCell(int x, int y){
        if (isNextMoveX())
        field[3 - x][y - 1] = 'X';
        else field[3 - x][y - 1] = 'O';
        X = !X;
    }

    public char getTurn(){
        if (isNextMoveX()) return 'X';
        else return 'O';
    }

    public boolean isNextMoveX(){
        int xQ = 0;
        int oQ = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 'X') xQ++;
                if (field[i][j] == 'O') oQ++;
            }
        }
        return xQ == oQ;
    }

    public String getState(){
        if (!isPossible()) return "Impossible";
        if (have3InRow('X')) return "X wins";
        if (have3InRow('O')) return "O wins";
        if (!isFinished()) return "Game not finished";
        return "Draw";
    }


    public List<Point> getAvailableMoves() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] != 'X' && field[i][j] != 'O') points.add(new Point(' ', i, j));
            }
        }
        return points;
    }

    public GameField getDeepCopy() {
        GameField gameField = new GameField();
        gameField.field = new char[3][3];
        for (int i = 0; i < this.field.length; i++) {
            gameField.field[i] = this.field[i].clone();
        }
        gameField.X = this.X;
        return gameField;

    }
}
