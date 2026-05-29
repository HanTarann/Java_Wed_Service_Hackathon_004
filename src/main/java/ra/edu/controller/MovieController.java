package ra.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.dto.request.MovieDTO;
import ra.edu.model.dto.response.ApiDataResponse;
import ra.edu.model.entity.Movie;
import ra.edu.service.MovieService;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @Value("${pageSize}")
    private Integer pageSize;

    @GetMapping
    public ResponseEntity<ApiDataResponse<Page<Movie>>> getAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(
                new ApiDataResponse<>(
                        true,
                        "Lấy danh sách movie thành công",
                        movieService.getAllMovies(keyword, page - 1, pageSize),
                        null,
                        HttpStatus.OK
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Movie>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(
                new ApiDataResponse<>(
                        true,
                        "Lấy movie thành công",
                        movieService.getMovieById(id),
                        null,
                        HttpStatus.OK
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Movie>> create(@Valid @RequestBody MovieDTO request) {
        return new ResponseEntity<>(
                new ApiDataResponse<>(
                        true,
                        "Tạo movie thành công",
                        movieService.createMovie(request),
                        null,
                        HttpStatus.CREATED
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Movie>> update(@PathVariable Integer id, @Valid @RequestBody MovieDTO request) {
        return ResponseEntity.ok(
                new ApiDataResponse<>(
                        true,
                        "Cập nhật toàn bộ movie thành công",
                        movieService.updateMovie(id, request),
                        null,
                        HttpStatus.OK
                )
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Movie>> patch(@PathVariable Integer id, @RequestBody MovieDTO request) {
        return ResponseEntity.ok(
                new ApiDataResponse<>(
                        true,
                        "Cập nhật một phần movie thành công",
                        movieService.patchMovie(id, request),
                        null,
                        HttpStatus.OK
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Boolean>> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(
                new ApiDataResponse<>(
                        true,
                        "Xóa movie thành công",
                        movieService.deleteMovie(id),
                        null,
                        HttpStatus.OK
                )
        );
    }
}