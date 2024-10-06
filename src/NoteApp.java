import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Note {
    private String title;
    private String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Tytuł: " + title + "\nTreść: " + content;
    }
}

public class NoteApp {
    private static List<Note> notes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Notatnik ---");
            System.out.println("1. Dodaj notatkę");
            System.out.println("2. Usuń notatkę");
            System.out.println("3. Edytuj notatkę");
            System.out.println("4. Wyświetl notatki");
            System.out.println("5. Wyjście");
            System.out.print("Wybierz opcję: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Odczytuje nową linię

            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    removeNote();
                    break;
                case 3:
                    editNote();
                    break;
                case 4:
                    displayNotes();
                    break;
                case 5:
                    running = false;
                    System.out.println("Zamykanie aplikacji.");
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }

    private static void addNote() {
        System.out.print("Podaj tytuł notatki (lub wpisz 'stop' aby wrócić do menu): ");
        String title = scanner.nextLine();
        if (title.equalsIgnoreCase("stop")) {
            return;
        }

        System.out.print("Podaj treść notatki (lub wpisz 'stop' aby wrócić do menu): ");
        String content = scanner.nextLine();
        if (content.equalsIgnoreCase("stop")) {
            return;
        }

        notes.add(new Note(title, content));
        System.out.println("Notatka dodana.");
        waitForEnter();
    }

    private static void removeNote() {
        displayNotes();
        System.out.print("Podaj numer notatki do usunięcia (lub wpisz 'stop' aby wrócić do menu): ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("stop")) {
            return;
        }

        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < notes.size()) {
                notes.remove(index);
                System.out.println("Notatka usunięta.");
            } else {
                System.out.println("Nieprawidłowy numer notatki.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Nieprawidłowy format. Podaj liczbę.");
        }
        waitForEnter();
    }

    private static void editNote() {
        displayNotes();
        System.out.print("Podaj numer notatki do edycji (lub wpisz 'stop' aby wrócić do menu): ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("stop")) {
            return;
        }

        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < notes.size()) {
                Note note = notes.get(index);

                System.out.print("Nowy tytuł (pozostaw puste, aby nie zmieniać lub wpisz 'stop' aby wrócić): ");
                String newTitle = scanner.nextLine();
                if (newTitle.equalsIgnoreCase("stop")) {
                    return;
                }
                if (!newTitle.isEmpty()) {
                    note.setTitle(newTitle);
                }

                System.out.print("Nowa treść (pozostaw puste, aby nie zmieniać lub wpisz 'stop' aby wrócić): ");
                String newContent = scanner.nextLine();
                if (newContent.equalsIgnoreCase("stop")) {
                    return;
                }
                if (!newContent.isEmpty()) {
                    note.setContent(newContent);
                }

                System.out.println("Notatka zaktualizowana.");
            } else {
                System.out.println("Nieprawidłowy numer notatki.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Nieprawidłowy format. Podaj liczbę.");
        }
        waitForEnter();
    }

    private static void displayNotes() {
        if (notes.isEmpty()) {
            System.out.println("Brak notatek do wyświetlenia.");
        } else {
            System.out.println("\n--- Lista notatek ---");
            for (int i = 0; i < notes.size(); i++) {
                System.out.println((i + 1) + ". " + notes.get(i));
                System.out.println("---------------------");
            }
        }
        waitForEnter();
    }

    private static void waitForEnter() {
        System.out.println("\nNaciśnij Enter, aby wrócić do menu głównego...");
        scanner.nextLine();
    }
}
