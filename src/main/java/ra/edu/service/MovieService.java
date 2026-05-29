package ra.edu.service;

import org.springframework.data.domain.Page;
import ra.edu.model.dto.request.MovieDTO;
import ra.edu.model.entity.Movie;

public interface MovieService {
    Page<Movie> getAllMovies(String keyword, Integer page, Integer pageSize);
    Movie getMovieById(Integer id);
    Movie createMovie(MovieDTO request);
    Movie updateMovie(Integer id, MovieDTO request);
    Movie patchMovie(Integer id, MovieDTO request);
    Boolean deleteMovie(Integer id);
}