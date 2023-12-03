package project.goodreads.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import project.goodreads.enums.Role;
import project.goodreads.exceptions.UserAlreadyExistException;
import project.goodreads.models.User;
import project.goodreads.repositories.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;
    final private PasswordEncoder passwordEncoder;

    public void createUser(String username, String password) {

        if (usernameExist(username)) {
            throw new UserAlreadyExistException("User with this username already exists: " + username);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);

        userRepository.save(user);
    }

    private boolean usernameExist(String username) {

        return userRepository.findByUsername(username).isPresent();
    }

    public void updateUser(User user, String username, String password) {

        if (username != null && !username.isEmpty())
            user.setUsername(username);
        if (password != null && !password.isEmpty())
            user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
    }

    public void deleteUser(User user) {

        userRepository.delete(user);
    }
}
