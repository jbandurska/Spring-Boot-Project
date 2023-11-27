package project.goodreads.repositories;

import org.springframework.data.repository.CrudRepository;

import project.goodreads.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
