package com.techelevator.tenmo.model.Transfer;

public interface TransferDAO {

    public Transfer sendTransfer(Long senderId, Long receiverId, Double amount);

}
