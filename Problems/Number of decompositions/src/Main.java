import java.util.Scanner;

class Main {
    static StringBuilder stringBuilder = new StringBuilder();
    public static void partition(int n) {
        partition(n, n, "");
    }
    public static void partition(int n, int max, String prefix) {
        if (n == 0) {
            stringBuilder.append(prefix + "\n");
            return;
        }

        for (int i = Math.min(max, n); i >= 1; i--) {
            partition(n-i, i, prefix + " " + i);
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        partition(n);
        System.out.println(stringBuilder.reverse());
    }
}