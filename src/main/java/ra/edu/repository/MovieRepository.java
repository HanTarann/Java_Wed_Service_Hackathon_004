package ra.edu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.model.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Page<Movie> findByIsDeleteFalse(Pageable pageable);
    Page<Movie> findByIsDeleteFalseAndTitleContainingIgnoreCaseOrDirectorContainingIgnoreCase(String title, String director, Pageable pageable);
}