import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Lab2 {

    static int getIntFromInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception _) {
                System.out.println("Invalid input, please try again!");
                System.out.print(prompt);
            }
        }
    }

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        System.out.println("=== TASK 1 ===");
        int length;
        while (true) {
            length = getIntFromInput(scanner, "Please enter the length of the random number (>= 2): ");
            if (length >= 2) break;
            System.out.println("Length must be at least 2 to swap the first two digits.");
        }

        var random = new Random();
        var sb = new StringBuilder();
        sb.append(random.nextInt(9) + 1);
        for (int i = 1; i < length; i++) {
            sb.append(random.nextInt(10));
        }

        var originalNumber = sb.toString();
        var chars = originalNumber.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            int digit = chars[i] - '0';
            if (digit % 2 == 0) {
                chars[i] = digit == 0 ? '9' : (char) (chars[i] - 1);
            }
        }

        char temp = chars[0];
        chars[0] = chars[1];
        chars[1] = temp;

        var processedNumber = new String(chars);
        System.out.println("%s -> %s".formatted(originalNumber, processedNumber));

        System.out.println("\n=== TASK 2 ===");  
        var text = """
                This is a simple text for testing our Java program.\n
                   We need to find words like Programming, Game, or CPU.\n
                   також тут є українські слова, які алгоритм просто пропустить.\n
                """;

        int targetLength = getIntFromInput(scanner, "Please enter target word length: ");

        System.out.println("\n--- Inputs ---");
        System.out.println("Target length: %d".formatted(targetLength));
        System.out.println("Original text:\n%s".formatted(text));

        var pattern = Pattern.compile("[a-zA-Zа-яА-ЯіІїЇєЄґҐ0-9]+");
        var matcher = pattern.matcher(text);
        var foundWords = new ArrayList<String>();

        while (matcher.find()) {
            var word = matcher.group();
            if (word.length() == targetLength) {
                int latinCount = 0;
                for (char c : word.toCharArray()) {
                    if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                        latinCount++;
                    }
                }
                if (latinCount > 2) {
                    foundWords.add(word);
                }
            }
        }

        System.out.println("--- Results ---");
        if (foundWords.isEmpty()) {
            System.out.println("No matching words found.");
        } else {
            System.out.println("Found words:");
            for (var w : foundWords) {
                System.out.println("- %s".formatted(w));
            }
        }
    }
}