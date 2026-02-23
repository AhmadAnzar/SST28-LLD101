import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final Store store;
    private int invoiceSeq = 1000;
    private final InvoiceCalculator calculator = new InvoiceCalculator();

    public CafeteriaSystem(Store store) {
        this.store = store;
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    // Intentionally SRP-violating: menu mgmt + tax + discount + format +
    // persistence.
    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        TaxRule taxRule;
        DiscountRule discountRule;

        if ("student".equalsIgnoreCase(customerType)) {
            taxRule = new StudentTaxRule();
            discountRule = new StudentDiscountRule();
        } else if ("staff".equalsIgnoreCase(customerType)) {
            taxRule = new StaffTaxRule();
            discountRule = new StaffDiscountRule();
        } else {
            taxRule = new DefaultTaxRule();
            discountRule = new DefaultDiscountRule();
        }

        InvoiceResult res = calculator.calculate(
                taxRule,
                discountRule,
                lines,
                menu);

        String printable = InvoiceFormatter.format(
                invId,
                lines,
                menu,
                res);

        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
