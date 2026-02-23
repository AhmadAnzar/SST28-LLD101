public class CreditsRule implements EligibilityRule {

    private final int minCredits;

    public CreditsRule(int minCredits) {
        this.minCredits = minCredits;
    }

    @Override
    public boolean isEligible(StudentProfile s) {
        return s.earnedCredits >= minCredits;
    }

    @Override
    public String getReason() {
        return "credits below " + minCredits;
    }
}