package project.goodreads.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import project.goodreads.services.UserService;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    @GetMapping("/edit")
    public String getEdit() {

        return "user-edit.html";
    }

    @PostMapping("/edit")
    public String postEdit(@RequestParam(required = false) String username,
            @RequestParam(required = false) String password) {

        userService.updateUser(username, password);

        return "redirect:/home";
    }
}
