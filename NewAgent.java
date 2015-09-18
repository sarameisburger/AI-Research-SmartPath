import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Collections;
/**
 * class NewAgent
 *
 * This is a "trial" agent that is being used to test a new algorithm for
 * finding the shortest path to the goal. This algorithm looks at
 * sequences of length 8 in episodic memory and combines the scores from
 * the positional weight matrix, a constituency/substring match algorithm, and the
 * number of steps to the goal to find the best possible
 * next move.
 *
 * @author: Sara Meisburger and Christine Chen
 *
 */
public class NewAgent extends StateMachineAgent
{
  //VARIABLES////////////////////////////

  //episodic memory generated
  //for this little NewAgent to use
  protected ArrayList<Episode> generateEpisodicMemory;

  //chance that a duplicate cmd is allowed if a random action is necessary
  double DUPLICATE_FORGIVENESS = .25; //25% chance a duplicate is permitted
  int COMPARE_SIZE = 8;
  public static final String OUTPUT_FILE2 = "sequences.csv";

  /**
  *
  */
  //public static void main(String[] args)
  public NewAgent()
  {
    //set up environment in order to
    //initialize alphabet array (inherited from StateMachineAgent)
    env = new StateMachineEnvironment();
    alphabet = env.getAlphabet();

    //make sure the agent's memory is all spic and span
    generateEpisodicMemory.clear();

    //call generateRandomAction///////////////////////
    generateEpisodicMemory =
  }

  protected int generateQualityScore(){

    Episode[] originalSequence = new Episode[COMPARE_SIZE];
    Episode[] foundSequence = new Episode[COMPARE_SIZE];
    int lastGoalIndex = findLastGoal(episodicMemory.size());
    int qualityScore = 0;//var to be returned

    if (lastGoalIndex == -1) {
        //since qualityScore has been init to 0, the ending score will be poor
        return qualityScore;
    }


    //If we've just reached the goal in the last 8 characters, then generate random steps
    //until we have a long enough original sequence
    for(int i=0; i< COMPARE_SIZE; i++){
      if (lastGoalIndex == episodicMemory.size() - i){
        generateRandomAction();
        generateQualityScore();
    }


    //fill the two arrays we will be comparing with 8 episodes
    //GENERATEEPISODICMEMORY.SIZE()-i-1
    for (int i=0; i<COMPARE_SIZE; i++){
      originalSequence[i] = (generateEpisodicMemory.get(generateEpisodicMemory.size()-i));

    for (int j=0; j<(COMPARE_SIZE); j++){
      foundSequence[j] = (generateEpisodicMemory.get(lastGoalIndex-j));
    }


    try {
        FileWriter csv = new FileWriter(OUTPUT_FILE2);
        for(int i=0; i<8; i++){
          csv.append(originalSequence[i].command);
        }
        for(int i=0; i<8; i++){
          csv.append(foundSequence[i].command);
        }

        csv.close();
      }
      catch (IOException e) {
          System.out.println("tryAllCombos: Could not create file, what a noob...");
          System.exit(-1);
      }
    //test to see if works


    return qualityScore;

  }
  public char generateRandomAction() {
        //decide if a dup command is acceptable
        double chanceForDup = Math.random();
        boolean dupPermitted = false;
        if (chanceForDup < DUPLICATE_FORGIVENESS) {
            dupPermitted = true;
        }

        //keep generating random moves till it is different from last or dups are allowed
        char possibleCmd;
        Episode lastEpisode = generateEpisodicMemory.get(generateEpisodicMemory.size() - 1);
        char lastCommand = lastEpisode.command;

        do {
            possibleCmd = alphabet[random.nextInt(alphabet.length)];
            if (dupPermitted)//if they are allowed we don't care to check for dup
                break;
        } while (possibleCmd == lastCommand); //same cmd, redo loop

		return possibleCmd;
	}


}
