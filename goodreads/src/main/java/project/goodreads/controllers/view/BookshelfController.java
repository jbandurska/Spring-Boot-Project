package project.goodreads.controllers.view;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import project.goodreads.models.User;
import project.goodreads.services.BookshelfService;

@Controller
@RequestMapping("/bookshelves")
@RequiredArgsConstructor
public class BookshelfController {

    final BookshelfService bookshelfService;

    @GetMapping
    public String bookshelves(Authentication authentication, Model model) {

        User user = (User) authentication.getPrincipal();

        model.addAttribute("bookshelves", bookshelfService.getBookshelves(user.getId()));

        return "bookshelves";
    }

    @GetMapping("/{bookshelfId}")
    public String bookshelf(@PathVariable Long bookshelfId, Model model) {

        var bookshelf = bookshelfService.getBookshelfById(bookshelfId);

        model.addAttribute("bookshelf", bookshelf);
        model.addAttribute("books", bookshelf.getBooks());

        return "bookshelf";
    }

    @PostMapping
    public String addBookshelf(Authentication authentication, Model model, @RequestParam String name) {

        User user = (User) authentication.getPrincipal();
        bookshelfService.createBookshelf(name, user.getId());

        model.addAttribute("bookshelves", bookshelfService.getBookshelves(user.getId()));

        return "bookshelves";
    }

    @ResponseBody
    @DeleteMapping("/{bookshelfId}/book/{bookId}")
    public void deleteFromBookshelf(@PathVariable Long bookshelfId, @PathVariable Long bookId, Model model) {

        bookshelfService.deleteBookFromBookshelf(bookshelfId, bookId);
    }
}
