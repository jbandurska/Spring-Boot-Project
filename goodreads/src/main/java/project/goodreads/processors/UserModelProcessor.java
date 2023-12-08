package project.goodreads.processors;

import java.util.HashSet;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import lombok.Data;
import project.goodreads.models.Bookshelf;
import project.goodreads.models.User;
import project.goodreads.repositories.BookshelfRepository;
import project.goodreads.repositories.CommentRepository;
import project.goodreads.repositories.RatingRepository;
import project.goodreads.services.BookService;
import project.goodreads.services.BookshelfService;

@Component
@Scope("prototype")
@Data
public class UserModelProcessor {

    private final BookService bookService;
    private final BookshelfService bookshelfService;
    private final BookshelfRepository bookshelfRepository;
    private final RatingRepository ratingRepository;
    private final CommentRepository commentRepository;

    private User user;

    public void configureBookViewModel(Model model, Long bookId) {
        configureBookViewModel(model, bookId, "");
    }

    public void configureBookViewModel(Model model, Long bookId, String message) {

        var book = bookService.getBook(bookId);
        var bookshelves = bookshelfService.getBookshelvesWithoutBook(user.getId(), bookId);

        // filter 'read' and 'to be read' bookshelves
        // as they won't appear in the 'add bookshelf' select
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

        // bookshelves for select
        model.addAttribute("bookshelves", filteredBookshelves);

        // book info
        model.addAttribute("book", book);

        // stats
        model.addAttribute("readStat", bookshelfRepository.countBookshelves("read", bookId));
        model.addAttribute("tbrStat", bookshelfRepository.countBookshelves("to be read", bookId));
        model.addAttribute("commentStat", commentRepository.countByBookId(bookId));
        model.addAttribute("ratingStat", ratingRepository.countByBookId(bookId));

        // reviews
        model.addAttribute("rating", ratingRepository.getAverageRatingByBookId(bookId));
        model.addAttribute("comments", commentRepository.findAllByBookId(bookId));

        // message
        model.addAttribute("message", message);
    }
}
