package project.goodreads;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import project.goodreads.models.Book;
import project.goodreads.repositories.BookRepository;

@SpringBootApplication
public class GoodreadsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodreadsApplication.class, args);
	}

	@Bean
	public CommandLineRunner check(BookRepository bookRepository) {
		return args -> {
			System.out.println("COMMAND LINE RUNNER");

			Book newBook = new Book();
			newBook.setTitle("Warriors");
			bookRepository.save(newBook);

			Iterable<Book> books = bookRepository.findAll();

			for (Book book : books) {
				System.out.println(book.getTitle());
			}
		};
	}

}
