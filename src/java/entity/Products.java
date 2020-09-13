package entity;
// Generated Aug 1, 2020 2:29:25 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Products generated by hbm2java
 */
@Entity
@Table(name = "Products")
public class Products implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ProductId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    
    @Column(name = "BrandId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "BrandId")
    @Transient
    private Brands brands;

    @Column(name = "CategoryId")
    @ManyToOne(optional = false)
    @Transient
    @JoinColumn(name = "CategoryId")
    private Categories categories;

    @Column(name = "ProductName")
    private String productName;
    @Column(name = "ProductCode")
    private String productCode;
    @Column(name = "StarAvg")
    private double starAvg;
    @Column(name = "FeatureImage")
    private String featureImage;
    @Column(name = "Images")
    private String images;
    @Column(name = "Price")
    private double price;
    @Column(name = "ProductSale")
    private int productSale;
    @Column(name = "Warranty")
    private int warranty;
    @Column(name = "SaleQuantity")
    private int saleQuantity;
    @Column(name = "ProductDescription")
    private String productDescription;
    @Column(name = "SpecificationName")
    private String specificationName;
    @Column(name = "SpecificationValue")
    private String specificationValue;
    @Column(name = "ProductStatus")
    private int productStatus;
    private Set wishlistses = new HashSet(0);
    private Set orderDetailses = new HashSet(0);
    private Set productCommentses = new HashSet(0);

    public Products() {
    }

    public Products(int productId, Brands brands, Categories categories, String productName, String productCode, double starAvg, String featureImage, double price, int productSale, int warranty, int saleQuantity, String productDescription, String specificationName, String specificationValue, int productStatus) {
        this.productId = productId;
        this.brands = brands;
        this.categories = categories;
        this.productName = productName;
        this.productCode = productCode;
        this.starAvg = starAvg;
        this.featureImage = featureImage;
        this.price = price;
        this.productSale = productSale;
        this.warranty = warranty;
        this.saleQuantity = saleQuantity;
        this.productDescription = productDescription;
        this.specificationName = specificationName;
        this.specificationValue = specificationValue;
        this.productStatus = productStatus;
    }

    public Products(int productId, Brands brands, Categories categories, String productName, String productCode, double starAvg, String featureImage, String images, double price, int productSale, int warranty, int saleQuantity, String productDescription, String specificationName, String specificationValue, int productStatus, Set wishlistses, Set orderDetailses, Set productCommentses) {
        this.productId = productId;
        this.brands = brands;
        this.categories = categories;
        this.productName = productName;
        this.productCode = productCode;
        this.starAvg = starAvg;
        this.featureImage = featureImage;
        this.images = images;
        this.price = price;
        this.productSale = productSale;
        this.warranty = warranty;
        this.saleQuantity = saleQuantity;
        this.productDescription = productDescription;
        this.specificationName = specificationName;
        this.specificationValue = specificationValue;
        this.productStatus = productStatus;
        this.wishlistses = wishlistses;
        this.orderDetailses = orderDetailses;
        this.productCommentses = productCommentses;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Brands getBrands() {
        return this.brands;
    }

    public void setBrands(Brands brands) {
        this.brands = brands;
    }

    public Categories getCategories() {
        return this.categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public double getStarAvg() {
        return this.starAvg;
    }

    public void setStarAvg(double starAvg) {
        this.starAvg = starAvg;
    }

    public String getFeatureImage() {
        return this.featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public String getImages() {
        return this.images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductSale() {
        return this.productSale;
    }

    public void setProductSale(int productSale) {
        this.productSale = productSale;
    }

    public int getWarranty() {
        return this.warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public int getSaleQuantity() {
        return this.saleQuantity;
    }

    public void setSaleQuantity(int saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    public String getProductDescription() {
        return this.productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getSpecificationName() {
        return this.specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    public String getSpecificationValue() {
        return this.specificationValue;
    }

    public void setSpecificationValue(String specificationValue) {
        this.specificationValue = specificationValue;
    }

    public int getProductStatus() {
        return this.productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public Set getWishlistses() {
        return this.wishlistses;
    }

    public void setWishlistses(Set wishlistses) {
        this.wishlistses = wishlistses;
    }

    public Set getOrderDetailses() {
        return this.orderDetailses;
    }

    public void setOrderDetailses(Set orderDetailses) {
        this.orderDetailses = orderDetailses;
    }

    public Set getProductCommentses() {
        return this.productCommentses;
    }

    public void setProductCommentses(Set productCommentses) {
        this.productCommentses = productCommentses;
    }

}