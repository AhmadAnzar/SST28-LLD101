import java.util.ArrayList;
import java.util.List;

class Screen {
    int id;
    String name;

    List<Seat> seats = new ArrayList<>();

    Screen(int id, String name) {
        this.id = id;
        this.name = name;
    }

    void addSeat(Seat seat) {
        seats.add(seat);
    }
}