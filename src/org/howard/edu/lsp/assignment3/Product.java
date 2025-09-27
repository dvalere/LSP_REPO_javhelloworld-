package org.howard.edu.lsp.assignment3;

/**
 * Represents a product with its core attributes.
 * Encapsulates product data and provides methods for accessing and modifying product properties.
 * 
 * @author Darius Valere
 * @version 1.0
 */
public class Product {
    private String productId;
    private String name;
    private double price;
    private String category;
    private String originalCategory;
    private String priceRange;
    
    /**
     * Constructs a Product with the specified attributes.
     * 
     * @param productId the unique identifier for the product
     * @param name the product name
     * @param price the product price
     * @param category the product category
     */
    public Product(String productId, String name, double price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.originalCategory = category; // Store original category for transformation rules
        this.priceRange = "";
    }
    
    /**
     * Gets the product ID.
     * 
     * @return the product ID
     */
    public String getProductId() {
        return productId;
    }
    
    /**
     * Gets the product name.
     * 
     * @return the product name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the product name.
     * 
     * @param name the new product name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the product price.
     * 
     * @return the product price
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Sets the product price.
     * 
     * @param price the new product price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Gets the product category.
     * 
     * @return the product category
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * Sets the product category.
     * 
     * @param category the new product category
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * Gets the original product category before any transformations.
     * 
     * @return the original product category
     */
    public String getOriginalCategory() {
        return originalCategory;
    }
    
    /**
     * Gets the price range classification.
     * 
     * @return the price range classification
     */
    public String getPriceRange() {
        return priceRange;
    }
    
    /**
     * Sets the price range classification.
     * 
     * @param priceRange the price range classification
     */
    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }
    
    /**
     * Converts the product to a CSV row format.
     * 
     * @return a string array representing the product in CSV format
     */
    public String[] toCsvRow() {
        return new String[] {
            productId,
            name,
            String.format("%.2f", price),
            category,
            priceRange
        };
    }
    
    /**
     * Creates a Product from a CSV row.
     * 
     * @param csvRow the CSV row data
     * @return a new Product instance
     * @throws IllegalArgumentException if the CSV row is invalid
     */
    public static Product fromCsvRow(String[] csvRow) {
        if (csvRow.length != 4) {
            throw new IllegalArgumentException("CSV row must have exactly 4 columns");
        }
        
        try {
            String productId = csvRow[0].trim();
            String name = csvRow[1].trim();
            double price = Double.parseDouble(csvRow[2].trim());
            String category = csvRow[3].trim();
            
            return new Product(productId, name, price, category);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price format in CSV row", e);
        }
    }
    
    @Override
    public String toString() {
        return String.format("Product{id='%s', name='%s', price=%.2f, category='%s', priceRange='%s'}", 
                           productId, name, price, category, priceRange);
    }
}