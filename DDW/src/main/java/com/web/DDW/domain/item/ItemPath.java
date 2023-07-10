package com.web.DDW.domain.item;

public enum ItemPath {
    IMGPATH("/img/");

    private final String path;

    ItemPath(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}
