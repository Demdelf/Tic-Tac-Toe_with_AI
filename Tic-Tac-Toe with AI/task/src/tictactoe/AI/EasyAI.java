package tictactoe.AI;

import tictactoe.AI.AI;
import tictactoe.GameField;

import java.util.Random;

import static tictactoe.Main.gameField;

public class EasyAI extends AI {

    @Override
    public void makeMove() {
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
