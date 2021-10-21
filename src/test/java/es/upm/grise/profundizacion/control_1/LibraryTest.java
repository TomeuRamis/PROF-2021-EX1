package es.upm.grise.profundizacion.control_1;

import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LibraryTest {

    private static Library library;

    @Test
    public void testAddBook() {
        Book b = new Book("libro 1");
        library = new Library();

        try {
            library.addBook(b);
        } catch (DuplicatedBookException ex) {
            fail();
        }
    }

    @Test
    public void testAddDuplicatedBook() {
        Book b = new Book("libro 1");
        library = new Library();

        try {
            library.addBook(b);
        } catch (DuplicatedBookException ex) {
            fail();
        }
        //Añadimos un segundo libro igual. Si no salta la exception el test falla.
        try {
            library.addBook(b);
        } catch (DuplicatedBookException ex) {
            return;
        }
        fail();
    }

    @Test
    public void testAddMultipleBooks() {
        Book b1 = new Book("libro 1");
        Book b2 = new Book("libro 2");
        Book b3 = new Book("libro 3");
        Book b4 = new Book("libro 4");
        library = new Library();

        try {
            library.addBook(b1);
            library.addBook(b2);
            library.addBook(b3);
            library.addBook(b4);
        } catch (DuplicatedBookException ex) {
            fail();
        }
    }

    @Test
    public void testAddMultipleDuplicatedBooks() {
        Book b1 = new Book("libro 1");
        Book b2 = new Book("libro 2");
        Book b4 = new Book("libro 4");
        library = new Library();

        try {
            library.addBook(b1);
            library.addBook(b2);
            library.addBook(b4);
        } catch (DuplicatedBookException ex) {
            fail();
        }

        try {
            library.addBook(b2);
        } catch (DuplicatedBookException ex) {
            return;
        }
        fail();
    }

    @Test
    public void testRemoveBookOnEmptyLibrary() {
        Book b = new Book("libro 1");
        library = new Library();

        //Si salta algún tipo de excepción el test falla
        try {
            library.removeBook(b);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void testRemoveNonExistingBook() {
        Book b1 = new Book("libro 1");
        Book b2 = new Book("libro 2");
        library = new Library();

        try {
            library.addBook(b2);
        } catch (DuplicatedBookException ex) {
            fail();
        }

        //Si salta algún tipo de excepción el test falla
        try {
            library.removeBook(b1);
        } catch (Exception ex) {
            fail();
        }
    }
 
    @Test
    public void testGetBookOnEmptyLibrary() {
        String title = "libro 1";
        library = new Library();

        try {
            library.getBook(title);
        } catch (NonExistingBookException ex) {
            fail();
        } catch (EmptyLibraryException ex) {
            return;
        }
        fail();
    }

    @Test
    public void testGetNonExisiting() {

        Book b1 = new Book("libro 1");
        Book b2 = new Book("libro 2");
        library = new Library();

        try {
            library.addBook(b1);
            library.addBook(b2);
        } catch (DuplicatedBookException ex) {
            fail();
        }
        try {
            library.getBook("libro 3");
        } catch (NonExistingBookException ex) {
            return;
        } catch (EmptyLibraryException ex) {
            fail();
        }
    }
    
    @Test
    public void testRemoveBook() {
        Book b1 = new Book("libro 1");
        Book b2 = new Book("libro 2");
        library = new Library();

        //Si salta algún tipo de excepción el test falla
        try {
            library.addBook(b1);
            library.addBook(b2);
        } catch (Exception ex) {
            fail();
        }

        library.removeBook(b1);

        //Una vez eliminado el libro debe saltar exception de libro inexistente
        try {
            library.getBook("libro 1");
        } catch (NonExistingBookException ex) {
            return;
        } catch (EmptyLibraryException ex) {
            fail();
        }
    }


}
