package project.goodreads.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import project.goodreads.models.User;
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

    @ResponseBody
    @PatchMapping
    public void patchUser(@RequestBody User user) {

        userService.updateUser(user.getUsername(), user.getPassword());
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
    }
}
