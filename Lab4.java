import java.io.*;
import java.util.*;

public class Lab4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Введення імен файлів з клавіатури
        System.out.print("Введіть шлях до вхідного файлу (наприклад, input.txt): ");
        String inputFilePath = scanner.nextLine();

        System.out.print("Введіть шлях до файлу для збереження результату (наприклад, output.txt): ");
        String outputFilePath = scanner.nextLine();

        System.out.println("\n[ІНФО] Починаємо обробку файлу...");

        List<String> words = new ArrayList<>();

        // 2. Читання файлу та вилучення слів
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            System.out.println("[ІНФО] Вихідний файл успішно відкрито. Читаємо дані...");
            
            String line;
            while ((line = reader.readLine()) != null) {
                // Використовуємо регулярний вираз [^\p{L}]+
                // Це означає: розбити рядок по всьому, що НЕ є літерою (пробіли, розділові знаки, цифри).
                // \p{L} підтримує як англійський, так і український алфавіт.
                String[] lineWords = line.split("[^\\p{L}]+");
                
                for (String word : lineWords) {
                    if (!word.isEmpty()) {
                        words.add(word); // Додаємо знайдене слово до списку
                    }
                }
            }
            System.out.println("[ІНФО] Читання завершено. Знайдено слів для сортування: " + words.size());

        } catch (FileNotFoundException e) {
            System.out.println("[ПОМИЛКА] Вхідний файл не знайдено! Перевірте правильність шляху.");
            scanner.close();
            return; // Зупиняємо програму, якщо файлу немає
        } catch (IOException e) {
            System.out.println("[ПОМИЛКА] Сталася помилка під час читання файлу: " + e.getMessage());
            scanner.close();
            return;
        }

        // 3. Сортування слів
        System.out.println("[ІНФО] Сортуємо слова за алфавітом...");
        // String.CASE_INSENSITIVE_ORDER дозволяє сортувати без урахування регістру (щоб "А" і "а" були поруч)
        words.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("[ІНФО] Сортування успішно завершено.");

        // 4. Запис результату у новий файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            System.out.println("[ІНФО] Відкриваємо файл для запису результату...");
            
            for (String word : words) {
                writer.write(word);
                writer.newLine(); // Записуємо кожне слово з нового рядка для зручності
            }
            
            System.out.println("[ІНФО] Дані успішно записано у файл: " + outputFilePath);

        } catch (IOException e) {
            System.out.println("[ПОМИЛКА] Помилка запису у файл: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}