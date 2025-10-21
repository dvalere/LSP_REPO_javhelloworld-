package org.howard.edu.lsp.midterm.question2;

public class Main {
    public static void main(String[] args) {
        // Demonstrate each overloaded method with the required output
        
        // Circle area
        double circleRadius = 3.0;
        System.out.println("Circle radius " + circleRadius + " → area = " + 
                          AreaCalculator.area(circleRadius));
        
        // Rectangle area
        double rectWidth = 5.0;
        double rectHeight = 2.0;
        System.out.println("Rectangle " + rectWidth + " x " + rectHeight + " → area = " + 
                          AreaCalculator.area(rectWidth, rectHeight));
        
        // Triangle area
        int triangleBase = 10;
        int triangleHeight = 6;
        System.out.println("Triangle base " + triangleBase + ", height " + triangleHeight + 
                          " → area = " + AreaCalculator.area(triangleBase, triangleHeight));
        
        // Square area
        int squareSide = 4;
        System.out.println("Square side " + squareSide + " → area = " + 
                          AreaCalculator.area(squareSide));
        
        // Demonstrate exception handling
        try {
            // This will throw an IllegalArgumentException
            double invalidArea = AreaCalculator.area(-5.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Cannot calculate area with negative dimensions!");
        }
    }
    
    /* 
     * Method overloading is better than using different method names because it provides
     * a cleaner, more intuitive API where the method name (area) clearly indicates the
     * operation regardless of shape. The compiler automatically selects the correct method
     * based on the parameter types, making the code more readable and maintainable.
     */
}