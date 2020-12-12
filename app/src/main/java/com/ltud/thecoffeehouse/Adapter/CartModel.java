package com.ltud.thecoffeehouse.Adapter;

public class CartModel {
    String img_product;
    String name_product;
    String price;
    String des_product;
    String total;

    public CartModel(String img_product, String name_product, String price, String des_product, String total) {
        this.img_product = img_product;
        this.name_product = name_product;
        this.price = price;
        this.des_product = des_product;
        this.total = total;
    }

    public String getImg_product() {
        return img_product;
    }

    public void setImg_product(String img_product) {
        this.img_product = img_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDes_product() {
        return des_product;
    }

    public void setDes_product(String des_product) {
        this.des_product = des_product;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
