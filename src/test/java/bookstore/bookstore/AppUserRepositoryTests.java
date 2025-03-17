package bookstore.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import bookstore.bookstore.model.AppUser;
import bookstore.bookstore.model.AppUserRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AppUserRepositoryTests {

  private AppUser testUser;

  @Autowired
  private AppUserRepository appUserRepository;

  @BeforeEach
  public void initAppUserTest() {
    this.testUser = new AppUser("testuser", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
    appUserRepository.save(testUser);
  }

  @Test
  public void addingNewAppUserWorks() {
    assertThat(testUser.getId()).isNotNull();
  }

  @Test
  public void searchAppUserByUsernameWorks() {
    AppUser user = appUserRepository.findByUsername("testuser");
    assertThat(user).isNotNull();
  }

  @Test
  public void deletingAppUserWorks() {
    appUserRepository.delete(testUser);
    assertThat(appUserRepository.findByUsername("testuser")).isNull();
  }

}
