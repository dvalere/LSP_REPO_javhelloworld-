Assignment 1: CSV ETL Pipeline

1) I used Claude to generate the code in ETLPipeline.java
2) The prompts I gave were;
   1.) Look at this assignment and prepare to help me complete it. I also attached a screenshot of the structure and names of my project folders so you know what names to use.
   2.) Yes, make the ETL_pipeline.java code. I have the file made and I'm ready to paste whatever code you give me.

3) Claude's responses: 1.) 
                        Based on your project structure screenshot, I can help you implement this      CSV processing program.
                        Let me break down what needs to be done:
                        Key Requirements:

                        Extract: Read data/products.csv with columns: ProductID, Name, Price, Category
                        Transform:

                        Apply 10% discount to Electronics category
                        Convert names to UPPERCASE
                        Recategorize to "Premium Electronics" if post-discount price > $500 AND original category was Electronics
                        Add PriceRange based on final price


                        Load: Write to data/transformed_products.csv

                        Important Details:

                        Transform order matters: uppercase → discount → recategorize → price range
                        Round prices to 2 decimals (half up)
                        Handle missing files and empty files gracefully
                        Use relative paths from project root

                        I notice you have the correct package structure: org.howard.edu.lsp.assignment2
                        Would you like me to create the complete ETLPipeline.java implementation for you? I can also help with:

                        The main processing logic
                        Error handling for missing/empty files
                        Proper file I/O with relative paths
                        The transformation logic in the correct order
                        Sample test data if needed

                        What would be most helpful for you right now?

                    2.) It gave me the code for ETLPipeline.java

4) After I got the code from Claude, I put it into my codebase. Then I looked through the code to make sure I understood it, then and made the products.csv file. I edited the csv file and tested all three cases given in the assignment instructions.
After this, I edited the comments and object/variable names throughout the code so they could automatically make sense to me. 

How to run:
To run this, all you have to do is download it onto your machine, enter the project root folder through the terminal, and run: java -cp src org.howard.edu.lsp.assignment2.ETLPipeline
If you want to change it up and try different cases, you can edit the products.csv file

Assumptions:
- Input CSV file is properly formatted with comma-separated values
- Category names are case-insensitive for comparison (i.e., "Electronics" = "electronics")
- Input file has exactly 4 columns: ProductID, Name, Price, Category
- The program runs from the project root directory containing both src/ and data/ folders