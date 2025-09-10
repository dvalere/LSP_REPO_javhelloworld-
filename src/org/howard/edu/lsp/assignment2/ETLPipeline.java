package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//

public class ETLPipeline {
    private static final String INPUT_FILE = "data/products.csv";
    private static final String OUTPUT_FILE = "data/transformed_products.csv";
    private static final String HEADER = "ProductID,Name,Price,Category,PriceRange";
    
    public static void main(String[] args) {
        ETLPipeline pipeline = new ETLPipeline();
        pipeline.runETL();
    }
    

    public void runETL() {
        System.out.println("Starting ETL Pipeline...");
        
        try {
            // Extract
            List<String[]> data = extract();
            
            // Transform
            List<String[]> transformedData = transform(data);
            
            // Load
            load(transformedData);
            
            // Print summary
            printSummary(data.size() - 1, transformedData.size() - 1); // -1 to exclude header
            
        } catch (Exception e) {
            System.err.println("ETL Pipeline failed: " + e.getMessage());
            System.exit(1);
        }
    }
    

    public List<String[]> extract() throws IOException {
        List<String[]> data = new ArrayList<>();
        
        // Check if file exists
        if (!Files.exists(Paths.get(INPUT_FILE))) {
            throw new IOException("Input file not found: " + INPUT_FILE + 
                ". Please ensure the file exists in the data/ directory.");
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split by comma and trim whitespace
                String[] fields = line.split(",");
                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].trim();
                }
                data.add(fields);
            }
        }
        
        System.out.println("Extracted " + (data.size() > 0 ? data.size() - 1 : 0) + " data rows from " + INPUT_FILE);
        return data;
    }
    

    public List<String[]> transform(List<String[]> data) {
        List<String[]> transformedData = new ArrayList<>();
        
        if (data.isEmpty()) {
            return transformedData;
        }
        
        // Add header row to output
        transformedData.add(new String[]{"ProductID", "Name", "Price", "Category", "PriceRange"});
        
        // Skip header row in input (index 0), process data rows
        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);
            
            // Validate row has correct number of columns
            if (row.length != 4) {
                System.out.println("Skipping invalid row " + i + ": expected 4 columns, got " + row.length);
                continue;
            }
            
            try {
                String[] transformedRow = transformRow(row);
                transformedData.add(transformedRow);
            } catch (Exception e) {
                System.out.println("Skipping row " + i + " due to error: " + e.getMessage());
            }
        }
        
        System.out.println("Transformed " + (transformedData.size() - 1) + " rows");
        return transformedData;
    }
    

    private String[] transformRow(String[] row) {
        String productId = row[0];
        String name = row[1];
        String priceStr = row[2];
        String category = row[3];
        
        // Parse price
        double price = Double.parseDouble(priceStr);
        String originalCategory = category;
        
        // Convert name to UPPERCASE
        name = name.toUpperCase();
        
        // Apply 10% discount if Electronics category
        if ("Electronics".equalsIgnoreCase(originalCategory)) {
            price = price * 0.9; // Apply 10% discount
        }
        
        // Round price to 2 decimal places
        price = roundToTwoDecimals(price);
        
        // Recategorize to Premium Electronics if needed
        if (price > 500.00 && "Electronics".equalsIgnoreCase(originalCategory)) {
            category = "Premium Electronics";
        }
        
        // Step 4: Determine price range based on final price
        String priceRange = determinePriceRange(price);
        
        // Format price to 2 decimal places
        String formattedPrice = String.format("%.2f", price);
        
        return new String[]{productId, name, formattedPrice, category, priceRange};
    }
    

    private double roundToTwoDecimals(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    

    private String determinePriceRange(double price) {
        if (price >= 0.00 && price <= 10.00) {
            return "Low";
        } else if (price >= 10.01 && price <= 100.00) {
            return "Medium";
        } else if (price >= 100.01 && price <= 500.00) {
            return "High";
        } else { // 500.01 and above
            return "Premium";
        }
    }
    

    public void load(List<String[]> transformedData) throws IOException {
        // Create data directory if it doesn't exist
        Files.createDirectories(Paths.get("data"));
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_FILE))) {
            for (String[] row : transformedData) {
                writer.println(String.join(",", row));
            }
        }
        
        System.out.println("Data written to " + OUTPUT_FILE);
    }
    

    private void printSummary(int rowsRead, int rowsTransformed) {
        System.out.println("\nETL Pipeline Summary");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + (rowsRead - rowsTransformed));
        System.out.println("Output written to: " + OUTPUT_FILE);
        System.out.println("ETL Pipeline completed successfully");
    }
}