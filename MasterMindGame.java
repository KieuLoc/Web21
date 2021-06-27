import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MasterMindGame {
    public static void main(String[] args) throws IOException {
        MasterMind mGame = new MasterMind();
        displayScreen();
        while (mGame.isProgress()){
            System.out.print("Enter Your Guess");
            try {
                mGame.evaluateResult(new Answers(acceptNumber()));
            } catch (IOException ex) {
                Logger.getLogger(MasterMind.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.print(mGame.getResult()+"\n");
        }
        if (mGame.isWon()){
            System.out.print("YOU WIN");
        }else{
            System.out.print("YOU LOST \n");
            System.out.print("CORRECT ANSWER IS "+ mGame.getHiddenNumber());
        }
    }
    public static void displayScreen(){
        System.out.println("Please Guess four Hidden Digits");
    }
    private static String acceptNumber() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }
}
