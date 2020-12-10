package com.ltud.thecoffeehouse.model;

public class Notification {
    private int image;
    private String title;
    private String shortContent;
    private String outDate;

    public Notification(int image, String title, String shortContent, String outDate) {
        this.image = image;
        this.title = title;
        this.shortContent = shortContent;
        this.outDate = outDate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }
}
