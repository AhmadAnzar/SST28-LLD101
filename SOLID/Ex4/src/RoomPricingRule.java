import java.util.*;

public class RoomPricingRule implements PricingRule {

    private final Map<Integer, Money> prices = new HashMap<>();

    public RoomPricingRule() {
        prices.put(LegacyRoomTypes.SINGLE, new Money(14000));
        prices.put(LegacyRoomTypes.DOUBLE, new Money(15000));
        prices.put(LegacyRoomTypes.TRIPLE, new Money(12000));
        prices.put(LegacyRoomTypes.DELUXE, new Money(16000));
    }

    @Override
    public Money monthlyFee(BookingRequest req) {
        return prices.getOrDefault(req.roomType, new Money(0));
    }
}