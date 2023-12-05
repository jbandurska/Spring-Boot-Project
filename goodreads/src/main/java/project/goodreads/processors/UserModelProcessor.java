package project.goodreads.processors;

import java.util.HashSet;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import lombok.Data;
import project.goodreads.models.Bookshelf;
import project.goodreads.models.User;
import project.goodreads.repositories.RatingRepository;
import project.goodreads.services.BookService;
import project.goodreads.services.BookshelfService;

@Component
@Scope("prototype")
@Data
public class UserModelProcessor {

    private final BookService bookService;
    private final BookshelfService bookshelfService;
    private final RatingRepository ratingRepository;

    private User user;

    public void configureBookViewModel(Model model, Long bookId) {
        configureBookViewModel(model, bookId, "");
    }

    public void configureBookViewModel(Model model, Long bookId, String message) {

        var book = bookService.getBook(bookId);
        var bookshelves = bookshelfService.getBookshelvesWithoutBook(user.getId(), bookId);

        var filteredBookshelves = new HashSet<Bookshelf>();
        for (Bookshelf bookshelf : bookshelves) {

            if (bookshelf.getName().equals("read")) {
                model.addAttribute("read", bookshelf);
            } else if (bookshelf.getName().equals("to be read")) {
                model.addAttribute("tbr", bookshelf);
            } else {
                filteredBookshelves.add(bookshelf);
            }

        }

        model.addAttribute("bookshelves", filteredBookshelves);
        model.addAttribute("book", book);
        model.addAttribute("rating", ratingRepository.getAverageRatingByBookId(bookId));
        model.addAttribute("message", message);
    }
}
