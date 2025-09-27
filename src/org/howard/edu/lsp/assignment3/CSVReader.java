package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading CSV files and parsing them into Product objects.
 * Provides error handling for missing files and malformed data.
 * 
 * @author Darius Valere
 * @version 1.0
 */
public class CSVReader {
    
    /**
     * Reads a CSV file and converts it to a list of Product objects.
     * The first row is treated as a header and is skipped.
     * 
     * @param filePath the path to the CSV file
     * @return a list of Product objects parsed from the CSV
     * @throws IOException if the file cannot be read or does not exist
     */
    public List<Product> readProducts(String filePath) throws IOException {
        List<Product> products = new ArrayList<>();
        
        // Check if file exists
        if (!Files.exists(Paths.get(filePath))) {
            throw new IOException("Input file not found: " + filePath + 
                ". Please ensure the file exists in the data/ directory.");
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                
                // Skip header row
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                
                try {
                    Product product = parseCsvLine(line, lineNumber);
                    if (product != null) {
                        products.add(product);
                    }
                } catch (Exception e) {
                    System.out.println("Skipping line " + lineNumber + " due to error: " + e.getMessage());
                }
            }
        }
        
        return products;
    }
    
    /**
     * Parses a single CSV line into a Product object.
     * 
     * @param line the CSV line to parse
     * @param lineNumber the line number for error reporting
     * @return a Product object, or null if the line should be skipped
     * @throws IllegalArgumentException if the line is malformed
     */
    private Product parseCsvLine(String line, int lineNumber) {
        if (line.trim().isEmpty()) {
            return null; // Skip empty lines
        }
        
        // Split by comma and trim whitespace
        String[] fields = line.split(",");
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
        }
        
        // Validate row has correct number of columns
        if (fields.length != 4) {
            throw new IllegalArgumentException("Expected 4 columns, got " + fields.length);
        }
        
        return Product.fromCsvRow(fields);
    }
    
    /**
     * Reads the raw CSV data including the header row.
     * Used for cases where header information is needed.
     * 
     * @param filePath the path to the CSV file
     * @return a list of string arrays representing each row
     * @throws IOException if the file cannot be read
     */
    public List<String[]> readRawCsv(String filePath) throws IOException {
        List<String[]> rows = new ArrayList<>();
        
        if (!Files.exists(Paths.get(filePath))) {
            throw new IOException("Input file not found: " + filePath + 
                ". Please ensure the file exists in the data/ directory.");
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].trim();
                }
                rows.add(fields);
            }
        }
        
        return rows;
    }
}