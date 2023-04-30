package com.example.mytruyentranh;

import java.util.ArrayList;

public class TruyenTranh {
    private String name, author, detail, rank;
    private int chap;
    private ArrayList<String> Category;
    private ArrayList Chapter;

    public TruyenTranh(String name, String author, String detail) {
        this.name = name;
        this.author = author;
        this.detail = detail;
        this.rank = null;
        this.chap = 0;
        this.Category = new ArrayList<String>();
        this.Chapter =  new ArrayList();
    }

    public TruyenTranh(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public ArrayList getCategory() {
        return Category;
    }

    public void setCategory(ArrayList<Category> category, int n){
        Category.add(category.get(n).getCategory());
    }

    public ArrayList getChapter() {
        return Chapter;
    }

    public void setChapter() {
        Chapter.add("chap " + this.chap);
    }


    public int getChap() {
        return chap;
    }

    public void newChap() {
        this.chap = chap + 1;
    }

    public void setChap(int chap) {
        this.chap = chap;
    }
}
