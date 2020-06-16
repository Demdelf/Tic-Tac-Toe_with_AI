package tictactoe.AI;

import tictactoe.GameField;
import tictactoe.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static tictactoe.Main.gameField;

public class HardAI extends AI {


    @Override
    public void makeMove() {
        MiniMax.run(gameField, gameField.getTurn(), Double.POSITIVE_INFINITY);
    }




}
