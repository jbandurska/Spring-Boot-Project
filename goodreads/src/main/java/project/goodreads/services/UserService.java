package project.goodreads.services;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import project.goodreads.models.Book;
import project.goodreads.models.User;
import project.goodreads.repositories.BookRepository;
import project.goodreads.repositories.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;
    final private BookRepository bookRepository;

    public void addBookToUserShelf(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " +
                        userId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " +
                        bookId));

        user.getBookShelf().add(book);
        userRepository.save(user);
    }
}
