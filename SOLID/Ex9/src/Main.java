public class Main {
    public static void main(String[] args) {
        System.out.println("=== Evaluation Pipeline ===");
        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");
        
        PlagiarismService plagiarismService = new PlagiarismChecker();
        GradingService gradingService = new CodeGrader();
        ReportService reportService = new ReportWriter();
        Rubric rubric = new Rubric();
        
        EvaluationPipeline pipeline = new EvaluationPipeline(
            plagiarismService, 
            gradingService, 
            reportService, 
            rubric
        );
        
        pipeline.evaluate(sub);
    }
}