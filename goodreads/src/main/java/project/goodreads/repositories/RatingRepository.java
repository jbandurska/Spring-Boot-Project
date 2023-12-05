package project.goodreads.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.goodreads.models.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("SELECT ROUND(AVG(r.stars), 2) FROM Rating r WHERE r.bookId = :bookId")
    Double getAverageRatingByBookId(@Param("bookId") Long bookId);

}
