package org.howard.edu.lsp.finale.question1;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton service for generating passwords using configurable algorithms.
 * 
 * DESIGN PATTERN DOCUMENTATION:
 * 
 * 1. Design patterns used:
 *    Singleton Pattern
 *    Strategy Pattern
 * 
 * 2. Why these patterns were appropriate:
 *    
 *    Singleton Pattern:
 *    - The requirement states "Only one instance of the service may exist"
 *    - Provides a single shared access point (getInstance()), as required
 *    - Ensures consistent state management across the application
 *    
 *    Strategy Pattern:
 *    - Supports multiple approaches to password generation (basic, enhanced, letters)
 *    - setAlgorithm() allows runtime selection of algorithm
 *    - Makes password generation behavior swappable without editing client code
 *    - Allows future expansion by adding new PasswordAlgorithm implementations
 *    - Encapsulates the varying part (algorithm) from the stable part (service interface)
 *    
 *    Together, these patterns satisfy all 5 requirements stated in part A:
 *    1. Support multiple approaches (Strategy)
 *    2. Runtime algorithm selection (Strategy)
 *    3. Future expansion (Strategy - new algorithms just implement interface)
 *    4. Swappable behavior (Strategy)
 *    5. Single shared access point (Singleton)
 */
public class PasswordGeneratorService {
    
    private static PasswordGeneratorService instance;
    private PasswordAlgorithm currentAlgorithm;
    private Map<String, PasswordAlgorithm> algorithms;
    
    /**
     * Private constructor to prevent direct instantiation (Singleton pattern)
     */
    private PasswordGeneratorService() {
        algorithms = new HashMap<>();
        algorithms.put("basic", length -> {
            StringBuilder sb = new StringBuilder();
            java.util.Random r = new java.util.Random();
            for (int i = 0; i < length; i++) sb.append("0123456789".charAt(r.nextInt(10)));
            return sb.toString();
        });
        algorithms.put("enhanced", length -> {
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder sb = new StringBuilder();
            java.security.SecureRandom r = new java.security.SecureRandom();
            for (int i = 0; i < length; i++) sb.append(chars.charAt(r.nextInt(chars.length())));
            return sb.toString();
        });
        algorithms.put("letters", length -> {
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            StringBuilder sb = new StringBuilder();
            java.util.Random r = new java.util.Random();
            for (int i = 0; i < length; i++) sb.append(chars.charAt(r.nextInt(chars.length())));
            return sb.toString();
        });
    }
    
    /**
     * Returns the singleton instance of PasswordGeneratorService
     * 
     * @return the single instance of this service
     */
    public static PasswordGeneratorService getInstance() {
        if (instance == null) {
            instance = new PasswordGeneratorService();
        }
        return instance;
    }
    
    /**
     * Sets the password generation algorithm to use
     * 
     * @param name the name of the algorithm ("basic", "enhanced", or "letters")
     */
    public void setAlgorithm(String name) {
        currentAlgorithm = algorithms.get(name);
    }
    
    /**
     * Generates a password of the specified length using the currently selected algorithm
     * 
     * @param length the desired length of the password
     * @return the generated password
     * @throws IllegalStateException if no algorithm has been set
     */
    public String generatePassword(int length) {
        if (currentAlgorithm == null) {
            throw new IllegalStateException("No algorithm selected");
        }
        return currentAlgorithm.generate(length);
    }
}