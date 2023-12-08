package project.goodreads.controllers.rest;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import project.goodreads.dto.UserDto;
import project.goodreads.dto.UserWithIdDto;
import project.goodreads.services.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Transactional
public class UserRestController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserWithIdDto> createUser(@Valid @RequestBody UserDto userDto) {

        var user = userService.createUser(userDto.getUsername(), userDto.getPassword());

        var response = new UserWithIdDto();
        BeanUtils.copyProperties(user, response);

        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserWithIdDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {

        var user = userService.updateUser(userService.getUser(id), userDto.getUsername(), userDto.getPassword(),
                userDto.getRole());

        var response = new UserWithIdDto();
        BeanUtils.copyProperties(user, response);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.deleteUserById(id);

        return ResponseEntity.noContent().build();
    }
}
