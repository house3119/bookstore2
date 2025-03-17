package bookstore.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import bookstore.bookstore.model.Book;
import bookstore.bookstore.model.BookRepository;
import bookstore.bookstore.model.Category;
import bookstore.bookstore.model.CategoryRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTests {

  private Category testCategory;
  private Book testBook;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @BeforeEach
  public void initBookTest() {
    this.testCategory = new Category("some category");
    categoryRepository.save(testCategory);

    this.testBook = new Book("Title1", "Author1", "some year", "some isbn", "some price", testCategory);
    bookRepository.save(testBook);
  }

  @Test
  public void addingNewBookWorks () {
    assertThat(testBook.getId()).isNotNull();
  }

  @Test void searchBookByTitleWorkds () {
    List<Book> books = bookRepository.findByTitle("Title1");
    assertThat(books).hasSize(1);
  }

  @Test
  public void deletingBookWorks () { 
    List<Book> books = bookRepository.findByTitle("Title1");
    Book bookToBeDeleted = books.get(0);
    bookRepository.delete(bookToBeDeleted);

    List<Book> newBooks = bookRepository.findByTitle("Title1");
    assertThat(newBooks).hasSize(0);
  }

}
