package tracker;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Application {
    private static final Random random = new Random(1000L);
    private static final String ADD_STUDENT_PROMPT = "Enter student credentials or 'back' to return:";
    private static final String ADD_POINTS_PROMPT = "Enter an id and points to add or 'back' to return:";
    private static final String FAREWELL = "Bye!";
    private static final String INCORRECT_CREDENTIALS_PROMPT = "Incorrect credentials.";
    private static final String STARTUP = "Learning Progress Tracker";
    private static final String STUDENT_ADDED_PROMPT = "The student has been added.";

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(STARTUP);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals(Operation.EXIT.command)) {
                System.out.println(FAREWELL);
                break;
            } else if (input.equals(Operation.BACK.command)) {
                System.out.println("Enter 'exit' to exit the program");
            } else if (input.equals(Operation.ADD_STUDENT.command)) {
                addStudents();
            }  else if (input.equals(Operation.LIST.command)) {
                DatabaseStudents.listStudentsIds();
            } else if (input.equals(Operation.ADD_POINTS.command)) {
                addPoints();
            } else if (input.equals(Operation.FIND.command)) {
                findStudent();
            } else if (input.equals(Operation.STATISTICS.command)) {
                statistics();
            } else if (input.equals(Operation.NOTIFY.command)) {
                notifyStudents();
            } else if (input.isBlank()) {
                System.out.println("No input");
            } else {
                System.out.println("Unknown command!");
            }
        }
    }

    void addStudents() {
        Scanner scanner = new Scanner(System.in);
        int addedStudents = 0;
        System.out.println(ADD_STUDENT_PROMPT);
        while(scanner.hasNextLine()) {
            String cred = scanner.nextLine();
            if (cred.equals(Operation.BACK.command)) {
                break;
            }
            String[] temp = cred.split(" ");
            if (temp.length < 3) {
                System.out.println(INCORRECT_CREDENTIALS_PROMPT);
                continue;
            }
            String[] credArr = getCredentials(cred);
            if (Validator.isValidAllCredentials(credArr[0], credArr[1], credArr[2])
                    && !DatabaseStudents.emailExistInDB(credArr[2])) {
                DatabaseStudents.addStudent(String.valueOf(Math.abs(random.nextInt()) % 999999),
                        new Student(credArr[0], credArr[1], credArr[2], new Points()));
                addedStudents++;
                System.out.println(STUDENT_ADDED_PROMPT);
            } else {
                Validator.printErrorMessage(credArr[0], credArr[1], credArr[2]);
            }
        }
        System.out.println("Total " + addedStudents + " students have been added.");
    }

    public String[] getCredentials(String prompt) {
        String[] credentialsArr = prompt.split("\\s+");
        StringBuilder lstName = new StringBuilder();
        for (int i = 1; i < credentialsArr.length - 1; i++) {
            lstName.append(credentialsArr[i]).append(" ");
        }
        return new String[]{credentialsArr[0], lstName.toString(), credentialsArr[credentialsArr.length - 1]};
    }

    void addPoints() {
        StudentActivity.addPoints();
    }

    void findStudent() {
        System.out.println("Enter an id or 'back' to return");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String id = scanner.nextLine();
            if (id.equals(Operation.BACK.command)) {
                break;
            }
            if (DatabaseStudents.exists(id)) {
                System.out.println(id + " " + DatabaseStudents.getStudent(id).getScore());
            } else {
                System.out.println("No student is found for id=" + id);
            }
        }
    }

    void statistics() {
        System.out.println("Type the name of a course to see details or 'back' to quit");
        getStatistics();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String course = scanner.nextLine();
            if (course.equals(Operation.BACK.command)) {
                break;
            }
            if (course.equalsIgnoreCase("java")
                || course.equalsIgnoreCase("dsa")
                || course.equalsIgnoreCase("databases")
                || course.equalsIgnoreCase("spring")) {
                DatabaseStudents.getCourseDetails(course);
            } else {
                System.out.println("Unknown course");
            }
        }
    }

    void getStatistics() {
        if (DatabaseStudents.getStudents().size() > 0) {
            DatabaseStudents.getMostAndLeastPopularCourse();
            StudentActivity.getCourseActivity();
            DatabaseStudents.getEasiestAndHardestCourses();
        } else {
            System.out.println("Most popular: n/a");
            System.out.println("Least popular: n/a");
            System.out.println("Highest activity: n/a");
            System.out.println("Lowest activity: n/a");
            System.out.println("Easiest course: n/a");
            System.out.println("Hardest course: n/a");
        }
    }

    void notifyStudents() {
        DatabaseStudents.notifyStudents();
    }
}
