import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Placement Eligibility ===");

        StudentProfile s =
            new StudentProfile("23BCS1001", "Ayaan",
                               8.10, 72, 18,
                               LegacyFlags.NONE);

        RuleInput config = new RuleInput();

        List<EligibilityRule> rules = List.of(
            new DisciplinaryRule(),
            new CgrRule(config.minCgr),
            new AttendanceRule(config.minAttendance),
            new CreditsRule(config.minCredits)
        );

        EligibilityEngine engine =
            new EligibilityEngine(
                rules,
                new FakeEligibilityStore(),
                new ReportPrinter()
            );

        engine.runAndPrint(s);
    }
}