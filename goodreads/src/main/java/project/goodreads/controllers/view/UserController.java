package project.goodreads.controllers.view;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import project.goodreads.models.User;
import project.goodreads.services.UserService;

@Controller
@RequestMapping("/edit-profile")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getEdit() {

        return "user-edit.html";
    }

    @ResponseBody
    @PatchMapping
    public void patchUser(@RequestBody User userInfo, Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        userService.updateUser(user, userInfo.getUsername(), userInfo.getPassword());
    }

    @ResponseBody
    @DeleteMapping
    public void deleteUser(Authentication authentication, HttpServletRequest request) throws ServletException {

        User user = (User) authentication.getPrincipal();
        userService.deleteUser(user);

        request.logout();
    }
}
