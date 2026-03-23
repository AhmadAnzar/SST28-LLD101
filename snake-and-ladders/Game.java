import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    private final Board board;
    private final List<Player> players;
    private final Dice dice;
    private final GameDifficulty difficulty;
    private final List<Player> winners;
    private int currentPlayerIndex;

    public Game(Board board, List<Player> players, Dice dice, GameDifficulty difficulty) {
        this.board = board;
        this.players = players;
        this.dice = dice;
        this.difficulty = difficulty;
        this.winners = new ArrayList<>();
    }

    public void makeTurn() {
        if (isFinished()) {
            return;
        }
        currentPlayerIndex = getNextActivePlayerIndex(currentPlayerIndex);
        Player player = players.get(currentPlayerIndex);
        int consecutiveSixes = 0;

        boolean extraTurn = true;

        while (extraTurn) {
            extraTurn = false;
            int roll = dice.roll();

            if (roll == 6) { consecutiveSixes++;
            } else { consecutiveSixes = 0;
            }
            int currentPosition = player.getPosition();

            int tentativePosition = currentPosition + roll;

            if (tentativePosition <= board.getFinalSquare()) {
                player.setPosition(tentativePosition);
                Jump object = board.getEntityAt(player.getPosition());

                if (object != null) {
                    player.setPosition( player.getPosition() + object.getJump());
                }
            }

            System.out.println(player.getName() + " rolled " + roll);

            System.out.println("position"+ player.getPosition());

            if (player.getPosition()==board.getFinalSquare()) {
                winners.add(player);
                System.out.println(player.getName() + " wins");
                if (!isFinished()) {
                    currentPlayerIndex = getNextActivePlayerIndex((currentPlayerIndex + 1)% players.size());
                }
                return;
            }
            if (roll == 6) {
                if (difficulty == GameDifficulty.EASY) {
                    extraTurn = true;
                }
                else if (consecutiveSixes < 3) {
                    extraTurn = true;
                }
            }
            if (roll == 6 && difficulty == GameDifficulty.DIFFICULT && !extraTurn) {
                System.out.println("turn over");
            }
        }

        currentPlayerIndex = getNextActivePlayerIndex((currentPlayerIndex + 1)% players.size());
    }

    public Player getWinner() {
        if (winners.isEmpty()) {
            return null;
        }
        return winners.get(0);
    }

    public List<Player> getWinners() {
        return Collections.unmodifiableList(winners);
    }

    public boolean isFinished() {
        return players.size() - winners.size() < 2;
    }

    private int getNextActivePlayerIndex(int startIndex) {
        for (int i=0; i<players.size();i++) {
            int candidateIndex = (startIndex + i) % players.size();
            if (!winners.contains(players.get(candidateIndex))) {
                return candidateIndex;
            }
        }
        return startIndex;
    }
}