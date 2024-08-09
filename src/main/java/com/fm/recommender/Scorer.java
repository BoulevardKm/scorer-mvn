package com.fm.recommender;

public interface Scorer<Movie, User> {
    double getScore(Movie movie, User user);
}
