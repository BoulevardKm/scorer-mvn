package com.fm.recommender.impl;

public class Movie {
    private final String id;
    private final String title;
    private final String snippet;
    private final double [] embedding;

    public Movie(String id, String name, String snippet, double[] embedding) {
        this.id = id;
        this.title = name;
        this.snippet = snippet;
        this.embedding = embedding;
    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double[] getEmbedding() {
        return embedding;
    }

    public String getSnippet() {
        return snippet;
    }
}
