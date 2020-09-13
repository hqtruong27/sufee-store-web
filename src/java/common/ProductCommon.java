package common;

import entity.Customers;
import entity.Products;
import entity.Wishlists;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Hoang Truong
 */
public class ProductCommon {

    private Products product;

    private double productSalePrice;

    private double newPrice;

    private String productStarString;

    private boolean isNewProduct;

    private List<Wishlists> wishlists;

    private boolean isWishlist;

    private String productStatusString;

    private String priceString;
    
    private String priceDetail;

    public ProductCommon() {
    }

    public ProductCommon(Products product, Customers customer, List<Wishlists> wishlists) {
        this.product = product;

        if (product.getProductSale() > 0) {
            this.productSalePrice = product.getPrice() * product.getProductSale() / 100;
        } else {
            this.productSalePrice = 0;
        }

        this.newPrice = product.getPrice() - this.productSalePrice;
        this.wishlists = wishlists;

        if (customer != null) {
            for (Wishlists wishlist : this.wishlists) {
                if (Objects.equals(wishlist.getProducts().getProductId(), product.getProductId())) {
                    this.isWishlist = true;
                }
            }
//            this.wishlists.stream().filter((p) -> (Objects.equals(p.getProducts().getProductId(), product.getProductId()))).forEachOrdered((_item) -> {
//                this.isWishlist = true;
//            });
        }

        if (product.getStarAvg() == 5) {
            this.productStarString = "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star'></i>";
        } else if (product.getStarAvg() < 5 && product.getStarAvg() > 4) {
            this.productStarString = "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star-half-o'></i>";
        } else if (product.getStarAvg() == 4) {
            this.productStarString = "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star-o'></i>";
        } else if (product.getStarAvg() < 4 && product.getStarAvg() > 3) {
            this.productStarString = "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star-half-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>";
        } else if (product.getStarAvg() == 3) {
            this.productStarString = "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>";
        } else if (product.getStarAvg() < 3 && product.getStarAvg() > 2) {
            this.productStarString = "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star-half-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>";
        } else if (product.getStarAvg() == 2) {
            this.productStarString = "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>";
        } else if (product.getStarAvg() < 2 && product.getStarAvg() > 1) {
            this.productStarString = "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star-half-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>";
        } else if (product.getStarAvg() == 1) {
            this.productStarString = "<i class='fa fa-star'></i>\n"
                    + "<i class='fa fa-star-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>";
        } else if (product.getStarAvg() < 1 && product.getStarAvg() > 0) {
            this.productStarString = "<i class='fa fa-star-half-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>";
        } else if (product.getStarAvg() == 0) {
            this.productStarString = "<i class='fa fa-star-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>\n"
                    + "<i class='fa fa-star-o'></i>";
        }

        switch (product.getProductStatus()) {
            case 1:
                this.productStatusString = "Còn hàng";
                break;
            case 2:
                this.productStatusString = "Hết hàng";
                break;
            default:
                this.productStatusString = "Ngừng kinh doanh";
                break;
        }

        if (productSalePrice > 0) {
            this.priceString = "<div class='proflex'> <div><div>Chỉ còn</div><div class='textflex'>" + String.format("%,.0f", this.newPrice) + "₫</div></div> ";
            this.priceString += " <div style='text-align: right'><div><div>Giảm giá</div><div class='textflex'>" + String.format("%,.0f", this.productSalePrice) + "₫</div></div></div></div>";
        } else {
            this.priceString = String.format("%,.0f", this.product.getPrice()) + "₫ ";
        }
        
        if (productSalePrice > 0) {
            this.priceDetail = "<span>Giá: </span><span style='font-size:15px;font-weight:600;color:red'>" + String.format("%,.0f", this.newPrice) + "₫ </span>";
            this.priceDetail += " <p style='font-size:12px'><span>Đã giảm thêm </span><span  style='color:red'>" + String.format("%,.0f", this.productSalePrice) + "₫</span></p> ";
        } else {
            this.priceDetail = String.format("%,.0f", this.product.getPrice()) + "₫ ";
        }
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public double getProductSalePrice() {
        return productSalePrice;
    }

    public void setProductSalePrice(double productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public String getProductStarString() {
        return productStarString;
    }

    public void setProductStarString(String productStarString) {
        this.productStarString = productStarString;
    }

    public boolean isIsNewProduct() {
        return isNewProduct;
    }

    public void setIsNewProduct(boolean isNewProduct) {
        this.isNewProduct = isNewProduct;
    }

    public String getProductStatusString() {
        return productStatusString;
    }

    public void setProductStatusString(String productStatusString) {
        this.productStatusString = productStatusString;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public String getPriceString() {
        return priceString;
    }

    public void setPriceString(String priceString) {
        this.priceString = priceString;
    }

    public boolean isIsWishlist() {
        return isWishlist;
    }

    public void setIsWishlist(boolean isWishlist) {
        this.isWishlist = isWishlist;
    }

    public String getPriceDetail() {
        return priceDetail;
    }

    public void setPriceDetail(String priceDetail) {
        this.priceDetail = priceDetail;
    }
    
}
