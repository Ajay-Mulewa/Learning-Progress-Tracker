import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Character, Character> codesForChars = new HashMap<>();
        Map<Character, Character> charsForCodes = new HashMap<>();
        String text = scanner.nextLine();
        String codes = scanner.nextLine();
        String textToEncrypt = scanner.nextLine();
        String textToDecrypt = scanner.nextLine();
        for (int i = 0; i < text.length(); i++) {
            codesForChars.put(text.charAt(i), codes.charAt(i));
            charsForCodes.put(codes.charAt(i), text.charAt(i));
        }
        for (int i = 0; i < textToEncrypt.length(); i++) {
            System.out.print(codesForChars.get(textToEncrypt.charAt(i)));
        }
        System.out.println();
        for (int i = 0; i < textToDecrypt.length(); i++) {
            System.out.print(charsForCodes.get(textToDecrypt.charAt(i)));
        }
    }
}