package com.groupfour.eMovie.service.impl;

import com.groupfour.eMovie.dao.GenreDao;
import com.groupfour.eMovie.entity.Genre;
import com.groupfour.eMovie.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service("GenreServiceImpl")
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreDao genreDao;

    public List<Genre> getGenres() {
        return genreDao.getGenres();
    }

    public Genre getGenreById(int genreid) {
        return genreDao.getGenreById(genreid);
    }

    public Genre insertGenre(Genre genre){
        genreDao.insertGenre(genre);
        int id = genre.getId();
        genre.setId(id);
        return genre;
    }
}
