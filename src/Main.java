import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.HashMap;
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
        int slots = 4;
        int removedModel;

        ArrayDeque<Integer> bumpLine = new ArrayDeque<>(slots);

        HashMap<Character, Integer> showRoom = new HashMap<>();
        // data structure for showroom?

        showRoom.put('a', 0);
        showRoom.put('b', 0);
        showRoom.put('c', 0);
        showRoom.put('d', 0);

        Scanner requestReader = null;
        try {
            requestReader = new Scanner( new File("customerRequests.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (requestReader.hasNextInt()) {
            System.out.println("getting customer request...");

            int request = requestReader.nextInt();
            System.out.println("request: model " + request);

            // check showroom for request
            System.out.println("checking showroom for model " + request);

            if (showRoom.containsValue(request)) {
                // if in showroom, log 'Hit'
                System.out.println("model " + request + " is in the showroom. Yay! It's a HIT!");
            } else {
                // if not in showroom, log 'Fail'
                System.out.println("model " + request + " is NOT in the showroom. epic FAIL!");
                // who's next in line to get bumped
                if (bumpLine.size() == 4) {
                    System.out.println("pulling next model from FIFO queue...");
                    removedModel = bumpLine.remove();
                }
                if (!bumpLine.isEmpty()) {
                    System.out.println("bumped model " + removedModel + " from FIFO queue.");
                    System.out.println("getting showroom slot for model " + removedModel + ".");
                    // obtain showroom slot
                    System.out.println("replacing model " + removedModel + " with model " + request + ".");
                    // remove model from showroom.
                }
                // add request to queue, "pull car from back-lot" and add to showroom
                System.out.println("adding model " + request + " to showroom...");
                // add requested model to showroom here
                System.out.println("adding model " + request + " to back of FIFO queue...");
                bumpLine.add(request);
                System.out.println("bumpLine: " + bumpLine.toString());
                System.out.println("Showroom: " + showRoom.values());
            }
        }
    }
}