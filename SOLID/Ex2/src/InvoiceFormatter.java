import java.util.List;
import java.util.Map;

public class InvoiceFormatter {

    public static String format(
            String invoiceId,
            List<OrderLine> lines,
            Map<String, MenuItem> menu,
            InvoiceResult res) {

        StringBuilder out = new StringBuilder();

        out.append("Invoice# ").append(invoiceId).append("\n");

        // Line items
        for (OrderLine l : lines) {

            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;

            out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        // Totals
        out.append(String.format("Subtotal: %.2f\n", res.subtotal));

        out.append(String.format("Tax(%.0f%%): %.2f\n", res.taxPct, res.tax));

        out.append(String.format("Discount: -%.2f\n", res.discount));
        out.append(String.format("TOTAL: %.2f\n", res.total));

        return out.toString();
    }
}