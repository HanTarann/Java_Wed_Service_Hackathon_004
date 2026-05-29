package ra.edu.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ra.edu.model.enums.GenreStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieDTO {
    @NotBlank(message = "title không được để trống")
    private String title;

    @NotBlank(message = "director không được để trống")
    private String director;

    @NotNull(message = "duration không được để trống")
    @Min(value = 1, message = "Thời lượng phải > 0")
    private Integer durationMinutes;

    @NotNull(message = "genre không được để trống")
    private GenreStatus genre;
}