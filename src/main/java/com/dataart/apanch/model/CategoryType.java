package com.dataart.apanch.model;

public enum CategoryType {

    GAMES("GAMES"),
    EDUCATION("EDUCATION"),
    HEALTH("HEALTH");

    private final String categoryType;

    CategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryType() {
        return categoryType;
    }

}
