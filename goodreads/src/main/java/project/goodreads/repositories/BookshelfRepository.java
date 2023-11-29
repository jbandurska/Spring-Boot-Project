package project.goodreads.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.goodreads.models.Bookshelf;

public interface BookshelfRepository extends JpaRepository<Bookshelf, Long> {

}
