package tracker;

public class Points {
    public static final double javaPassingScore = 600;
    public static final double dsaPassingScore = 400;
    public static final double databasesPassingScore = 480;
    public static final double springPassingScore = 550;
    private int javaPoints;
    private int dsaPoints;
    private int databasesPoints;
    private int springPoints;

    public Points() {
        this.javaPoints = 0;
        this.dsaPoints = 0;
        this.databasesPoints = 0;
        this.springPoints = 0;
    }

    public int getJavaPoints() {
        return javaPoints;
    }

    public int getDsaPoints() {
        return dsaPoints;
    }

    public int getDatabasesPoints() {
        return databasesPoints;
    }

    public int getSpringPoints() {
        return springPoints;
    }

    public void updatePoints(int javaPoints, int dsaPoints, int databasesPoints, int springPoints) {
        this.javaPoints += javaPoints;
        this.dsaPoints += dsaPoints;
        this.databasesPoints += databasesPoints;
        this.springPoints += springPoints;
    }

    public int[] getEnrolledCourse() {
        // if points are greater than 0, then the student is enrolled in the course
        int[] enrolledCourses = new int[4];
        enrolledCourses[0] = javaPoints > 0 ? 1 : 0;
        enrolledCourses[1] = dsaPoints > 0 ? 1 : 0;
        enrolledCourses[2] = databasesPoints > 0 ? 1 : 0;
        enrolledCourses[3] = springPoints > 0 ? 1 : 0;
        return enrolledCourses;
    }

    public int[] getTotalPoints() {
        return new int[] {javaPoints, dsaPoints, databasesPoints, springPoints};
    }

    public static String[] getCourses() {
        return new String[] {"Java", "DSA", "Databases", "Spring"};
    }

    public static int[] getPassingScore() {
        return new int[] {(int) javaPassingScore, (int) dsaPassingScore,
                (int) databasesPassingScore, (int) springPassingScore};
    }

    public String getScore() {
         return String.format("points: Java=%d; DSA=%d; Databases=%d; Spring=%d%n",
                javaPoints, dsaPoints, databasesPoints, springPoints);
    }
}
