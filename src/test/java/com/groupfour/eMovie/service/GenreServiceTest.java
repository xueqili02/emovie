package com.groupfour.eMovie.service;

import com.groupfour.eMovie.dao.GenreDao;
import com.groupfour.eMovie.entity.Genre;
import com.groupfour.eMovie.service.impl.GenreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class GenreServiceTest {
    @Autowired
    private GenreDao genreDao;

    @Mock
    private Genre genre;

    @Autowired
    private GenreServiceImpl genreService;

    @BeforeEach()
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void callGetGenres() {
        assertEquals(genreService.getGenres(), genreDao.getGenres());
    }

    @Test
    public void getGenreById(){
        int[] ids = {5, 106};
        for (int i : ids) {
            genre = genreDao.getGenreById(i);
            assertEquals(genre, genreService.getGenreById(i));
        }
    }

    @Test
    public void callInsertGenre() {
        int id = 1;
        String[] genres = {"18x", "NSFW"};
        for (String g : genres) {
            Genre genre = new Genre(id, g);
            Genre newGenre = new Genre(id, g);
            id++;
            genreService.insertGenre(genre);
            newGenre.setId(genre.getId());
            assertEquals(newGenre, genre);
        }
    }
}
