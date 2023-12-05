package project.goodreads.services;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import project.goodreads.models.Rating;
import project.goodreads.repositories.RatingRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;

    public void addRating(Double stars, Long bookId, Long userId) {
        var rating = new Rating();

        rating.setStars(stars);
        rating.setBookId(bookId);
        rating.setUserId(userId);

        ratingRepository.save(rating);
    }

}
