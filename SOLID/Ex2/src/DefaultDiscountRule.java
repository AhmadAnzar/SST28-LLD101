public class DefaultDiscountRule implements DiscountRule {

    public double discount(double subtotal, int lines) {
        return 0.0;
    }
}