package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.entity.Genre;
import com.groupfour.eMovie.service.GenreService;
import com.groupfour.eMovie.utils.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GenreControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Mock
    private GenreService genreService;

    @InjectMocks
    private GenreController genreController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(genreController).build();
    }

    // write test at here
    @Test
    void getGenresTest() {
        List<Genre> someGenreList = new ArrayList<>();
        someGenreList.add(new Genre(1, "Action"));
        someGenreList.add(new Genre(2, "Comedy"));
        someGenreList.add(new Genre(3, "Drama"));

        when(genreService.getGenres()).thenReturn(someGenreList);

        Result result = genreController.getGenres();

        assertEquals(HttpStatus.OK.value(), result.getCode());
        assertEquals("success", result.getMessage());
        assertEquals(someGenreList, result.getData());

        verify(genreService, times(1)).getGenres();
    }

    @Test
    void getGenreByIdTest() {

        int genreId = 1;
        Genre genre = new Genre(genreId,"Action");

        genre.setId(genreId);

        when(genreService.getGenreById(genreId)).thenReturn(genre);

        Result result = genreController.getGenreById(genreId);

        assertEquals(HttpStatus.OK.value(), result.getCode());
        assertEquals("success", result.getMessage());
        assertEquals(genre, result.getData());

        verify(genreService, times(1)).getGenreById(genreId);
    }

    @Test
    void insertGenreTest() throws Exception{
        int genreId = 1;
        Genre genre = new Genre(genreId,"Action");
        genre.setGenre("Adventure");

        int genreId2 = 2;
        Genre genre2 = new Genre(genreId2,"Action");
        genre2.setGenre(null);

        when(genreService.insertGenre(genre)).thenReturn(genre);

        Result result = genreController.insertGenre(genre);

        assertEquals(HttpStatus.CREATED.value(), result.getCode());
        assertEquals("success", result.getMessage());
        assertEquals(genre, result.getData());

        Result result2 = genreController.insertGenre(genre2);
        HttpStatus expectedCode = HttpStatus.BAD_REQUEST;

        assertEquals(expectedCode, HttpStatus.valueOf(result2.getCode()));
        assertEquals("Genre is null.", result2.getMessage());

    }
}
