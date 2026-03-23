import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter board size n for an nxn board: ");

        int boardSize = sc.nextInt();
        System.out.print("Enter number of players: ");

        int playerCount =sc.nextInt();
        System.out.print("Enter game difficulty (EASY or DIFFICULT): ");

        String input = sc.next().trim().toUpperCase();
        GameDifficulty difficulty = GameDifficulty.EASY;

        if (input.equals("DIFFICULT")) {
            difficulty = GameDifficulty.DIFFICULT;
        }
        Game game = GameFactory.createGame(boardSize, playerCount, difficulty);
        while (!game.isFinished()) {
            game.makeTurn();
        }
    }
}