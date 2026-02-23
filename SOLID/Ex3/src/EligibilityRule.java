public interface EligibilityRule {
    boolean isEligible(StudentProfile s);
    String getReason();
}