import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowAlgorithm implements RateLimiterAlgorithm {

    private final ConcurrentHashMap<String, SlidingData> store = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Object> locks = new ConcurrentHashMap<>();

    @Override
    public boolean isAllowed(String clientKey, List<RateLimitRule> activeRules, int timestampSeconds) {
        synchronized (getLock(clientKey)) {
            for (RateLimitRule rule : activeRules) {
                SlidingData slidingData = getData(clientKey, rule);
                int windowSize = rule.getWindowSize();
                int windowStart = (timestampSeconds / windowSize) * windowSize;

                if (slidingData.currentWindowStart != windowStart) {
                    if (slidingData.currentWindowStart + windowSize == windowStart) {
                        slidingData.previousCount = slidingData.currentCount;
                    } else {
                        slidingData.previousCount = 0;
                    }
                    slidingData.currentWindowStart = windowStart;
                    slidingData.currentCount = 0;
                }

                int timePassed = timestampSeconds - windowStart;
                double previousWeight = (double) (windowSize - timePassed) / windowSize;
                double estimatedCount = slidingData.currentCount + (slidingData.previousCount * previousWeight);

                if (estimatedCount + 1 > rule.getMaxRequests()) {
                    return false;
                }
            }

            for (RateLimitRule rule : activeRules) {
                SlidingData slidingData = getData(clientKey, rule);
                slidingData.currentCount++;
            }

            return true;
        }
    }

    private Object getLock(String clientKey) {
        return locks.computeIfAbsent(clientKey, k -> new Object());
    }

    private SlidingData getData(String clientKey, RateLimitRule rule) {
        String ruleBucketKey = clientKey + ":" + rule.getName();
        return store.computeIfAbsent(ruleBucketKey, k -> new SlidingData());
    }

    private static class SlidingData {
        private int currentWindowStart;
        private int previousCount;
        private int currentCount;
    }
}