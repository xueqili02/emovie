package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.entity.Genre;
import com.groupfour.eMovie.service.GenreService;
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
@RequestMapping("/genres")
@Tag(name = "类别API")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("")
    @Operation(summary = "获取全部genre")
    public Result getGenres() {
        HttpStatus code = HttpStatus.OK;
        String message = "success";

        return new Result(code.value(), message, genreService.getGenres());
    }

    @GetMapping("/id/{genreid}")
    @Operation(summary = "根据genre id获取genre")
    @Parameter(description = "genre id")
    public Result getGenreById(@PathVariable int genreid) {
        HttpStatus code = HttpStatus.OK;
        String message = "success";

        return new Result(code.value(), message, genreService.getGenreById(genreid));
    }
}
