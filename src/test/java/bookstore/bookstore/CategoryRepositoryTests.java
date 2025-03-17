package bookstore.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import bookstore.bookstore.model.Category;
import bookstore.bookstore.model.CategoryRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTests {

  private Category testCategory;

  @Autowired
  private CategoryRepository categoryRepository;

  @BeforeEach
  public void initCategoryTest() {
    this.testCategory = new Category("some category");
    categoryRepository.save(testCategory);
  }

  @Test
  public void addingNewCategoryWorks () {
    assertThat(testCategory.getCategoryid()).isNotNull();
  }

  @Test
  public void searchCategoryByNameWorks () {
    assertThat(categoryRepository.findByName("some category")).hasSize(1);
  }

  @Test
  public void deletingCategoryWorks () {
    List<Category> categories = categoryRepository.findByName("some category");
    Category categoryToBeDeleted = categories.get(0);
    categoryRepository.delete(categoryToBeDeleted);

    List<Category> newCategories = categoryRepository.findByName("some category");
    assertThat(newCategories).hasSize(0);
  }

}
