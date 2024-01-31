package com.example.mortgagecalculator.service;

import org.springframework.stereotype.Service; 
import com.example.mortgagecalculator.model.Prospect;

import java.util.ArrayList;
import java.util.List;

@Service
public class MortgageCalculatorService {

    public List<String> calculateMonthlyPayments(List<Prospect> prospects) {
        List<String> results = new ArrayList<>();

        for (int i = 0; i < prospects.size(); i++) {
            Prospect prospect = prospects.get(i);
            double monthlyPayment = calculateMonthlyPayment(
                    prospect.getLoanAmount(), prospect.getYearlyInterestRate(), prospect.getLoanTermInYears());
        
                    String result = String.format("Prospect %d: <a href='/mortgage/prospect/%d'>%s</a> wants to borrow %.2f € for a period of %d years and pay %.2f € each month",
                    prospect.getId(), prospect.getId(), prospect.getCustomerName(), prospect.getLoanAmount(), prospect.getLoanTermInYears(), monthlyPayment);

            results.add(result);
        }

        return results;
    }

    public double calculateMonthlyPayment(double loanAmount, double yearlyInterestRate, int loanTermInYears) {
        if (yearlyInterestRate == 0) {
            // If yearly interest rate is 0, the monthly interest rate is also 0
            return loanAmount / (loanTermInYears * 12);
        }

        double monthlyInterestRate = yearlyInterestRate / 100 / 12;
        int numberOfPayments = loanTermInYears * 12;
    
        // Calculate (1 + b)^p
        double powFactor = 1;
        for (int i = 0; i < numberOfPayments; i++) {
            powFactor *= (1 + monthlyInterestRate);
        }
    
        // Calculate E = U[b(1 + b)^p] / [(1 + b)^p - 1]
        double monthlyPayment = loanAmount * (monthlyInterestRate * powFactor) / (powFactor - 1);

        
        return monthlyPayment;
    }
}
