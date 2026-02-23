import java.util.*;

public class EligibilityEngine {

    private final List<EligibilityRule> rules;
    private final FakeEligibilityStore store;
    private final ReportPrinter printer;

    public EligibilityEngine(List<EligibilityRule> rules,
                             FakeEligibilityStore store,
                             ReportPrinter printer) {
        this.rules = rules;
        this.store = store;
        this.printer = printer;
    }

    public void runAndPrint(StudentProfile s) {

        EligibilityEngineResult r = evaluate(s);

        printer.print(s, r);
        store.save(s.rollNo, r.status);
    }

    private EligibilityEngineResult evaluate(StudentProfile s) {

        List<String> reasons = new ArrayList<>();
        String status = "ELIGIBLE";

        // Same behavior: stop at first failure
        for (EligibilityRule rule : rules) {

            if (!rule.isEligible(s)) {
                status = "NOT_ELIGIBLE";
                reasons.add(rule.getReason());
                break; // important: preserves old behavior
            }
        }

        return new EligibilityEngineResult(status, reasons);
    }
}
class EligibilityEngineResult {

    public final String status;
    public final List<String> reasons;

    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}