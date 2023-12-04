package project.goodreads.controllers.view;

import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import project.goodreads.models.Book;
import project.goodreads.models.User;
import project.goodreads.repositories.BookRepository;
import project.goodreads.services.BookshelfService;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final BookshelfService bookshelfService;

    @GetMapping
    public String bookList(@RequestParam(required = false) String key, Model model) {

        Set<Book> books;

        if (key == null) {
            books = Set.copyOf(bookRepository.findAll());
        } else {
            books = bookRepository.findBooksByKey(key);
        }

        model.addAttribute("key", key);
        model.addAttribute("books", books);

        return "booklist";
    }

    @GetMapping("/{bookId}")
    public String book(@PathVariable Long bookId, Model model, Authentication authentication) {

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found"));

        User user = (User) authentication.getPrincipal();

        model.addAttribute("bookshelvesWithoutBook", bookshelfService.getBookshelvesWithoutBook(user.getId(), bookId));
        model.addAttribute("book", book);

        return "book";
    }

    @PostMapping
    public String addBookToBookshelf(@RequestParam Long bookshelfId, @RequestParam Long bookId, Model model,
            Authentication authentication) {

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found"));

        User user = (User) authentication.getPrincipal();

        bookshelfService.addBookToBookshelf(bookshelfId, bookId);

        model.addAttribute("bookshelvesWithoutBook", bookshelfService.getBookshelvesWithoutBook(user.getId(), bookId));
        model.addAttribute("book", book);
        model.addAttribute("message", "Book successfully added to bookshelf");

        return "book";
    }
}
