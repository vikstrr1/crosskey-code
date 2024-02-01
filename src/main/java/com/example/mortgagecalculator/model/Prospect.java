package com.example.mortgagecalculator.model;

public class Prospect {
    private String customerName;
    private double loanAmount;
    private double yearlyInterestRate;
    private int loanTermInYears;

    public Prospect(String customerName, double loanAmount, double yearlyInterestRate, int loanTermInYears) {
        this.customerName = customerName;
        this.loanAmount = loanAmount;
        this.yearlyInterestRate = yearlyInterestRate;
        this.loanTermInYears = loanTermInYears;
    }

    // getters and setters
    public void setCustomerName(String name){
         this.customerName = name;
    }

    public void setLoanAmount(double loanAmount){
        this.loanAmount = loanAmount;
    }

    public void setYearlyInterestRate(double yearlyInterestRate){
        this.yearlyInterestRate = yearlyInterestRate;
    }

    public void setLoanTermInYears(Integer loanTermInYears){
        this.loanTermInYears = loanTermInYears;
    }

    public double getLoanAmount() {
        // implement the logic to return the loan amount
        return loanAmount;
    }

    public double getYearlyInterestRate() {
        // implement the logic to return the yearly interest rate
        return yearlyInterestRate;
    }

    public int getLoanTermInYears() {
        // implement the logic to return the loan term in years
        return loanTermInYears;
    }

    public String getCustomerName(){

       return customerName; 
    }

}
