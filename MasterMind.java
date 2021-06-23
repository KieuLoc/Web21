
import java.util.*;
public class MasterMind {
    private static int defaultmaxAllowTime = 8;
    private static int QUIZZ_LENGTH = 4;
    private static char allCorrect='*';
    private static char numberCorrect='!';
    public enum State {PROGRESS, LOST, WIN};

    private String hiddenNumber;
    private State GameState;
    private int maxAllowTime;
    private List<Answers> atemptList;
    private String correctAnswer = new String();

    public MasterMind(){
        GameState = State.PROGRESS;
        hiddenNumber = generateHiddenNumber();
        maxAllowTime = defaultmaxAllowTime;
        for (int i =1; i< QUIZZ_LENGTH; i++){
            correctAnswer = correctAnswer + allCorrect;
        }
        atemptList = new ArrayList<Answers>();
    }
    public String getHiddenNumber(){
        return hiddenNumber;
    }
    public boolean isProgress(){
        return GameState == State.PROGRESS;
    }
    public boolean isOver(){
        return GameState == State.LOST;
    }
    public boolean isWon(){
        return GameState == State.WIN;
    }
    private String generateHiddenNumber(){
        Random rand = new Random();
        return String.format("%04d", rand.nextInt(10000));
    }
    public String getResult(){
        // Dien code
        return ans.getResult();
    }
    public void evaluateResult(Answers Ans){
        if(// Dien code){
            Ans.setResult(matchResult(Ans.getAnswer()));
            this.atemptList.add(Ans);
        }
        changeGameStatus(Ans);
    }
    private void changeGameStatus(Answers paraAttmpt){
        if(atemptList.size() < maxAllowTime){
            if ( Dien code){
                GameState = State.WIN;
            }else
                GameState = State.LOST;
        }
    }

    public String matchResult(String inputNumber){
        char[] inChar;
        char[] hidChar;
        String rtnValue = new String();
        inChar = inputNumber.toCharArray();
        hidChar = hiddenNumber.toCharArray();
        for(int i = 0; i<hiddenNumber.length(); i++){
            if(inChar[i] == hidChar[i]){
                inChar[i] = '#';
                rtnValue = rtnValue + this.allCorrect;
            }else
                for(int j=0; j<hiddenNumber.length(); j++){
                    if(){
                        inChar[i] = '#';
                        rtnValue = rtnValue + this.numberCorrect;
                        break;
                    }
                }
        }

        return rtnValue;
    }
}
