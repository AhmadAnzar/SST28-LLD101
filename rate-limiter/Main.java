import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<RateLimitRule> limitRules = new ArrayList<>();
        limitRules.add(new RateLimitRule("5 per minute", 5, 60));
        limitRules.add(new RateLimitRule("100 per hour", 100, 3600));

        runExample("Fixed Window Counter", new FixedWindowAlgorithm(), limitRules);
        System.out.println();
        runExample("Sliding Window Counter", new SlidingWindowAlgorithm(), limitRules);
    }

    private static void runExample(String title, RateLimiterAlgorithm algorithm, List<RateLimitRule> rules) {
        System.out.println("=== " + title + " ===");

        RateLimiter rateLimiter = new RateLimiter(algorithm, rules);
        InternalService service = new InternalService(rateLimiter);

        boolean[] externalCallFlags = {false, true, true, true, true, true, true};

        int timestampSeconds = 0;
        for (int i = 0; i < externalCallFlags.length; i++) {
            service.handleRequest("T1", externalCallFlags[i], "request-" + (i + 1), timestampSeconds);
            timestampSeconds += 9;
        }
    }
}