/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import entity.Products;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class ShoppingCart implements Serializable {

    public List<CartSingle> carts;

    public Double totalAmount = 0d;

    public Double lastAmount = 0d;

    public ShoppingCart() {
    }

    public ShoppingCart(List<CartSingle> carts, Double totalAmount, Double lastAmount) {
        this.carts = carts;
        this.totalAmount = totalAmount;
        this.lastAmount = lastAmount;
    }

    public List<CartSingle> getCarts() {
        return carts;
    }

    public void setCarts(List<CartSingle> carts) {
        this.carts = carts;
        updateTotalAmount();
        updateLastAmount();
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
        updateTotalAmount();
    }

    public Double getLastAmount() {
        return lastAmount;
    }

    public void setLastAmount(Double lastAmount) {
        this.lastAmount = lastAmount;
        updateTotalAmount();
        updateLastAmount();
    }

    public boolean insertCarts(Products products, Integer quantity) {
        boolean result = false;

        if (carts == null || carts.isEmpty()) {
            carts = new ArrayList<>();
            CartSingle cartSingle = new CartSingle(products, quantity);
            carts.add(cartSingle);
            result = true;
        } else {
            
            boolean isExists = false;

            for (CartSingle cart : carts) {
                if (Objects.equals(cart.products.getProductId(), products.getProductId())) {
                    cart.productQuantity += quantity;
                    result = true;
                    isExists = false;
                    break;
                } else {
                    isExists = true;
                }
            }

            if (isExists) {
                CartSingle cartSingle = new CartSingle(products, quantity);
                carts.add(cartSingle);
                result = true;

            }
        }
        updateTotalAmount();
        return result;
    }

    public boolean updateCarts(Products products, Integer quantity) {
        boolean result = false;

        for (CartSingle cart : carts) {
            if (Objects.equals(cart.products.getProductId(), products.getProductId())) {
                cart.productQuantity = quantity;
                result = true;
                break;
            }
        }
        updateTotalAmount();
        return result;
    }

    public boolean deleteOneOfCarts(Products products) {
        if (carts.size() > 0) {
            for (CartSingle cart : carts) {
                if (Objects.equals(cart.products.getProductId(), products.getProductId())) {
                    boolean check = carts.remove(cart);
                    updateTotalAmount();

                    if (carts.isEmpty()) {
                        carts = null;
                    }

                    return check;
                }
            }
        }
        updateTotalAmount();
        return false;
    }
    
    public boolean deleteAllCarts(){
        boolean result ;
        totalAmount = 0d;
        
        result = carts.removeAll(carts);
        return result;
    }
    
    private void updateTotalAmount() {
        this.totalAmount = 0d;
        
        if (carts.size() > 0) {
            carts.forEach((c) -> {
                if (c.products.getProductSale() > 0) {
                    this.totalAmount += c.products.getPrice() * (100 - c.products.getProductSale()) / 100 * c.productQuantity;
                }else{
                    this.totalAmount += c.products.getPrice() * c.productQuantity;
                }
            });
        }
    }

    private void updateLastAmount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
