import java.util.ArrayList;
import java.util.List;

public class GameFactory {

    public static Game createGame(
            int boardSize,
            int playerCount,
            GameDifficulty difficulty) {

        Board board = new Board(boardSize);
        List<Player> players = new ArrayList<>();

        for (int i = 1; i <= playerCount; i++) {
            players.add( new Player( i, "Player-" + i));
        }

        return new Game( board,players,new SixDice(),difficulty);
    }
}