import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
     private final List<PricingRule> rules;

    public HostelFeeCalculator(FakeBookingRepo repo, List<PricingRule> rules) { 
        this.repo = repo;
        this.rules = rules;
     }

    // OCP violation: switch + add-on branching + printing + persistence.
    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)); // deterministic-ish
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        Money total = new Money(0);

        for (PricingRule r : rules) {
            total = total.plus(r.monthlyFee(req));
        }

        return total;
    }
}
