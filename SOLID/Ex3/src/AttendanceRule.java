public class AttendanceRule implements EligibilityRule {

    private final int minAttendance;

    public AttendanceRule(int minAttendance) {
        this.minAttendance = minAttendance;
    }

    @Override
    public boolean isEligible(StudentProfile s) {
        return s.attendancePct >= minAttendance;
    }

    @Override
    public String getReason() {
        return "attendance below " + minAttendance;
    }
}