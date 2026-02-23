public interface PricingRule {
    Money monthlyFee(BookingRequest req);
}