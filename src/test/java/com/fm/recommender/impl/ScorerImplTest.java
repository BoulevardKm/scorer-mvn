package com.fm.recommender.impl;

import com.fm.recommender.Scorer;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ScorerImplTest {
    @Test
    void testScorer() {
        Movie movie = new Movie(
                UUID.randomUUID().toString(),
                "Twilight",
                "When Bella Swan moves to a small town in the Pacific Northwest, she falls in love with Edward Cullen, a mysterious classmate who reveals himself to be a 108-year-old vampire.",
                new double[] {1., 2.}
        );
        User user = new User(
                UUID.randomUUID().toString(),
                "Maxim",
                new double[] {2., 3.}
        );
        final Scorer<Movie, User> scorer = new ScorerImpl();
        double score = scorer.getScore(movie, user);
        assertEquals(8., score, 1e-5);
    }
}