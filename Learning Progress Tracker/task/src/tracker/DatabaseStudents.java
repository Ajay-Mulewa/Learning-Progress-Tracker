package tracker;

import java.util.*;

public class DatabaseStudents {
    private static Map<String, Student> students = new LinkedHashMap<>();

    public static Map<String, Student> getStudents() {
        return students;
    }

    static void addStudent(String id, Student student) {
        students.put(id, student);
    }

    static boolean exists(String id) {
        return students.containsKey(id);
    }

    static Student getStudent(String id) {
        return students.get(id);
    }

    static void listStudentsIds() {
        if (students.isEmpty()) {
            System.out.println("No students found");
        } else {
            System.out.println("Students:");
            for (Map.Entry<String, Student> entry : students.entrySet()) {
                System.out.println(entry.getKey());
            }
        }
    }

    static boolean emailExistInDB(String email) {
        for (Student student : students.values()) {
            if (student.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    static void getMostAndLeastPopularCourse() {
        String[] courses = {"Java", "DSA", "Databases", "Spring"};
        int[] enrolledStudents = new int[4];
        for (Student student : students.values()) {
            int[] enrolledCourses = student.getPoints().getEnrolledCourse();
            for (int i = 0; i < enrolledCourses.length; i++) {
                enrolledStudents[i] += enrolledCourses[i];
            }
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int total : enrolledStudents) {
            if (total > max) {
                max = total;
            }
            if (total < min) {
                min = total;
            }
        }
        List<String> mostPopularCourses = new ArrayList<>();
        List<String> leastPopularCourses = new ArrayList<>();
        for (int i = 0; i < enrolledStudents.length; i++) {
            if (enrolledStudents[i] == max) {
                mostPopularCourses.add(courses[i]);
            }
            if (enrolledStudents[i] == min) {
                leastPopularCourses.add(courses[i]);
            }
        }
        for (String course : mostPopularCourses) {
            leastPopularCourses.remove(course);
        }
        System.out.println("Most popular: " + (!mostPopularCourses.isEmpty() ?
                            String.join(", ", mostPopularCourses) : "n/a"));
        System.out.println("Least popular: " + (!leastPopularCourses.isEmpty() ?
                            String.join(", ", leastPopularCourses) : "n/a"));
    }

    static void getEasiestAndHardestCourses() {
        String[] courses = {"Java", "DSA", "Databases", "Spring"};
        int[] points = new int[4];
        int[] numAssignments = new int[4];
        for (Student student : students.values()) {
            int[] studentsPoints = student.getPoints().getTotalPoints();
            for (int i = 0; i < studentsPoints.length; i++) {
                if (studentsPoints[i] > 0) {
                    points[i] += studentsPoints[i];
                    numAssignments[i]++;
                }
            }
        }
        double[] averageGradePerAssignment = new double[4];
        for (int i = 0; i < averageGradePerAssignment.length; i++) {
            averageGradePerAssignment[i] = (double) points[i] / numAssignments[i];
        }
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        for (double average : averageGradePerAssignment) {
            if (average > max) {
                max = average;
            }
            if (average < min) {
                min = average;
            }
        }
        List<String> easiestCourses = new ArrayList<>();
        List<String> hardestCourses = new ArrayList<>();
        for (int i = 0; i < averageGradePerAssignment.length; i++) {
            if (averageGradePerAssignment[i] == max) {
                easiestCourses.add(courses[i]);
            }
            if (averageGradePerAssignment[i] == min) {
                hardestCourses.add(courses[i]);
            }
        }
        System.out.println("Easiest course: " + String.join(", ", easiestCourses));
        System.out.println("Hardest course: " + String.join(", ", hardestCourses));
    }

    static void getCourseDetails(String course) {
        List<String> topStudents = new ArrayList<>();
        String id = "";
        int totalPoints = 0;
        double grade = 0;
        for (Map.Entry<String, Student> entry : students.entrySet()) {
            if (course.strip().equalsIgnoreCase("java")) {
                id = entry.getKey();
                totalPoints = entry.getValue().getPoints().getJavaPoints();
                grade = totalPoints / Points.javaPassingScore * 100;
            } else if ("dsa".equalsIgnoreCase(course)) {
                id = entry.getKey();
                totalPoints = entry.getValue().getPoints().getDsaPoints();
                grade = totalPoints / Points.dsaPassingScore * 100;
            } else if ("databases".equalsIgnoreCase(course)) {
                id = entry.getKey();
                totalPoints = entry.getValue().getPoints().getDatabasesPoints();
                grade = totalPoints / Points.databasesPassingScore * 100;
            } else if ("spring".equalsIgnoreCase(course)) {
                id = entry.getKey();
                totalPoints = entry.getValue().getPoints().getSpringPoints();
                grade = totalPoints / Points.springPassingScore * 100;
            }
            topStudents.add(id + " " + totalPoints + " " + grade);
        }
        System.out.println(course);
        System.out.println("id     points  completed");
        topStudents.stream()
                .sorted((s1, s2) -> {
                    String[] s1Arr = s1.split(" ");
                    String[] s2Arr = s2.split(" ");
                    int s1Points = Integer.parseInt(s1Arr[1]);
                    int s2Points = Integer.parseInt(s2Arr[1]);
                    if (s1Points == s2Points) {
                        return s1Arr[0].compareTo(s2Arr[0]);
                    }
                    return s2Points - s1Points;
                })
                .filter(s -> Integer.parseInt(s.split(" ")[1]) > 0)
                .forEach(s -> System.out.printf("%s %s      %.1f%%\n",
                        s.split(" ")[0], s.split(" ")[1],
                        Double.parseDouble(s.split(" ")[2])));
    }

    static void notifyStudents() {
        int notified = 0;
        for (Student student : students.values()) {
            boolean notifiedStudent = false;
            for (int i = 0; i < 4; i++) {
                if (student.getPoints().getTotalPoints()[i] == Points.getPassingScore()[i] &&
                    !student.getNotified()[i]) {
                    System.out.printf("To: %s\nRe: Your Learning Progress\nHello, %s! You have accomplished our %s course!\n",
                            student.getEmail().strip(), student.getName().strip(), Points.getCourses()[i].strip());
                    student.setNotified(i, true);
                    notifiedStudent = true;
                }
            }
            if (notifiedStudent) {
                notified++;
            }
        }
        System.out.println("Total " + notified + " students have been notified.");
    }
}
