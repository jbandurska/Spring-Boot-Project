package project.goodreads.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.goodreads.models.Bookshelf;

public interface BookshelfRepository extends JpaRepository<Bookshelf, Long> {

    @Query("SELECT b FROM Bookshelf b WHERE b.userId = :userId")
    Set<Bookshelf> findAllBookshelvesByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(bs) FROM Bookshelf bs JOIN bs.books b WHERE bs.name = :name AND b.id = :bookId")
    public Long countBookshelves(@Param("name") String name, @Param("bookId") Long bookId);

    @Query("SELECT COUNT(b) FROM Bookshelf bs JOIN bs.books b WHERE bs.name = :name AND bs.userId = :userId")
    public Long countBooksOnBookshelf(@Param("name") String name, @Param("userId") Long userId);

}
