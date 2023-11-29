package project.goodreads.services;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import project.goodreads.models.User;
import project.goodreads.repositories.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;

    public void updateUser(String username, String password) {

        User user = userRepository.findById(Long.valueOf(1)).get();

        if (username != null)
            user.setUsername(username);
        if (password != null)
            user.setPassword(password);

        userRepository.save(user);
    }

    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }
}
