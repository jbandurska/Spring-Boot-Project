package project.goodreads.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingWithIdDto {

    @NotNull
    private Long id;

    @NotNull
    @Min(1)
    @Max(5)
    private Double stars;

    @NotNull
    private Long bookId;

    @NotNull
    private Long userId;

}
