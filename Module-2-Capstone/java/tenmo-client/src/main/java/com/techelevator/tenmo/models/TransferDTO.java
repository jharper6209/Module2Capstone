package com.techelevator.tenmo.models;

public class TransferDTO {
    private  long accountFrom;
    private long accountTo;
    private double amount;
    private Long id;
    private String userName;


    public long getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(long accountFrom) {
        this.accountFrom = accountFrom;
    }

    public long getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(long accountTo) {
        this.accountTo = accountTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "TransferDTO{" +
                "accountFrom=" + accountFrom +
                ", accountTo=" + accountTo +
                ", amount=" + amount +
                ", id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
