import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowAlgorithm implements RateLimiterAlgorithm {

    private final ConcurrentHashMap<String, Data> store = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Object> locks = new ConcurrentHashMap<>();

    @Override
    public boolean isAllowed(String clientKey, List<RateLimitRule> activeRules, int timestampSeconds) {
        synchronized (getLock(clientKey)) {
            for (RateLimitRule rule : activeRules) {
                Data Data = getData(clientKey, rule);
                int windowStart = (timestampSeconds / rule.getWindowSize()) * rule.getWindowSize();

                if (Data.windowStart != windowStart) {
                    Data.windowStart = windowStart;
                    Data.count = 0;
                }
                if (Data.count >= rule.getMaxRequests()) {
                    return false;
                }
            }
            for (RateLimitRule rule : activeRules) {
                Data Data = getData(clientKey, rule);
                Data.count++;
            }
            return true;
        }
    }

    private Object getLock(String clientKey) {
        return locks.computeIfAbsent(clientKey, k -> new Object());
    }

    private Data getData(String clientKey, RateLimitRule rule) {
        String ruleBucketKey = clientKey + ":" + rule.getName();
        return store.computeIfAbsent(ruleBucketKey, k -> new Data());
    }

    private static class Data {
        private int windowStart;
        private int count;
    }
}