package automatedgrader.strategy;

// Class representing the result of an evaluation
public class EvaluationResult {
     private String testname;
     private String total;
     private String feedback;

     public EvaluationResult( String testname, String total, String feedback ){
          this.testname = testname;
          this.total = total;
          this.feedback = feedback;
          
    }
    
    public String getTestName() {
        return null;
    }

    public boolean isPassed() {
        return false;
    }

    public String getFeedback() {
        return null;
    }
    
    // Class implementation for representing the evaluation outcome
    // Different tests feedback
    // Calculating score based of evaluation results
}
