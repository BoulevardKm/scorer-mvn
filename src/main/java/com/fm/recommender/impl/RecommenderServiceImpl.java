package com.fm.recommender.impl;

import com.fm.recommender.RecommenderService;
import com.fm.recommender.Scorer;

import java.util.ArrayList;
import java.util.List;

public class RecommenderServiceImpl<Movie, User> implements RecommenderService<Movie, User> {
    private Scorer<Movie, User> scorer;
    private List<Movie> movies = new ArrayList<>();
    RecommenderServiceImpl(Scorer<Movie, User> scorer) {this.scorer = scorer;}

    @Override
    public List<Movie> getTop(User user, int limit) {

        List<Movie> allMovies = movies;
        allMovies.sort((m1, m2) -> Double.compare(scorer.getScore(m2, user), scorer.getScore(m1, user)));
        return allMovies.subList(0, Math.min(limit, allMovies.size()));
    }

    @Override
    public void addMovie(Movie movie) {
        movies.add(movie);
    }
}
