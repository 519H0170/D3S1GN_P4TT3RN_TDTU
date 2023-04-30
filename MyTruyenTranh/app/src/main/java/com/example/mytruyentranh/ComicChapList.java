package com.example.mytruyentranh;

import java.util.ArrayList;
import java.util.List;

public class ComicChapList implements Entity{
    List<Entity> chapList = new ArrayList<Entity>();

    public ComicChapList(){}

    public void addChap(Entity chap){
        chapList.add(chap);
    }

    @Override
    public String getChap() {
        List<String> stringList = new ArrayList<String>();
        int i=0;
        for(Entity chap:chapList){
            stringList.add(chap.getChap());
        }
        return String.join(",", stringList.toArray(new String[0]));
    }
}
