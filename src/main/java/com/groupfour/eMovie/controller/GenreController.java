package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.entity.Genre;
import com.groupfour.eMovie.service.GenreService;
import com.groupfour.eMovie.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    // todo complete the method
    @PostMapping("")
    @Operation(summary = "")
    public Result insertGenre(@RequestBody Genre genre) {
        HttpStatus code = HttpStatus.OK;
        String message = "success";

        // todo insert a new genre, 调用genreService中的方法（需要新写）
        // 逻辑：判断genre的属性是否为空，空则返回错误信息，不为空则调用genreService的方法

        return new Result(code.value(), message, null);
    }
}
