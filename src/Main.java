import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
        int removedModel = 0;
        int hitCount = 0;
        int failCount = 0;
        int requestNo = 0;
        //empty slot for tracking showroom slots
        Character emptySlot = null;
        // hit log to track hits and fails
        ArrayList<Character> hitLog = new ArrayList<>();
        ArrayDeque<Integer> bumpLine = new ArrayDeque<>(slots);
        Map<Character, Integer> showRoom = new LinkedHashMap<>();
        // data structure for showroom?

        showRoom.put('D', 0);
        showRoom.put('C', 0);
        showRoom.put('B', 0);
        showRoom.put('A', 0);

        Scanner requestReader = null;
        try {
            requestReader = new Scanner( new File("customerRequests.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (requestReader.hasNextInt()) {
            System.out.println();
            System.out.println();
            ++requestNo;
            System.out.printf("getting customer request #%d...\n", requestNo);

            int request = requestReader.nextInt();
            System.out.println("request: model " + request);

            // check showroom for request
            System.out.println("checking showroom for model " + request);

            if (showRoom.containsValue(request)) {
                // if in showroom, log 'Hit'
                System.out.println("model " + request + " is in the showroom. Yay! It's a HIT!");
                hitLog.add('H');
                hitCount++;
            } else {
                // if not in showroom, log 'Fail'
                System.out.println("model " + request + " is NOT in the showroom. epic FAIL!");
                hitLog.add('F');
                failCount++;
                // if bumpLine is full, see who's next in line to get bumped
                if (bumpLine.size() == 4) {
                    System.out.println("pulling next model from FIFO queue...");
                    removedModel = bumpLine.remove();
                    System.out.println("bumped model " + removedModel + " from FIFO queue.");
                    System.out.println("getting showroom slot for model " + removedModel + ".");
                    // obtain showroom slot
                    for (Map.Entry<Character, Integer> entry : showRoom.entrySet()) {
                        if (entry.getValue().equals(removedModel)) {
                            emptySlot = entry.getKey();
                        }
                    }
                    System.out.println("replacing model " + removedModel + " with model " + request + ".");
                    showRoom.replace(emptySlot, request);
                } else {
                    // if bumpLine is not full, see which showroom slot is available (which has value of zero)
                    for (Map.Entry<Character, Integer> entry : showRoom.entrySet()) {
                        if (entry.getValue().equals(0)) {
                            emptySlot = entry.getKey();
                        }
                    }
                }
                // add request to queue, "pull car from back-lot" and add to showroom
                System.out.println("adding model " + request + " to showroom slot " + emptySlot);
                // add requested model to showroom here
                showRoom.replace(emptySlot, request);
                System.out.println("adding model " + request + " to back of FIFO queue...");
                bumpLine.add(request);
            }
            System.out.println("bumpLine: " + bumpLine.toString());
            System.out.println("Showroom: " + showRoom.entrySet());
            System.out.println("Hit Log: " + hitLog);
            System.out.printf("hit count: %d\n", hitCount);
            System.out.printf("fail count: %d\n", failCount);
        }
    }
}