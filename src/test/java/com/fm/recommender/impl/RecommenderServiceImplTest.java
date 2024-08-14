package com.fm.recommender.impl;

import com.fm.recommender.Scorer;
import com.fm.recommender.db.Db;
import com.fm.recommender.db.InMemDb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class RecommenderServiceImplTest {
    private RecommenderServiceImpl<Movie, User> recommenderService;
    private Scorer<Movie, User> scorer;
    private Db db;

    @BeforeEach
    void setUp() {
        scorer = new ScorerImpl();
        db = new InMemDb();
        recommenderService = new RecommenderServiceImpl<>(scorer, db);
    }
    @Test
    void testAddMovie() {
        final Scorer<Movie, User> scorer = new ScorerImpl();
        Movie movie1 = new Movie(
                UUID.randomUUID().toString(),
                "Twilight",
                "When Bella Swan moves to a small town in the Pacific Northwest, she falls in love with Edward Cullen, a mysterious classmate who reveals himself to be a 108-year-old vampire.",
                new double[] {1.1, 2.2});


        Movie movie2 = new Movie(
                UUID.randomUUID().toString(),
                "Inception",
                "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
                new double[] {8.8, 9.0});


        Movie movie3 = new Movie(
                UUID.randomUUID().toString(),
                "Interstellar",
                "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
                new double[] {9.2, 9.3});

        recommenderService.addMovie(movie1);
        recommenderService.addMovie(movie2);
        recommenderService.addMovie(movie3);

        User user = new User(
                UUID.randomUUID().toString(),
                "Maxim",
                new double[] {2., 3.});

        List<Movie> topMoviesLimit2 = recommenderService.getTop(user, 2);
        assertEquals(2, topMoviesLimit2.size());
        assertEquals("Interstellar", topMoviesLimit2.get(0).getTitle());
        assertEquals("Inception", topMoviesLimit2.get(1).getTitle());

        List<Movie> topMoviesLimit4 = recommenderService.getTop(user, 4);
        assertEquals(3, topMoviesLimit4.size());
        assertEquals("Interstellar", topMoviesLimit4.get(0).getTitle());
        assertEquals("Inception", topMoviesLimit4.get(1).getTitle());
        assertEquals("Twilight", topMoviesLimit4.get(2).getTitle());

    }


    @Test
    public void testNewRecommenderService() {

        recommenderService.addMovie(new Movie(
                UUID.randomUUID().toString(),
                "Interstellar",
                "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
                new double[] {9.2, 9.3}));

        Scorer<Movie, User> newScorer = new ScorerImpl();

        RecommenderServiceImpl<Movie, User> newRecommenderService = new RecommenderServiceImpl<>(newScorer, db);


        List<Movie> topMovies = newRecommenderService.getTop(new User(
                UUID.randomUUID().toString(),
                "Maxim",
                new double[] {2., 3.}), 1);

        assertEquals(1, topMovies.size());
        assertEquals("Interstellar", topMovies.get(0).getTitle());


        newRecommenderService.addMovie(new Movie(
                UUID.randomUUID().toString(),
                "Matrix",
                "Test",
                new double[] {10.0, 10.0}));

        List<Movie> topMovies1 = newRecommenderService.getTop(new User(
                UUID.randomUUID().toString(),
                "Maxim",
                new double[] {2., 3.}), 2);
        assertEquals(2, topMovies1.size());
        assertEquals("Matrix",  topMovies1.get(0).getTitle());
        assertEquals("Interstellar",  topMovies1.get(1).getTitle());


    }
}
