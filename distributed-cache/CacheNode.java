import java.util.HashMap;
import java.util.Map;

public class CacheNode {
    private final String serverId;
    private final int maxCapacity;
    private final Map<String, String> storage = new HashMap<>();
    private final EvictionPolicy evictionPolicy;

    public CacheNode(String serverId, int maxCapacity, EvictionPolicy evictionPolicy) {
        this.serverId = serverId;
        this.maxCapacity = maxCapacity;
        this.evictionPolicy = evictionPolicy;
    }

    public String fetch(String key) {
        if (storage.containsKey(key)) {
            evictionPolicy.keyAccessed(key);
            return storage.get(key);
        }
        return null;
    }

    public void store(String key, String value) {
        if (!storage.containsKey(key) && storage.size() >= maxCapacity) {
            String evictKey = evictionPolicy.getKeyToEvict();

            if (evictKey != null) {
                storage.remove(evictKey);
                evictionPolicy.removeKey(evictKey);
            }
        }
        storage.put(key, value);
        evictionPolicy.keyAccessed(key);
    }

    public String getServerId() {
        return serverId;
    }
}