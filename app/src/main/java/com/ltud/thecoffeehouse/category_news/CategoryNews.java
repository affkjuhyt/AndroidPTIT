package com.ltud.thecoffeehouse.category_news;

import com.ltud.thecoffeehouse.news.News;

import java.util.List;

public class CategoryNews<news> {
    private String nameCategory;
    private List<News> books;

    public CategoryNews(String nameCategory, List<News> books) {
        this.nameCategory = nameCategory;
        this.books = books;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<News> getBooks() {
        return books;
    }

    public void setBooks(List<News> books) {
        this.books = books;
    }
}
