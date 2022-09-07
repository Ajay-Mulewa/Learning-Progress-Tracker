package tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentActivity {
    private static final String ADD_POINTS_PROMPT = "Enter an id and points to add or 'back' to return:";
    private static final String[] courses = {"Java", "DSA", "Databases", "Spring"};
    private static final int[] coursesActivity = new int[4];

    static void addPoints() {
        System.out.println(ADD_POINTS_PROMPT);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals(Operation.BACK.command)) {
                break;
            }
            if (!Validator.isValidPointsFormat(input)) {
                System.out.println("Incorrect points format");
                continue;
            }
            String[] temp = input.split(" ");
            String id = temp[0];
            if (DatabaseStudents.exists(id)) {
                adjustActivity(temp);
                DatabaseStudents.getStudent(id).updatePoints(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]),
                        Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
                System.out.println("Points updated");
            } else {
                System.out.println("No student is found for id=" + id);
            }
        }
    }

    static void adjustActivity(String[] studentPoints) {
        for (int i = 0; i < coursesActivity.length; i++) {
            if (Integer.parseInt(studentPoints[i + 1]) > 0) {
                coursesActivity[i]++;
            }
        }
    }

    static void getCourseActivity() {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int total : coursesActivity) {
            if (total > max) {
                max = total;
            }
            if (total < min) {
                min = total;
            }
        }
        List<String> highestActivity = new ArrayList<>();
        List<String> leastActivity = new ArrayList<>();
        for (int i = 0; i < coursesActivity.length; i++) {
            if (coursesActivity[i] == max) {
                highestActivity.add(courses[i]);
            }
            if (coursesActivity[i] == min) {
                leastActivity.add(courses[i]);
            }
        }
        for (String course : highestActivity) {
            leastActivity.remove(course);
        }
        System.out.println("Most popular: " + (!highestActivity.isEmpty() ?
                String.join(", ", highestActivity) : "n/a"));
        System.out.println("Least popular: " + (!leastActivity.isEmpty() ?
                String.join(", ", leastActivity) : "n/a"));
    }
}
