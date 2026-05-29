package ra.edu.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ra.edu.model.enums.GenreStatus;

@Entity
@Table(name = "movie")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String director;
    private Integer durationMinutes;
    @Enumerated(EnumType.STRING)
    private GenreStatus genre;
    private Boolean isDelete;
}