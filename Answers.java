public class Answers {
    private String Answer;
    private String result;
    public Answers(String ans){
        this.Answer = new String(ans);
        result = new String();
    }
    public String getResult(){
        return this.result;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
