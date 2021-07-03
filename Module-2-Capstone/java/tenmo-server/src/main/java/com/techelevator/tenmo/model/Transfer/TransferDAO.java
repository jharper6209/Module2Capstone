package com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDAO {

    public TransferDTO sendTransfer(TransferDTO transfer);
    public List<Transfer> findAll();

}
