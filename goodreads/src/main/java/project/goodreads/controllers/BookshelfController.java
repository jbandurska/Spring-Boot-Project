package project.goodreads.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import project.goodreads.services.BookshelfService;

@Controller
@RequestMapping("/user/bookshelf")
@RequiredArgsConstructor
public class BookshelfController {

    final BookshelfService bookshelfService;

    @GetMapping
    public String bookshelves(Model model) {

        model.addAttribute("bookshelves", bookshelfService.getBookshelves(Long.valueOf(1)));

        return "bookshelves.html";
    }
}
