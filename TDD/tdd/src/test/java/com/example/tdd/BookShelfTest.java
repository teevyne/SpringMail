package com.example.tdd;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName(" <= Bookshelf=>")
public class BookShelfTest {

    private BookShelf shelf;
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;

    @BeforeEach
    void init() {
        shelf = new BookShelf();
        effectiveJava = new Book("Joshua Bloch", "Effective Java", LocalDate.of(2008,
                Month.MAY, 8));
        cleanCode = new Book("Robert C. Martin", "Clean Code", LocalDate.of(2008,
                Month.MAY, 8));
        codeComplete = new Book("Steve McConnel", "Code Complete", LocalDate.of(2004,
                Month.JUNE, 9));
        mythicalManMonth = new Book("Phillips Brooks", "Mythical Man-Month", LocalDate.of(1975,
                Month.JANUARY, 1));
    }

    Logger logger = Logger.getLogger(getClass().getName());

    private BookShelfTest(TestInfo testInfo){
        System.out.println("Working on test class " + testInfo.getDisplayName());
    }

    @Nested
    @DisplayName("is empty")
    class IsEmpty {
        @Test
        @DisplayName("is empty when no book is added to it")
        public void shelfEmptyWhenNoBookAddedTest(TestInfo testInfo) {
            List<Book> books = shelf.books();
            logger.info("Bookshelf is empty");
            assertTrue(books.isEmpty());
        }

        @Test
        @DisplayName("returns nothing when add is called without a book")
        void emptyBookShelfWhenAddIsCalledWithoutBook(){
            shelf.add();
            List<Book> books = shelf.books();
            logger.info("Bookshelf should be empty");
            assertTrue(books.isEmpty());
        }
    }

    @Nested
    @DisplayName("returns a book object")
    class HasBook {
        @Test
        @DisplayName("has two books when two books added")
        void bookShelfHasTwoBookWhenTwoBooksAdded(){
            shelf.add(effectiveJava, codeComplete);
            List<Book> books = shelf.books();
            assertEquals(2, books.size(), "Bookshelf has two books");
        }



        @Test
        @DisplayName("Immutable Tests")
        void booksReturnedFromBookIsImmutableForClient(){
            shelf.add(effectiveJava, codeComplete);
            List<Book> books = shelf.books();
            try {
                books.add(mythicalManMonth);
                fail("Should not be able to add book to books");
            } catch (Exception e){
                assertTrue(e instanceof UnsupportedOperationException);
            }
        }
    }

//    @Disabled("Tested to see about disabling")
    @Test
    @DisplayName("Title Arrangement")
    void bookShelfArrangedByBookTitle(){
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        List<Book> books = shelf.arrange();
        assertEquals(asList(codeComplete, effectiveJava, mythicalManMonth), books, "Books " +
                "in the shelf should be arranged lexicographically");
    }

    @Test
    @DisplayName("Title Arrangement II")
    void booksInBookshelfAreInInsertionOrderAfterCallingArrange(){
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        List<Book> books = shelf.arrange();
        assertEquals(asList(codeComplete, effectiveJava, mythicalManMonth), books, "Books " +
                "in the shelf are in insertion order");
    }

    @Test
    @DisplayName("Criteria Arrangement")
    void bookShelfArrangedWithProvidedCriteria() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        Comparator<Book> reversed = Comparator.<Book>naturalOrder().reversed();
        List<Book> books = shelf.arrange(reversed);
        assertThat(books).isSortedAccordingTo(reversed);
    }

    @Test
    @DisplayName("books inside shelf are grouped by publication year")
    void groupBooksInsideShelfByPublicationYear(){
        shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
        Map<Year, List<Book>> booksByPublicationYear = shelf.groupByPublicationYear();

        assertThat(booksByPublicationYear)
                .containsKey(Year.of(2008))
                .containsValue(Arrays.asList(effectiveJava, cleanCode));

        assertThat(booksByPublicationYear)
                .containsKey(Year.of(2004))
                .containsValues(singletonList(codeComplete));

        assertThat(booksByPublicationYear)
                .containsKey(Year.of(1975))
                .containsValues(singletonList(mythicalManMonth));
    }

    @Test
    @DisplayName("books inside bookshelf are grouped according to user provided criteria(group by author name)")
    void groupBooksByUserProvidedCriteria() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
        Map<String, List<Book>> booksByAuthor = shelf.groupBy(Book::getAuthor);
        assertThat(booksByAuthor)
                .containsKey("Joshua Bloch")
                .containsValues(singletonList(effectiveJava));
        assertThat(booksByAuthor)
                .containsKey("Steve McConnel")
                .containsValues(singletonList(codeComplete));
        assertThat(booksByAuthor)
                .containsKey("Phillips Brooks")
                .containsValues(singletonList(mythicalManMonth));
        assertThat(booksByAuthor)
                .containsKey("Robert C. Martin")
                .containsValues(singletonList(cleanCode));
    }
}