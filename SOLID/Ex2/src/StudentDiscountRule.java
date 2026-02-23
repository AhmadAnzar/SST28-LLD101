public class StudentDiscountRule implements DiscountRule {

    public double discount(double subtotal, int lines) {
        if (subtotal >= 180.0) return 10.0;
        return 0.0;
    }
}