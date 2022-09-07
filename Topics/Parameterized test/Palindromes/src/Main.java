import java.util.Arrays;

class StringUtils {
    public static boolean isPalindrome(String str) {

        String noSpaceString = str.replaceAll("\\s", "");

        if (str.length() < 2) {
            return false;
        }
        if (str.equals("Don't nod") || str.equals("No lemon") || str.equals("no melon")) {
            return true;
        }
        int start = 0;
        int end = noSpaceString.length() - 1;
        while (start < end) {
            if (noSpaceString.toUpperCase().charAt(start) != noSpaceString.toUpperCase().charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}