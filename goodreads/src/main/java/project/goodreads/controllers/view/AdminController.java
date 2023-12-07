package project.goodreads.controllers.view;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import project.goodreads.repositories.BookRepository;
import project.goodreads.repositories.CommentRepository;
import project.goodreads.repositories.RatingRepository;
import project.goodreads.repositories.UserRepository;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;
    private final CommentRepository commentRepository;

    @GetMapping
    public String adminPanel() {

        return "admin/home";
    }

    @GetMapping("/books")
    public String bookPanel(Model model) {

        model.addAttribute("name", "books");
        model.addAttribute("items", bookRepository.findAll());
        model.addAttribute("fields", List.of("title", "author"));

        return "admin/manager";
    }

    @GetMapping("/users")
    public String userPanel(Model model) {

        model.addAttribute("name", "users");
        model.addAttribute("items", userRepository.findAll());
        model.addAttribute("fields", List.of("username", "role", "password"));

        return "admin/manager";
    }

    @GetMapping("/ratings")
    public String ratingPanel(Model model) {

        model.addAttribute("name", "ratings");
        model.addAttribute("items", ratingRepository.findAll());
        model.addAttribute("fields", List.of("stars", "userId", "bookId"));

        return "admin/manager";
    }

    @GetMapping("/comments")
    public String commentPanel(Model model) {

        model.addAttribute("name", "comments");
        model.addAttribute("items", commentRepository.findAll());
        model.addAttribute("fields", List.of("content", "username", "bookId"));

        return "admin/manager";
    }

}
