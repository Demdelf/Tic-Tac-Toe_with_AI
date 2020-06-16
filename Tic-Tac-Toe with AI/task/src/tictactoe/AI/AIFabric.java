package tictactoe.AI;

import tictactoe.GameField;

public class AIFabric {

    public AI createAI(Level level){
        switch (level){
            case EASY:
                return new EasyAI();
            case MEDIUM:
                return new MediumAI();
            case HARD:
                return new HardAI();
        }
        return new EasyAI();
    }


}
