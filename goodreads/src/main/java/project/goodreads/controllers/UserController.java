package project.goodreads.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import project.goodreads.dto.UserDto;
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
    @PatchMapping("/edit")
    public void patchEdit(@RequestBody UserDto userDto) {

        userService.updateUser(userDto.getUsername(), userDto.getPassword());
    }
}
