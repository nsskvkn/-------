import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Searchable {
    void findBySubject(String subject);
}

public class Lab5 implements Searchable {
    private String studentName;
    private List<Session> sessions;

    public Lab5(String studentName) {
        this.studentName = studentName;
        this.sessions = new ArrayList<>();
        System.out.println("[СИСТЕМА]: Створено залікову книжку для: " + studentName);
    }

    class Session {
        private String discipline;
        private String controlForm;

        public Session(String discipline, String controlForm) {
            this.discipline = discipline;
            this.controlForm = controlForm;
        }

        @Override
        public String toString() {
            return String.format("| %-20s | %-15s |", discipline, controlForm);
        }
    }

    public void addSession(String discipline, String controlForm) {
        sessions.add(new Session(discipline, controlForm));
        System.out.println("[СИСТЕМА]: Запис '" + discipline + "' додано.");
    }

    @Override
    public void findBySubject(String subject) {
        System.out.println("\n[СИСТЕМА]: Пошук: " + subject);
        boolean found = false;
        for (Session s : sessions) {
            if (s.discipline.equalsIgnoreCase(subject)) {
                System.out.println("Знайдено: " + s);
                found = true;
            }
        }
        if (!found) System.out.println("Нічого не знайдено.");
    }

    public void displayAll() {
        System.out.println("\n--- ЗАЛІКОВА КНИЖКА: " + studentName + " ---");
        System.out.println("-------------------------------------------");
        for (Session s : sessions) {
            System.out.println(s);
        }
        System.out.println("-------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Введіть ім'я студента: ");
        Lab5 myLab = new Lab5(scanner.nextLine());

        System.out.print("Кількість записів: ");
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < count; i++) {
            System.out.print("Дисципліна: ");
            String d = scanner.nextLine();
            System.out.print("Форма контролю: ");
            String f = scanner.nextLine();
            myLab.addSession(d, f);
        }

        myLab.displayAll();

        System.out.print("\nПошук за назвою: ");
        myLab.findBySubject(scanner.nextLine());

        scanner.close();
    }
}