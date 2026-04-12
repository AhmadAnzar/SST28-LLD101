public class InternalService {

    private final RateLimiter rateLimiter;
    public InternalService(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }
    public void handleRequest(String Id, boolean externalCallNeeded, String payload, int timestampSeconds) {
        System.out.println("Processing request for tenant: " + Id);
        if (!externalCallNeeded) {
            System.out.println("External call not needed. No rate limit check.");
            return;
        }
        String Key = "tenant:" + Id;
        boolean requestAllowed = rateLimiter.isAllowed(Key, timestampSeconds);
        if (!requestAllowed) {
            System.out.println("Request denied by rate limiter.");
            return;
        }

        System.out.println("Request allowed.");
        callExternalResource(payload);
    }
    private void callExternalResource(String payload) {
        System.out.println("External resource called with payload: " + payload);
    }
}