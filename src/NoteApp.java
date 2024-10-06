import java.util.ArrayList;
import java.util.List;

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

    public static String addNote(String title, String content) {
        if (title == null || title.isEmpty() || content == null || content.isEmpty()) {
            return "Tytuł i treść nie mogą być puste";
        }
        notes.add(new Note(title, content));
        return "Notatka dodana";
    }

    public static String removeNote(int index) {
        if (index < 0 || index >= notes.size()) {
            return "Nieprawidłowy numer notatki";
        }
        notes.remove(index);
        return "Notatka usunięta";
    }

    public static String editNote(int index, String newTitle, String newContent) {
        if (index < 0 || index >= notes.size()) {
            return "Nieprawidłowy numer notatki";
        }
        Note note = notes.get(index);
        if (newTitle != null && !newTitle.isEmpty()) {
            note.setTitle(newTitle);
        }
        if (newContent != null && !newContent.isEmpty()) {
            note.setContent(newContent);
        }
        return "Notatka zaktualizowana";
    }

    public static List<Note> getNotes() {
        return new ArrayList<>(notes);
    }

    public static void clearNotes() {
        notes.clear();
    }
}
