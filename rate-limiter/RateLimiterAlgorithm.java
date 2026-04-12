import java.util.List;

public interface RateLimiterAlgorithm {
    boolean isAllowed(String clientKey, List<RateLimitRule> activeRules, int timestampSeconds);
}