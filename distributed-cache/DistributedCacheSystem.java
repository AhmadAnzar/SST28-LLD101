import java.util.ArrayList;
import java.util.List;

public class DistributedCacheSystem {

    private final List<CacheNode> servers = new ArrayList<>();
    private final DistributionStrategy distributionStrategy;
    private final Database database;

    public DistributedCacheSystem( int numberOfServers,int serverCapacity,DistributionStrategy distributionStrategy,Database database) {
        this.distributionStrategy = distributionStrategy;
        this.database = database;

        for (int i = 0; i < numberOfServers; i++) {
            servers.add(new CacheNode("server-" + i,serverCapacity,new LRUEvictionPolicy()));
        }
    }

    public String get(String key) {
        CacheNode server = locateServer(key);
        String value = server.fetch(key);

        if (value != null) {
            return value;
        }

        value = database.get(key);

        if (value != null) {
            server.store(key, value);
        }
        return value;
    }

    public void put(String key, String value) {
        CacheNode server = locateServer(key);
        server.store(key, value);
        database.put(key, value);
    }

    public String getServerIdForKey(String key) {
        return locateServer(key).getServerId();
    }

    private CacheNode locateServer(String key) {
        int index = distributionStrategy.selectNode(key,servers.size());
        return servers.get(index);
    }
}