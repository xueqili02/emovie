package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.entity.Link;
import com.groupfour.eMovie.entity.Movie;
import com.groupfour.eMovie.service.MovieService;
import com.groupfour.eMovie.utils.Result;
import com.groupfour.eMovie.utils.lucene.Indexer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jsqlparser.statement.create.table.Index;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name = "电影API")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/page/{pageNum}")
    @Operation(summary = "获取电影metadata, 实现分页")
    @Parameter(description = "页号")
    public Result getMovies(@PathVariable int pageNum) {
        HttpStatus code = HttpStatus.OK;
        String message = "";

        List<Movie> movieList = movieService.getMovies(pageNum);
        message = "success";

        return new Result(code.value(), message, movieList);
    }

    @GetMapping("/originaltitle/{originalTitle}")
    @Operation(summary = "通过originalTitle获取电影信息")
    @Parameter(description = "电影的originalTitle")
    public Result getMovieByOriginalTitle(@PathVariable String originalTitle) {
        HttpStatus code = HttpStatus.OK;
        String message = "";

        List<Movie> movieList = new ArrayList<>();

//        Instant startTime = Instant.now();
        try {
            Indexer indexer = new Indexer();
            movieList = indexer.indexSearch(originalTitle, "src/main/java/com/groupfour/eMovie/utils/lucene/data", 2);
//            movieList = movieService.setMovieGenreAndKeyword(movieList);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Instant endTime = Instant.now();
//        System.out.println("Time cost for Lucene Indexing " + Duration.between(startTime, endTime));
//
//        startTime = Instant.now();
//        List<Movie> movieList2 = movieService.getMovieByOriginalTitle(originalTitle);
//        endTime = Instant.now();
//        System.out.println("Time cost for SQL query " + Duration.between(startTime, endTime));

        message = "success";

        return new Result(code.value(), message, movieList);
    }

    @GetMapping("/hotmovie")
    @Operation(summary = "获取热门电影")
    public Result getHotMovie() {
        HttpStatus code = HttpStatus.OK;
        String message = "";

        List<Movie> movieList = movieService.getHotMovie();
        message = "success";

        return new Result(code.value(), message, movieList);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "通过电影id获取电影信息")
    @Parameter(description = "电影id")
    public Result getMovieById(@PathVariable int id) {
        HttpStatus code = HttpStatus.OK;
        String message = "";

        Movie movie = movieService.getMovieById(id);
        message = "success";

        return new Result(code.value(), message, movie);
    }

    @PostMapping("")
    @Operation(summary = "插入电影metadata")
    public Result insertMovie(@Schema(example = "{\"budget\": \"30000000\", \"originalLanguage\": \"en\"," +
            "\"originalTitle\": \"Toy Story\", \"popularity\": \"21.946943\", \"releaseDate\": \"1995/12/15\", " +
            "\"revenue\": \"373554033\", \"runtime\": \"81\", \"title\": \"Toy Story\", " +
            "\"voteAverage\": \"7.7\", \"voteCount\": \"5415\", \"overview\": \"abcdefg\"}")
                              @RequestBody Movie movie) {
        HttpStatus code = HttpStatus.OK;
        String message = "";
        Movie newMovie = null;

        try {
            if (movie.getOriginalTitle() == null || movie.getOriginalTitle().equals("")) {
                code = HttpStatus.BAD_REQUEST;
                message = "Original title is null.";
                throw new Exception("Original title is null.");
            }

            newMovie = movieService.insertMovie(movie);
            message = "success";
            code = HttpStatus.CREATED;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return new Result(code.value(), message, newMovie);
        }
    }

    @GetMapping("/popularity/page/{pageNum}")
    @Operation(summary = "获取popularity排序后的电影，每页16部电影")
    public Result getMovieByPopularityOrdered(@PathVariable int pageNum) {
        HttpStatus code = HttpStatus.OK;
        String message = "";

        List<Movie> movieList = movieService.getMovieByPopularityOrdered(pageNum);
        message = "success";

        return new Result(code.value(), message, movieList);
    }

    @GetMapping("/genreid/{genreid}/page/{pageNum}")
    @Operation(summary = "通过genreid获取电影，同时实现分页")
    @Parameters({@Parameter(description = "genreid"),
                @Parameter(description = "pageNum")})
    public Result getMovieByGenreId(@PathVariable int genreid,
                                    @PathVariable int pageNum) {
        HttpStatus code = HttpStatus.OK;
        String message = "";

        List<Movie> movieList = movieService.getMovieByGenreId(genreid, pageNum);
        message = "success";

        return new Result(code.value(), message, movieList);
    }

    @GetMapping("/recommend/movieid/{id}")
    @Operation(summary = "通过电影id，获取对应的推荐电影，hybrid+overview")
    @Parameter(description = "movie id")
    public Result getMovieRecommendById(@PathVariable int id) {
        HttpStatus code = HttpStatus.OK;
        String message = "";

        List<Movie> movieList = movieService.getMovieRecommendById(id);
        message = "success";

        return new Result(code.value(), message, movieList);
    }

    @GetMapping("/link/{id}")
    @Operation(summary = "通过电影id，获取对应的链接")
    @Parameter(description = "电影id")
    public Result getMovieLink(@PathVariable int id) {
        HttpStatus code = HttpStatus.OK;
        String message = "";

        Link link = movieService.getMovieLink(id);
        message = "success";

        return new Result(code.value(), message, link);
    }
}
