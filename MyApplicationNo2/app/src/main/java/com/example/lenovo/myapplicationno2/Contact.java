package com.example.lenovo.myapplicationno2;

public class Contact {
    private int img;
    private String title;
    Contact(int img ,String title){
        this.title=title;
        this.img=img;
    }
    public int getImg()
    {
        return this.img;
    }
    public void setImg(int img){
        this.img=img;
    }
    public String getTitle()
    {
        return this.title;
    }
    public void setTitle(String title){
        this.title=title;
    }
}
