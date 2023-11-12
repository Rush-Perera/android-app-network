package lk.rush.network.model;

public class Product {
    /**
     *
     * {
     *     "title": "Iphone 14",
     *     "description": "The iPhone 14: a compact powerhouse, boasting a 6.9-inch Super Retina XDR display and an advanced triple-lens camera system, delivering top-tier performance in a sleek design. With the A16 Bionic chip and extended battery life,",
     *     "brand": "Apple",
     *     "category": "Smart Phones",
     *     "price": 240000,
     *     "active": true
     * }
     *
     */
    private int id;
    private String title;
    private String description;
    private String brand;
    private String category;
    private String price;
    private int active;

    public Product() {
    }

    public Product(int id, String title, String description, String brand, String category, String price, int active) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int isActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
