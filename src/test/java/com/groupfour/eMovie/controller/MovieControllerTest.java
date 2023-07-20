package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.service.MovieService;
import com.groupfour.eMovie.utils.Result;
import com.groupfour.eMovie.utils.lucene.Indexer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MovieService movieService;

    @Mock
    private Indexer indexer;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void getMovieById() throws Exception {
        RequestBuilder request;
        request = get("/movies/id/{id}", 862);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getMovies() throws Exception {
        RequestBuilder request;
        request = get("/movies/page/{pageNum}", 1);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getMovieByFuzzyQuery() throws Exception {
        RequestBuilder request;
        request = get("/movies/fuzzy/{originalTitle}", "Minion");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());


        mockMvc.perform(get("/movies/fuzzy/{originalTitle}", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void insertMovie() throws Exception {
        String requestBody = "{\"budget\": \"30000000\", \"originalLanguage\": \"en\"," +
                "\"originalTitle\": \"Toy \", \"popularity\": \"21.946943\", \"releaseDate\": \"1995/12/15\", " +
                "\"revenue\": \"373554033\", \"runtime\": \"81\", \"title\": \"Toy Story\", " +
                "\"voteAverage\": \"7.7\", \"voteCount\": \"5415\", \"overview\": \"abcdefg\"}";

        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        String requestBody1 = "{\"budget\": \"30000000\", \"originalLanguage\": \"en\"," +
                "\"originalTitle\": \"\", \"popularity\": \"21.946943\", \"releaseDate\": \"1995/12/15\", " +
                "\"revenue\": \"373554033\", \"runtime\": \"81\", \"title\": \"Toy Story\", " +
                "\"voteAverage\": \"7.7\", \"voteCount\": \"5415\", \"overview\": \"abcdefg\"}";

        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody1))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void getMovieByPopularityOrdered() throws Exception {
        RequestBuilder request;
        request = get("/movies/popularity/page/{pageNum}", 1);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void getMovieByGenreId() throws Exception {
        RequestBuilder request;
        request = get("/movies/genreid/{genreid}/page/{pageNum}", 14,1);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }
    @Test
    public void getMovieLink() throws Exception {
        RequestBuilder request;
        request = get("/movies/link/{id}", 842);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getMovieByOriginalTitle() throws Exception {
        RequestBuilder request;
        request = get("/movies/originaltitle/{originalTitle}", "Min");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

}