package hw4;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookServiceTest {

    private int lastBookIdx = 0;

    Book createTestBook() {
        int idx = ++lastBookIdx;
        return new Book("00000000-0000-0000-C000-000000000046-" + idx);
    }

    List<String> getBooksIds(List<Book> books) {
        return books.stream()
                .map(Book::getId)
                .collect(Collectors.toList());
    }

    @Test
    void findBookById() {
        BookRepository bookRepository = mock(BookRepository.class);
        BookService testService = new BookService(bookRepository);

        Book expected = createTestBook();
        String expectedId = expected.getId();
        when(bookRepository.findById(expectedId)).thenReturn(expected);

        Book actual = testService.findBookById(expectedId);

        assertEquals(expectedId, actual.getId());
    }

    @Test
    void findAllBooks() {
        BookRepository bookRepository = mock(BookRepository.class);
        BookService testService = new BookService(bookRepository);

        List<Book> expected = List.of(createTestBook(), createTestBook(), createTestBook());
        when(bookRepository.findAll()).thenReturn(expected);

        List<Book> actual = testService.findAllBooks();

        assertIterableEquals(getBooksIds(expected), getBooksIds(actual));
    }
}