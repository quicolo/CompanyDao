package es.tuespiral.u6.p1.conexionoracle.negocio;

public class Product {
    private Long productId;
    private String productName;
    private String description;
    private Double standardCost;
    private Double listPrice;
    private Long categoryId;

    public Product(Long productId, String productName, String description, Double standardCost, Double listPrice, Long categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.standardCost = standardCost;
        this.listPrice = listPrice;
        this.categoryId = categoryId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getStandardCost() {
        return standardCost;
    }

    public void setStandardCost(Double standardCost) {
        this.standardCost = standardCost;
    }

    public Double getListPrice() {
        return listPrice;
    }

    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", description=" + description + ", standardCost=" + standardCost + ", listPrice=" + listPrice + ", categoryId=" + categoryId + '}';
    }
    
    
}
