import java.util.ArrayDeque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int el = scanner.nextInt();
            if (el % 2 == 0) {
                deque.addFirst(el);
            } else {
                deque.addLast(el);
            }
        }
        deque.forEach(System.out::println);
    }
}