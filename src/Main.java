import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * INSTRUCTIONS FOR ASSIGNMENT 4B
 * The sequence of models requested by a customers in the average day should be read in from a file.
 * The models in inventory slots should be represented  in a Queue.
 * Run a simulation for each of the replacement algorithms.
 * Each time a model is read in from the file.
 * Output the model requested
 * Output whether the model request is a  hit (is available in the queue) or fail (had to be brought into the queue).
 * Output the letter representation of the inventory slot.
 * Keep track of the number of fails.  After all customer requests are read in from the file, output the number of fails
 * and the amount of extra time in hours it costs the  salesmen.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner requestReader = null;
        try {
            requestReader = new Scanner( new File("customerRequests.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int request = requestReader.nextInt();
        System.out.println(request);
    }
}