import java.util.ArrayList;
import java.util.List;

public class Levels {
    String id;
    List<Parkingslot> slots = new ArrayList<>();

    public Levels(String id) {
        this.id = id;
    }

    public Levels addSlot(Parkingslot slot) {
        slots.add(slot);
        return this;
    }
}