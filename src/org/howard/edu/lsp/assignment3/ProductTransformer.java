package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Handles all product transformation logic according to business rules.
 * Applies discounts, name transformations, category updates, and price range calculations.
 * 
 * @author Darius Valere
 * @version 1.0
 */
public class ProductTransformer {
    
    private static final double ELECTRONICS_DISCOUNT = 0.1; // 10% discount
    private static final double PREMIUM_THRESHOLD = 500.00;
    private static final String ELECTRONICS_CATEGORY = "Electronics";
    private static final String PREMIUM_ELECTRONICS_CATEGORY = "Premium Electronics";
    
    /**
     * Applies all transformation rules to a product in the correct order.
     * Transformation sequence: uppercase name -> discount -> recategorization -> price range
     * 
     * @param product the product to transform
     * @return the transformed product
     */
    public Product transform(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        
        transformName(product);
        
        applyDiscount(product);
        
        updateCategory(product);
        
        calculatePriceRange(product);
        
        return product;
    }
    
    /**
     * Converts the product name to uppercase.
     * 
     * @param product the product to transform
     */
    private void transformName(Product product) {
        product.setName(product.getName().toUpperCase());
    }
    
    /**
     * Applies a 10% discount to products in the Electronics category.
     * Rounds the result to two decimal places using HALF_UP rounding.
     * 
     * @param product the product to apply discount to
     */
    private void applyDiscount(Product product) {
        if (ELECTRONICS_CATEGORY.equalsIgnoreCase(product.getOriginalCategory())) {
            double discountedPrice = product.getPrice() * (1.0 - ELECTRONICS_DISCOUNT);
            double roundedPrice = roundToTwoDecimals(discountedPrice);
            product.setPrice(roundedPrice);
        }
    }
    
    /**
     * Updates category to "Premium Electronics" if the product meets the criteria:
     * - Post-discount price is over $500.00
     * - Original category was "Electronics"
     * 
     * @param product the product to potentially recategorize
     */
    private void updateCategory(Product product) {
        if (product.getPrice() > PREMIUM_THRESHOLD && 
            ELECTRONICS_CATEGORY.equalsIgnoreCase(product.getOriginalCategory())) {
            product.setCategory(PREMIUM_ELECTRONICS_CATEGORY);
        }
    }
    
    /**
     * Calculates and sets the price range based on the final product price.
     * Price ranges:
     * - $0.00–$10.00 → Low
     * - $10.01–$100.00 → Medium  
     * - $100.01–$500.00 → High
     * - $500.01 and above → Premium
     * 
     * @param product the product to calculate price range for
     */
    private void calculatePriceRange(Product product) {
        double price = product.getPrice();
        String priceRange;
        
        if (price >= 0.00 && price <= 10.00) {
            priceRange = "Low";
        } else if (price >= 10.01 && price <= 100.00) {
            priceRange = "Medium";
        } else if (price >= 100.01 && price <= 500.00) {
            priceRange = "High";
        } else { // 500.01 and above
            priceRange = "Premium";
        }
        
        product.setPriceRange(priceRange);
    }
    
    /**
     * Rounds a price value to two decimal places using HALF_UP rounding mode.
     * 
     * @param value the value to round
     * @return the rounded value
     */
    private double roundToTwoDecimals(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}