import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class AdventOfCodeDayOne {

    public static int taskOneTotalDistance(List<Integer> leftList, List<Integer> rightList) {
        Collections.sort(leftList);
        Collections.sort(rightList);
        int totalDistance = 0;
        for (int i = 0; i < leftList.size(); i++) {
            totalDistance += Math.abs(leftList.get(i) - rightList.get(i));
        }
        return totalDistance;
    }

    public static int taskTwoSimilarityScore(List<Integer> leftList, List<Integer> rightList) {
        Map<Integer, Integer> rightCounts = new HashMap<>();
        for (int num : rightList) {
            rightCounts.put(num, rightCounts.getOrDefault(num, 0) + 1);
        }
        int similarityScore = 0;
        for (int num : leftList) {
            similarityScore += num * rightCounts.getOrDefault(num, 0);
        }
        return similarityScore;
    }

    public static void main(String[] args) {
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        AdventOfCodeDayOne adventOfCodeDayOne = new AdventOfCodeDayOne();
        adventOfCodeDayOne.readIn("./src/inputleft", leftList);
        adventOfCodeDayOne.readIn("./src/inputright", rightList);

        int totalDistance = taskOneTotalDistance(leftList, rightList);
        int similarityScore = taskTwoSimilarityScore(leftList, rightList);

        System.out.println("Total Distance: " + totalDistance);
        System.out.println("Similarity Score: " + similarityScore);
    }

    public void readIn(String filePath, List<Integer> list){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String element;
            while ((element = br.readLine()) != null) {
                int number = Integer.parseInt(element);
                list.add(number);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
