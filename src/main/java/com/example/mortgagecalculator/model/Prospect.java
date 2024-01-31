package com.example.mortgagecalculator.model;

import javax.persistence.*;

@Entity
@Table(name = "prospects")
public class Prospect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private double loanAmount;
    private double yearlyInterestRate;
    private int loanTermInYears;

  
    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getYearlyInterestRate() {
        return yearlyInterestRate;
    }

    public void setYearlyInterestRate(double yearlyInterestRate) {
        this.yearlyInterestRate = yearlyInterestRate;
    }

    public int getLoanTermInYears() {
        return loanTermInYears;
    }

    public void setLoanTermInYears(int loanTermInYears) {
        this.loanTermInYears = loanTermInYears;
    }
}
