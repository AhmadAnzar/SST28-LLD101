import java.util.List;
import java.util.Map;

public class InvoiceCalculator {

    public InvoiceResult calculate(
            TaxRule taxRule,
            DiscountRule discountRule,
            List<OrderLine> lines,
            Map<String, MenuItem> menu) {

        double subtotal = 0.0;

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
        }

        double taxPct = taxRule.taxPercent();
        double tax = subtotal * (taxPct / 100.0);

        double discount =
                discountRule.discount(subtotal, lines.size());

        double total = subtotal + tax - discount;

        return new InvoiceResult(subtotal, taxPct, tax, discount, total);
    }
}