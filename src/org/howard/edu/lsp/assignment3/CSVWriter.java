package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Handles writing Product objects to CSV files.
 * Ensures proper formatting and includes header rows.
 * 
 * @author Darius Valere
 * @version 1.0
 */
public class CSVWriter {
    
    private static final String CSV_HEADER = "ProductID,Name,Price,Category,PriceRange";
    
    /**
     * Writes a list of Product objects to a CSV file.
     * Includes a header row and formats each product as a CSV row.
     * 
     * @param products the list of products to write
     * @param filePath the output file path
     * @throws IOException if the file cannot be written
     */
    public void writeProducts(List<Product> products, String filePath) throws IOException {
        // Create directory if it doesn't exist
        createDirectoryIfNotExists(filePath);
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Write header
            writer.println(CSV_HEADER);
            
            // Write product data
            for (Product product : products) {
                String[] csvRow = product.toCsvRow();
                writer.println(String.join(",", csvRow));
            }
        }
    }
    
    /**
     * Writes a header-only CSV file (used for empty input cases).
     * 
     * @param filePath the output file path
     * @throws IOException if the file cannot be written
     */
    public void writeHeaderOnly(String filePath) throws IOException {
        createDirectoryIfNotExists(filePath);
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(CSV_HEADER);
        }
    }
    
    /**
     * Creates the parent directory for the file if it doesn't exist.
     * 
     * @param filePath the file path to create directories for
     * @throws IOException if the directory cannot be created
     */
    private void createDirectoryIfNotExists(String filePath) throws IOException {
        String parentDir = Paths.get(filePath).getParent().toString();
        Files.createDirectories(Paths.get(parentDir));
    }
}