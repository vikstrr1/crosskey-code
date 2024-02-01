package com.example.mortgagecalculator.service;

import org.junit.jupiter.api.Test;

import com.example.mortgagecalculator.model.Prospect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

class MortgageCalculatorServiceTest {

    @Test
    void calculateMonthlyPayment() {
        MortgageCalculatorService service = new MortgageCalculatorService();

        // Test with different loan amounts, interest rates, and terms
        double result1 = service.calculateMonthlyPayment(100000, 5, 10);
        assertEquals(1060.65, result1, 0.01);

        double result2 = service.calculateMonthlyPayment(150000, 6, 15);
        assertEquals(1265.79, result2, 0.01);

        double result3 = service.calculateMonthlyPayment(200000, 4.5, 20);
        assertEquals(1265.30, result3, 0.01);

        // Test with zero interest rate
        double result4 = service.calculateMonthlyPayment(80000, 0, 8);
        assertEquals(833.33, result4, 0.01);

        // Test with zero loan amount
        double result5 = service.calculateMonthlyPayment(0, 7, 12);
        assertEquals(0, result5, 0.01);

        // Test with negative loan amount (expecting IllegalArgumentException)
        try {
            service.calculateMonthlyPayment(-50000, 5, 10);
        } catch (IllegalArgumentException e) {
            assertEquals("Loan amount must be a positive value", e.getMessage());
        }

        // Add more test cases as needed
    }



    @Test
    void createProspect() {
        // Use the constructor to create a Prospect instance
        Prospect prospect = new Prospect("John Doe", 100000, 5.0, 10);
        assertNotNull(prospect);
    }

    @Test
    void setAndGetCustomerName() {
        // Use the constructor to create a Prospect instance
        Prospect prospect = new Prospect("John Doe", 100000, 5.0, 10);

        // Existing test code
        prospect.setCustomerName("Jane Smith");
        assertEquals("Jane Smith", prospect.getCustomerName());
    }

    @Test
    void setAndGetLoanAmount() {
        // Use the constructor to create a Prospect instance
        Prospect prospect = new Prospect("John Doe", 100000, 5.0, 10);

        // Existing test code
        prospect.setLoanAmount(120000);
        assertEquals(120000, prospect.getLoanAmount());
    }

    @Test
    void setAndGetYearlyInterestRate() {
        // Use the constructor to create a Prospect instance
        Prospect prospect = new Prospect("John Doe", 100000, 5.0, 10);

        // Existing test code
        prospect.setYearlyInterestRate(4.8);
        assertEquals(4.8, prospect.getYearlyInterestRate(), 0.01);
    }

    @Test
    void setAndGetLoanTermInYears() {
        // Use the constructor to create a Prospect instance
        Prospect prospect = new Prospect("John Doe", 100000, 5.0, 10);

        // Existing test code
        prospect.setLoanTermInYears(15);
        assertEquals(15, prospect.getLoanTermInYears());
    }

    @Test
    void testCalculateMonthlyPayments() {
        MortgageCalculatorService service = new MortgageCalculatorService();

        // Create a list of prospects for testing
        List<Prospect> prospects = new ArrayList<>();

        prospects.add(new Prospect("John Doe", 100000, 5.0, 10));
        prospects.add(new Prospect("Jane Smith", 150000, 6.0, 15));

        // Expected results based on the provided prospects
        List<String> expectedResults = new ArrayList<>();
        expectedResults.add("Prospect 1: John Doe wants to borrow 100000.00 € for a period of 10 years and pay 1060.66 € each month");
        expectedResults.add("Prospect 2: Jane Smith wants to borrow 150000.00 € for a period of 15 years and pay 1265.79 € each month");

        // Call the method to be tested
        List<String> actualResults = service.calculateMonthlyPayments(prospects);

        // Assert that the actual results match the expected results
        assertEquals(expectedResults, actualResults);
    }
}


