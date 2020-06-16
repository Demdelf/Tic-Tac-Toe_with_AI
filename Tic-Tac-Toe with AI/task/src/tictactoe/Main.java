package tictactoe;

import tictactoe.AI.AI;
import tictactoe.AI.AIFabric;
import tictactoe.AI.EasyAI;
import tictactoe.AI.Level;

import java.util.Scanner;

public class Main {
    public static GameField gameField;
    public static Scanner scanner;
    private static AI ai;
    public static void main(String[] args) {
        // write your code here
        scanner = new Scanner(System.in);
        gameField = new GameField("         ");
        gameField.printField();
        AIFabric aiFabric = new AIFabric();
        //ai = new EasyAI();
        String[] command = getInputCommand();

        while (!command[0].equals("exit")){
            if (command[1].equals("user") && !command[2].equals("user")) {
                ai = aiFabric.createAI(Level.valueOf(command[2].toUpperCase()));
                playGame("user&AI");
            }
            else if (!command[1].equals("user") && command[2].equals("user")){
                ai = aiFabric.createAI(Level.valueOf(command[1].toUpperCase()));
                playGame("AI&user");
            }
            else if (!command[1].equals("user") && !command[2].equals("user")){
                ai = aiFabric.createAI(Level.valueOf(command[2].toUpperCase()));
                playGame("AI&AI");
            }
            else playGame("user&user");
            command = getInputCommand();
        }

    }

    public static void playGame(String mode){
        switch (mode){
            case "user&AI":
                while (gameField.getState().equals("Game not finished")){

                    int x = -1;
                    int y = -1;
                    boolean flag = true;

                    while (x < 0 || y < 0 || flag){
                        try {

                            System.out.println("Enter the coordinates: ");
                            String[] s = scanner.nextLine().split(" ");
                            if (s.equals("exit")) break;

                            x = Integer.parseInt(s[0]);
                            y = Integer.parseInt(s[1]);

                            if ((x < 1 || x > 3) || (y < 1 || y > 3)){
                                System.out.println("Coordinates should be from 1 to 3!");
                                continue;
                            }
                            flag = gameField.isCellOccupied(y, x);
                            if (flag) {
                                System.out.println("This cell is occupied! Choose another one!");
                            }else {
                                gameField.setCell(y, x);
                            }
                        }catch (Exception e){
                            System.out.println("You should enter numbers!");
                        }
                    }

                    gameField.printField();
                    if (!gameField.getState().equals("Game not finished")){
                        break;
                    }
                    System.out.println("Making move level \"easy\"");
                    ai.makeMove();
                    gameField.printField();
                }

                System.out.println(gameField.getState());
                break;
            case "AI&user":
                while (gameField.getState().equals("Game not finished")){

                    int x = -1;
                    int y = -1;
                    boolean flag = true;

                    while (x < 0 || y < 0 || flag){
                        System.out.println("Making move level \"easy\"");
                        ai.makeMove();
                        gameField.printField();
                        if (!gameField.getState().equals("Game not finished")){
                            break;
                        }
                        try {

                            System.out.println("Enter the coordinates: ");
                            String[] s = scanner.nextLine().split(" ");
                            if (s.equals("exit")) break;
                            x = Integer.parseInt(s[0]);
                            y = Integer.parseInt(s[1]);

                            if ((x < 1 || x > 3) || (y < 1 || y > 3)){
                                System.out.println("Coordinates should be from 1 to 3!");
                                continue;
                            }
                            flag = gameField.isCellOccupied(y, x);
                            if (flag) {
                                System.out.println("This cell is occupied! Choose another one!");
                            }else {
                                gameField.setCell(y, x);
                            }
                        }catch (Exception e){
                            System.out.println("You should enter numbers!");
                        }
                    }

                    gameField.printField();
                    if (!gameField.getState().equals("Game not finished")){
                        break;
                    }


                }

                System.out.println(gameField.getState());
                break;
            case "AI&AI":
                while (gameField.getState().equals("Game not finished")){
                    while (true){
                        System.out.println("Making move level \"easy\"");
                        ai.makeMove();
                        gameField.printField();
                        if (!gameField.getState().equals("Game not finished")){
                            break;
                        }

                    }
                }
                System.out.println(gameField.getState());
                break;
            case "user&user":
                while (gameField.getState().equals("Game not finished")){

                    int x = -1;
                    int y = -1;
                    boolean flag = true;
                    String ex = "";

                    while ((x < 0 || y < 0 || flag) && !ex.equals("exit")){
                        try {

                            System.out.println("Enter the coordinates: ");
                            String[] s = scanner.nextLine().split(" ");
                            if (s[0].equals("exit")) break;
                            x = Integer.parseInt(s[0]);
                            y = Integer.parseInt(s[1]);

                            if ((x < 1 || x > 3) || (y < 1 || y > 3)){
                                System.out.println("Coordinates should be from 1 to 3!");
                                continue;
                            }
                            flag = gameField.isCellOccupied(y, x);
                            if (flag) {
                                System.out.println("This cell is occupied! Choose another one!");
                            }else {
                                gameField.setCell(y, x);
                            }
                        }catch (Exception e){
                            System.out.println("You should enter numbers!");
                        }
                    }

                    gameField.printField();
                    if (!gameField.getState().equals("Game not finished")){
                        break;
                    }


                }

                System.out.println(gameField.getState());
                break;

        }
    }

    public static String[] getInputCommand(){
        String[] command;
        while (true){
            System.out.println("Input command: ");
            command = scanner.nextLine().split(" ");
            if (command[0].equals("exit")) return new String[]{"exit"};
            if (command.length == 3){
                if ((command[1].equals("user") || command[1].equals("easy")) && (command[2].equals("user") || command[2].equals("easy")))
                    break;
            }
            System.out.println("Bad parameters!");

        }
        return command;
    }
}
