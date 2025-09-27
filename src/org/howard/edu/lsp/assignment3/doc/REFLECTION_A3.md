What is different about the design?
Assignment 2 used a single monolithic class (ETLPipeline) that contained file reading, data parsing, transformation logic, file writing, and orchestration - within one 200+ line class with multiple private helper methods.
Assignment 3 decomposes this into five classes:
    Product: Data container for product information
    CSVReader: File input operations and parsing
    CSVWriter: File output operations and formatting
    ProductTransformer: Business logic for all transformations
    ETLPipeline: Orchestration and coordination only
The monolithic approach mixed data handling, business logic, and I/O operations, while the object-oriented approach separates these concerns into distinct, focused classes.


How is Assignment 3 more object-oriented?
Assignment 3's code demonstrates core OO principles:
Separation of Concerns: Each class has a single, responsibility instead of one class doing everything.
Data Abstraction: The Product class encapsulates product data with controlled access through getters/setters, instead of passing around raw string arrays.
Dependency Injection: The ETLPipeline creates and uses instances of other classes, demonstrating object composition and collaboration.
Method Cohesion: Methods within each class are highly related to that class's purpose, rather than mixing unrelated operations.


Which OO ideas did you use?
Objects/Classes: data, transformation, I/O, orchestration.
Encapsulation:
Product class encapsulates data with private fields and public accessors
ProductTransformer encapsulates transformation logic with private helper methods
Each class hides implementation details while exposing clean public interfaces

Object Composition: ETLPipeline contains instances of CSVReader, CSVWriter, and ProductTransformer, demonstrating "has-a" relationships.
Method Overloading: Static factory method Product.fromCsvRow() provides alternative construction.
Inheritance and polymorphism were not used, as the current requirements didn't need them, but the design could easily accommodate them (e.g., different transformer types implementing a common interface).


How did you test to confirm Assignment 3 works the same as Assignment 2?
I ran the same three test cases:
Normal input with 6 sample products
Empty input file (header only)
Missing input file

Output Verification: Compared terminal output messages, which showed identical text for each case.
File Content Comparison: Verified that transformed_products.csv contained identical data with the same transformations applied in the correct order.
Error Handling Verification: Confirmed that missing file and empty file cases produced the same error messages and exit behavior.
Transformation Logic Verification: Manually confirmed that the business rules (10% discount, uppercase names, Premium Electronics recategorization, price ranges) produced identical results for each test.
The testing demonstrated that the object-oriented refactoring maintained complete functional equivalence while improving code organization and maintainability.