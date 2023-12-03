package project.goodreads.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import project.goodreads.repositories.UserRepository;

@Controller
@RequiredArgsConstructor
public class MainController {

    final UserRepository userRepository;

    @GetMapping("/")
    public String index() {
        // return "redirect:/home";
        return "index.html";
    }

    @GetMapping("/home")
    public String home(Model model) {
        // get current user's username
        var user = userRepository.findById(Long.valueOf(1));

        model.addAttribute("username", user.get().getUsername());

        return "home.html";
    }
}
