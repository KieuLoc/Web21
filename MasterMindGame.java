import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MasterMindGame {
    public static void main(String[] args) throws IOException {
        MasterMind mGame = new MasterMind();
        displayScreen();
        while (mGame.isProgress()){
            System.out.print("Enter Your Guess");
            // Dien code
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
