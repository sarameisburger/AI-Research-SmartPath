
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;

public class TestNewAgent extends NewAgent{

  protected ArrayList<Episode> original;
  protected ArrayList<Episode> found;
  protected static Episode[] originalSequence;
  protected static Episode[] foundSequence;
  protected static int COMPARE_SIZE = 8;
  protected NewAgent ourAgent;
  //protected static int count;
  //protected static double calc;

  public TestNewAgent(){

    original = generateRandomEpisodes(COMPARE_SIZE);
    found = generateRandomEpisodes(COMPARE_SIZE);
    originalSequence = original.toArray(new Episode[COMPARE_SIZE]);
    foundSequence = found.toArray(new Episode[COMPARE_SIZE]);
    ourAgent = new NewAgent();


  }
  /**
	 * main
     *
     * helper methods (above) have been defined to do various things here.
     * Modify this method to call the one(s) you want.
	 */
	public static void main(String [ ] args) {
    TestNewAgent testNewAgentObj = new TestNewAgent();

    char[] originalChars = new char[COMPARE_SIZE];
    char[] foundChars = new char[COMPARE_SIZE];


    for (int p = 0; p<COMPARE_SIZE; p++)
    {
      originalChars[p] = originalSequence[p].command;
      foundChars[p] = foundSequence[p].command;
    }

    System.out.println(Arrays.toString(originalChars));
    System.out.println(Arrays.toString(foundChars));



    testNewAgentObj.getCountingScoreTest(originalSequence, foundSequence);
    testNewAgentObj.calcPWMScoreTest(originalSequence, foundSequence);

    //System.out.println(count + " " + calc);

	}

  private void getCountingScoreTest(Episode[] original, Episode[] found){
    double testValueCS = getCountingScore(original, found);
    System.out.println("the counting score is: " + testValueCS);

  }

  private void calcPWMScoreTest(Episode[] originalEpisodes, Episode[] foundEpisodes)
  {
    double testValuePWM = calcPWMScore(originalEpisodes, foundEpisodes);
    System.out.println("the pwm score is: " + testValuePWM);
  }
}
