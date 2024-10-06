import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NoteAppTest {

    @BeforeEach
    void setUp() {
        NoteApp.clearNotes(); // Resetowanie listy przed każdym testem
    }

    @Test
    void testAddNote() {
        String result = NoteApp.addNote("Tytuł testowy", "Treść testowa");
        assertEquals("Notatka dodana", result);
        assertEquals(1, NoteApp.getNotes().size());
    }

    @Test
    void testAddNoteEmptyTitle() {
        String result = NoteApp.addNote("", "Treść testowa");
        assertEquals("Tytuł i treść nie mogą być puste", result);
        assertEquals(0, NoteApp.getNotes().size());
    }

    @Test
    void testRemoveNoteValidIndex() {
        NoteApp.addNote("Tytuł testowy", "Treść testowa");
        String result = NoteApp.removeNote(0);
        assertEquals("Notatka usunięta", result);
        assertEquals(0, NoteApp.getNotes().size());
    }

    @Test
    void testRemoveNoteInvalidIndex() {
        NoteApp.addNote("Tytuł testowy", "Treść testowa");
        String result = NoteApp.removeNote(5); // Nieprawidłowy indeks
        assertEquals("Nieprawidłowy numer notatki", result);
        assertEquals(1, NoteApp.getNotes().size());
    }

    @Test
    void testEditNoteValidIndex() {
        NoteApp.addNote("Tytuł testowy", "Treść testowa");
        String result = NoteApp.editNote(0, "Nowy tytuł", "Nowa treść");
        assertEquals("Notatka zaktualizowana", result);
        assertEquals("Nowy tytuł", NoteApp.getNotes().get(0).getTitle());
        assertEquals("Nowa treść", NoteApp.getNotes().get(0).getContent());
    }

    @Test
    void testEditNoteInvalidIndex() {
        NoteApp.addNote("Tytuł testowy", "Treść testowa");
        String result = NoteApp.editNote(5, "Nowy tytuł", "Nowa treść"); // Nieprawidłowy indeks
        assertEquals("Nieprawidłowy numer notatki", result);
    }

    @Test
    void testEditNoteWithEmptyFields() {
        NoteApp.addNote("Tytuł testowy", "Treść testowa");
        String result = NoteApp.editNote(0, "", ""); // Brak zmiany danych
        assertEquals("Notatka zaktualizowana", result);
        assertEquals("Tytuł testowy", NoteApp.getNotes().get(0).getTitle());
        assertEquals("Treść testowa", NoteApp.getNotes().get(0).getContent());
    }

    @Test
    void testGetNotes() {
        NoteApp.addNote("Tytuł testowy", "Treść testowa");
        assertEquals(1, NoteApp.getNotes().size());
    }

    @Test
    void testClearNotes() {
        NoteApp.addNote("Tytuł testowy", "Treść testowa");
        NoteApp.clearNotes();
        assertEquals(0, NoteApp.getNotes().size());
    }


@Test
void testAddNoteNullTitle() {
    String result = NoteApp.addNote(null, "Treść testowa");
    assertEquals("Tytuł i treść nie mogą być puste", result);
}

@Test
void testAddNoteNullContent() {
    String result = NoteApp.addNote("Tytuł testowy", null);
    assertEquals("Tytuł i treść nie mogą być puste", result);
}

@Test
void testAddNoteBothEmpty() {
    String result = NoteApp.addNote("", "");
    assertEquals("Tytuł i treść nie mogą być puste", result);
}

@Test
void testRemoveNoteFromEmptyList() {
    String result = NoteApp.removeNote(0); // Próba usunięcia z pustej listy
    assertEquals("Nieprawidłowy numer notatki", result);
}

@Test
void testEditNoteOnEmptyList() {
    String result = NoteApp.editNote(0, "Nowy tytuł", "Nowa treść");
    assertEquals("Nieprawidłowy numer notatki", result);
}

@Test
void testEditNoteWithNullTitle() {
    NoteApp.addNote("Tytuł testowy", "Treść testowa");
    String result = NoteApp.editNote(0, null, "Nowa treść");
    assertEquals("Notatka zaktualizowana", result);
    assertEquals("Treść testowa", NoteApp.getNotes().get(0).getContent());
}

@Test
void testEditNoteWithNullContent() {
    NoteApp.addNote("Tytuł testowy", "Treść testowa");
    String result = NoteApp.editNote(0, "Nowy tytuł", null);
    assertEquals("Notatka zaktualizowana", result);
    assertEquals("Tytuł testowy", NoteApp.getNotes().get(0).getTitle());
}

@Test
void testEditNoteWithBothNulls() {
    NoteApp.addNote("Tytuł testowy", "Treść testowa");
    String result = NoteApp.editNote(0, null, null);
    assertEquals("Notatka zaktualizowana", result);
    assertEquals("Tytuł testowy", NoteApp.getNotes().get(0).getTitle());
    assertEquals("Treść testowa", NoteApp.getNotes().get(0).getContent());
}

@Test
void testAddMultipleNotes() {
    NoteApp.addNote("Tytuł 1", "Treść 1");
    NoteApp.addNote("Tytuł 2", "Treść 2");
    NoteApp.addNote("Tytuł 3", "Treść 3");
    assertEquals(3, NoteApp.getNotes().size());
}

@Test
void testRemoveLastNote() {
    NoteApp.addNote("Tytuł testowy", "Treść testowa");
    String result = NoteApp.removeNote(0);
    assertEquals("Notatka usunięta", result);
    assertEquals(0, NoteApp.getNotes().size());
}

@Test
void testEditNoteAfterAddingMultiple() {
    NoteApp.addNote("Tytuł 1", "Treść 1");
    NoteApp.addNote("Tytuł 2", "Treść 2");
    NoteApp.editNote(1, "Nowy Tytuł", "Nowa Treść");
    assertEquals("Nowy Tytuł", NoteApp.getNotes().get(1).getTitle());
    assertEquals("Nowa Treść", NoteApp.getNotes().get(1).getContent());
}

@Test
void testEditNoteNotAffectOtherNotes() {
    NoteApp.addNote("Tytuł 1", "Treść 1");
    NoteApp.addNote("Tytuł 2", "Treść 2");
    NoteApp.editNote(0, "Nowy Tytuł", "Nowa Treść");
    assertEquals("Tytuł 2", NoteApp.getNotes().get(1).getTitle()); // Powinno być niezmienione
}

@Test
void testAddNoteWithSpecialCharacters() {
    String result = NoteApp.addNote("Tytuł@!#$%^&*()", "Treść@!#$%^&*()");
    assertEquals("Notatka dodana", result);
    assertEquals(1, NoteApp.getNotes().size());
    assertEquals("Tytuł@!#$%^&*()", NoteApp.getNotes().get(0).getTitle());
}

@Test
void testRemoveNoteAndCheckSize() {
    NoteApp.addNote("Tytuł testowy", "Treść testowa");
    NoteApp.addNote("Tytuł testowy 2", "Treść testowa 2");
    NoteApp.removeNote(0);
    assertEquals(1, NoteApp.getNotes().size());
}

@Test
void testEditNoteWithWhitespace() {
    NoteApp.addNote("   ", "Treść testowa"); // Tytuł z samych spacji
    assertEquals("Notatka dodana", NoteApp.addNote("   ", "Treść testowa"));
    assertEquals(1, NoteApp.getNotes().size());
    assertEquals("   ", NoteApp.getNotes().get(0).getTitle()); // Tytuł z samych spacji
}

@Test
void testEditNoteContentWithWhitespace() {
    NoteApp.addNote("Tytuł testowy", "Treść testowa");
    NoteApp.editNote(0, "Tytuł testowy", "   "); // Treść z samych spacji
    assertEquals("   ", NoteApp.getNotes().get(0).getContent());
}

@Test
void testGetNotesReturnsEmptyListInitially() {
    assertTrue(NoteApp.getNotes().isEmpty(), "Powinna zwracać pustą listę na początku");
}

@Test
void testAddNoteWithLongTitle() {
    String longTitle = "Tytuł" + "a".repeat(1000);
    String result = NoteApp.addNote(longTitle, "Treść testowa");
    assertEquals("Notatka dodana", result);
    assertEquals(1, NoteApp.getNotes().size());
}

@Test
void testAddNoteWithLongContent() {
    String longContent = "Treść" + "b".repeat(1000);
    String result = NoteApp.addNote("Tytuł testowy", longContent);
    assertEquals("Notatka dodana", result);
    assertEquals(1, NoteApp.getNotes().size());
}

@Test
void testRemoveMultipleNotes() {
    NoteApp.addNote("Tytuł 1", "Treść 1");
    NoteApp.addNote("Tytuł 2", "Treść 2");
    NoteApp.removeNote(0); // Usuń pierwszą
    NoteApp.removeNote(0); // Usuń teraz drugą
    assertEquals(0, NoteApp.getNotes().size());
}
}
