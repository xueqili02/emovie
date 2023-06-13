package com.groupfour.eMovie.service;

import com.groupfour.eMovie.dao.MovieDao;
import com.groupfour.eMovie.entity.Movie;
import com.groupfour.eMovie.service.impl.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MovieServiceTest {
    @Mock
    private MovieDao movieDao;

    @Mock
    private Movie movie;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach()
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
}
