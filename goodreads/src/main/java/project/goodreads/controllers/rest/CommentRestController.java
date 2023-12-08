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
import project.goodreads.dto.CommentDto;
import project.goodreads.dto.CommentWithIdDto;
import project.goodreads.repositories.CommentRepository;
import project.goodreads.services.ReviewService;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Transactional
public class CommentRestController {

    private final CommentRepository commentRepository;
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<CommentWithIdDto> createComment(@Valid @RequestBody CommentDto commentDto) {

        var comment = reviewService.addComment(commentDto.getContent(), commentDto.getBookId(),
                commentDto.getUsername());

        var response = new CommentWithIdDto();
        BeanUtils.copyProperties(comment, response);

        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentWithIdDto> updateComment(@PathVariable Long id,
            @Valid @RequestBody CommentDto commentDto) {

        var comment = reviewService.getComment(id);
        comment.setContent(commentDto.getContent());
        comment.setBookId(commentDto.getBookId());
        comment.setUsername(commentDto.getUsername());

        var response = new CommentWithIdDto();
        BeanUtils.copyProperties(comment, response);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {

        commentRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
