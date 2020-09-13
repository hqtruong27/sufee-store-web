/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import entity.Products;

/**
 *
 * @author ASUS
 */
public class CartSingle {
    
    public Products products;
    
    public Integer productQuantity;

    public CartSingle() {
    }

    public CartSingle(Products products, Integer productQuantity) {
        this.products = products;
        this.productQuantity = productQuantity;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
    
    
}
