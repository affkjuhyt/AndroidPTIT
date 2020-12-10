package category_news;

import java.util.List;
import news.News;

public class CategoryNews {
    private String nameCategory;
    private List<News> news;

    public CategoryNews(String nameCategory, List<News> books) {
        this.nameCategory = nameCategory;
        this.news = news;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<News> getBooks() {
        return news;
    }

    public void setBooks(List<News> books) {
        this.news = books;
    }
}
