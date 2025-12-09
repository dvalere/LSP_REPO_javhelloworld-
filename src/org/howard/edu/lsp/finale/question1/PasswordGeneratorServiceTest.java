package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test suite for PasswordGeneratorService.
 */
public class PasswordGeneratorServiceTest {
    
    private PasswordGeneratorService service;
    
    /**
     * Sets up the test fixture before each test method.
     */
    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
    }
    
    /**
     * Verifies that getInstance() returns a non-null service instance.
     */
    @Test
    public void checkInstanceNotNull() {
        assertNotNull(service, "Service instance should not be null");
    }
    
    /**
     * Verifies that getInstance() always returns the same instance (singleton behavior).
     */
    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        assertSame(service, second, "getInstance() should return the exact same instance");
    }
    
    /**
     * Verifies that generatePassword throws IllegalStateException when no algorithm is set.
     */
    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();
        s.setAlgorithm(null);
        assertThrows(IllegalStateException.class, () -> {
            s.generatePassword(10);
        }, "Should throw IllegalStateException when no algorithm is set");
    }
    
    /**
     * Verifies that the basic algorithm generates passwords of correct length with digits only.
     */
    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);
        
        assertEquals(10, p.length(), "Password should have length 10");
        assertTrue(p.matches("[0-9]+"), "Password should contain only digits");
    }
    
    /**
     * Verifies that the enhanced algorithm generates passwords of correct length with valid characters.
     */
    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);
        
        assertEquals(12, p.length(), "Password should have length 12");
        assertTrue(p.matches("[A-Za-z0-9]+"), "Password should contain only letters and digits");
    }
    
    /**
     * Verifies that the letters algorithm generates passwords with letters only.
     */
    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);
        
        assertEquals(8, p.length(), "Password should have length 8");
        assertTrue(p.matches("[A-Za-z]+"), "Password should contain only letters");
    }
    
    /**
     * Verifies that switching algorithms changes the password generation behavior appropriately.
     */
    @Test
    public void switchingAlgorithmsChangesBehavior() {
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);
        
        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);
        
        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);
        
        assertTrue(p1.matches("[0-9]+"), "Basic algorithm should generate digits only");
        assertTrue(p2.matches("[A-Za-z]+"), "Letters algorithm should generate letters only");
        assertTrue(p3.matches("[A-Za-z0-9]+"), "Enhanced algorithm should generate letters and digits");
    }
}