package project.goodreads.services;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import project.goodreads.models.Comment;
import project.goodreads.models.Rating;
import project.goodreads.repositories.CommentRepository;
import project.goodreads.repositories.RatingRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final RatingRepository ratingRepository;
    private final CommentRepository commentRepository;

    public void addRating(Double stars, Long bookId, Long userId) {
        var rating = new Rating();

        rating.setStars(stars);
        rating.setBookId(bookId);
        rating.setUserId(userId);

        ratingRepository.save(rating);
    }

    public void addComment(String content, Long bookId, String username) {
        var comment = new Comment();

        comment.setContent(content);
        comment.setBookId(bookId);
        comment.setUsername(username);

        commentRepository.save(comment);
    }

}
