import java.io.*;
import java.util.*;

public class DayTwo {

    public static int countSafeReports(List<List<Integer>> reports) {
        int safeCount = 0;
        for (List<Integer> report : reports) {
            if (isSafe(report)) {
                safeCount++;
            }
        }
        return safeCount;
    }

    public static int countSafeReportsWithDampener(List<List<Integer>> reports) {
        int safeCount = 0;
        for (List<Integer> report : reports) {
            if (isSafe(report) || canBeMadeSafe(report)) {
                safeCount++;
            }
        }
        return safeCount;
    }

    private static boolean isSafe(List<Integer> report) {
        for (int i = 0; i < report.size() - 1; i++) {
            int diff = Math.abs(report.get(i) - report.get(i + 1));
            if (diff < 1 || diff > 3) {
                return false;
            }
        }
        boolean increasing = true;
        boolean decreasing = true;
        for (int i = 0; i < report.size() - 1; i++) {
            if (report.get(i) >= report.get(i + 1)) {
                increasing = false;
            }
            if (report.get(i) <= report.get(i + 1)) {
                decreasing = false;
            }
        }
        return increasing || decreasing;
    }

    private static boolean canBeMadeSafe(List<Integer> report) {
        for (int i = 0; i < report.size(); i++) {
            List<Integer> modifiedReport = new ArrayList<>(report);
            modifiedReport.remove(i);
            if (isSafe(modifiedReport)) {
                return true;
            }
        }
        return false;
    }

    public static List<List<Integer>> readReportsFromFile(String filename) throws IOException {
        List<List<Integer>> reports = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Integer> report = new ArrayList<>();
                for (String num : line.trim().split("\\s+")) {
                    report.add(Integer.parseInt(num));
                }
                reports.add(report);
            }
        }

        return reports;
    }

    public static void main(String[] args) {
        try {
            String filename = "./src/input";
            List<List<Integer>> reports = readReportsFromFile(filename);
            int safeReportsCount = countSafeReports(reports);
            int safeReportsWithDampenerCount = countSafeReportsWithDampener(reports);

            System.out.println("Number of safe reports without Problem Dampener: " + safeReportsCount);
            System.out.println("Number of safe reports with Problem Dampener: " + safeReportsWithDampenerCount);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
