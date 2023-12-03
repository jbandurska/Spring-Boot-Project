package project.goodreads.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.goodreads.models.Bookshelf;

public interface BookshelfRepository extends JpaRepository<Bookshelf, Long> {

    @Query("SELECT b FROM Bookshelf b WHERE b.userId = :userId")
    Set<Bookshelf> findAllBookshelvesByUserId(@Param("userId") Long userId);
}
