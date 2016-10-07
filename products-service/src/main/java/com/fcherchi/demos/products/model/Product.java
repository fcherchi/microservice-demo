package com.fcherchi.demos.products.model;

/**
 * Created by Fernando Cherchi on 07/10/16.
 */
public class Product {

    private int productId;
    private String name;
    private String extra;

    public Product() {

    }

    public Product(int productId, String name) {
        this.productId = productId;
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getExtra() {
        return extra;
    }

}




