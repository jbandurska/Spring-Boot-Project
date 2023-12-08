package project.goodreads.services;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
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

    public Rating getRating(Long id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rating not found"));

        return rating;
    }

    public Rating addRating(Double stars, Long bookId, Long userId) {
        var rating = new Rating();

        rating.setStars(stars);
        rating.setBookId(bookId);
        rating.setUserId(userId);

        ratingRepository.save(rating);

        return rating;
    }

    public Comment getComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        return comment;
    }

    public Comment addComment(String content, Long bookId, String username) {
        var comment = new Comment();

        comment.setContent(content);
        comment.setBookId(bookId);
        comment.setUsername(username);

        commentRepository.save(comment);

        return comment;
    }

}
