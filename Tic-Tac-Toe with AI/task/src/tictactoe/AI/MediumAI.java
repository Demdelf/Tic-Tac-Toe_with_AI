package tictactoe.AI;

import tictactoe.GameField;
import tictactoe.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static tictactoe.Main.gameField;

public class MediumAI extends AI {

    @Override
    public void makeMove() {
        Point wp = have2InRow(gameField.isNextMoveX()? 'X' : 'O');
        if (wp != null){
            if (!gameField.isCellOccupied(wp.getX(), wp.getY()))
            gameField.setCell(wp.getX(), wp.getY());
        }else {
            Point lp = have2InRow(gameField.isNextMoveX()? 'O' : 'X');
            if (lp != null){
                if (!gameField.isCellOccupied(wp.getX(), wp.getY()))
                    gameField.setCell(wp.getX(), wp.getY());
            }else {
                Random random = new Random();
                int x = -1;
                int y = -1;
                while (true){
                    x =  1 + random.nextInt(3);
                    y = 1 + random.nextInt(3);
                    if (!gameField.isCellOccupied(x, y)) break;
                }
                gameField.setCell(x, y);
            }
        }


    }



    public Point have2InRow(char c){
        char[][] field = gameField.getField();
        StringBuilder canWin = new StringBuilder();
        canWin.append(c).append(c);
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            StringBuilder sb = new StringBuilder();
            int tx = -1;
            int ty = -1;
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == c) sb.append(field[i][j]);
                else if(Character.isLetter(field[i][j])) break;
                else {
                    tx = i;
                    ty = j;
                }
            }
            if (sb.length() == 2) return new Point(c, tx, ty);
        }

        for (int j = 0; j < 3; j++) {
            StringBuilder sb = new StringBuilder();
            int tx = -1;
            int ty = -1;
            for (int i = 0; i < 3; i++) {
                if (field[i][j] == c) sb.append(field[i][j]);
                else if(Character.isLetter(field[i][j])) break;
                else {
                    tx = i;
                    ty = j;
                }
            }
            if (sb.length() == 2) return new Point(c, tx, ty);
        }

        StringBuilder sb = new StringBuilder();
        int tx = -1;
        int ty = -1;
        for (int i = 0; i < 3; i++) {
            if (field[i][i] == c) sb.append(field[i][i]);
            else if(Character.isLetter(field[i][i])) break;
            else {
                tx = i;
                ty = i;
            }
        }
        if (sb.length() == 2) return new Point(c, tx, ty);

        sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            if (field[i][2 - i] == c) sb.append(field[i][i]);
            else if(Character.isLetter(field[i][2 - i])) break;
            else {
                tx = i;
                ty = i;
            }
        }
        if (sb.length() == 2) return new Point(c, tx, ty);
       return null;
    }
}
