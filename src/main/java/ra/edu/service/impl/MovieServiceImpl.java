package ra.edu.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import ra.edu.exception.NotFoundException;
import ra.edu.model.dto.request.MovieDTO;
import ra.edu.model.entity.Movie;
import ra.edu.repository.MovieRepository;
import ra.edu.service.MovieService;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public Page<Movie> getAllMovies(String keyword, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        if (keyword == null || keyword.isBlank()) {
            return movieRepository.findByIsDeleteFalse(pageable);
        }

        return movieRepository.findByIsDeleteFalseAndTitleContainingIgnoreCaseOrDirectorContainingIgnoreCase(keyword, keyword, pageable);
    }

    @Override
    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id).orElseThrow(() ->
                        new NotFoundException("Không tồn tại movie id: " + id));
    }

    @Override
    public Movie createMovie(MovieDTO request) {
        Movie movie = Movie.builder()
                .title(request.getTitle())
                .director(request.getDirector())
                .durationMinutes(request.getDurationMinutes())
                .genre(request.getGenre())
                .isDelete(false)
                .build();

        Movie saved = movieRepository.save(movie);
        log.info("CREATE movie: {}", saved.getTitle());
        return saved;
    }

    @Override
    public Movie updateMovie(Integer id, MovieDTO request) {
        Movie movie = movieRepository.findById(id).orElseThrow(() ->
                        new NotFoundException("Không tồn tại movie id: " + id));

        movie.setTitle(request.getTitle());
        movie.setDirector(request.getDirector());
        movie.setDurationMinutes(request.getDurationMinutes());
        movie.setGenre(request.getGenre());

        Movie updated = movieRepository.save(movie);
        log.info("PUT movie: {}", updated.getTitle());
        return updated;
    }

    @Override
    public Movie patchMovie(Integer id, MovieDTO request) {
        Movie movie = movieRepository.findById(id).orElseThrow(() ->
                        new NotFoundException("Không tồn tại movie id: " + id));

        if (request.getTitle() != null) {
            movie.setTitle(request.getTitle());
        }

        if (request.getDirector() != null) {
            movie.setDirector(request.getDirector());
        }

        if (request.getDurationMinutes() != null) {
            movie.setDurationMinutes(request.getDurationMinutes());
        }

        if (request.getGenre() != null) {
            movie.setGenre(request.getGenre());
        }

        Movie updated = movieRepository.save(movie);
        log.info("PATCH movie: {}", updated.getTitle());
        return updated;
    }

    @Override
    public Boolean deleteMovie(Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() ->
                        new NotFoundException("Không tồn tại movie id: " + id));

        movie.setIsDelete(true);
        movieRepository.save(movie);

        log.info("DELETE movie: {}", movie.getTitle());
        return true;
    }
}