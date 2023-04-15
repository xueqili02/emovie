package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.entity.Genre;
import com.groupfour.eMovie.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<Genre> getGenres() {
        return genreService.getGenres();
    }

}
