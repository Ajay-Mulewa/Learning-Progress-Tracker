package tracker;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private Points points;
    private static boolean[] notified = {false, false, false, false};

    public Student(String firstName, String lastName, String email, Points points) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.points = points;
    }

    public void updatePoints(int javaPoints, int dsaPoints, int databasesPoints, int springPoints) {
        points.updatePoints(javaPoints, dsaPoints, databasesPoints, springPoints);
    }

    public static boolean[] getNotified() {
        return notified;
    }

    public static void setNotified(int index, boolean value) {
        notified[index] = value;
    }

    public int[] getCourses() {
        return points.getEnrolledCourse();
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getScore() {
        return points.getScore();
    }
    public String getEmail() {
        return email;
    }

    public Points getPoints() {
        return points;
    }
}
