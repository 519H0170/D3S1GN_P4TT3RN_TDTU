package com.example.mytruyentranh;

public class Chap implements Entity{
    int noChap;

    public Chap(int ChapNo){
        this.noChap = ChapNo;
    }

    @Override
    public String getChap(){
        return "chap " +noChap;
    }
}
