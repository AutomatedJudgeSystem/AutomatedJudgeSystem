package automatedgrader.strategy;

// Class representing the result of an evaluation
public class EvaluationResult {
     private String testname;
     private int total;
     private String feedback;
     private boolean status;

     public EvaluationResult( String testname, int total, String feedback, boolean status){
          this.testname = testname;
          this.total = total;
          this.feedback = feedback;   
          this.status = status;
    }
    
    public String getTestName() {
        return testname;
    }

    public int getTotal() {
        return total;
    }

    public boolean getStatus() {
        return status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public String isPassed(EvaluationResult result) {
        if(result.getStatus()){
            return "Test passed!";
        }
        return "Test failed!";
    }

    

}
