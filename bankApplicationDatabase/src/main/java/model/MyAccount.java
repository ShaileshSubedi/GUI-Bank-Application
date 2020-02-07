/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Shailesh
 */
public class MyAccount {
    private String accountName;
    private int accountNumber;
    private int Balance;

    public MyAccount(String accountName, int accountNumber, int Balance) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.Balance = Balance;
    }
    
    public String showAll(){
        return " Account number "+accountNumber+"   Account Name "+accountName+"   Balance  "+Balance+"";
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(int Balance) {
        this.Balance = Balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return Balance;
    }
    
    
    
    
}
