package com.fm.recommender.impl;

public class User {
    private final String id;
    private final String name;
    private final double [] embedding;

    public User(String id, String name, double[] embedding) {
        this.id = id;
        this.name = name;
        this.embedding = embedding;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double[] getEmbedding() {
        return embedding;
    }
}
