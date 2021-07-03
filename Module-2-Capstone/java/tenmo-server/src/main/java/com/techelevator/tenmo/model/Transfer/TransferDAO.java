package com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDAO {

    public Transfer sendTransfer(Transfer transfer);
    public List<Transfer> findAll();
    public Long getTransferId();

}
