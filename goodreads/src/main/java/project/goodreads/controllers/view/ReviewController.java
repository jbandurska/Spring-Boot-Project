package project.goodreads.controllers.view;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import project.goodreads.models.User;
import project.goodreads.services.RatingService;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final RatingService ratingService;

    @PostMapping("/rating")
    public String addRating(@RequestParam Double stars, @RequestParam Long bookId, Authentication authentication,
            HttpServletRequest request) {

        ratingService.addRating(stars, bookId, ((User) authentication.getPrincipal()).getId());

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
