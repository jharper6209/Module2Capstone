package com.techelevator.tenmo.model.Transfer;

public interface TransferDAO {

    public Transfer sendTransfer(long senderId, long receiverId, Double amount);

}
