package project.goodreads.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import project.goodreads.models.Book;
import project.goodreads.models.Bookshelf;
import project.goodreads.repositories.BookRepository;
import project.goodreads.repositories.BookshelfRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class BookshelfService {

    final BookshelfRepository bookshelfRepository;
    final BookRepository bookRepository;

    public void createBookshelf(String name, Long userId) {
        Bookshelf bookshelf = new Bookshelf();
        bookshelf.setName(name);
        bookshelf.setUserId(userId);

        bookshelfRepository.save(bookshelf);
    }

    public void addBookToBookshelf(Long bookshelfId, Long bookId) {
        Bookshelf bookshelf = bookshelfRepository.findById(bookshelfId)
                .orElseThrow(() -> new EntityNotFoundException("Bookshelf with id " + bookshelfId + " not found."));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with id " + bookId + " not found."));

        bookshelf.getBooks().add(book);
        bookshelfRepository.save(bookshelf);
    }

    public Set<Bookshelf> getBookshelves(Long userId) {

        return bookshelfRepository.findAllBookshelvesByUserId(userId);
    }

}
