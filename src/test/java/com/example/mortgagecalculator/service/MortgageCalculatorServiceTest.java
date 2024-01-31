package com.example.mortgagecalculator.service;

import org.junit.jupiter.api.Test;

import com.example.mortgagecalculator.model.Prospect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        Prospect prospect = new Prospect();
        assertNotNull(prospect);
    }

    @Test
    void setAndGetCustomerName() {
        Prospect prospect = new Prospect();
        prospect.setCustomerName("John Doe");
        assertEquals("John Doe", prospect.getCustomerName());
    }

    @Test
    void setAndGetLoanAmount() {
        Prospect prospect = new Prospect();
        prospect.setLoanAmount(120000);
        assertEquals(120000, prospect.getLoanAmount());
    }

    @Test
    void setAndGetYearlyInterestRate() {
        Prospect prospect = new Prospect();
        prospect.setYearlyInterestRate(4.8);
        assertEquals(4.8, prospect.getYearlyInterestRate(), 0.01);
    }

    @Test
    void setAndGetLoanTermInYears() {
        Prospect prospect = new Prospect();
        prospect.setLoanTermInYears(15);
        assertEquals(15, prospect.getLoanTermInYears());
    }

    
}


