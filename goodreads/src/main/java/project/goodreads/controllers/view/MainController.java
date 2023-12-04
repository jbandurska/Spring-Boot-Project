package project.goodreads.controllers.view;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import project.goodreads.exceptions.UserAlreadyExistException;
import project.goodreads.models.User;
import project.goodreads.services.UserService;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public String index(Authentication authentication) {

        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();

        model.addAttribute("username", user.getUsername());

        return "home";
    }

    @GetMapping("/search")
    public String searchForm() {

        return "search";
    }

    @GetMapping("/register")
    public String registerForm() {

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {

        try {
            userService.createUser(username, password);
        } catch (UserAlreadyExistException exception) {
            model.addAttribute("message", "Username is taken. Please choose a different one.");
            return "register";
        }

        model.addAttribute("message", "Account successfully created.");

        return "register";
    }
}
