package project.goodreads.controllers.view;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

        return "bookshelves.html";
    }
}
