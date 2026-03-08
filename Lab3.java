import java.util.InputMismatchException;
import java.util.Scanner;

// Клас, що описує сутність "Квартира"
class Apartment {
    private int number;
    private double area;
    private int floor;
    private int rooms;
    private String buildingType;
    private int serviceLife;

    public Apartment(int number, double area, int floor, int rooms, String buildingType, int serviceLife) {
        this.number = number;
        this.area = area;
        this.floor = floor;
        this.rooms = rooms;
        this.buildingType = buildingType;
        this.serviceLife = serviceLife;
    }

    public int getRooms() { return rooms; }
    public double getArea() { return area; }
    public int getFloor() { return floor; }

    @Override
    public String toString() {
        return String.format("| %-7d | %-7.1f | %-6d | %-8d | %-12s | %-18d |",
                number, area, floor, rooms, buildingType, serviceLife);
    }

    // Метод для виведення заголовка таблиці без пунктирних ліній
    public static void printHeader() {
        System.out.println("| Номер   | Площа   | Поверх | Кімнати  | Тип будівлі  | Строк експлуатації |");
    }
}

// Головний клас обов'язково має збігатися з назвою файлу (Lab3.java)
public class Lab3 {
    
    // Ось цей метод шукала Java, щоб запустити програму
    public static void main(String[] args) {
        
        // 1. Створення масиву з 5 об'єктів
        Apartment[] apartments = {
            new Apartment(101, 45.5, 3, 1, "Панельний", 15),
            new Apartment(102, 68.0, 5, 2, "Цегляний", 5),
            new Apartment(103, 85.5, 9, 3, "Монолітний", 2),
            new Apartment(104, 50.0, 2, 2, "Цегляний", 20),
            new Apartment(105, 110.0, 12, 4, "Монолітний", 1)
        };

        System.out.println("Вихідні дані про квартири");
        printTable(apartments);

        Scanner scanner = new Scanner(System.in);

        // 2. Виконання першого завдання: Пошук за кількістю кімнат
        System.out.println("\nЗавдання 1: Пошук за кількістю кімнат");
        int searchRooms = getValidIntInput(scanner, "Введіть кількість кімнат для пошуку: ");
        findAndPrintByRooms(apartments, searchRooms);

        // 3. Виконання другого завдання: Пошук за площею та поверхом
        System.out.println("\nЗавдання 2: Пошук за площею та поверхом");
        double searchArea = getValidDoubleInput(scanner, "Введіть мінімальну площу: ");
        int searchFloor = getValidIntInput(scanner, "Введіть мінімальний поверх: ");
        findAndPrintByAreaAndFloor(apartments, searchArea, searchFloor);

        scanner.close();
    }

    // Метод для виведення всього масиву у вигляді таблиці
    private static void printTable(Apartment[] array) {
        if (array == null || array.length == 0) {
            System.out.println("Дані відсутні.");
            return;
        }
        Apartment.printHeader();
        for (Apartment apt : array) {
            System.out.println(apt.toString());
        }
    }

    // Метод для пошуку квартир за кількістю кімнат
    private static void findAndPrintByRooms(Apartment[] apartments, int rooms) {
        System.out.println("\nРезультат пошуку (Кімнат: " + rooms + "):");
        boolean found = false;
        Apartment.printHeader();
        for (Apartment apt : apartments) {
            if (apt.getRooms() == rooms) {
                System.out.println(apt.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("| Дані за заданим критерієм відсутні.                                       |");
        }
    }

    // Метод для пошуку квартир за площею та поверхом
    private static void findAndPrintByAreaAndFloor(Apartment[] apartments, double minArea, int minFloor) {
        System.out.println("\nРезультат пошуку (Площа > " + minArea + ", Поверх > " + minFloor + "):");
        boolean found = false;
        Apartment.printHeader();
        for (Apartment apt : apartments) {
            if (apt.getArea() > minArea && apt.getFloor() > minFloor) {
                System.out.println(apt.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("| Дані за заданим критерієм відсутні.                                       |");
        }
    }

    // Метод для безпечного введення цілих чисел з обробкою виключень
    private static int getValidIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                if (value < 0) {
                    System.out.println("Помилка: Значення не може бути від'ємним. Спробуйте ще раз.");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Помилка: Введено некоректні дані. Очікується ціле число.");
                scanner.next(); 
            }
        }
    }

    // Метод для безпечного введення дробових чисел з обробкою виключень
    private static double getValidDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = scanner.nextDouble();
                if (value < 0) {
                    System.out.println("Помилка: Значення не може бути від'ємним. Спробуйте ще раз.");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Помилка: Введено некоректні дані. Очікується числове значення.");
                scanner.next(); 
            }
        }
    }
}