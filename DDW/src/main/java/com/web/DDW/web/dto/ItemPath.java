package com.web.DDW.web.dto;

public enum ItemPath {
    IMGPATH("/img");

    private final String value;

    ItemPath(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
