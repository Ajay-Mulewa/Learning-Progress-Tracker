package tracker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final String INCORRECT_FIRST_NAME_PROMPT = "Incorrect first name.";
    private static final String INCORRECT_LAST_NAME_PROMPT = "Incorrect last name.";
    private static final String INCORRECT_EMAIL_PROMPT = "Incorrect email.";
    private static final String EMAIL_ALREADY_TAKEN_PROMPT = "This email is already taken.";

    private static final Pattern correctFirstName = Pattern.compile("[a-zA-Z]+(['-]?[a-zA-Z]\\s?)+");
    private static final Pattern correctLastName = Pattern.compile("[a-zA-Z]+(['-]?[a-zA-Z]\\s?)+");
    private static final Pattern correctEmail = Pattern.compile("^[\\w.]+@[\\w.]+\\.[\\w]+$");
    private static final Pattern correctPoints = Pattern.compile("^([\\w]+\\s){1}([\\d]+\\s+){3}[\\d]+$");

    public static boolean isValidPointsFormat(String points) {
        Matcher matcher = correctPoints.matcher(points);
        return matcher.matches();
    }
    public static boolean isValidAllCredentials(String firstName, String lastName, String email) {
        return isValidFirstName(firstName) && isValidLastName(lastName) && isValidEmail(email);
    }

    public static void printErrorMessage(String firstName, String lastName, String email) {
        if (!isValidFirstName(firstName)) {
            System.out.println(INCORRECT_FIRST_NAME_PROMPT);
        }
        if (!isValidLastName(lastName)) {
            System.out.println(INCORRECT_LAST_NAME_PROMPT);
        }
        if (!isValidEmail(email)) {
            System.out.println(INCORRECT_EMAIL_PROMPT);
        }
        if (DatabaseStudents.emailExistInDB(email)) {
            System.out.println(EMAIL_ALREADY_TAKEN_PROMPT);
        }
    }

    public static boolean isValidFirstName(String firstName) {
        Matcher matcher = correctFirstName.matcher(firstName);
        return matcher.matches();
    }

    public static boolean isValidLastName(String lastName) {
        Matcher matcher = correctLastName.matcher(lastName);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        Matcher matcher = correctEmail.matcher(email);
        return matcher.matches();
    }
}
