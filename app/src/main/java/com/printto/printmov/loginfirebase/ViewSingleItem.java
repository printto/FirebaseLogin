package com.printto.printmov.loginfirebase;

public class ViewSingleItem {

    private String Image_URL, Image_Title;

    public ViewSingleItem(String image_url, String image_title) {
        Image_URL = image_url;
        Image_Title = image_title;
    }

    public ViewSingleItem() {

    }

    public String getImage_URL() {
        return Image_URL;
    }

    public void setImage_URL(String image_URL) {
        Image_URL = image_URL;
    }

    public String getImage_Title() {
        return Image_Title;
    }

    public void setImage_Title(String image_Title) {
        Image_Title = image_Title;
    }
}