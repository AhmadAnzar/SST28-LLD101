
import java.util.HashMap;
import java.util.Map;

public class Parkingslot {
    String id;
    String levelId;
    String type;
    boolean occupied;
    Map<String, Integer> distanceByGate = new HashMap<>();

    public Parkingslot(String id, String levelId, String type) {
        this.id = id;
        this.levelId = levelId;
        this.type = type;
    }

    public Parkingslot setDistance(String gateId, int distance) {
        distanceByGate.put(gateId, distance);
        return this;
    }

    public int getDistance(String gateId) {
        Integer distance = distanceByGate.get(gateId);
        if (distance == null) {
            throw new RuntimeException("Distance not found for gate " + gateId + " and slot " + id);
        }
        return distance;
    }
}
