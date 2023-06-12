package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.entity.MovieGenre;
import com.groupfour.eMovie.service.MovieGenreService;
import com.groupfour.eMovie.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/moviegenre")
@Tag(name = "电影-类别API")
public class MovieGenreController {

    @Autowired
    private MovieGenreService movieGenreService;

//    @GetMapping("/{movieid}")
//    @Operation(summary = "通过movieid获取电影类别")
//    @Parameter(description = "movie id")
//    public Result getMovieGenreByMovieId(@PathVariable int movieid) {
//        HttpStatus code = HttpStatus.OK;
//        String message = "";
//
//        List<MovieGenre> movieGenreList = movieGenreService.getMovieGenreByMovieId(movieid);
//        message = "success";
//
//        return new Result(code.value(), message, movieGenreList);
//    }
}
