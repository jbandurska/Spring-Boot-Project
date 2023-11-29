package project.goodreads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import project.goodreads.models.User;
import project.goodreads.repositories.BookRepository;
import project.goodreads.repositories.BookshelfRepository;
import project.goodreads.repositories.UserRepository;
import project.goodreads.services.BookshelfService;
import project.goodreads.services.UserService;

@SpringBootApplication
public class GoodreadsApplication {
	private static final Logger logger = LoggerFactory.getLogger(GoodreadsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GoodreadsApplication.class, args);
	}

	// @Bean
	// public CommandLineRunner check(UserService userService, UserRepository
	// userRepository,
	// BookRepository bookRepository, BookshelfService bookshelfService) {
	// return args -> {
	// logger.info("COMMAND LINE RUNNER");

	// };
	// }

}
