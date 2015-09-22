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
<<<<<<< HEAD
=======
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

>>>>>>> master
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
  Episode tempEpisode;

  /**
  *
  */
  //public static void main(String[] args)
  public NewAgent()
  {
<<<<<<< HEAD
    //set up environment in order to
    //initialize alphabet array (inherited from StateMachineAgent)
    env = new StateMachineEnvironment();
    alphabet = env.getAlphabet();

    //make sure the agent's memory is all spic and span
    generateEpisodicMemory.clear();

    //call generateRandomAction///////////////////////
    generateEpisodicMemory =
=======
    //create a StateMachineAgent object, have it
    //roam around for a while, and then stash its episodic memory
    //away in genEpisodicMemory
    //StateMachineAgent gilligan = new StateMachineAgent();
    //gilligan.exploreEnvironment();
    //generateEpisodicMemory = gilligan.episodicMemory;
    generateRandomEpisodes(100);
>>>>>>> master
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
<<<<<<< HEAD
    //GENERATEEPISODICMEMORY.SIZE()-i-1
    for (int i=0; i<COMPARE_SIZE; i++){
      originalSequence[i] = (generateEpisodicMemory.get(generateEpisodicMemory.size()-i));
=======
    for (int k=0; k<COMPARE_SIZE; k++){
      originalSequence[i] = (generateEpisodicMemory.get(generateEpisodicMemory.size()-k));
>>>>>>> master

    for (int j=0; j<(COMPARE_SIZE); j++){
      foundSequence[j] = (generateEpisodicMemory.get(lastGoalIndex-j));
    }


    try {
        FileWriter csv = new FileWriter(OUTPUT_FILE2);
        for(int q=0; q<8; q++){
          csv.append(originalSequence[q].command);
        }
        for(int p=0; p<8; p++){
          csv.append(foundSequence[p].command);
        }

        csv.close();
      }
      catch (IOException e) {
          System.out.println("tryAllCombos: Could not create file, what a noob...");
          System.exit(-1);
      }
    //test to see if works


  }
}
return qualityScore;
}

  protected char generateRandomAction(){
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

  protected ArrayList<Episode> generateRandomEpisodes(int length){
    //generate random episodes based on chosen length
    for(int i=0; i<length; i++){
      //create a random episode
      Episode tempEpisode = null;
      tempEpisode.command = generateRandomAction();
      tempEpisode.sensorValue = randomAtGoal(50);
      generateEpisodicMemory.add(tempEpisode);
    }
    //PrintWriter writer = new PrintWriter ("outputfile2.txt");
    //PrintWriter out = new PrintWriter(new FileWriter("users\\sarameisburger\\Desktop\\outputfile2.txt"));
    save(generateEpisodicMemory);
    return generateEpisodicMemory;

  }

  public void save(ArrayList<Episode> output) {
  try {
    PrintWriter pw = new PrintWriter(new FileOutputStream(OUTPUT_FILE2));
  for (Episode episode : output)
      pw.println(episode.command);
  pw.close();
}
  catch (IOException e) {
    System.out.println("tryAllCombos: Could not create file, what a noob...");
    System.exit(-1);
}
}

  //randomly gives an "at goal?" value of 0 or 1
  public int randomAtGoal(int probability){
    int atGoal = (int)(Math.random()*probability);
    if(atGoal == 0){
      return 1; //say it reeached the goal
    }
    else {
      return 0; //otherwise it did not reach the goal
    }
  }

  public char getChar(Episode epi){
    return epi.command;
  }
}
