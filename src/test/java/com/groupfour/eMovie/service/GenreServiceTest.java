package com.groupfour.eMovie.service;

import com.groupfour.eMovie.dao.GenreDao;
import com.groupfour.eMovie.entity.Genre;
import com.groupfour.eMovie.service.impl.GenreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GenreServiceTest {
    @Mock
    private GenreDao genreDao;

    @Mock
    private Genre genre;

    @InjectMocks
    private GenreServiceImpl genreService;

    @BeforeEach()
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
}
