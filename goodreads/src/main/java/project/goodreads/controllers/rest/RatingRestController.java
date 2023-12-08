package project.goodreads.controllers.rest;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import project.goodreads.dto.RatingDto;
import project.goodreads.dto.RatingWithIdDto;
import project.goodreads.repositories.RatingRepository;
import project.goodreads.services.ReviewService;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
@Transactional
public class RatingRestController {

    private final RatingRepository ratingRepository;
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<RatingWithIdDto> createRating(@Valid @RequestBody RatingDto ratingDto) {

        var rating = reviewService.addRating(ratingDto.getStars(), ratingDto.getBookId(), ratingDto.getUserId());

        var response = new RatingWithIdDto();
        BeanUtils.copyProperties(rating, response);

        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingWithIdDto> updateRating(@PathVariable Long id,
            @Valid @RequestBody RatingDto ratingDto) {

        var rating = reviewService.getRating(id);
        rating.setStars(ratingDto.getStars());
        rating.setBookId(ratingDto.getBookId());
        rating.setUserId(ratingDto.getUserId());

        var response = new RatingWithIdDto();
        BeanUtils.copyProperties(rating, response);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {

        ratingRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
