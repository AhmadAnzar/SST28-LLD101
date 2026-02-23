import java.util.*;

public class AddOnPricingRule implements PricingRule {

    private final Map<AddOn, Money> prices = new HashMap<>();

    public AddOnPricingRule() {
        prices.put(AddOn.MESS, new Money(1000));
        prices.put(AddOn.LAUNDRY, new Money(500));
        prices.put(AddOn.GYM, new Money(300));
    }

    @Override
    public Money monthlyFee(BookingRequest req) {

        Money total = new Money(0);

        for (AddOn a : req.addOns) {
            Money p = prices.get(a);
            if (p != null) {
                total = total.plus(p);
            }
        }

        return total;
    }
}