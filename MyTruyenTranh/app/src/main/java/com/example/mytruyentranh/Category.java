package com.example.mytruyentranh;

public class Category {
    private String category;
    private boolean active;

    public Category(String category, boolean active) {
        this.category = category;
        this.active = active;
    }
    public Category(String category) {
        this.category = category;
        this.active = false;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
