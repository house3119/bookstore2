package bookstore.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import bookstore.bookstore.model.AppUser;
import bookstore.bookstore.model.AppUserRepository;
import bookstore.bookstore.model.Book;
import bookstore.bookstore.model.Category;
import bookstore.bookstore.model.BookRepository;
import bookstore.bookstore.model.CategoryRepository;


@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookStoreRunner(
		BookRepository bookRepository,
		CategoryRepository categoryRepository,
		AppUserRepository appUserRepository
		) {
		return(args) -> {

			Category category1 = new Category("Fantasy");
			Category category2 = new Category("Magical realism");
			Category category3 = new Category("Historical novel");
			Category category4 = new Category("Educational");

			if (categoryRepository.count() == 0) {
				categoryRepository.save(category1);
				categoryRepository.save(category2);
				categoryRepository.save(category3);
				categoryRepository.save(category4);
			}

			log.info("Add some demo books to db...");

			if (bookRepository.count() == 0) {
				bookRepository.save(new Book("Lord of the Rings: The Two Towers", "J.R.R. Tolkien", "1954", "978-951-0-33337-2", "24.00", category1));
				bookRepository.save(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "1997", "951-31-1146-6", "19.00", category1));
				bookRepository.save(new Book("Kafka on the Shore", "H. Murakami", "2002", "1-84343-110-6", "9.90", category2));
				bookRepository.save(new Book("The Egyptian", "M. Waltari", "1945", "1-55652-441-2", "39.90", category3));
			}

			log.info("Printing list of books...");

			for (Book book: bookRepository.findAll()) {
				log.info(book.toString());
			}

			if (appUserRepository.count() == 0) {
				appUserRepository.save(new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER"));
				appUserRepository.save(new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN"));
			}

		};

	}

}
