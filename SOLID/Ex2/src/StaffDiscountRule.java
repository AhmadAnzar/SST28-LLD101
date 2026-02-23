public class StaffDiscountRule implements DiscountRule {

    public double discount(double subtotal, int lines) {
        if (lines >= 3) return 15.0;
        return 5.0;
    }
}