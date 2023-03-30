package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.entity.Movie;
import com.groupfour.eMovie.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
@Tag(name = "电影API")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/{pageNum}")
    @Operation(summary = "获取电影metadata, 实现分页")
    @Parameter(description = "页号")
    public ResponseEntity<Object> getMovies(@PathVariable int pageNum) {
        HttpStatus code = HttpStatus.OK;
        String message = "";
        Map<String, Object> map = new HashMap<>();

        List<Movie> movieList = movieService.getMovies(pageNum);
        message = "success";

        map.put("message", message);
        map.put("data", movieList);
        return new ResponseEntity<>(map, code);
    }
}
